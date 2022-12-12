package edu.eschina.mall.model.response;

/**
 * 通用网络请求响应模型
 */
public class BaseResponse<T> {
    /**
     * 状态码
     */
    private int code;


    /**
     * 出错的提示信息
     * 发生了错误不一定有
     */

    private String message;

    /**
     * 响应的数据
     */
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

    public BaseResponse() {
    }

    public BaseResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
