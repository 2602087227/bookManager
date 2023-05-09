package com.cjj.bookManager.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cjj.bookManager.common.R;
import com.cjj.bookManager.domain.Book;
import com.cjj.bookManager.domain.Lend;
import com.cjj.bookManager.service.BookService;
import com.cjj.bookManager.service.LendService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * ClassName: LendController
 * Package: com.cjj.bookManager.controller
 * Description:
 *
 * @Author: 陈
 * @Create: 2023/3/27 - 1:02
 * @Version: v1.0
 */
@RestController
@RequestMapping("/lend")
@Transactional
public class LendController {

    @Autowired
    private LendService lendService;

    @Autowired
    private BookService bookService;

    @PostMapping
    public R<String> save(@RequestBody Lend lend) {
        LambdaQueryWrapper<Lend> queryWrapper = new LambdaQueryWrapper<>();
        Long bookId = lend.getBookId();
        Long userId = lend.getUserId();
        if (bookId != null && userId != null) {
            queryWrapper.eq(Lend::getBookId, bookId);
            queryWrapper.eq(Lend::getUserId, userId);
            Lend one = lendService.getOne(queryWrapper);
            if (one != null && one.getStatus() == 0) {
                return R.error("每人只能借一相同的本书");
            } else {
                Book book = bookService.getById(lend.getBookId());
                Integer stock = book.getStock();
                if (stock > 0) {
                    book.setStock(stock - 1);
                    bookService.updateById(book);
                } else {
                    return R.error("库存不足");
                }
                lend.setStatus(0);
                lend.setLendTime(LocalDateTime.now());
                boolean save = lendService.save(lend);
                if (save != false) {
                    return R.success("借阅成功");
                } else {
                    return R.error("借阅失败");
                }
            }
        } else {
            return R.error("系统异常请重新登录");
        }
    }

    /**
     * 分页查询
     *
     * @param page
     * @param pageSize
     * @param bookName
     * @param userName
     * @return
     */
    @GetMapping("/lendPage")
    public R<PageInfo> LendPage(int page, int pageSize, String bookName, String userName, Long userId) {
        PageInfo<Lend> LendPageInfo = lendService.queryLendInfoAll(page, pageSize, bookName, userName, userId);
        return R.success(LendPageInfo);
    }


    /**
     * 修改用户信息
     *
     * @param lend
     * @return
     */
    @PutMapping
    public R<String> update(@RequestBody Lend lend) {
        Long bookId = lend.getBookId();
        Long userId = lend.getUserId();
        if (bookId != null && userId != null) {
            LambdaQueryWrapper<Lend> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Lend::getBookId, lend.getBookId());
            queryWrapper.eq(Lend::getUserId, lend.getUserId());
            queryWrapper.eq(Lend::getStatus, 0);
            Lend oneLend = lendService.getOne(queryWrapper);
            if (oneLend != null) {
                Book book = bookService.getById(lend.getBookId());
                book.setStock(book.getStock() + 1);
                bookService.updateById(book);
                lend.setStatus(1);
                lend.setBackTime(LocalDateTime.now());
                lend.setId(oneLend.getId());
                boolean b = lendService.updateById(lend);
                if (b) {
                    return R.success("修改成功");
                } else {
                    return R.error("归还失败");
                }
            } else {
                return R.error("你还没有借这本书");
            }
        } else {
            return R.error("系统异常请重新登录");
        }

    }

    @GetMapping("/{id}")
    public R<Lend> getById(@PathVariable Long id) {
        Lend lend = lendService.getById(id);
        if (lend != null) {
            return R.success(lend);
        }
        return R.error("没有查询到该借阅表");
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping
    public R<String> delete(@RequestParam List<Long> ids) {
        lendService.removeByIds(ids);
        return R.success("删除成功");
    }
}
