package com.qq.server.tools;

/**
 * 这个类专门负责添加用户的界面
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

	// 定义一些控件
	JPanel jp1, jp2, jp3;
	JLabel jl1, jl2, jl3, jl4, jl5, jl6,jp4_jlb;
	JTextField jtf1, jtf2, jtf3, jtf4, jtf5, jtf6;
	JButton jp3_jb1, jp3_jb2;

	// 定义构造函数进行初始化
	// owner指它的父窗口
	// title 窗口名
	// model 指定是模态窗口还是非模态窗口
	public QqAdd(Frame owner, String title,String number, boolean model) {
		// 调用父类的构造方法，达到模式对话框效果
		super(owner, title, model);
		// 定义北部的组件
		jp1 = new JPanel(new GridLayout(6, 1));

		jl1 = new JLabel("qq号码:",SwingConstants.CENTER);
		jl2 = new JLabel("昵称:",SwingConstants.CENTER);
		jl3 = new JLabel("性别:",SwingConstants.CENTER);
		jl4 = new JLabel("年龄:",SwingConstants.CENTER);
		jl5 = new JLabel("真实姓名:",SwingConstants.CENTER);
		jl6 = new JLabel("qq密码:",SwingConstants.CENTER);

		// 向面板中添加组件
		jp1.add(jl1);
		jp1.add(jl2);
		jp1.add(jl3);
		jp1.add(jl4);
		jp1.add(jl5);
		jp1.add(jl6);

		// 定义保存取消的按钮面板，南部组件
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
		jp3_jb1 = new JButton("保存");
		jp3_jb1.addActionListener(this);
		jp3_jb2 = new JButton("取消");
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
		// 调用父类的构造方法，达到模式对话框效果
		super(owner, title, model);
		// 定义北部的组件
		jp1 = new JPanel(new GridLayout(6, 1));

		jl1 = new JLabel("qq号码:",SwingConstants.CENTER);
		jl2 = new JLabel("昵称:",SwingConstants.CENTER);
		jl3 = new JLabel("性别:",SwingConstants.CENTER);
		jl4 = new JLabel("年龄:",SwingConstants.CENTER);
		jl5 = new JLabel("真实姓名:",SwingConstants.CENTER);
		jl6 = new JLabel("qq密码:",SwingConstants.CENTER);

		// 向面板中添加组件
		jp1.add(jl1);
		jp1.add(jl2);
		jp1.add(jl3);
		jp1.add(jl4);
		jp1.add(jl5);
		jp1.add(jl6);

		// 定义保存取消的按钮面板，南部组件
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
		jp3_jb1 = new JButton("保存");
		jp3_jb1.addActionListener(this);
		jp3_jb2 = new JButton("取消");
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
		if (e.getSource() == jp3_jb1) {// 当用户点击保存按钮
			// 将加入的数据保存下来
			MyQqModel temp = new MyQqModel();
			String sql = "insert into qqList values(?,?,?,?,?,?,?)";
		    String b="0";
			String[] paras = { jtf1.getText(), jtf2.getText(), jtf3.getText(),
					jtf4.getText(), jtf5.getText(), jtf6.getText(),b};
			if(!temp.updateQq(sql, paras))
			{
				//提示
				JOptionPane.showMessageDialog(this,"添加失败");
			}
			//关闭对话框
			this.dispose();

			// 将保存的数据添加到主界面中去

		} else {// 当用户点击取消按钮时
			this.dispose();
		}
	}

}
