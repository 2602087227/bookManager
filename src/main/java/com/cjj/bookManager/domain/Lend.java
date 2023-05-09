package com.cjj.bookManager.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

/**
 * 借阅记录（谁在何时借走了什么书，并且有没有归还，归还时间多少）
 * @TableName lend
 */
@TableName(value ="lend")
@Data
public class Lend implements Serializable {
    /**
     * id
     */
    @TableId
    private Long id;

    /**
     * 图书id
     */
    private Long bookId;

    /**
     * 读者id
     */
    private Long userId;
    /**
     * 借书状态
     * 0：已还
     * 1：未还
     */
    private int status;

    /**
     * 借书时间
     */
    private LocalDateTime lendTime;

    /**
     * 还书时间
     */
    private LocalDateTime backTime;

    /**
     * 备注信息
     */
    private String bookMark;

    @TableField(exist = false)
    private String userName;

    @TableField(exist = false)
    private String bookName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}