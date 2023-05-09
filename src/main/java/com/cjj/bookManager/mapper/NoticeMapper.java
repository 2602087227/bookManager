package com.cjj.bookManager.mapper;

import com.cjj.bookManager.domain.Notice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author admin
* @description 针对表【notice(公告)】的数据库操作Mapper
* @createDate 2023-04-08 11:29:36
* @Entity com.cjj.bookManager.domain.Notice
*/
@Mapper
public interface NoticeMapper extends BaseMapper<Notice> {
    List<Notice> queryAllByTopic(@Param("topic") String topic);
}




