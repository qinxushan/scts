package pers.hao.scts.tms.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 课程安排表VO
 *
 * @author hujiahao
 * @email wt4437892@163.com
 * @date 2021-03-01 11:12:30
 */
@Data
public class CourseArrangementVO {

    /**
     * 课程安排编号
     */
    private Integer id;

    /**
     * 星期
     */
    private Integer dayOrder;

    /**
     * 持续周数
     */
    private String weekDuration;

    /**
     * 单双周
     */
    private Integer singleOrDouble;

    /**
     * 课程名
     */
    private String courseName;

    /**
     * 教师名
     */
    private String teacherName;

    /**
     * 教室
     */
    private String classRoom;

    /**
     * 启始小节
     */
    private Integer beginSection;

    /**
     * 持续小节
     */
    private Integer endSection;

}
