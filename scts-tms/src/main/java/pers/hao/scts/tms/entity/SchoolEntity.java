package pers.hao.scts.tms.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 学校表
 * 
 * @author hujiahao
 * @email wt4437892@163.com
 * @date 2021-03-01 11:12:30
 */
@Data
@TableName("tms_school")
public class SchoolEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 学校编号
	 */
	@TableId
	private Integer id;
	/**
	 * 学校名称
	 */
	private String name;
	/**
	 * 学校英文缩写
	 */
	private String abbreviation;
	/**
	 * 学校描述
	 */
	private String description;

}
