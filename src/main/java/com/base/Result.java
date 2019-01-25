package com.base;

/**
 *  统一自定义返回类
 *
 * @author liweibing
 * @since 2018/7/24 上午8:49
 */
public class Result<T> {
    //错误码
    private int code;

    //提示信息
    private String message;

    //具体的内容
    private T data;
    

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
    
}
