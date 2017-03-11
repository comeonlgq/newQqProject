package com.client.commonTools;

import java.io.BufferedReader;  
import java.io.InputStreamReader;  
import java.util.Properties;  
 
import javax.mail.Folder;  
import javax.mail.Message;  
import javax.mail.Session;  
import javax.mail.Store;  
 
/**  
 * �򵥵��ʼ����ճ��򣬴�ӡ���ʼ���ԭʼ����  
 * @author haolloyin  
 */ 
public class SimpleStoreMails {  
    public static void main(String[] args) throws Exception {  
        // ����pop3����������������Э�顢�û���������  
        String pop3Server = "pop3.126.com";  
        String protocol = "pop3";  
        String user = "testhao";  
        String pwd = "123456";  
          
        // ����һ���о���������Ϣ��Properties����  
        Properties props = new Properties();  
        props.setProperty("mail.store.protocol", protocol);  
        props.setProperty("mail.pop3.host", pop3Server);  
          
        // ʹ��Properties������Session����  
        Session session = Session.getInstance(props);  
        session.setDebug(true);  
          
        // ����Session������Store���󣬲�����pop3������  
        Store store = session.getStore();  
        store.connect(pop3Server, user, pwd);  
          
        // ��������ڵ��ʼ���Folder������"ֻ��"��  
        Folder folder = store.getFolder("inbox");  
        folder.open(Folder.READ_ONLY);  
          
        // ����ʼ���Folder�ڵ������ʼ�Message����  
        Message [] messages = folder.getMessages();  
          
        int mailCounts = messages.length;  
        for(int i = 0; i < mailCounts; i++) {  
              
            String subject = messages[i].getSubject();  
            String from = (messages[i].getFrom()[0]).toString();  
              
            System.out.println("�� " + (i+1) + "���ʼ������⣺" + subject);  
            System.out.println("�� " + (i+1) + "���ʼ��ķ����˵�ַ��" + from);  
              
            System.out.println("�Ƿ�򿪸��ʼ�(yes/no)?��");  
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  
            String input = br.readLine();  
            if("yes".equalsIgnoreCase(input)) {  
                // ֱ�����������̨��  
                messages[i].writeTo(System.out);  
            }             
        }  
        folder.close(false);  
        store.close();  
    }  
} 
