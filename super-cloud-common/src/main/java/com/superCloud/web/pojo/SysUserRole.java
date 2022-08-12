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
 * 用户角色表
 * </p>
 *
 * @author ys
 * @since 2022-08-10
 */
@Data
@TableName("sys_user_role")
@ApiModel(value = "SysUserRole对象", description = "用户角色表")
public class SysUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键 ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("用户 ID")
    private Long userId;

    @ApiModelProperty("角色 ID")
    private Long roleId;


}
