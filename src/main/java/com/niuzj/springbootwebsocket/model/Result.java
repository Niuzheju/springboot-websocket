package com.niuzj.springbootwebsocket.model;

public class Result {

    private Integer code = 200;

    private String resultMsg = "操作成功";

    private Object data;

    private boolean success = true;

    public static Result newSuccess(){
        return new Result();
    }

    public static Result newSuccess(Object data){
        Result result = new Result();
        result.setData(data);
        return result;
    }

    public static Result newFailed(){
        Result result = new Result();
        result.setCode(9999);
        result.setSuccess(false);
        result.setResultMsg("操作失败");
        return result;
    }

    public static Result newFailed(String resultMsg){
        Result result = new Result();
        result.setCode(9999);
        result.setSuccess(false);
        result.setResultMsg(resultMsg);
        return result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
