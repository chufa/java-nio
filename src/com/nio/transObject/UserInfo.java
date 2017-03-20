package com.nio.transObject;

public class UserInfo implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String QQNum;
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name=name;
	}
	public String getQQNum(){
		return QQNum;
	}
	public void setQQNum(String qQNum){
		this.QQNum=qQNum;
	}
}
