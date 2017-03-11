/**
 * �������������Ľ���
 * ��Ϊ�ͻ��ˣ�Ҫ���ڶ�ȡ��״̬��������ǰ�������һ���߳�
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
		jb2=new JButton("����");
		jp1.add(jb1);
		jp1.add(jb2);
		
		jtf=new JTextField(15);
		jb=new JButton("����");
		jb.addActionListener(this);
	
		jp2=new JPanel();
		jp2.add(jtf);
		jp2.add(jb);
		
		//Ŀ����ʹ�ı�����Գ��ֹ�����
		add(new JScrollPane(jta),"Center");
		
	//	this.add(jp1,"North");
		this.add(jp2,"South");
		this.setTitle(ownerId+" ���ں� "+friend+" ����");
		this.setIconImage((new ImageIcon("image/chatIco.jpg").getImage()));
		this.setSize(300, 200);
		this.setVisible(true);
	}
	
	//дһ��������������ʾ��Ϣ
	public void showMessage(Message m)
	{
		String info=m.getSendTime()+":\n"+m.getSender()+" �� "+m.getGetter()+" ˵:\t"+m.getCon()+"\r\n";
		this.jta.append(info);
	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==jb)
		{
			//����û�����ˣ����Ͱ�ť
			Message m=new Message();
			m.setMesType(MessageType.message_comm_mes);
			m.setSender(this.ownerId);
			m.setGetter(this.friendId);
			m.setCon(jtf.getText());
			m.setSendTime(new java.util.Date().toString());
			QqChat qqChat=ManageQqChat.getQqChat(m.getGetter()+" "+m.getSender());

			//�����յ�����Ϣ�洢�����ݿ���
			String []paras={m.getSender(),m.getGetter(),m.getCon(),m.getSendTime()};
			String sql = "insert into qqChat values(?,?,?,?)";
			MyQqModel temp = new MyQqModel();
			temp.updateQq(sql, paras);
			
			//��ʾ
			if(qqChat!=null){
			   qqChat.showMessage(m);
			}
			this.jta.append(m.getSendTime()+":\n"+m.getSender()+" �� "+m.getGetter()+" ˵:\t"+m.getCon()+"\r\n");
			//���û�������ͺ󽫸շ��͵�������գ��Ա��ں����ٷ���Ϣ
			jtf.setText("");
			//���͸�������.
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
