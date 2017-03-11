/**
 * ���Ƿ��������û�����
 */
package com.qq.server.view;
import java.awt.*;
import javax.swing.*;

import com.qq.server.tools.*;
import com.qq.server.model.*;

import java.sql.*;
import java.awt.event.*;

public class MyServerUserFrame extends JFrame  implements ActionListener{

	// ����һЩ�ؼ�
		JPanel jp1, jp2;
		JLabel jl1;
		JTextField jtf;
		JButton jp1_jb1, jp2_jb1, jp2_jb2, jp2_jb3;
		JTable jt;
		JScrollPane jsp ;

		// �������嵽������Ϊ�˷�ֹ�ڴ�й¶
		MyQqModel mt;
		
		//���캯����ʼ���������
		public MyServerUserFrame(){
			// ��������������
			jp1 = new JPanel();
			jl1 = new JLabel("�ǳƣ�");
			jtf = new JTextField(10);
			jp1_jb1 = new JButton("��ѯ");
			jp1_jb1.addActionListener(this);

			// �����֣��Ѹ����ؼ�����jp1
			jp1.add(jl1);
			jp1.add(jtf);
			jp1.add(jp1_jb1);

			// ��������Ŀؼ�
			jp2 = new JPanel();
			jp2_jb1 = new JButton("���");
			jp2_jb1.addActionListener(this);// ����

			jp2_jb2 = new JButton("�޸�");
			jp2_jb2.addActionListener(this);// ����

			jp2_jb3 = new JButton("ɾ��");
			jp2_jb3.addActionListener(this);// ����

			// ��������ť���뵽jp2��
			jp2.add(jp2_jb1);
			jp2.add(jp2_jb2);
			jp2.add(jp2_jb3);

			// �����м�ؼ�

			// ����һ������ģ�Ͷ���
			MyQqModel myTable = new MyQqModel();
			String []paras={"1"};
			myTable.queryQq("select * from qqList where 1=?", paras);
			// ��ʼ��JTable
			jt = new JTable(myTable);

			// ��ʼ��jsp JScrollPane
			jsp = new JScrollPane(jt);

			// ��jsp���뵽JFrame��ȥ
			this.add(jsp);
			this.add(jp1, "North");
			this.add(jp2, "South");
			this.setSize(400, 300);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setVisible(true);

		}
		
		
		
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == jp1_jb1) {// ���û������ѯ��ťʱ���¼��Ĵ���
			// ��Ϊ�ѶԱ�����ݷ�װ��MyTable�У����ǾͿ��ԱȽϼ򵥵���ɲ�ѯ����

			String name = this.jtf.getText().trim();
			// дһ��sql���
			String sql = "select * from qqList where qqName=?";
			String []paras={name};
			// �����µ�����ģ���࣬������
			mt = new MyQqModel();
			mt.queryQq(sql, paras);
			// ����JTable
			jt.setModel(mt);
		} else if (e.getSource() == jp2_jb1) {// ���û������Ӱ�ťʱ

			
			QqAdd qa = new QqAdd(this, "��Ӻ���", true);
			// �����µ�����ģ���࣬������
			mt = new MyQqModel();
			String []paras={"1"};
			mt.queryQq("select * from qqList where 1=?",paras);
			// ����JTable
			jt.setModel(mt);
		}
		else if (e.getSource() == jp2_jb2) {// ���û�����޸İ�ťʱ
			
			int rowNum = this.jt.getSelectedRow();
			if (rowNum == -1) {
				// ��ʾ�û�һ��û��ѡ����Ҫѡ��һ��ɾ��
				JOptionPane.showMessageDialog(this, "��ѡ��һ��");
				return;// ˭���þͷ��ص����������Ǹ�����
			}
			// �õ�qq���
			mt = new MyQqModel();// ���û����仰������ֿ�ָ���쳣
			mt = new MyQqModel();// ���û����仰������ֿ�ָ���쳣
			String []paras1={"1"};
			mt.queryQq("select * from qqList where 1=?", paras1);
			EditQq eq = new EditQq(this, "�޸ĺ�����Ϣ", true,mt,rowNum);
			// �����µ�����ģ���࣬������
		   String paras[]={"1"};
			
			mt.queryQq("select * from qqList where 1=?",paras);
			// ����JTable
			jt.setModel(mt);

		} 
			else {// ���û����ɾ����ťʱ
				// ˵���û�ϣ��ɾ����¼
				// �õ�ѡ��qq��id
				// ���Է����û�������У�����û�һ�û��ѡ���᷵��-1
			int rowNum = this.jt.getSelectedRow();
			if (rowNum == -1) {
				// ��ʾ�û�һ��û��ѡ����Ҫѡ��һ��ɾ��
				JOptionPane.showMessageDialog(this, "��ѡ��һ��");
				return;// ˭���þͷ��ص����������Ǹ�����
			}
			// �õ�qq���
			System.out.println("ѡ�е���Ϊ��"+rowNum);
			mt = new MyQqModel();// ���û����仰������ֿ�ָ���쳣
			String []paras1={"1"};
			mt.queryQq("select * from qqList where 1=?", paras1);
			String qqId = (String) mt.getValueAt(rowNum, 0);
			System.out.println("ѡ�е��û�idΪ��"+qqId);
			
			// ����һ��sql���
			String sql1= "delete from qqList where qqId=?";
			String[] paras = { qqId };
			MyQqModel temp = new MyQqModel();
			temp.updateQq(sql1, paras);
			
           //��������ģ��
			mt = new MyQqModel();
			String []paras2={"1"};
			mt.queryQq("select * from qqList where 1=?",paras2);
			// ����JTable
			jt.setModel(mt);
		}
	
	}
	
     
}
