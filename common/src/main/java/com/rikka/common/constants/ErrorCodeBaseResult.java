package com.rikka.common.constants;

public enum ErrorCodeBaseResult {
    SUCCESS("0000","交易成功"),
    USER_ERROR_ONE("1001", "用户已存在"),
    USER_ERROR_TWO("1002", "用户名或密码为空"),
    USER_ERROR_THREE("1003", "账号或密码错误"),
    DATABASE_ERROR("2001","操作数据库异常"),
    PARAMS_ERROR_1("3001","参数有误"),
    PARAMS_ERROR_2("3002","未登录"),
    BASE_ERROR_2("3003","上传文件失败"),
    MY_ERROR("9999","自定义error");

    private String status;
    private String content;

    ErrorCodeBaseResult(String status, String content) {
        this.status = status;
        this.content = content;
    }


    public String getStatus() {
        return status;
    }

    public String getContent() {
        return content;
    }
}
