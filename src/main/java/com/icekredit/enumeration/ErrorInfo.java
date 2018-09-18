package com.icekredit.enumeration;

/**
 * Created by icekredit on 2017/6/9.
 */
public enum ErrorInfo {
    UNKNOWERROR("000001", "未知错误,请联系客服"),
    UPLAODFILERROR("000002", "上传文件错误"),
    DOWNLAODFILERROR("000003", "下载文件错误"),
    LOGINERROR("000004", "账号或密码错误"),
    GETSERVICELISTERROR("000005", "获取Service列表错误"),
    GETSERVICEREPORTSTYLELISTERROR("000006", "获取Service报告样式列表错误"),
    GETREPORTLISTERROR("000007", "获取报告列表错误"),
    VALICODEERROR("000008", "验证码错误");

    private String code;
    private String msg;

    ErrorInfo(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode(){
        return this.code;
    }

    public String getMsg(){
        return this.msg;
    }

    public void setCode(String code){
        this.code = code;
    }

    public void setMsg(String msg){
        this.msg = msg;
    }
}
