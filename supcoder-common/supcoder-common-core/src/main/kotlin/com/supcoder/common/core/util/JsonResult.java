package com.supcoder.common.core.util;


import com.supcoder.common.core.exception.ErrorCodeEnum;

/**
 * @author lee
 */
@Deprecated
public class JsonResult<T> {

	private int code;

	private String msg;

	private T data;
	
	public JsonResult(){
	}
	
	public JsonResult(T data){
		this.code = ErrorCodeEnum.SUCCESS.getCode();
		this.msg = "success";
		this.data = data;
	}
	
	public JsonResult(String msg, T data){
		this.code = ErrorCodeEnum.SUCCESS.getCode();
		this.msg = msg;
		this.data = data;
	}

	public JsonResult(int code, String msg){
		this.code = code;
		this.msg = msg;
	}
	
	public JsonResult(int code, String msg, T data){
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

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

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "JsonResult [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}
	
}
