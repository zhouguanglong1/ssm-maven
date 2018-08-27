package com.zhougl.bean;

import java.util.HashMap;

public class RespEntity {
	
	private int code;
	private Object data;
	private String msg;
	
	public RespEntity(){
		this.code = 200;
		this.data = new HashMap<String, Object>();
		this.msg = "success";
	}
	
	public RespEntity(int code, String msg){
		this.code = code;
		this.msg = msg;
		this.data = new HashMap<String,Object>();
	}
	
	public RespEntity(int code, String msg, Object data){
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	
	public static void main(String[] args) {
		
		//System.out.println(JsonUtil.toJSONString(new RespEntity()));
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
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

}
