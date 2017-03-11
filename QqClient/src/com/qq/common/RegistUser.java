/**
 * 这是注册用户的个人信息
 */
package com.qq.common;
import java.io.*;

public class RegistUser implements Serializable {
	private String type;//用户显示这是登录信息
	private String userId;
	private String qqName;
	private String qqSex;
	private String  qqAge;
	private String qqRealName;
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
	public String getQqName() {
		return qqName;
	}
	public void setQqName(String qqName) {
		this.qqName = qqName;
	}
	public String getQqSex() {
		return qqSex;
	}
	public void setQqSex(String qqSex) {
		this.qqSex = qqSex;
	}
	public String getQqAge() {
		return qqAge;
	}
	public void setQqAge(String qqAge) {
		this.qqAge = qqAge;
	}
	public String getQqRealName() {
		return qqRealName;
	}
	public void setQqRealName(String qqRealName) {
		this.qqRealName = qqRealName;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

}
