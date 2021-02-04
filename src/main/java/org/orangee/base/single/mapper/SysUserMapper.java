package org.orangee.base.single.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.orangee.base.single.entity.SysPermission;
import org.orangee.base.single.entity.SysUser;


import java.util.Set;

/**
 * <p>
 * 用户模块 Mapper 接口
 * </p>
 *
 * @author orangee
 * @since 2021-1-31 22:39:24
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
    @Select("select * from sys_permission where id in" +
            "( select permission_id from mid_role_permission where role_id in " +
            "( select id from sys_role where role_flag in " +
            "( select role_flag from sys_role where id in " +
            "( select role_id from mid_user_role where user_id = " +
            "( select id from sys_user where username = #{username})))))")
    public Set<SysPermission> selectPermissions(String username);
}
