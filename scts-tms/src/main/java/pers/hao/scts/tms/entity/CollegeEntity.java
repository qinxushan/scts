package pers.hao.scts.tms.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 学院表
 * 
 * @author hujiahao
 * @email wt4437892@163.com
 * @date 2021-03-01 11:12:31
 */
@Data
@TableName("tms_college")
public class CollegeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 学院编号
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private Integer schoolId;
	/**
	 * 学院名称
	 */
	private String name;
	/**
	 * 学院描述
	 */
	private String description;
	/**
	 * 学院顺序字段
	 */
	private Integer orderId;

}
