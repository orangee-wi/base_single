package org.orangee.base.single.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.orangee.base.single.entity.SysRole;

import java.util.Set;

/**
 * <p>
 * 系统角色表 Mapper 接口
 * </p>
 *
 * @author orangee
 * @since 2021-1-31 22:39:14
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {
    @Select("select * from sys_role where id in " +
            "(select role_id from mid_user_role where user_id = " +
            "(select id from sys_user where username = #{username}))")
    public Set<SysRole> selectRoles(String username);
}
