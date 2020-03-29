package com.rikka.common.result;

import com.rikka.common.constants.ErrorCodeBaseResult;


public class BaseResult {
    private String errorCode;
    private String errorMessage;
    private Object result;

    private static BaseResult createResult(String code, String msg, Object o) {
        BaseResult baseResult = new BaseResult();
        baseResult.setErrorCode(code);
        baseResult.setErrorMessage(msg);
        baseResult.setResult(o);
        return baseResult;
    }


    //有返回值
    public static BaseResult success(Object o) {
        return createResult(ErrorCodeBaseResult.SUCCESS.getStatus(), ErrorCodeBaseResult.SUCCESS.getContent(), o);
    }

    //无返回值
    public static BaseResult success() {
        return createResult(ErrorCodeBaseResult.SUCCESS.getStatus(), ErrorCodeBaseResult.SUCCESS.getContent(), null);
    }

    //无返回值
    public static BaseResult error(ErrorCodeBaseResult result) {
        return createResult(result.getStatus(), result.getContent(), null);
    }

    //有返回值
    public static BaseResult error(ErrorCodeBaseResult result,Object error) {
        return createResult(result.getStatus(), result.getContent(), error);
    }

    //自定义错误信息
    public static BaseResult error(String message,Object object){
        return createResult(ErrorCodeBaseResult.MY_ERROR.getStatus(),message,object);
    }
    //自定义错误信息
    public static BaseResult error(String message){
        return createResult(ErrorCodeBaseResult.MY_ERROR.getStatus(),message,null);
    }
    private void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    private void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    private void setResult(Object result) {
        this.result = result;
    }
}
