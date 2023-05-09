package com.cjj.bookManager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cjj.bookManager.domain.Type;
import com.cjj.bookManager.service.TypeService;
import com.cjj.bookManager.mapper.TypeMapper;
import org.springframework.stereotype.Service;

/**
* @author admin
* @description 针对表【type(图书类型表)】的数据库操作Service实现
* @createDate 2023-03-27 00:43:43
*/
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type>
    implements TypeService{

}




