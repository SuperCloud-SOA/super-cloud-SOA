package com.superCloud.service;


import com.superCloud.web.mapper.SysUserMapper;
import com.superCloud.web.pojo.SysPermission;
import com.superCloud.web.pojo.SysUser;
import com.superCloud.web.service.ISysPermissionService;
import com.superCloud.web.service.ISysUserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;


public abstract class AbstractUserDetailsService implements UserDetailsService {

    @Autowired
    private ISysUserService userService;
    @Autowired
    private ISysPermissionService permissionService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. 通过请求的用户名去数据库中查询用户信息
        SysUser sysUser = findSysUser(username);
        findSysPermission(sysUser);
        return sysUser;
    }

    /**
     * @param username 用户名
     * @return
     * @throws UsernameNotFoundException
     */
    abstract SysUser findSysUser(String username);

    /**
     * 查询认真信息
     * @param sysUser
     * @throws UsernameNotFoundException
     */
    public void findSysPermission(SysUser sysUser) throws UsernameNotFoundException{
        if(sysUser == null) {
            throw new UsernameNotFoundException("未查询到有效用户信息");
        }

        // 2. 查询该用户有哪一些权限
        List<SysPermission> sysPermissions =
                permissionService.findByUserId(sysUser.getId());

        // 无权限
        if(CollectionUtils.isEmpty(sysPermissions)) {
            return;
        }

        // 存入权限,认证通过后用于渲染左侧菜单
        sysUser.setPermissions(sysPermissions);

        // 3. 封装用户信息和权限信息
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(SysPermission sp: sysPermissions) {
            //权限标识
            authorities.add(new SimpleGrantedAuthority(sp.getCode()));
        }
        sysUser.setAuthorities(authorities);
    }



}
