/**
 * ����qq�����������ڼ������ȴ�ĳ��qq�ͻ��ˣ�������
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
			// ��9999����
			System.out.println("���Ƿ���������9999����");
			ServerSocket ss = new ServerSocket(9999);
			while (true) {
				// ����,�ȴ�����
				Socket s = ss.accept();
				// ���տͻ��˷�������Ϣ.
				ObjectInputStream ois = new ObjectInputStream(
						s.getInputStream());
				//��Ҫ���ж���ע����Ϣ���ǵ�¼��֤��Ϣ
				RegistUser ru=(RegistUser)ois.readObject();
				 User u=new User();
				 String type=null; 
				if(ru.getType().equals("8")){//�����û�����Ҫ��¼
					u.setPasswd(ru.getPasswd());
					u.setUserId(ru.getUserId());
					u.setType(ru.getType());
					ru=null;		
					Message m = new Message();
					ObjectOutputStream oos = new ObjectOutputStream(
							s.getOutputStream());
				//�õ���¼��Ϣ�ɹ�������Ϣ����,����ȡtypeֵ
					QqServerUser qu=new QqServerUser();
					type=qu.isLoginOk(u);		
					m.setMesType(type);
					oos.writeObject(m);
					// ������Բ�ͬ����Ϣ��������ͬ�Ĵ���
					if (m.getMesType().equals("1")) {

						
                        //�����û��Ƿ�������Ϣ
						qu.updateIsOnLine(u.getUserId());
		
						
						// ����͵���һ���̣߳��ø��߳���ÿͻ��˱���ͨѶ.
						SerConClientThread scct = new SerConClientThread(s);
						ManageClientThread.addClientThread(u.getUserId(), scct);
						// ������ÿͻ���ͨ�ŵ��߳�.
						scct.start();
						scct.sleep(500);
						// ��֪ͨ���������û�.
						scct.notifyOther(u.getUserId());
							
					   //��������Ҫ�ر��߳�
					}
					else {
					
						// �ر�Socket
						s.close();
					}
					
				}else  if(ru.getType().equals("9")){//�����û�����Ҫע��
					
					Message m=new Message();
					ObjectOutputStream oos = new ObjectOutputStream(
							s.getOutputStream());
				//�õ���¼��Ϣ�ɹ�������Ϣ����,����ȡtypeֵ
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
