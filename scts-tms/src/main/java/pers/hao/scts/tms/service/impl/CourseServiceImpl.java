package pers.hao.scts.tms.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import pers.hao.scts.common.util.PageUtils;
import pers.hao.scts.common.util.Query;
import pers.hao.scts.tms.dao.CourseDao;
import pers.hao.scts.tms.entity.CourseEntity;
import pers.hao.scts.tms.service.CourseService;


@Service("courseService")
public class CourseServiceImpl extends ServiceImpl<CourseDao, CourseEntity> implements CourseService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CourseEntity> page = this.page(
                new Query<CourseEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageUtils(page);
    }

    @Override
    public String queryCourseName(Integer courseId) {
        CourseEntity courseEntity = this.baseMapper.selectById(courseId);
        return courseEntity.getName();
    }
}