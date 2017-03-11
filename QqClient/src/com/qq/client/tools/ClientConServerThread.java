/**
 * 这是客户端和服务器端保持通讯的线程.
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
	
	//构造函数
	public ClientConServerThread(Socket s)
	{
		this.s=s;
	}
	
	public void run()
	{
		while(true)
		{
			//不停的读取从服务器端发来的消息
			try {	
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				Message m=(Message)ois.readObject();
				if(m.getMesType().equals(MessageType.message_comm_mes))
				{//收到普通信息包	
					//把从服务器获得消息，显示到该显示的聊天界面
					QqChat qqChat=ManageQqChat.getQqChat(m.getGetter()+" "+m.getSender());
					System.out.println("读取到从服务发来的消息:"+ m.getSender() +" 给 "+m.getGetter()+" 内容是"+
							m.getCon());
					//显示
					qqChat.showMessage(m);	
				}else if(m.getMesType().equals(MessageType.message_ret_onLineFriend))
				{//收到返回在线好友包
					System.out.println("服务器发给客户返回的在线好友信息内容为："+m.getCon());
					String con=m.getCon();
					String friends[]=con.split(" ");
					String getter=m.getGetter();
					System.out.println("需要好友列表的人是："+getter);
				
	//				QqFriendList	qqFriendList1=ManageQqFriendList.getQqFriendList(m.getSender());
//					
					if(friends.length!=0){
					//修改相应的好友列表.
					  QqFriendList	qqFriendList=ManageQqFriendList.getQqFriendList(getter);
//					for(int i=0;i<friends.length;i++){
//						
//						QqFriendList	qqFriendList=ManageQqFriendList.getQqFriendList(friends[i]);
//					
//					//判断在线的人中有没有我的好友 
//					QqClientConServer qc=new QqClientConServer();
//					boolean isMyFriend =qc.isNeedUpdate(m.getGetter());
					
					    //更新在线好友.
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
