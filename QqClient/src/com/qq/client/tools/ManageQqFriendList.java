/**
 * 管理好友、黑名单..界面类
 */
package com.qq.client.tools;

import java.util.*;
import java.io.*;
import com.qq.client.view.*;
public class ManageQqFriendList {

	private static HashMap hm=new HashMap<String, QqFriendList>();
	
	private static HashMap hm1=new HashMap<String, Integer>();//用于存放好友在自己列表对应的哪一行
	
    public static void addWhereFriendPut(String friendId,Integer hang){
		
		hm1.put(friendId, hang);
	}
	
    
    
    
	public static Integer getWhereFriendIs(String friendId)
	{
		return (Integer)hm1.get(friendId);
	}
	
	public static void addQqFriendList(String qqid,QqFriendList qqFriendList){
		
		hm.put(qqid, qqFriendList);
	}
	
	public static QqFriendList getQqFriendList(String qqId)
	{
		return (QqFriendList)hm.get(qqId);
	}
}
