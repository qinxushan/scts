package pers.hao.scts.tms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.hao.scts.common.util.PageUtils;
import pers.hao.scts.tms.entity.CourseEntity;

import java.util.Map;

/**
 * 课程表
 *
 * @author hujiahao
 * @email wt4437892@163.com
 * @date 2021-03-01 11:12:31
 */
public interface CourseService extends IService<CourseEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 查询课程名
     * @param courseId 编号
     * @return 课程名
     */
    String queryCourseName(Integer courseId);
}

