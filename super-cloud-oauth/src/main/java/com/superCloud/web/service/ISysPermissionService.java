package com.superCloud.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.superCloud.web.pojo.SysPermission;

import java.util.List;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author ys
 * @since 2022-08-10
 */
public interface ISysPermissionService extends IService<SysPermission> {

    List<SysPermission> findByUserId(Long userId);

}
