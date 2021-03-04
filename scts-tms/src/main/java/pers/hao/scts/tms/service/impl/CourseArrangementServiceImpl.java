package pers.hao.scts.tms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import pers.hao.scts.common.exception.ResultException;
import pers.hao.scts.common.util.PageUtils;
import pers.hao.scts.common.util.Query;
import pers.hao.scts.common.util.ResultStatus;
import pers.hao.scts.tms.feign.UmsService;
import pers.hao.scts.tms.service.CourseArrangementService;
import pers.hao.scts.tms.service.CourseService;
import pers.hao.scts.tms.vo.CourseArrangementVO;
import pers.hao.scts.tms.dao.CourseArrangementDao;
import pers.hao.scts.tms.entity.CourseArrangementEntity;

import javax.annotation.Resource;


@Slf4j
@Service("courseArrangementService")
public class CourseArrangementServiceImpl extends ServiceImpl<CourseArrangementDao, CourseArrangementEntity> implements CourseArrangementService {

    @Resource
    CourseService courseService;

    @Resource
    UmsService umsService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CourseArrangementEntity> page = this.page(
                new Query<CourseArrangementEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageUtils(page);
    }

    @Override
    public int saveCourseArrangement(CourseArrangementEntity courseArrangementEntity) {
        // 获取同一周几内班级课程
        Integer dayOrder = courseArrangementEntity.getDayOrder();
        Integer classId = courseArrangementEntity.getClassId();
        Integer beginSection = courseArrangementEntity.getBeginSection();
        Integer endSection = courseArrangementEntity.getEndSection();
        Integer startWeek = courseArrangementEntity.getStartWeek();
        Integer endWeek = courseArrangementEntity.getEndWeek();
        Integer singleOrDouble = courseArrangementEntity.getSingleOrDouble();
        LambdaQueryWrapper<CourseArrangementEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CourseArrangementEntity::getClassId, classId).eq(CourseArrangementEntity::getDayOrder, dayOrder);
        List<CourseArrangementEntity> courseArrangementEntities = this.baseMapper.selectList(queryWrapper);

        // 过滤出冲突课程
        long count = courseArrangementEntities.stream().filter(courseArrangement -> {
            boolean isConflict = false;
            // 判断节次冲突
            Integer itemBeginSection = courseArrangement.getBeginSection();
            Integer itemEndSection = courseArrangement.getEndSection();
            Integer itemStartWeek = courseArrangement.getStartWeek();
            Integer itemEndWeek = courseArrangement.getEndWeek();
            Integer itemSingleOrDouble = courseArrangement.getSingleOrDouble();
            if (Math.max(itemBeginSection, beginSection) <= Math.min(itemEndSection, endSection)) {
                isConflict = true;
            }
            // 周次不重叠
            if (!(Math.max(itemStartWeek, startWeek) <= Math.min(itemEndWeek, endWeek))) {
                isConflict = false;
            }
            // 单双周
            if (singleOrDouble != 2 && !itemSingleOrDouble.equals(singleOrDouble)) {
                isConflict = false;
            }
            return isConflict;
        }).count();
        if (count > 0) {
            throw new ResultException(ResultStatus.COURSE_CONFLICT);
        }
        return this.baseMapper.insert(courseArrangementEntity);
    }

    @Override
    public Map<Integer, List<CourseArrangementVO>> queryCourseArrangementByWeek(int classId, Integer weekOrder) {
        // 获得课程安排
        LambdaQueryWrapper<CourseArrangementEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CourseArrangementEntity::getClassId, classId);
        List<CourseArrangementEntity> courseArrangementEntities = this.baseMapper.selectList(queryWrapper);
        int oddOrEvenWeek = weekOrder % 2;

        // 当周课程
        List<CourseArrangementEntity> presentWeekCourse = courseArrangementEntities.stream().filter(courseArrangementEntity -> {
                    Integer startWeek = courseArrangementEntity.getStartWeek();
                    Integer endWeek = courseArrangementEntity.getEndWeek();
                    Integer singleOrDouble = courseArrangementEntity.getSingleOrDouble();
                    return startWeek <= weekOrder && endWeek >= weekOrder && (singleOrDouble == oddOrEvenWeek || singleOrDouble == 2);
                }
        ).collect(Collectors.toList());

        // 数据转VO
        return presentWeekCourse
                .stream()
                .map(courseArrangementEntity -> {
                    CourseArrangementVO courseArrangementVO = new CourseArrangementVO();
                    BeanUtils.copyProperties(courseArrangementEntity, courseArrangementVO);
                    // 持续周数
                    Integer startWeek = courseArrangementEntity.getStartWeek();
                    Integer endWeek = courseArrangementEntity.getEndWeek();
                    courseArrangementVO.setWeekDuration(startWeek + "-" + endWeek);
                    // 课程名
                    courseArrangementVO.setCourseName(courseService.queryCourseName(courseArrangementEntity.getCourseId()));
                    // 教师名称
                    String realName = umsService.getUserRealName(courseArrangementEntity.getTeacherId()).getData();
                    courseArrangementVO.setTeacherName(realName);
                    return courseArrangementVO;
                })
                .collect(Collectors.groupingBy(CourseArrangementVO::getDayOrder));
    }
}