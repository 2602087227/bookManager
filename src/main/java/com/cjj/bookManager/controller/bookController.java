package com.cjj.bookManager.controller;

import com.cjj.bookManager.common.R;
import com.cjj.bookManager.domain.Book;
import com.cjj.bookManager.service.BookService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


/**
 * ClassName: bookController
 * Package: com.cjj.bookManager.controller
 * Description:
 *
 * @Author: 陈
 * @Create: 2023/1/1 - 22:43
 * @Version: v1.0
 */
@RestController
@RequestMapping("/book")
public class bookController {
    @Autowired
    BookService bookService;

    @PostMapping
    public R<String> save(@RequestBody Book book){
        bookService.save(book);
        return R.success("新增成功");
    }

    @GetMapping("/page")
    public R<PageInfo> page(int page, int pageSize, Book book, String beginTime, String endTime){
        PageInfo<Book> bookPageInfo = bookService.queryBookInfoAll(page, pageSize, book, beginTime, endTime);
        return R.success(bookPageInfo);
    }

    @PutMapping
    public R<String> update(@RequestBody Book book){
        bookService.updateById(book);
        return R.success("修改成功");
    }

    @GetMapping("/{id}")
    public R<Book> getById(@PathVariable Long id){
        Book book = bookService.getById(id);
        return R.success(book);
    }

    @DeleteMapping("/{id}")
    public R<String> delete(@PathVariable Long id){
        boolean b = bookService.removeById(id);
        if (b) {
            return R.success("删除成功");
        }
        return R.error("删除失败");
    }
}
