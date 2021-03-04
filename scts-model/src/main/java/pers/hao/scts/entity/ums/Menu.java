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
@TableName("auth_menu")
@ApiModel(value="Menu对象", description="")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "路径编码")
    private String code;

    @ApiModelProperty(value = "标题")
    private String name;

    @ApiModelProperty(value = "父级节点")
    private Integer parentId;

    @ApiModelProperty(value = "资源路径")
    private String uri;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "菜单类型")
    private String type;

    @ApiModelProperty(value = "排序")
    private Integer orderNum;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "菜单上下级关系")
    private String path;

    @ApiModelProperty(value = "启用禁用")
    private String enabled;


}
