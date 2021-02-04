package org.orangee.base.single.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.orangee.base.single.entity.SysPermission;
import org.orangee.base.single.entity.SysUser;


import java.util.Set;

/**
 * <p>
 * 用户模块 服务类
 * </p>
 *
 * @author orangee
 * @since 2021-1-31 22:43:11
 */
public interface SysUserService extends IService<SysUser> {
    public Set<SysPermission> list(String username);
}
