/**
 * ���ǿͻ������ӷ������ĺ�̨
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
	
	//���͵�һ������,�����ǵ�¼��ϢҲ������ע����Ϣ
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
			System.out.println("��¼�����Ϣ����Ϊ��"+ms.getMesType());
			//���������֤�û���¼�ĵط�
			if(ms.getMesType().equals("1"))
			{
				u.setPasswd(o.getPasswd());
				u.setType(o.getType());
				u.setUserId(o.getUserId());
				//�ʹ���һ����qq�źͷ������˱���ͨѶ���ӵ��߳�
				ClientConServerThread ccst=new ClientConServerThread(s);
				//������ͨѶ�߳�
				ccst.start();
				ManageClientConServerThread.addClientConServerThread
				(u.getUserId(), ccst);
				b=true;
			}else if(ms.getMesType().equals("2"))
			{
				//�ر�Scoket
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
			//ֻҪ�����쳣�Ҿ͹ر�ͨ��
			
		}
		return b;
	}
	
//	//�����жϺ������ߵ�����û���Լ��ĺ���
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
