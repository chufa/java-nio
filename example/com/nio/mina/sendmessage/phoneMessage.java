package com.nio.mina.sendmessage;

import java.io.Serializable;

public class phoneMessage implements Serializable{
	private static final long serialVersionUID = 1L;
	private String sendPhone;
	private String recvPhone;
	private String message;
	public String getSendPhone() {
		return sendPhone;
	}
	public void setSendPhone(String phone){
		this.sendPhone=phone;
	}
	public String getRecvPhone(){
		return recvPhone;
	}
	public void setRecvPhone(String phone){
		this.recvPhone=phone;
	}
	public String getMessage(){
		return message;
	}
	public void setMessage(String message){
		this.message=message;
	}
}
