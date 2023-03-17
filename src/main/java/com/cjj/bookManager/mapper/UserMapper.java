package com.cjj.bookManager.mapper;

import com.cjj.bookManager.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author admin
* @description 针对表【user】的数据库操作Mapper
* @createDate 2022-12-27 15:06:27
* @Entity com.cjj.bookManager.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




