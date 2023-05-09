package com.cjj.bookManager.mapper;

import com.cjj.bookManager.domain.Type;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author admin
* @description 针对表【type(图书类型表)】的数据库操作Mapper
* @createDate 2023-03-27 00:43:43
* @Entity com.cjj.bookManager.domain.Type
*/
@Mapper
public interface TypeMapper extends BaseMapper<Type> {

}




