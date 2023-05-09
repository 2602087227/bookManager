package com.cjj.bookManager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cjj.bookManager.domain.Notice;
import com.cjj.bookManager.service.NoticeService;
import com.cjj.bookManager.mapper.NoticeMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author admin
* @description 针对表【notice(公告)】的数据库操作Service实现
* @createDate 2023-04-08 11:29:36
*/
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice>
    implements NoticeService{
    @Autowired
    private NoticeMapper noticeMapper;
    @Override
    public PageInfo<Notice> queryAllByTopic(int page,int pageSize,String topic) {
        PageHelper.startPage(page,pageSize);
        List<Notice> notices = noticeMapper.queryAllByTopic(topic);
        return new PageInfo<>(notices);
    }
}




