package pers.hao.scts.tms.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 班级表
 * 
 * @author hujiahao
 * @email wt4437892@163.com
 * @date 2021-03-03 23:05:16
 */
@Data
@TableName("tms_clazz")
public class ClazzEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 班级编号
	 */
	@TableId
	private Integer id;
	/**
	 * 班级次序 e.g.(1班、2班)
	 */
	private Integer classId;
	/**
	 * 专业编号
	 */
	private Integer professionId;
	/**
	 * 班级总人数
	 */
	private Integer studentSize;

}
