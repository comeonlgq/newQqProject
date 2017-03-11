package com.qq.common;
public class Message implements java.io.Serializable{

	private String mesType; //表示消息的类型
	private String sender;  //表示消息的发送者
	private String getter;  //表示是消息的接收者
	private String con;     //表示消息的内容
	private String sendTime;//表示消息的发送时间
	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getGetter() {
		return getter;
	}

	public void setGetter(String getter) {
		this.getter = getter;
	}

	public String getCon() {
		return con;
	}

	public void setCon(String con) {
		this.con = con;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getMesType() {
		return mesType;
	}

	public void setMesType(String mesType) {
		this.mesType = mesType;
	}
}
