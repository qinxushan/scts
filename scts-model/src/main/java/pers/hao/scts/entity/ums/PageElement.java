package pers.hao.scts.entity.ums;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author hujiahao
 * @since 2021-02-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("auth_page_element")
@ApiModel(value="PageElement对象", description="")
public class PageElement implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "资源编码")
    private String code;

    @ApiModelProperty(value = "资源类型")
    private String type;

    @ApiModelProperty(value = "资源名称")
    private String name;

    @ApiModelProperty(value = "资源路径")
    private String uri;

    @ApiModelProperty(value = "资源关联菜单")
    private String menuId;

    @ApiModelProperty(value = "父元素编号")
    private String parentId;

    @ApiModelProperty(value = "资源树状检索路径")
    private String path;

    @ApiModelProperty(value = "资源请求类型")
    private String method;

    @ApiModelProperty(value = "描述")
    private String description;


}
