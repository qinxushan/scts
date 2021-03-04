package pers.hao.scts.tms.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import pers.hao.scts.common.util.PageUtils;
import pers.hao.scts.common.util.Query;
import pers.hao.scts.tms.service.CourseAttendanceService;
import pers.hao.scts.tms.dao.CourseAttendanceDao;
import pers.hao.scts.tms.entity.CourseAttendanceEntity;


@Service("courseAttendanceService")
public class CourseAttendanceServiceImpl extends ServiceImpl<CourseAttendanceDao, CourseAttendanceEntity> implements CourseAttendanceService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CourseAttendanceEntity> page = this.page(
                new Query<CourseAttendanceEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageUtils(page);
    }

}