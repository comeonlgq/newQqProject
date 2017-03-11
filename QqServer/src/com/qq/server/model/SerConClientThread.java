/**
 * ���ܣ��Ƿ�������ĳ���ͻ��˵�ͨ���߳�
 */
package com.qq.server.model;

import java.util.*;
import java.net.*;
import java.io.*;

import com.qq.common.*;
public class SerConClientThread  extends Thread{

	Socket s;
	
	public SerConClientThread(Socket s)
	{
		//�ѷ������͸ÿͻ��˵����Ӹ���s
		this.s=s;
	}
	
//	//֪ͨȥ�޸����ݿ����û��Ƿ����ߵ���Ϣ
//	public void notifyUpdateSql(String iam){
//		//�õ��������ߵ��˵��߳�
//		HashMap hm=ManageClientThread.hm;
//     	Iterator it=hm.keySet().iterator();
//	}
	
	
	//�ø��߳�ȥ֪ͨ�����û�
	public void notifyOther(String iam)
	{
		//�õ��������ߵ��˵��߳�
		HashMap hm=ManageClientThread.hm;
		Iterator it=hm.keySet().iterator();
		
		while(it.hasNext())
		{
			Message m=new Message();
			m.setCon(iam);
			m.setMesType(MessageType.message_ret_onLineFriend);
			//ȡ�������˵�id
			String onLineUserId=it.next().toString();
			try {
				ObjectOutputStream oos=new ObjectOutputStream(ManageClientThread.getClientThread(onLineUserId).s.getOutputStream());
				m.setGetter(onLineUserId);
				oos.writeObject(m);
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
			
		}
	}
	
	public void run()
	{	
		while(true)
		{		
			//������߳̾Ϳ��Խ��տͻ��˵���Ϣ.
			try {
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				Message m=(Message)ois.readObject();				
				//�Դӿͻ���ȡ�õ���Ϣ���������жϣ�Ȼ������Ӧ�Ĵ���
				if(m.getMesType().equals(MessageType.message_comm_mes))
				{
					//һ�����ת��.
					//ȡ�ý����˵�ͨ���߳�
					SerConClientThread sc=ManageClientThread.getClientThread(m.getGetter());
					ObjectOutputStream oos=new ObjectOutputStream(sc.s.getOutputStream());
					oos.writeObject(m);					
					//��δ�����Ҫ�ǽ��û����͵���Ϣ���������ݿ���
					MyQqModel temp = new MyQqModel();
					String []paras={m.getSender(),m.getGetter(),m.getCon(),m.getSendTime()};
					String sql="insert qqChat values(?,?,?,?)";
					temp.updateQq(sql, paras);		
				}else if(m.getMesType().equals(MessageType.message_get_onLineFriend))
				{
					System.out.println(m.getSender()+" Ҫ���ĺ���");
					//���ڷ������ĺ��Ѹ��ÿͻ��˷���.
					String res=ManageClientThread.getAllOnLineUserid(m.getSender());
					Message m2=new Message();
					m2.setMesType(MessageType.message_ret_onLineFriend);
					m2.setCon(res);
					m2.setGetter(m.getSender());
					ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
					oos.writeObject(m2);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
			
			
		}
		
		
	}
}
