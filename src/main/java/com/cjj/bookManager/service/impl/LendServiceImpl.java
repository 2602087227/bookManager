package com.cjj.bookManager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cjj.bookManager.domain.Book;
import com.cjj.bookManager.domain.Lend;
import com.cjj.bookManager.mapper.BookMapper;
import com.cjj.bookManager.service.BookService;
import com.cjj.bookManager.service.LendService;
import com.cjj.bookManager.mapper.LendMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author admin
* @description 针对表【lend(借阅记录（谁在何时借走了什么书，并且有没有归还，归还时间多少）)】的数据库操作Service实现
* @createDate 2023-03-27 00:43:47
*/
@Service
public class LendServiceImpl extends ServiceImpl<LendMapper, Lend>
    implements LendService{
    @Autowired
    private LendMapper lendMapper;

    @Override
    public PageInfo<Lend> queryLendInfoAll(int page, int pageSize, String bookName, String userName,Long userId) {
        PageHelper.startPage(page,pageSize);
        List<Lend> bookInfoList = lendMapper.queryLendInfoAll(bookName,userName,userId);
        return new PageInfo<>(bookInfoList);
    }
}




