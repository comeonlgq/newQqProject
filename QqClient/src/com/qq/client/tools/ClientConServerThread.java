/**
 * ���ǿͻ��˺ͷ������˱���ͨѶ���߳�.
 */
package com.qq.client.tools;
import com.qq.client.model.*;
import java.io.*;
import java.net.*;

import javax.swing.JOptionPane;

import com.qq.client.view.QqChat;
import com.qq.client.view.QqFriendList;
import com.qq.common.*;
public class ClientConServerThread extends Thread {

	private Socket s;
	
	//���캯��
	public ClientConServerThread(Socket s)
	{
		this.s=s;
	}
	
	public void run()
	{
		while(true)
		{
			//��ͣ�Ķ�ȡ�ӷ������˷�������Ϣ
			try {	
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				Message m=(Message)ois.readObject();
				if(m.getMesType().equals(MessageType.message_comm_mes))
				{//�յ���ͨ��Ϣ��	
					//�Ѵӷ����������Ϣ����ʾ������ʾ���������
					QqChat qqChat=ManageQqChat.getQqChat(m.getGetter()+" "+m.getSender());
					System.out.println("��ȡ���ӷ���������Ϣ:"+ m.getSender() +" �� "+m.getGetter()+" ������"+
							m.getCon());
					//��ʾ
					qqChat.showMessage(m);	
				}else if(m.getMesType().equals(MessageType.message_ret_onLineFriend))
				{//�յ��������ߺ��Ѱ�
					System.out.println("�����������ͻ����ص����ߺ�����Ϣ����Ϊ��"+m.getCon());
					String con=m.getCon();
					String friends[]=con.split(" ");
					String getter=m.getGetter();
					System.out.println("��Ҫ�����б�����ǣ�"+getter);
				
	//				QqFriendList	qqFriendList1=ManageQqFriendList.getQqFriendList(m.getSender());
//					
					if(friends.length!=0){
					//�޸���Ӧ�ĺ����б�.
					  QqFriendList	qqFriendList=ManageQqFriendList.getQqFriendList(getter);
//					for(int i=0;i<friends.length;i++){
//						
//						QqFriendList	qqFriendList=ManageQqFriendList.getQqFriendList(friends[i]);
//					
//					//�ж����ߵ�������û���ҵĺ��� 
//					QqClientConServer qc=new QqClientConServer();
//					boolean isMyFriend =qc.isNeedUpdate(m.getGetter());
					
					    //�������ߺ���.
						if(qqFriendList!=null)
					    {
							qqFriendList.updateFriend(m);
					   }
					}
//			        	if(qqFriendList!=null)
//						{
//							qqFriendList.updateFriend(m);
//						}
						
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public Socket getS() {
		return s;
	}

	public void setS(Socket s) {
		this.s = s;
	}
	
}
