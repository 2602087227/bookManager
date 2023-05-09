package com.cjj.bookManager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cjj.bookManager.domain.Admin;
import com.cjj.bookManager.service.AdminService;
import com.cjj.bookManager.mapper.AdminMapper;
import org.springframework.stereotype.Service;

/**
* @author admin
* @description 针对表【admin(员工信息)】的数据库操作Service实现
* @createDate 2023-03-22 18:36:17
*/
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin>
    implements AdminService{

}




