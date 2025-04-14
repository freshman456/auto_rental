package com.rental.auto_rental.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author YinHang
 * @project IntelliJ IDEA
 * @date 2025/4/12
 */

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * 实现mybatis plus 自动填充功能
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        // 起始版本 3.3.3(推荐)
        this.strictInsertFill(metaObject, "createTime", LocalDateTime::now,
                LocalDateTime.class);
        // 起始版本 3.3.3(推荐)
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime::now,
                LocalDateTime.class);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 起始版本 3.3.3(推荐)
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime::now,
                LocalDateTime.class);
    }
}