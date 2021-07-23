package com.llw.mvplibrary.newnet.errorhandler;

import android.net.ParseException;

import com.google.gson.JsonParseException;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;

import java.net.ConnectException;

import retrofit2.HttpException;

/**
 * 异常处理
 * @author llw
 */
public class ExceptionHandle {
    //未授权
    private static final int UNAUTHORIZED = 401;
    //禁止的
    private static final int FORBIDDEN = 403;
    //未找到
    private static final int NOT_FOUND = 404;
    //请求超时
    private static final int REQUEST_TIMEOUT = 408;
    //内部服务器错误
    private static final int INTERNAL_SERVER_ERROR = 500;
    //错误网关
    private static final int BAD_GATEWAY = 502;
    //暂停服务
    private static final int SERVICE_UNAVAILABLE = 503;
    //网关超时
    private static final int GATEWAY_TIMEOUT = 504;

    /**
     * 处理异常
     * @param throwable
     * @return
     */
    public static ResponseThrowable handleException(Throwable throwable) {
        //返回时抛出异常
        ResponseThrowable responseThrowable;
        if (throwable instanceof HttpException) {
            HttpException httpException = (HttpException) throwable;
            responseThrowable = new ResponseThrowable(throwable, ERROR.HTTP_ERROR);
            switch (httpException.code()) {
                case UNAUTHORIZED:
                case FORBIDDEN:
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                case GATEWAY_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                default:
                    responseThrowable.message = "网络错误";
                    break;
            }
            return responseThrowable;
        } else if (throwable instanceof ServerException) {
            //服务器异常
            ServerException resultException = (ServerException) throwable;
            responseThrowable = new ResponseThrowable(resultException, resultException.code);
            responseThrowable.message = resultException.message;
            return responseThrowable;
        } else if (throwable instanceof JsonParseException
                || throwable instanceof JSONException
                || throwable instanceof ParseException) {
            responseThrowable = new ResponseThrowable(throwable, ERROR.PARSE_ERROR);
            responseThrowable.message = "解析错误";
            return responseThrowable;
        } else if (throwable instanceof ConnectException) {
            responseThrowable = new ResponseThrowable(throwable, ERROR.NETWORK_ERROR);
            responseThrowable.message = "连接失败";
            return responseThrowable;
        } else if (throwable instanceof javax.net.ssl.SSLHandshakeException) {
            responseThrowable = new ResponseThrowable(throwable, ERROR.SSL_ERROR);
            responseThrowable.message = "证书验证失败";
            return responseThrowable;
        } else if (throwable instanceof ConnectTimeoutException){
            responseThrowable = new ResponseThrowable(throwable, ERROR.TIMEOUT_ERROR);
            responseThrowable.message = "连接超时";
            return responseThrowable;
        } else if (throwable instanceof java.net.SocketTimeoutException) {
            responseThrowable = new ResponseThrowable(throwable, ERROR.TIMEOUT_ERROR);
            responseThrowable.message = "连接超时";
            return responseThrowable;
        }
        else {
            responseThrowable = new ResponseThrowable(throwable, ERROR.UNKNOWN);
            responseThrowable.message = "未知错误";
            return responseThrowable;
        }
    }


    /**
     * 约定异常
     */
    public class ERROR {
        /**
         * 未知错误
         */
        public static final int UNKNOWN = 1000;
        /**
         * 解析错误
         */
        public static final int PARSE_ERROR = 1001;
        /**
         * 网络错误
         */
        public static final int NETWORK_ERROR = 1002;
        /**
         * 协议出错
         */
        public static final int HTTP_ERROR = 1003;

        /**
         * 证书出错
         */
        public static final int SSL_ERROR = 1005;

        /**
         * 连接超时
         */
        public static final int TIMEOUT_ERROR = 1006;
    }

    public static class ResponseThrowable extends Exception {
        public int code;
        public String message;

        public ResponseThrowable(Throwable throwable, int code) {
            super(throwable);
            this.code = code;
        }
    }

    public static class ServerException extends RuntimeException {
        public int code;
        public String message;
    }
}

