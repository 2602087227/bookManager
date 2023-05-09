package com.cjj.bookManager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cjj.bookManager.domain.Book;
import com.cjj.bookManager.service.BookService;
import com.cjj.bookManager.mapper.BookMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author admin
* @description 针对表【book】的数据库操作Service实现
* @createDate 2023-01-01 22:37:21
*/
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book>
    implements BookService{

    @Autowired
    private BookMapper bookMapper;

    @Override
    public PageInfo<Book> queryBookInfoAll(int page, int pageSize, Book book, String beginTime, String endTime) {
        PageHelper.startPage(page,pageSize);
        List<Book> bookInfoList = bookMapper.queryBookInfoAll(book,beginTime,endTime);
        return new PageInfo<>(bookInfoList);
    }
}




