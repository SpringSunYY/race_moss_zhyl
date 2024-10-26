package com.moss.common.exception.sql;

import com.moss.common.exception.base.BaseException;

/**
 * @Project: MossZhyl
 * @Package: com.moss.common.exception.sql
 * @Author: YY
 * @CreateTime: 2024-10-26  22:20
 * @Description: DuplicateException
 * 数据存在异常处理器
 * @Version: 1.0
 */
public final class DuplicateException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误提示
     */
    private String message;

    /**
     * 错误明细，内部调试错误
     * <p>
     * 和 {@link CommonResult#getDetailMessage()} 一致的设计
     */
    private String detailMessage;

    /**
     * 空构造方法，避免反序列化问题
     */
    public DuplicateException() {
    }

    public DuplicateException(String message) {
        this.message = message;
    }

    public DuplicateException(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public String getDetailMessage() {
        return detailMessage;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }

    public DuplicateException setMessage(String message) {
        this.message = message;
        return this;
    }

    public DuplicateException setDetailMessage(String detailMessage) {
        this.detailMessage = detailMessage;
        return this;
    }
}
