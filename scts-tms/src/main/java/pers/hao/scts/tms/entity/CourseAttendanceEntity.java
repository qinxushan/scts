package pers.hao.scts.tms.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 考勤表
 * 
 * @author hujiahao
 * @email wt4437892@163.com
 * @date 2021-03-01 11:12:30
 */
@Data
@TableName("tms_course_attendance")
public class CourseAttendanceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 课程考勤编号
	 */
	@TableId
	private Integer id;
	/**
	 * 学生编号
	 */
	private Integer studentId;
	/**
	 * 课程安排编号
	 */
	private Integer courseArrangmentId;
	/**
	 * 打卡状态: 1->签到完成, 2->迟到, 3->病假, 4->事假
	 */
	private Integer state;
	/**
	 * 创建时间
	 */
	private Date gmtCreate;
	/**
	 * 修改时间
	 */
	private Date gmtModified;

}
