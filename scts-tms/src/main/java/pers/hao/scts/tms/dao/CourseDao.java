package pers.hao.scts.tms.dao;

import pers.hao.scts.tms.entity.CourseEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 课程表
 * 
 * @author hujiahao
 * @email wt4437892@163.com
 * @date 2021-03-01 11:12:31
 */
@Mapper
public interface CourseDao extends BaseMapper<CourseEntity> {
	
}
