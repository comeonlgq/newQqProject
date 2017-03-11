/**
 * 这是用户的注册界面
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

	// 定义一些控件
	JPanel jp1, jp2, jp3;
	JLabel jl1, jl2, jl3, jl4, jl5, jl6;
	JTextField jtf1, jtf2, jtf3, jtf4, jtf5, jtf6;
	JButton jp3_jb1, jp3_jb2;

	// 定义构造函数进行初始化
	// owner指它的父窗口
	// title 窗口名
	// model 指定是模态窗口还是非模态窗口
	public QqClientRegist(Frame owner, String title, boolean model) {
		// 调用父类的构造方法，达到模式对话框效果
		super(owner, title, model);
		// 定义北部的组件
		jp1 = new JPanel(new GridLayout(6, 1));

		jl1 = new JLabel("qq号码:");
		jl2 = new JLabel("昵称:");
		jl3 = new JLabel("性别:");
		jl4 = new JLabel("年龄:");
		jl5 = new JLabel("真实姓名:");
		jl6 = new JLabel("qq密码:");

		// 向面板中添加组件
		jp1.add(jl1);
		jp1.add(jl2);
		jp1.add(jl3);
		jp1.add(jl4);
		jp1.add(jl5);
		jp1.add(jl6);

		// 定义保存取消的按钮面板，南部组件
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
		jp3_jb1 = new JButton("保存");
		jp3_jb1.addActionListener(this);
		jp3_jb2 = new JButton("取消");
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
		if (e.getSource() == jp3_jb1) {// 当用户点击保存按钮

			QqClientUser qqClientUser = new QqClientUser();
			// 将加入的数据保存下来
			RegistUser ru = new RegistUser();
			ru.setType("9");
			ru.setUserId(jtf1.getText());
			ru.setQqName(jtf2.getText());
			ru.setQqSex(jtf3.getText());
			ru.setQqAge(jtf4.getText());
			ru.setQqRealName(jtf5.getText());
			ru.setPasswd(jtf6.getText());
			
			boolean b=qqClientUser.checkUser(ru) ;
			System.out.println("注册成功与否："+b);
			if (b&& ru != null) {

				// 将输入的用户的信息设法传送到服务器
				JOptionPane.showMessageDialog(this, "注册成功！");
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(this,
						"不好意思，出问题了，您的注册信息暂时无法发送到服务器！");
			}

		} else {// 当用户点击取消按钮时
			// 关闭对话框
			this.dispose();
		}
	}

}
