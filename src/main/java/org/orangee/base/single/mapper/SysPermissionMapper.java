package org.orangee.base.single.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.orangee.base.single.entity.SysPermission;

import java.util.Set;

/**
 * <p>
 * 系统权限表 Mapper 接口
 * </p>
 *
 * @author orangee
 * @since 2021-1-31 22:39:09
 */
public interface SysPermissionMapper extends BaseMapper<SysPermission> {
    @Select("select * from sys_permission where id in " +
            "(select permission_id from mid_role_permissions where role_id = " +
            "(select id from sys_role where role_flag = #{roleFlag}))")
    public Set<SysPermission> selectPermissions(String roleFlag);
}
