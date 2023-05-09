package com.cjj.bookManager.service;

import com.cjj.bookManager.domain.Lend;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

/**
* @author admin
* @description 针对表【lend(借阅记录（谁在何时借走了什么书，并且有没有归还，归还时间多少）)】的数据库操作Service
* @createDate 2023-03-27 00:43:47
*/
public interface LendService extends IService<Lend> {
    PageInfo<Lend> queryLendInfoAll(int page, int pageSize, String bookName, String userName,Long userId);
}
