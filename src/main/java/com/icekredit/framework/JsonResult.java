package com.icekredit.framework;

import com.icekredit.config.ErrorInfos;
import com.icekredit.enumeration.ErrorInfo;
import com.icekredit.enumeration.Status;
import com.icekredit.framework.auth.MyException;
import com.icekredit.utils.PageResult;

import java.io.Serializable;

public class JsonResult implements Serializable {
	private static final long serialVersionUID = 7553249056983455065L;
	private PageResult pageList;
	private Status success;
	private Object data;
	private  ErrorMessage error;
	//传递至前端的其他参数
	private Object others;

	public JsonResult(){
	}

	public JsonResult(ErrorInfo error) {
		this.data = null;
		this.success = Status.FAILED;
		this.setError(new ErrorMessage(error.getCode(), error.getMsg()));

	}

	public JsonResult(Status success){
		this.success = success;
	}

	public JsonResult(Status success,Object data,String errorCode,String errorMessage){
		this.data = data;
		this.success = success;
		if(success == Status.SUCCESS){
			this.error = new ErrorMessage(errorCode,errorMessage);
		}
	}
	public JsonResult(Status success, Object data, Object others){
		this( success, data);
		this.others = others;
		
	}
	public JsonResult(Status success,Object data){
		this.data = data;
		this.success = success;
	}

	public JsonResult(MyException exception){
		this.data = null;
		this.success = Status.FAILED;
		String errorCode = exception.getMessage();
		String errorMsg =  ErrorInfos.getMessage(errorCode);
		this.setError( new ErrorMessage(errorCode,errorMsg+(exception.getMsgExtention()==null?"":exception.getMsgExtention())));
	}
	
	public JsonResult(String errorCode){
		this.data = null;
		this.success = Status.FAILED;
		String errorMsg =  ErrorInfos.getMessage(errorCode);
		this.setError( new ErrorMessage(errorCode,errorMsg) );
	}
	
	
	public Status getSuccess() {
		return success;
	}

	public void setSuccess(Status success) {
		this.success = success;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public ErrorMessage getError() {
		return error;
	}

	public void setError(ErrorMessage error) {
		this.error = error;
	}

	public PageResult getPage() {
		return pageList;
	}

	public void setPage(PageResult pageList) {
		this.pageList = pageList;
	}
	public Object getOthers() {
		return others;
	}
	public void setOthers(Object others) {
		this.others = others;
	}
	
	
}

class ErrorMessage{
	private String code;
	private String message;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public ErrorMessage(String code,String message){
		this.setCode(code);
		this.setMessage(message);
	}
}
