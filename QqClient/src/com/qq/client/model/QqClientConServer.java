/**
 * 这是客户端连接服务器的后台
 */
package com.qq.client.model;
import com.qq.client.tools.*;
import java.util.*;
import java.net.*;
import java.io.*;

import javax.swing.JOptionPane;

import com.qq.common.*;
public class QqClientConServer {	
	public  Socket s;
	
	//发送第一次请求,可以是登录信息也可以是注册信息
	public boolean sendInfoToServer(RegistUser o)
	{
		boolean b=false;
		User u=new User();
		try {
			s=new Socket("127.0.0.1",9999);
			ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);
			ObjectInputStream ois=new ObjectInputStream(s.getInputStream());	
			Message ms=(Message)ois.readObject();
			System.out.println("登录后的信息类型为："+ms.getMesType());
			//这里就是验证用户登录的地方
			if(ms.getMesType().equals("1"))
			{
				u.setPasswd(o.getPasswd());
				u.setType(o.getType());
				u.setUserId(o.getUserId());
				//就创建一个该qq号和服务器端保持通讯连接得线程
				ClientConServerThread ccst=new ClientConServerThread(s);
				//启动该通讯线程
				ccst.start();
				ManageClientConServerThread.addClientConServerThread
				(u.getUserId(), ccst);
				b=true;
			}else if(ms.getMesType().equals("2"))
			{
				//关闭Scoket
				s.close();
			}else if(ms.getMesType().equals("10")){
				
				b=true;
				s.close();
			}else if(ms.getMesType().equals("11")){
				s.close();
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally{
			//只要发生异常我就关闭通道
			
		}
		return b;
	}
	
//	//用于判断后面上线的人有没有自己的好友
//	public boolean isNeedUpdate(String getter){
//		boolean b=false;
//		MyQqModel   mm=new MyQqModel();
//		String paras[]={getter};
//		b=mm.isMyFriend("select * from (select * from qqFriendList where  qqId=?)  m where isOnLine=1  ", paras);
//		return b;
//	}
		
	
	
	
//	public void SendInfoToServer(Object o)
//	{
//		/*try {
//			Socket s=new Socket("127.0.0.1",9999);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			// TODO: handle exception
//		}finally{
//			
//		}*/
//	}
}
