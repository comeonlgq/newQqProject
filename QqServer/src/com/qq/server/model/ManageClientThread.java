package com.qq.server.model;

import java.util.*;
public class ManageClientThread {

	public static HashMap hm=new HashMap<String, SerConClientThread>();
	
	//向hm中添加一个客户端通讯线程
	public static  void addClientThread(String uid,SerConClientThread ct)
	{
		hm.put(uid, ct);
	}
	
	public static SerConClientThread getClientThread(String uid)
	{
		return (SerConClientThread)hm.get(uid);
	}
	
	//返回当前在线的人的情况
	public static String getAllOnLineUserid(String userId)
	{
		//使用迭代器完成
		Iterator it=hm.keySet().iterator();
		String res="";
		System.out.println("在线人如下：");
		while(it.hasNext())
		{
			
			//查找在线人中是否有自己的好友
			MyQqModel   mm = new MyQqModel();
			String a=it.next().toString();
			System.out.print(a+"\t");
			String  []paras={userId,a};
			mm.isMyOnlineFriend("select * from qqFriendList where qqId=? and frienId=?",paras);
			
			if(mm.getRowCount()>0){
			          res+=a+" ";
			}
		}
		System.out.println();
		return res;
	}
}
