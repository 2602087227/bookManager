package com.cjj.bookManager.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cjj.bookManager.common.R;
import com.cjj.bookManager.domain.User;
import com.cjj.bookManager.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Retention;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * ClassName: UserController
 * Package: com.cjj.bookManager.controller
 * Description:
 *
 * @Author: 陈
 * @Create: 2022/12/27 - 15:13
 * @Version: v1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    /**
     * 新建用户
     * @param user
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody User user){
        String password = user.getPassword();
        password= DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        user.setPassword(password);
        boolean save = userService.save(user);
        if (save!=false) {
            return R.success("新增成功");
        }else {
            return R.error("新增失败");
        }
    }

    /**
     * 用户登录
     * @param user
     * @return
     */
    @PostMapping("/login")
    public R<User> login(@RequestBody User user){
        String username = user.getUsername();
        String password = user.getPassword();
        // 对密码进行MD5加密
        password = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,username);
        User oneUser = userService.getOne(queryWrapper);
        //如果没有查询到数据就返回登录失败的结果
        if (oneUser == null) {
            return R.error("用户名不存在！");
        }

        //如果密码不对就返回登录失败的结果
        if (!oneUser.getPassword().equals(password)) {
            return R.error("密码错误！");
        }

        return R.success(oneUser);
    }

    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name){
        Page pageInfo = new Page(page, pageSize);
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(name),User::getName,name);
        userService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }


    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @PutMapping
    public R<String> update(@RequestBody User user){
        userService.updateById(user);
        return R.success("修改成功");
    }

    @GetMapping("/{id}")
    public R<User> getById(@PathVariable Long id){
        User user= userService.getById(id);
        if (user!=null){
            return R.success(user);
        }
        return R.error("没有查询到该员工");
    }

    /**
     * 删除
     * @param ids
     * @return
     */
    @DeleteMapping
    public R<String> delete(@RequestParam List<Long> ids){
        userService.removeByIds(ids);
        return R.success("删除成功");
    }
}
