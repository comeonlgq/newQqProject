package com.qq.server.model;

import java.util.*;
public class ManageClientThread {

	public static HashMap hm=new HashMap<String, SerConClientThread>();
	
	//��hm�����һ���ͻ���ͨѶ�߳�
	public static  void addClientThread(String uid,SerConClientThread ct)
	{
		hm.put(uid, ct);
	}
	
	public static SerConClientThread getClientThread(String uid)
	{
		return (SerConClientThread)hm.get(uid);
	}
	
	//���ص�ǰ���ߵ��˵����
	public static String getAllOnLineUserid(String userId)
	{
		//ʹ�õ��������
		Iterator it=hm.keySet().iterator();
		String res="";
		System.out.println("���������£�");
		while(it.hasNext())
		{
			
			//�������������Ƿ����Լ��ĺ���
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
