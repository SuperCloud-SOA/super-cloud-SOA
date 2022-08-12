package com.superCloud.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.superCloud.web.pojo.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author ys
 * @since 2022-08-10
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

}
