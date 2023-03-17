package com.cjj.bookManager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cjj.bookManager.domain.User;
import com.cjj.bookManager.service.UserService;
import com.cjj.bookManager.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author admin
* @description 针对表【user】的数据库操作Service实现
* @createDate 2022-12-27 15:06:27
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

//    @Override
//    public void deleteByIds(List<Long> ids) {
//
//    }
}




