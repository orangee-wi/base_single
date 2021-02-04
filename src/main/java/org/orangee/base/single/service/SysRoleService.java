package org.orangee.base.single.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.orangee.base.single.entity.SysRole;


import java.util.Set;

/**
 * <p>
 * 系统角色表 服务类
 * </p>
 *
 * @author orangee
 * @since 2021-1-31 22:43:38
 */
public interface SysRoleService extends IService<SysRole> {
    public Set<SysRole> list(String username);
}
