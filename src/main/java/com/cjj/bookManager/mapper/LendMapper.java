package com.cjj.bookManager.mapper;

import com.cjj.bookManager.domain.Lend;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author admin
* @description 针对表【lend(借阅记录（谁在何时借走了什么书，并且有没有归还，归还时间多少）)】的数据库操作Mapper
* @createDate 2023-03-27 00:43:47
* @Entity com.cjj.bookManager.domain.Lend
*/
@Mapper
public interface LendMapper extends BaseMapper<Lend> {
    List<Lend> queryLendInfoAll(@Param("bookName")String bookName,@Param("userName")String userName,@Param("userId") Long userId);
}




