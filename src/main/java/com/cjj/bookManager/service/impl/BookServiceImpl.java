package com.cjj.bookManager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cjj.bookManager.domain.Book;
import com.cjj.bookManager.service.BookService;
import com.cjj.bookManager.mapper.BookMapper;
import org.springframework.stereotype.Service;

/**
* @author admin
* @description 针对表【book】的数据库操作Service实现
* @createDate 2023-01-01 22:37:21
*/
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book>
    implements BookService{

}




