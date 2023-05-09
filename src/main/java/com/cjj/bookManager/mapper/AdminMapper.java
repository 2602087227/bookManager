package com.cjj.bookManager.mapper;

import com.cjj.bookManager.domain.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author admin
* @description 针对表【admin(员工信息)】的数据库操作Mapper
* @createDate 2023-03-22 18:36:17
* @Entity com.cjj.bookManager.domain.Admin
*/
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

}




