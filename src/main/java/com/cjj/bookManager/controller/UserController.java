package com.cjj.bookManager.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cjj.bookManager.common.R;
import com.cjj.bookManager.domain.User;
import com.cjj.bookManager.service.UserService;
import com.cjj.bookManager.utils.RedisUtils;
import com.cjj.bookManager.utils.TokenUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    RedisUtils redisUtils;
    /**
     * 新建用户
     * @param user
     * @return
     */
    @PostMapping("/register")
    public R<String> save(@RequestBody User user){
        user.setStatus(1);
        String password = user.getPassword();
        password= DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        user.setPassword(password);
        boolean save = userService.save(user);
        if (save!=false) {
            return R.success("注册成功");
        }else {
            return R.error("注册失败");
        }
    }


    /**
     * 用户登录
     * @param user
     * @return
     */
    @PostMapping("/login")
    public R<User> login(@RequestBody User user,String checkCode){

            String username = user.getUsername();
            String password = user.getPassword();
            // 对密码进行MD5加密
            password = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getUsername, username);
            User oneUser = userService.getOne(queryWrapper);
            //如果没有查询到数据就返回登录失败的结果
            if (oneUser == null) {
                return R.error("用户名不存在！");
            }

            //如果密码不对就返回登录失败的结果
            if (!oneUser.getPassword().equals(password)) {
                return R.error("密码错误！");
            }
            String token = TokenUtil.sign(user);


            return R.success(oneUser).add("token",token);

    }

    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<PageInfo> page(int page, int pageSize, String name){
        PageHelper.startPage(page,pageSize);
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(name),User::getName,name);
        List<User> list = userService.list(queryWrapper);
        PageInfo<User> userPageInfo = new PageInfo<>(list);
        return R.success(userPageInfo);
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

    @PostMapping("/edit")
    public R<String> edit(@RequestBody User user){
        User user1 = userService.getById(user.getId());
        user1.setStatus(user.getStatus());
        boolean b = userService.updateById(user1);
        if (b) {
            return R.success("修改成功");
        }else{
            return R.error("修改失败");
        }
    }
    @GetMapping("/{id}")
    public R<User> getById(@PathVariable Long id){
        User user= userService.getById(id);
        if (user!=null){
            return R.success(user);
        }
        return R.error("没有查询到该成员");
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
