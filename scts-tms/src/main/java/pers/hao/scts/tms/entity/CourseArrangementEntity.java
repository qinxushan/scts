package pers.hao.scts.tms.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import lombok.Data;

/**
 * 课程安排表
 * 
 * @author hujiahao
 * @email wt4437892@163.com
 * @date 2021-03-01 15:50:26
 */
@Data
@TableName("tms_course_arrangement")
public class CourseArrangementEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 课程安排编号
	 */
	@TableId
	private Integer id;
	/**
	 * 课程编号
	 */
	private Integer courseId;
	/**
	 * 教师编号(用户编号)
	 */
	private Integer teacherId;
	/**
	 * 班级编号
	 */
	private Integer classId;
	/**
	 * 上课地点
	 */
	private String classRoom;
	/**
	 * 启始周
	 */
	private Integer startWeek;
	/**
	 * 结束周
	 */
	private Integer endWeek;
	/**
	 * 单双周
	 */
	private Integer singleOrDouble;
	/**
	 * 星期
	 */
	private Integer dayOrder;
	/**
	 * 启始小节
	 */
	private Integer beginSection;
	/**
	 * 结束小节
	 */
	private Integer endSection;
	/**
	 * 上课地点经度
	 */
	private BigDecimal longitude;
	/**
	 * 上课地点纬度
	 */
	private BigDecimal latitude;
}
