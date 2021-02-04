package org.orangee.base.single.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.orangee.base.single.entity.SysRole;
import org.orangee.base.single.mapper.SysRoleMapper;
import org.orangee.base.single.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Set;

/**
 * <p>
 * 系统角色表 服务实现类
 * </p>
 *
 * @author orangee
 * @since 2021-1-31 22:47:26
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    @Autowired
    SysRoleMapper sysRoleMapper;

    /**
     * 根据用户名获取用户角色列表
     * @param username
     * @return
     */
    @Override
    public Set<SysRole> list(String username) {
        return sysRoleMapper.selectRoles(username);
    }
}
