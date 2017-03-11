/**
 * ���Ƿ������˵Ŀ��ƽ��棬��������������������رշ�����
 * �洢�û�������
 * ���Թ���ͼ���û�.
 * ������ʾQQ���ѵ���Ϣ��ʹ�õ���Table��
 * 
 */
package com.qq.server.view;

import com.qq.server.model.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import com.qq.server.model.MyQqServer;
import com.qq.server.tools.EditQq;
import com.qq.server.tools.QqAdd;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class MyServerFrame extends JFrame implements ActionListener {

	// ����
	JTabbedPane jtp;
	JPanel jp5, jp6;// jp5���������û����棬jp6�����û�����
	JPanel jp1, jp2, jp3, jp4;// 
	JButton jp1_jb1, jp1_jb2;// ��ʾ������������رշ�������ť
	TitledBorder tb1, tb2;

	// �м䲿��
	JScrollPane jp2_jsp;
	JTable table ;
	JButton jb;
   
	// �ϲ�
	TitledBorder tb3;
	JTextArea jta;

	// �û����棨QQ������Ϣ��
	JPanel jpUser1, jpUser2;
	JLabel jlUser1;
	JTextField jtfUser;
	JButton jpUser1_jb1, jpUser2_jb1, jpUser2_jb2, jpUser2_jb3;
	JTable jtUser;
	JScrollPane jspUser ;

	MyQqModel mymodel;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyServerFrame mysf = new MyServerFrame();
	}

	public MyServerFrame() {
		mymodel = new MyQqModel();
		String[] paras = { "1" };
		mymodel.queryQq("select * from qqList where 1=?", paras);
	
		// �����û��������û����棨���ߣ�
		jp5 = new JPanel(new GridLayout(3,1));
	
		tb1 = new TitledBorder("������");
		jp1 = new JPanel();
		jp1_jb1 = new JButton("����������");
		jp1_jb1.addActionListener(this);
		jp1_jb2 = new JButton("�رշ�����");
		jp1_jb2.addActionListener(this);
		jp1.setBorder(tb1);
		jp1.add(jp1_jb1);
		jp1.add(jp1_jb2);

		// �����û���壬�м�ؼ�
		jp2 = new JPanel();
		
		tb2 = new TitledBorder("�����û�");

		// ����һ������ģ�Ͷ���
		MyQqModel myTable2 = new MyQqModel();
		String[] paras2 = { "1" ,"1"};
		myTable2.queryQq("select * from qqList where 1=? and isOnLine=?", paras2);
		// ��ʼ��JTable
		table = new JTable(myTable2);
		jp2_jsp = new JScrollPane(table);
		jp2_jsp.setBorder(tb2);
		// ��ʼ��jsp JScrollPane
		
		
		//�ϲ��ؼ�
		 jp3=new JPanel();
		 
		 tb3=new TitledBorder("ϵͳ�����");
		 jta=new JTextArea();
		 
		 
		 jp3.setBorder(tb3);
		 jp3.add(jta);

		// jp5���������
		jp5.add(jp1,BorderLayout.NORTH);
		jp5.add(jp2_jsp,BorderLayout.CENTER);
		jp5.add(jp3,BorderLayout.SOUTH);

		// �û�������Ǹ�����Ӧ�Ķ�������������

		jp6 = new JPanel();

		jpUser1 = new JPanel();
		jlUser1 = new JLabel("�ǳƣ�");
		jtfUser = new JTextField(10);
		jpUser1_jb1 = new JButton("��ѯ");
		jpUser1_jb1.addActionListener(this);

		// �����֣��Ѹ����ؼ�����jp1
		jpUser1.add(jlUser1);
		jpUser1.add(jtfUser);
		jpUser1.add(jpUser1_jb1);

		// ��������Ŀؼ�
		jpUser2 = new JPanel();
		jpUser2_jb1 = new JButton("���");
		jpUser2_jb1.addActionListener(this);// ����

		jpUser2_jb2 = new JButton("�޸�");
		jpUser2_jb2.addActionListener(this);// ����

		jpUser2_jb3 = new JButton("ɾ��");
		jpUser2_jb3.addActionListener(this);// ����

		// ��������ť���뵽jpUser2��
		jpUser2.add(jpUser2_jb1);
		jpUser2.add(jpUser2_jb2);
		jpUser2.add(jpUser2_jb3);

		// �����м�ؼ�

		// ����һ������ģ�Ͷ���
		MyQqModel myTable1 = new MyQqModel();
		String[] paras1 = { "1" };
		myTable1.queryQq("select * from qqList where 1=?", paras1);
		// ��ʼ��JTable
		jtUser = new JTable(myTable1);

		// ��ʼ��jsp JScrollPane
		jspUser = new JScrollPane(jtUser);

		// ��jsp���뵽JFrame��ȥ
		jp6.add(jpUser1,"North");
		jp6.add(jspUser);
		jp6.add(jpUser2,"South");
		
		jtp=new JTabbedPane();

		jtp.add("�����û�����", jp5);
		jtp.add("�û�����", jp6);

		this.add(jtp);
		this.setTitle("�������Ŀ��ƽ���");
		this.setSize(530, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
    
		if (e.getSource() == jp1_jb1) {
			JOptionPane.showMessageDialog(null, "�����������ˣ�");
			new MyQqServer();

		} 
		else if (e.getSource() == jp1_jb2) {

			JOptionPane.showMessageDialog(null, "�������ر��ˣ�");
			this.dispose();

		}

		else if (e.getSource() == jpUser1_jb1) {// ���û������ѯ��ťʱ���¼��Ĵ���
			// ��Ϊ�ѶԱ�����ݷ�װ��MyTable�У����ǾͿ��ԱȽϼ򵥵���ɲ�ѯ����

			String name = this.jtfUser.getText().trim();
			// дһ��sql���
			String sql = "select * from qqList where qqName=?";
			String[] paras = { name };
			// �����µ�����ģ���࣬������
			mymodel = new MyQqModel();
			mymodel.queryQq(sql, paras);
			// ����JTable
			jtUser.setModel(mymodel);
		} 
		else if (e.getSource() == jpUser2_jb1) 
		{// ���û������Ӱ�ťʱ

			//�ⲿ�ִ�����Ϊ���ҳ���ǰ�����ŵ����ֵ
			MyQqModel mymodel1 = new MyQqModel();
			String[] paras = { "1" };
			mymodel1.queryQq("select * from qqList where 1=?", paras);
			String number = (String) mymodel1. getValueAt(mymodel1.getRowCount()-1, 0);		
			
			QqAdd qa = new QqAdd(this, "��Ӻ���", number,true);
			// �����µ�����ģ���࣬������
			mymodel = new MyQqModel();
		
			mymodel.queryQq("select * from qqList where 1=?", paras);
			// ����JTable
			jtUser.setModel(mymodel);
		} 
		else if (e.getSource() == jpUser2_jb2) 
		{// ���û�����޸İ�ťʱ

			int rowNum = this.jtUser.getSelectedRow();
			if (rowNum == -1)
			{
				// ��ʾ�û�һ��û��ѡ����Ҫѡ��һ��ɾ��
				JOptionPane.showMessageDialog(this, "��ѡ��һ��");
				return;// ˭���þͷ��ص����������Ǹ�����
			}
			// �õ�qq���
			mymodel = new MyQqModel();// ���û����仰������ֿ�ָ���쳣
			String[] paras1 = { "1" };
			mymodel.queryQq("select * from qqList where 1=?", paras1);
			EditQq eq = new EditQq(this, "�޸ĺ�����Ϣ", true, mymodel, rowNum);
			// �����µ�����ģ���࣬������
			String paras[] = { "1" };

			mymodel.queryQq("select * from qqList where 1=?", paras);
			// ����JTable
			jtUser.setModel(mymodel);

		} 
		if (e.getSource() == jpUser2_jb3) 
		{// ���û����ɾ����ťʱ
				// ˵���û�ϣ��ɾ����¼
				// �õ�ѡ��qq��id
				// ���Է����û�������У�����û�һ�û��ѡ���᷵��-1
			int rowNum = this.jtUser.getSelectedRow();
			if (rowNum == -1) 
			{
				// ��ʾ�û�һ��û��ѡ����Ҫѡ��һ��ɾ��
				JOptionPane.showMessageDialog(this, "��ѡ��һ��");
				return;// ˭���þͷ��ص����������Ǹ�����
			}
			// �õ�qq���
			System.out.println("ѡ�е���Ϊ��" + rowNum);
			mymodel = new MyQqModel();// ���û����仰������ֿ�ָ���쳣
			String[] paras1 = { "1" };
			mymodel.queryQq("select * from qqList where 1=?", paras1);
			String qqId = (String) mymodel.getValueAt(rowNum, 0);
			System.out.println("ѡ�е��û�idΪ��" + qqId);

			// ����һ��sql���
			String sql1 = "delete from qqList where qqId=?";
			String[] paras = { qqId };
			MyQqModel temp = new MyQqModel();
			temp.updateQq(sql1, paras);

			// ��������ģ��
			mymodel = new MyQqModel();
			String[] paras2 = { "1" };
			mymodel.queryQq("select * from qqList where 1=?", paras2);
			// ����JTable
			jtUser.setModel(mymodel);
		}

	
	}

}
