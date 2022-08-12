package com.superCloud.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.superCloud.web.pojo.SysPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author ys
 * @since 2022-08-10
 */
@Mapper
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    /**
     * 根据用户ID获取拥有资源权限
     * @param userId 用户id
     * @return
     */
    List<SysPermission> findByUserId(@Param("userId") Long userId);

}
