package com.cjj.bookManager.service;

import com.cjj.bookManager.domain.Notice;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
* @author admin
* @description 针对表【notice(公告)】的数据库操作Service
* @createDate 2023-04-08 11:29:36
*/
public interface NoticeService extends IService<Notice> {
    PageInfo<Notice> queryAllByTopic(int page,int pageSize,String topic);
}
