package com.superCloud.web.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author ys
 * @since 2022-08-10
 */
@Data
@TableName("sys_user")
@ApiModel(value = "SysUser对象", description = "用户表")
public class SysUser implements UserDetails,Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户 ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码，加密存储, admin/1234")
    private String password;

    /**
     * 帐户是否过期(true(1) 未过期，false(0)已过期)
     * 设置默认值为true，新增用户默认未过期
     *
     * 注意：生成的setter和getter方法没有 `is`
     *        setAccountNonExpired
     *        getAccountNonExpired
     *   所以前端获取时也不要有 `is`
     */
    private boolean isAccountNonExpired = true;
    /**
     * 帐户是否被锁定(true(1) 未过期，false(0)已过期)
     * 设置默认值为true，新增用户默认未过期
     */
    private boolean isAccountNonLocked = true;
    /**
     * 密码是否过期(true(1) 未过期，false(0)已过期)
     * 设置默认值为true，新增用户默认未过期
     */
    private boolean isCredentialsNonExpired = true;
    /**
     * 帐户是否可用(true(1) 可用，false(0)未删除)
     * 设置默认值为true，新增用户默认未过期
     */
    private boolean isEnabled = true;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("注册手机号")
    private String mobile;

    @ApiModelProperty("注册邮箱")
    private String email;

    private Date createDate;

    private Date updateDate;

    /**
     * 拥有权限集合
     */
    @TableField(exist = false) //该属性不是数据库表字段
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }
    // 父接口认证方法 end

    /**
     * 拥有角色集合
     */
    @TableField(exist = false)
    private List<SysRole> roleList = new ArrayList<>();
    /**
     * 获取所有角色id
     */
    @TableField(exist = false)
    private List<Long> roleIds = new ArrayList<>();
    public List<Long> getRoleIds() {
        if(CollectionUtils.isNotEmpty(roleList)) {
            roleIds = new ArrayList<>();
            for(SysRole role : roleList) {
                roleIds.add(role.getId());
            }
        }
        return roleIds;
    }

    @TableField(exist = false)
    private List<SysPermission> permissions = new ArrayList<>();
}
