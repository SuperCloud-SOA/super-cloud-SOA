package com.superCloud.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.superCloud.web.mapper.SysPermissionMapper;
import com.superCloud.web.service.ISysPermissionService;
import com.superCloud.web.pojo.SysPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author ys
 * @since 2022-08-10
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements ISysPermissionService {

    @Autowired
    private SysPermissionMapper permissionMapper;

    @Override
    public List<SysPermission> findByUserId(Long userId) {
        if(userId == null) {
            return null;
        }
        List<SysPermission> list = baseMapper.findByUserId(userId);
        //用户无任何权限时，list会有一个 `null` 空的SysPermission 对象，把那个null移除
        list.remove(null);
        return list;
    }

}
