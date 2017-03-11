/**
 * 这是与好友聊天的界面
 * 因为客户端，要处于读取的状态，因此我们把它做成一个线程
 */
package com.qq.client.view;

import com.qq.client.tools.*;
import com.qq.client.model.*;
import com.qq.common.*;
import com.qq.client.model.MyQqModel;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
public class QqChat extends JFrame implements ActionListener{

	JTextArea jta;
	JTextField jtf;
	JButton jb,jb1,jb2;
	JPanel jp1,jp2;
	String ownerId;
	String friendId;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//	   QqChat qqChat=new QqChat("1","2");
	}
	
	public QqChat(String ownerId,String friend)
	{
		this.ownerId=ownerId;
		this.friendId=friend;
		jta=new JTextArea();
		jta.setEditable(false);
//		jta.setBackground(Color.lightGray);
		
		jp1=new JPanel();
		jb1=new JButton("A");
		jb2=new JButton("表情");
		jp1.add(jb1);
		jp1.add(jb2);
		
		jtf=new JTextField(15);
		jb=new JButton("发送");
		jb.addActionListener(this);
	
		jp2=new JPanel();
		jp2.add(jtf);
		jp2.add(jb);
		
		//目的是使文本框可以出现滚动条
		add(new JScrollPane(jta),"Center");
		
	//	this.add(jp1,"North");
		this.add(jp2,"South");
		this.setTitle(ownerId+" 正在和 "+friend+" 聊天");
		this.setIconImage((new ImageIcon("image/chatIco.jpg").getImage()));
		this.setSize(300, 200);
		this.setVisible(true);
	}
	
	//写一个方法，让它显示消息
	public void showMessage(Message m)
	{
		String info=m.getSendTime()+":\n"+m.getSender()+" 对 "+m.getGetter()+" 说:\t"+m.getCon()+"\r\n";
		this.jta.append(info);
	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==jb)
		{
			//如果用户点击了，发送按钮
			Message m=new Message();
			m.setMesType(MessageType.message_comm_mes);
			m.setSender(this.ownerId);
			m.setGetter(this.friendId);
			m.setCon(jtf.getText());
			m.setSendTime(new java.util.Date().toString());
			QqChat qqChat=ManageQqChat.getQqChat(m.getGetter()+" "+m.getSender());

			//将接收到的信息存储进数据库中
			String []paras={m.getSender(),m.getGetter(),m.getCon(),m.getSendTime()};
			String sql = "insert into qqChat values(?,?,?,?)";
			MyQqModel temp = new MyQqModel();
			temp.updateQq(sql, paras);
			
			//显示
			if(qqChat!=null){
			   qqChat.showMessage(m);
			}
			this.jta.append(m.getSendTime()+":\n"+m.getSender()+" 对 "+m.getGetter()+" 说:\t"+m.getCon()+"\r\n");
			//当用户点击发送后将刚发送的内容清空，以便于后续再发消息
			jtf.setText("");
			//发送给服务器.
			try {
				ObjectOutputStream oos=new ObjectOutputStream
				(ManageClientConServerThread.getClientConServerThread(ownerId).getS().getOutputStream());
				oos.writeObject(m);
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
			
		}
			
	}	

}
