package com.superCloud.web.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 角色权限表
 * </p>
 *
 * @author ys
 * @since 2022-08-10
 */
@Data
@TableName("sys_role_permission")
@ApiModel(value = "SysRolePermission对象", description = "角色权限表")
public class SysRolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键 ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("角色 ID")
    private Long roleId;

    @ApiModelProperty("权限 ID")
    private Long permissionId;


}
