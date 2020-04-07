package com.llw.mvplibrary.base;

/**
 * @ClassDest: 网络请求返回解析基类
 */
public class BaseResponse {
    /**
     * code : 200
     * msg : incorrect password
     * data : null
     */

    private int code;
    private String msg;
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
