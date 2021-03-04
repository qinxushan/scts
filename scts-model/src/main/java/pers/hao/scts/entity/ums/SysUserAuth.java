package pers.hao.scts.entity.ums;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author hujiahao
 * @since 2021-02-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("auth_sys_user_auth")
@ApiModel(value="SysUserAuth对象", description="用户表")
public class SysUserAuth implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户序号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "标识 (手机号/邮箱/用户名或第三方应用的唯一标识)")
    private String identifier;

    @ApiModelProperty(value = "站内的保存密码 , 站外的不保存")
    private String credential;

    @ApiModelProperty(value = "登录类型 (手机号/邮箱/用户名) 或第三方应用名称 (微信 , 微博等)")
    private String identityType;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime gmtModified;

    @ApiModelProperty(value = "上次登录时间")
    private LocalDateTime lastLoginTime;

    @ApiModelProperty(value = "账号是否可用。默认为1（可用）")
    private Boolean enabled;

    @ApiModelProperty(value = "是否过期。默认为1（没有过期）")
    private Boolean notExpired;

    @ApiModelProperty(value = "账号是否锁定。默认为1（没有锁定）")
    private Boolean accountNotLocked;

    @ApiModelProperty(value = "证书（密码）是否过期。默认为1（没有过期）")
    private Boolean credentialsNotExpired;


}
