/**
 * 这是qq服务器，它在监听，等待某个qq客户端，来连接
 */
package com.qq.server.model;

import com.qq.common.*;

import java.net.*;
import java.awt.event.ActionEvent;
import java.io.*;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class MyQqServer {

	public MyQqServer() {
		try {
			// 在9999监听
			System.out.println("我是服务器，在9999监听");
			ServerSocket ss = new ServerSocket(9999);
			while (true) {
				// 阻塞,等待连接
				Socket s = ss.accept();
				// 接收客户端发来的信息.
				ObjectInputStream ois = new ObjectInputStream(
						s.getInputStream());
				//主要是判断是注册信息还是登录验证信息
				RegistUser ru=(RegistUser)ois.readObject();
				 User u=new User();
				 String type=null; 
				if(ru.getType().equals("8")){//表明用户是想要登录
					u.setPasswd(ru.getPasswd());
					u.setUserId(ru.getUserId());
					u.setType(ru.getType());
					ru=null;		
					Message m = new Message();
					ObjectOutputStream oos = new ObjectOutputStream(
							s.getOutputStream());
				//得到登录信息成功与否的消息类型,即获取type值
					QqServerUser qu=new QqServerUser();
					type=qu.isLoginOk(u);		
					m.setMesType(type);
					oos.writeObject(m);
					// 这是针对不同的信息类型做不同的处理
					if (m.getMesType().equals("1")) {

						
                        //更新用户是否在线信息
						qu.updateIsOnLine(u.getUserId());
		
						
						// 这里就单开一个线程，让该线程与该客户端保持通讯.
						SerConClientThread scct = new SerConClientThread(s);
						ManageClientThread.addClientThread(u.getUserId(), scct);
						// 启动与该客户端通信的线程.
						scct.start();
						scct.sleep(500);
						// 并通知其它在线用户.
						scct.notifyOther(u.getUserId());
							
					   //在这里需要关闭线程
					}
					else {
					
						// 关闭Socket
						s.close();
					}
					
				}else  if(ru.getType().equals("9")){//表明用户是想要注册
					
					Message m=new Message();
					ObjectOutputStream oos = new ObjectOutputStream(
							s.getOutputStream());
				//得到登录信息成功与否的消息类型,即获取type值
					QqServerUser qu=new QqServerUser();
					type=qu.isRegistOk(ru);
					
					m.setMesType(type);
					oos.writeObject(m);
                      s.close();
					
				}
				
			}

		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {

		}

	}

}
