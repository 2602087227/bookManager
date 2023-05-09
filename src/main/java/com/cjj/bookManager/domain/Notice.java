package com.cjj.bookManager.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

/**
 * 公告
 * @TableName notice
 */
@TableName(value ="notice")
@Data
public class Notice implements Serializable {
    /**
     * id
     */
    @TableId
    private Long id;

    /**
     * 
     */
    private String topic;

    /**
     * 公告内容
     */
    private String content;

    /**
     * 发布人
     */
    private Long createUser;

    @TableField(exist = false)
    private String noticeCreateUser;

    /**
     * 公告发布时间
     */
    private LocalDateTime createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}