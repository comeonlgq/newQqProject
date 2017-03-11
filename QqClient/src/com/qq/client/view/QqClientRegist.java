/**
 * �����û���ע�����
 */
package com.qq.client.view;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import com.qq.common.*;
import com.qq.client.model.*;

public class QqClientRegist extends JDialog implements ActionListener {

	// ����һЩ�ؼ�
	JPanel jp1, jp2, jp3;
	JLabel jl1, jl2, jl3, jl4, jl5, jl6;
	JTextField jtf1, jtf2, jtf3, jtf4, jtf5, jtf6;
	JButton jp3_jb1, jp3_jb2;

	// ���幹�캯�����г�ʼ��
	// ownerָ���ĸ�����
	// title ������
	// model ָ����ģ̬���ڻ��Ƿ�ģ̬����
	public QqClientRegist(Frame owner, String title, boolean model) {
		// ���ø���Ĺ��췽�����ﵽģʽ�Ի���Ч��
		super(owner, title, model);
		// ���山�������
		jp1 = new JPanel(new GridLayout(6, 1));

		jl1 = new JLabel("qq����:");
		jl2 = new JLabel("�ǳ�:");
		jl3 = new JLabel("�Ա�:");
		jl4 = new JLabel("����:");
		jl5 = new JLabel("��ʵ����:");
		jl6 = new JLabel("qq����:");

		// �������������
		jp1.add(jl1);
		jp1.add(jl2);
		jp1.add(jl3);
		jp1.add(jl4);
		jp1.add(jl5);
		jp1.add(jl6);

		// ���屣��ȡ���İ�ť��壬�ϲ����
		jp2 = new JPanel(new GridLayout(6, 1));
		jtf1 = new JTextField();
		jtf2 = new JTextField();
		jtf3 = new JTextField();
		jtf4 = new JTextField();
		jtf5 = new JTextField();
		jtf6 = new JTextField();

		jp2.add(jtf1);
		jp2.add(jtf2);
		jp2.add(jtf3);
		jp2.add(jtf4);
		jp2.add(jtf5);
		jp2.add(jtf6);

		jp3 = new JPanel();
		jp3_jb1 = new JButton("����");
		jp3_jb1.addActionListener(this);
		jp3_jb2 = new JButton("ȡ��");
		jp3_jb2.addActionListener(this);

		jp3.add(jp3_jb1);
		jp3.add(jp3_jb2);

		this.add(jp1, BorderLayout.WEST);
		this.add(jp2, BorderLayout.CENTER);
		this.add(jp3, BorderLayout.SOUTH);
		this.setSize(350, 300);
		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == jp3_jb1) {// ���û�������水ť

			QqClientUser qqClientUser = new QqClientUser();
			// ����������ݱ�������
			RegistUser ru = new RegistUser();
			ru.setType("9");
			ru.setUserId(jtf1.getText());
			ru.setQqName(jtf2.getText());
			ru.setQqSex(jtf3.getText());
			ru.setQqAge(jtf4.getText());
			ru.setQqRealName(jtf5.getText());
			ru.setPasswd(jtf6.getText());
			
			boolean b=qqClientUser.checkUser(ru) ;
			System.out.println("ע��ɹ����"+b);
			if (b&& ru != null) {

				// ��������û�����Ϣ�跨���͵�������
				JOptionPane.showMessageDialog(this, "ע��ɹ���");
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(this,
						"������˼���������ˣ�����ע����Ϣ��ʱ�޷����͵���������");
			}

		} else {// ���û����ȡ����ťʱ
			// �رնԻ���
			this.dispose();
		}
	}

}
