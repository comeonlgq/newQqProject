/**
 * 
 */
package com.qq.client.model;

import com.qq.common.*;
public class QqClientUser {

	public boolean checkUser(RegistUser u)
	{
		return new QqClientConServer().sendInfoToServer(u);
	}

	
}
