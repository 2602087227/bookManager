package com.cjj.bookManager.service;

import com.cjj.bookManager.domain.Book;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

/**
* @author admin
* @description 针对表【book】的数据库操作Service
* @createDate 2023-01-01 22:37:21
*/
public interface BookService extends IService<Book> {
    PageInfo<Book> queryBookInfoAll(int page, int pageSize, Book book, String beginTime, String endTime);
}
