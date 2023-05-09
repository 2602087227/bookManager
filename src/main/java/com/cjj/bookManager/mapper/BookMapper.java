package com.cjj.bookManager.mapper;

import com.cjj.bookManager.domain.Book;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author admin
* @description 针对表【book】的数据库操作Mapper
* @createDate 2023-01-01 22:37:21
* @Entity com.cjj.bookManager.domain.Book
*/
@Mapper
public interface BookMapper extends BaseMapper<Book> {

    List<Book> queryBookInfoAll(@Param("book") Book book, @Param("beginTime") String beginTime, @Param("endTime")String endTime);
}




