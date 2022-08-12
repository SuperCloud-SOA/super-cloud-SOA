package com.superCloud.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.superCloud.web.pojo.SysUser;
import com.superCloud.web.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("customUserDetailsService")
@Slf4j
public class CustomUserDetailsService extends AbstractUserDetailsService {

    @Autowired
    private ISysUserService userService;

    @Override
    SysUser findSysUser(String username) {
        log.info("请求认证的用户名：" + username);
        SysUser sysUser = userService.getOne(new QueryWrapper<SysUser>().lambda().eq(SysUser::getUsername, username));
        return sysUser;
    }
}