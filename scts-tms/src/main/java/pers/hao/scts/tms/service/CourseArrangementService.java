package pers.hao.scts.tms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.hao.scts.common.util.PageUtils;
import pers.hao.scts.tms.entity.CourseEntity;
import pers.hao.scts.tms.vo.CourseArrangementVO;
import pers.hao.scts.tms.entity.CourseArrangementEntity;

import java.util.List;
import java.util.Map;

/**
 * 课程安排表
 *
 * @author hujiahao
 * @email wt4437892@163.com
 * @date 2021-03-01 15:50:26
 */
public interface CourseArrangementService extends IService<CourseArrangementEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 添加班级课程
     */
    int saveCourseArrangement(CourseArrangementEntity courseArrangementEntity);

    /**
     * 通过班级编号与周次查询周课程表
     */
    Map<Integer, List<CourseArrangementVO>> queryCourseArrangementByWeek(int classId, Integer weekOrder);

}

