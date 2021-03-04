package pers.hao.scts.tms.dao;

import pers.hao.scts.tms.entity.CourseAttendanceEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 考勤表
 * 
 * @author hujiahao
 * @email wt4437892@163.com
 * @date 2021-03-01 11:12:30
 */
@Mapper
public interface CourseAttendanceDao extends BaseMapper<CourseAttendanceEntity> {
	
}
