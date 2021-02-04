package org.orangee.base.single.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.orangee.base.single.entity.SysPermission;
import org.orangee.base.single.entity.SysUser;
import org.orangee.base.single.mapper.SysUserMapper;
import org.orangee.base.single.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * <p>
 * 用户模块 服务实现类
 * </p>
 *
 * @author orangee
 * @since 2021-1-31 22:49:30
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Autowired
    SysUserMapper userMapper;

    @Override
    public Set<SysPermission> list(String username) {
        return userMapper.selectPermissions(username);
    }
}
