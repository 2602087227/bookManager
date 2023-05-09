package com.cjj.bookManager.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cjj.bookManager.common.R;
import com.cjj.bookManager.domain.Admin;
import com.cjj.bookManager.service.AdminService;
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
 * ClassName: AdminController
 * Package: com.cjj.bookManager.controller
 * Description:
 *
 * @Author: 陈
 * @Create: 2023/3/22 - 18:43
 * @Version: v1.0
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;
    @Autowired
    RedisUtils redisUtils;

    /**
     * 新建用户
     * @param admin
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody Admin admin){
        String password = admin.getPassword();
        password= DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        admin.setPassword(password);
        admin.setStatus(1);
        boolean save = adminService.save(admin);
        if (save!=false) {
            return R.success("新增成功");
        }else {
            return R.error("新增失败");
        }
    }

    /**
     * 用户登录
     * @param admin
     * @return
     */
    @PostMapping("/login")
    public R<Admin> login(@RequestBody Admin admin){

            String username = admin.getUsername();
            String password = admin.getPassword();
            // 对密码进行MD5加密
            password = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
            LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Admin::getUsername,username);
            Admin oneAdmin = adminService.getOne(queryWrapper);
            //如果没有查询到数据就返回登录失败的结果
            if (oneAdmin == null) {
                return R.error("用户名不存在！");
            }

            //如果密码不对就返回登录失败的结果
            if (!oneAdmin.getPassword().equals(password)) {
                return R.error("密码错误！");
            }
            if (oneAdmin.getStatus()==0){
                return R.error("账号被禁用");
            }
            String token = TokenUtil.sign(admin);


            return R.success(oneAdmin).add("token",token);

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
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(name),Admin::getName,name);
        queryWrapper.orderByDesc(Admin::getUpdateTime);
        List<Admin> list = adminService.list(queryWrapper);
        PageInfo<Admin> adminPageInfo = new PageInfo<>(list);
        return R.success(adminPageInfo);
    }


    /**
     * 修改用户信息
     * @param admin
     * @return
     */
    @PutMapping
    public R<String> update(@RequestBody Admin admin){

        adminService.updateById(admin);
        return R.success("修改成功");
    }

    @GetMapping("/{id}")
    public R<Admin> getById(@PathVariable Long id){
        Admin admin= adminService.getById(id);
        if (admin!=null){
            return R.success(admin);
        }
        return R.error("没有查询到该成员");
    }


}
