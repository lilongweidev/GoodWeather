package com.llw.library.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * 基础返回类
 * @author llw
 */
public class BaseResponse {

    /**
     * 结果码
     */
    @SerializedName("res_code")
    @Expose
    public Integer responseCode;

    /**
     * 返回的错误信息
     */
    @SerializedName("res_error")
    @Expose
    public String responseError;
}
