/**
 * ������ѡ�������..������
 */
package com.qq.client.tools;

import java.util.*;
import java.io.*;
import com.qq.client.view.*;
public class ManageQqFriendList {

	private static HashMap hm=new HashMap<String, QqFriendList>();
	
	private static HashMap hm1=new HashMap<String, Integer>();//���ڴ�ź������Լ��б��Ӧ����һ��
	
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
