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
import java.util.Date;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author ys
 * @since 2022-08-10
 */
@Data
@TableName("sys_permission")
@ApiModel(value = "SysPermission对象", description = "权限表")
public class SysPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("权限 ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("父权限 ID (0为顶级菜单)")
    private Long parentId;

    @ApiModelProperty("菜单名称")
    private String title;

    @ApiModelProperty("授权标识符")
    private String code;

    @ApiModelProperty("路径")
    private String index;

    @ApiModelProperty("类型(1菜单，2按钮)")
    private Integer type;

    @ApiModelProperty("图标类型")
    private String icon;

    @ApiModelProperty("备注")
    private String remark;

    private Date createDate;

    private Date updateDate;

    private Integer sort;


}
