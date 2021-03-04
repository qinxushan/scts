package pers.hao.scts.tms.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 专业表
 * 
 * @author hujiahao
 * @email wt4437892@163.com
 * @date 2021-03-01 11:12:30
 */
@Data
@TableName("tms_profession")
public class ProfessionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 专业编号
	 */
	@TableId
	private Integer id;
	/**
	 * 学院编号
	 */
	private Integer collegeId;
	/**
	 * 专业名称
	 */
	private String name;
	/**
	 * 专业描述
	 */
	private String description;
	/**
	 * 专业顺序字段
	 */
	private Integer orderId;

}
