package com.qq.common;
public class Message implements java.io.Serializable{

	private String mesType; //��ʾ��Ϣ������
	private String sender;  //��ʾ��Ϣ�ķ�����
	private String getter;  //��ʾ����Ϣ�Ľ�����
	private String con;     //��ʾ��Ϣ������
	private String sendTime;//��ʾ��Ϣ�ķ���ʱ��
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
