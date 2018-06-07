package com.xu.headlinehelper.net;

/**
 * @author 许 on 2018/6/3.
 *         自定义异常类
 */

public class ApiException extends Exception {
    /**
     * 错误代码
     */
    private int code;

    public ApiException(String message) {
        super(message);
    }
}
