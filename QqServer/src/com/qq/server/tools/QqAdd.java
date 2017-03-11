package com.qq.server.tools;

/**
 * �����ר�Ÿ�������û��Ľ���
 */
import java.text.*;

import javax.swing.*;

import com.qq.server.model.MyQqModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class QqAdd extends JDialog implements ActionListener {

	// ����һЩ�ؼ�
	JPanel jp1, jp2, jp3;
	JLabel jl1, jl2, jl3, jl4, jl5, jl6,jp4_jlb;
	JTextField jtf1, jtf2, jtf3, jtf4, jtf5, jtf6;
	JButton jp3_jb1, jp3_jb2;

	// ���幹�캯�����г�ʼ��
	// ownerָ���ĸ�����
	// title ������
	// model ָ����ģ̬���ڻ��Ƿ�ģ̬����
	public QqAdd(Frame owner, String title,String number, boolean model) {
		// ���ø���Ĺ��췽�����ﵽģʽ�Ի���Ч��
		super(owner, title, model);
		// ���山�������
		jp1 = new JPanel(new GridLayout(6, 1));

		jl1 = new JLabel("qq����:",SwingConstants.CENTER);
		jl2 = new JLabel("�ǳ�:",SwingConstants.CENTER);
		jl3 = new JLabel("�Ա�:",SwingConstants.CENTER);
		jl4 = new JLabel("����:",SwingConstants.CENTER);
		jl5 = new JLabel("��ʵ����:",SwingConstants.CENTER);
		jl6 = new JLabel("qq����:",SwingConstants.CENTER);

		// �������������
		jp1.add(jl1);
		jp1.add(jl2);
		jp1.add(jl3);
		jp1.add(jl4);
		jp1.add(jl5);
		jp1.add(jl6);

		// ���屣��ȡ���İ�ť��壬�ϲ����
		jp2 = new JPanel(new GridLayout(6, 1));
		jtf1 = new JTextField(15);
		int a=Integer.parseInt(number)+1;
		jtf1.setText(String.valueOf(a));
		jtf1.setEnabled(false);
		jtf2 = new JTextField(15);
		jtf3 = new JTextField(15);
		jtf4 = new JTextField(15);
		jtf5 = new JTextField(15);
		jtf6 = new JTextField(15);
	
		jp2.add(jtf1);
		jp2.add(jtf2);
		jp2.add(jtf3);
		jp2.add(jtf4);
		jp2.add(jtf5);
		jp2.add(jtf6);

		jp3 = new JPanel(new FlowLayout());
		jp3_jb1 = new JButton("����");
		jp3_jb1.addActionListener(this);
		jp3_jb2 = new JButton("ȡ��");
		jp3_jb2.addActionListener(this);

		jp3.add(jp3_jb1);
		jp3.add(jp3_jb2);
		
		
		
		jp4_jlb=new JLabel(new ImageIcon("image/fengjing.jpg"));
		
		this.add(jp4_jlb,BorderLayout.WEST);
		this.add(jp1,BorderLayout.CENTER);
		this.add(jp2, BorderLayout.EAST);
		this.add(jp3, BorderLayout.SOUTH);
		
		this.setSize(484, 350);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);

	}

	public QqAdd(Frame owner, String title, boolean model) {
		// ���ø���Ĺ��췽�����ﵽģʽ�Ի���Ч��
		super(owner, title, model);
		// ���山�������
		jp1 = new JPanel(new GridLayout(6, 1));

		jl1 = new JLabel("qq����:",SwingConstants.CENTER);
		jl2 = new JLabel("�ǳ�:",SwingConstants.CENTER);
		jl3 = new JLabel("�Ա�:",SwingConstants.CENTER);
		jl4 = new JLabel("����:",SwingConstants.CENTER);
		jl5 = new JLabel("��ʵ����:",SwingConstants.CENTER);
		jl6 = new JLabel("qq����:",SwingConstants.CENTER);

		// �������������
		jp1.add(jl1);
		jp1.add(jl2);
		jp1.add(jl3);
		jp1.add(jl4);
		jp1.add(jl5);
		jp1.add(jl6);

		// ���屣��ȡ���İ�ť��壬�ϲ����
		jp2 = new JPanel(new GridLayout(6, 1));
		jtf1 = new JTextField(15);
		jtf2 = new JTextField(15);
		jtf3 = new JTextField(15);
		jtf4 = new JTextField(15);
		jtf5 = new JTextField(15);
		jtf6 = new JTextField(15);

		jp2.add(jtf1);
		jp2.add(jtf2);
		jp2.add(jtf3);
		jp2.add(jtf4);
		jp2.add(jtf5);
		jp2.add(jtf6);

		jp3 = new JPanel(new FlowLayout());
		jp3_jb1 = new JButton("����");
		jp3_jb1.addActionListener(this);
		jp3_jb2 = new JButton("ȡ��");
		jp3_jb2.addActionListener(this);

		jp3.add(jp3_jb1);
		jp3.add(jp3_jb2);
		
		
		
		jp4_jlb=new JLabel(new ImageIcon("image/fengjing.jpg"));
		
		this.add(jp4_jlb,BorderLayout.WEST);
		this.add(jp1,BorderLayout.CENTER);
		this.add(jp2, BorderLayout.EAST);
		this.add(jp3, BorderLayout.SOUTH);
		
		this.setSize(484, 350);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);

	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == jp3_jb1) {// ���û�������水ť
			// ����������ݱ�������
			MyQqModel temp = new MyQqModel();
			String sql = "insert into qqList values(?,?,?,?,?,?,?)";
		    String b="0";
			String[] paras = { jtf1.getText(), jtf2.getText(), jtf3.getText(),
					jtf4.getText(), jtf5.getText(), jtf6.getText(),b};
			if(!temp.updateQq(sql, paras))
			{
				//��ʾ
				JOptionPane.showMessageDialog(this,"���ʧ��");
			}
			//�رնԻ���
			this.dispose();

			// �������������ӵ���������ȥ

		} else {// ���û����ȡ����ťʱ
			this.dispose();
		}
	}

}
