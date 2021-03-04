package pers.hao.scts.tms.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 课程表
 * 
 * @author hujiahao
 * @email wt4437892@163.com
 * @date 2021-03-01 11:12:31
 */
@Data
@TableName("tms_course")
public class CourseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 教师编号(用户编号)
	 */
	private Integer teacherId;
	/**
	 * 课程名
	 */
	private String name;
	/**
	 * 课程描述
	 */
	private String description;
	/**
	 * 持续周数
	 */
	private String duration;

}
