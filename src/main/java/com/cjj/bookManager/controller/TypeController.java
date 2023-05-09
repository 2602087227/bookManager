package com.cjj.bookManager.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cjj.bookManager.common.R;
import com.cjj.bookManager.domain.Book;
import com.cjj.bookManager.domain.Notice;
import com.cjj.bookManager.domain.Type;
import com.cjj.bookManager.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * ClassName: TypeController
 * Package: com.cjj.bookManager.controller
 * Description:
 *
 * @Author: 陈
 * @Create: 2023/5/7 - 22:15
 * @Version: v1.0
 */
@RestController
@RequestMapping("/type")
public class TypeController {
    @Autowired
    private TypeService typeService;


    @PostMapping("/save")
    public R<String> save(@RequestBody Type type) {
        boolean save = typeService.save(type);
        if (save != false) {
            return R.success("添加成功");
        } else {
            return R.error("添加失败");
        }
    }


    /**
     * 分页查询
     *
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<PageInfo> page(int page, int pageSize, String name) {
        PageHelper.startPage(page, pageSize);
        LambdaQueryWrapper<Type> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(name), Type::getName, name);
        List<Type> list = typeService.list(queryWrapper);
        PageInfo<Type> typePageInfo = new PageInfo<>(list);
        return R.success(typePageInfo);
    }
    @GetMapping("/{id}")
    public R<Type> getById(@PathVariable Long id) {
        Type type = typeService.getById(id);
        return R.success(type);
    }
    @PutMapping
    public R<String> update(@RequestBody Type type){
        typeService.updateById(type);
        return R.success("修改成功");
    }
    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping
    public R<String> delete(@RequestParam List<Long> ids) {
        typeService.removeByIds(ids);
        return R.success("删除成功");
    }
}


