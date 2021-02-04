package org.orangee.base.single.rest;


import org.orangee.base.single.entity.SysUser;
import org.orangee.base.single.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户模块 前端控制器
 * </p>
 *
 * @author orangee
 * @since 2021-1-31 22:50:54
 */
//@CrossOrigin
@RestController
@RequestMapping("/api/v1/user")
public class SysUserController {
    @Autowired
    SysUserService userService;

    @GetMapping("/{userId}")
    public SysUser get(@PathVariable String userId){
//        System.out.println(userId);
//        SysUser user = userService.getById(userId);
//        return user;
        return userService.getById(userId);
    }
}
