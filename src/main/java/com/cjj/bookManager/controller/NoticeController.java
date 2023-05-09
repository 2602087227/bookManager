package com.cjj.bookManager.controller;

import com.cjj.bookManager.common.R;
import com.cjj.bookManager.domain.Notice;
import com.cjj.bookManager.service.NoticeService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * ClassName: NoticeController
 * Package: com.cjj.noticeManager.controller
 * Description:
 *
 * @Author: 陈
 * @Create: 2023/4/8 - 11:32
 * @Version: v1.0
 */
@RestController
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    @PostMapping
    public R<String> save(@RequestBody Notice notice) {
        notice.setCreateTime(LocalDateTime.now());
        boolean save = noticeService.save(notice);
        if (save) {
            return R.success("新增成功");
        } else {
            return R.error("新增失败");
        }

    }


    @GetMapping("/page")
    public R<PageInfo> page(int page, int pageSize, String topic) {
        PageInfo<Notice> noticePageInfo = noticeService.queryAllByTopic(page, pageSize, topic);
        return R.success(noticePageInfo);
    }


    @GetMapping("/{id}")
    public R<Notice> getById(@PathVariable Long id) {
        Notice notice = noticeService.getById(id);
        return R.success(notice);
    }

    @DeleteMapping("/{id}")
    public R<String> delete(@PathVariable Long id) {
        boolean b = noticeService.removeById(id);
        if (b) {
            return R.success("删除成功");
        }
        return R.error("删除失败");
    }

}
