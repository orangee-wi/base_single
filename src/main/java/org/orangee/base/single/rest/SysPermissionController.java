package org.orangee.base.single.rest;


import org.orangee.base.single.core.entity.Result;
import org.orangee.base.single.core.entity.ResultEnum;
import org.orangee.base.single.entity.SysPermission;
import org.orangee.base.single.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 系统权限表 前端控制器
 * </p>
 *
 * @author orangee
 * @since 2021-1-31 22:38:10
 */
@RestController
@RequestMapping("/api/v1/permission")
public class SysPermissionController {
    @Autowired
    SysUserService userService;

    @GetMapping("/{username}")
    public Set<SysPermission> list(@PathVariable String username){
        return userService.list(username);
//                .ok(userService.list(username));
    }
}
