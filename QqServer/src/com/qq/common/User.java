/**
 * 这是用户信息类
 */
package com.qq.common;

public class User implements java.io.Serializable {
   private String type;
	private String userId;
	private String passwd;


	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

}
