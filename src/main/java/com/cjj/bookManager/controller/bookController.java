package com.cjj.bookManager.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cjj.bookManager.common.R;
import com.cjj.bookManager.domain.Book;
import com.cjj.bookManager.service.BookService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @param book
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize, Book book){
        Page pageInfo = new Page(page,pageSize);
        LambdaQueryWrapper<Book> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Strings.isNotEmpty(book.getName()),Book::getName,book.getName());
        queryWrapper.like(Strings.isNotEmpty(book.getAuthor()),Book::getAuthor,book.getAuthor());
        queryWrapper.like(Strings.isNotEmpty(book.getMark()),Book::getMark,book.getMark());
        bookService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
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

    @DeleteMapping
    public R<String> delete(@RequestParam List<Long> ids){
        boolean b = bookService.removeByIds(ids);
        if (b) {
            return R.success("删除成功");
        }
        return R.error("删除失败");
    }
}
