/**
 * 这是服务器端的控制界面，可以完成启动服务器，关闭服务器
 * 存储用户的数据
 * 可以管理和监控用户.
 * 可以显示QQ好友的信息（使用的是Table）
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

	// 北部
	JTabbedPane jtp;
	JPanel jp5, jp6;// jp5代表在线用户界面，jp6代表用户管理，
	JPanel jp1, jp2, jp3, jp4;// 
	JButton jp1_jb1, jp1_jb2;// 表示启动服务器与关闭服务器按钮
	TitledBorder tb1, tb2;

	// 中间部分
	JScrollPane jp2_jsp;
	JTable table ;
	JButton jb;
   
	// 南部
	TitledBorder tb3;
	JTextArea jta;

	// 用户界面（QQ好友信息）
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
	
		// 在线用户界面与用户界面（北边）
		jp5 = new JPanel(new GridLayout(3,1));
	
		tb1 = new TitledBorder("服务器");
		jp1 = new JPanel();
		jp1_jb1 = new JButton("启动服务器");
		jp1_jb1.addActionListener(this);
		jp1_jb2 = new JButton("关闭服务器");
		jp1_jb2.addActionListener(this);
		jp1.setBorder(tb1);
		jp1.add(jp1_jb1);
		jp1.add(jp1_jb2);

		// 在线用户面板，中间控件
		jp2 = new JPanel();
		
		tb2 = new TitledBorder("在线用户");

		// 创建一个数据模型对象
		MyQqModel myTable2 = new MyQqModel();
		String[] paras2 = { "1" ,"1"};
		myTable2.queryQq("select * from qqList where 1=? and isOnLine=?", paras2);
		// 初始化JTable
		table = new JTable(myTable2);
		jp2_jsp = new JScrollPane(table);
		jp2_jsp.setBorder(tb2);
		// 初始化jsp JScrollPane
		
		
		//南部控件
		 jp3=new JPanel();
		 
		 tb3=new TitledBorder("系统公告板");
		 jta=new JTextArea();
		 
		 
		 jp3.setBorder(tb3);
		 jp3.add(jta);

		// jp5是最大的面板
		jp5.add(jp1,BorderLayout.NORTH);
		jp5.add(jp2_jsp,BorderLayout.CENTER);
		jp5.add(jp3,BorderLayout.SOUTH);

		// 用户界面的那个面板对应的定义最上面的组件

		jp6 = new JPanel();

		jpUser1 = new JPanel();
		jlUser1 = new JLabel("昵称：");
		jtfUser = new JTextField(10);
		jpUser1_jb1 = new JButton("查询");
		jpUser1_jb1.addActionListener(this);

		// 流布局，把各个控件加入jp1
		jpUser1.add(jlUser1);
		jpUser1.add(jtfUser);
		jpUser1.add(jpUser1_jb1);

		// 定义下面的控件
		jpUser2 = new JPanel();
		jpUser2_jb1 = new JButton("添加");
		jpUser2_jb1.addActionListener(this);// 监听

		jpUser2_jb2 = new JButton("修改");
		jpUser2_jb2.addActionListener(this);// 监听

		jpUser2_jb3 = new JButton("删除");
		jpUser2_jb3.addActionListener(this);// 监听

		// 将各个按钮加入到jpUser2中
		jpUser2.add(jpUser2_jb1);
		jpUser2.add(jpUser2_jb2);
		jpUser2.add(jpUser2_jb3);

		// 定义中间控件

		// 创建一个数据模型对象
		MyQqModel myTable1 = new MyQqModel();
		String[] paras1 = { "1" };
		myTable1.queryQq("select * from qqList where 1=?", paras1);
		// 初始化JTable
		jtUser = new JTable(myTable1);

		// 初始化jsp JScrollPane
		jspUser = new JScrollPane(jtUser);

		// 把jsp放入到JFrame中去
		jp6.add(jpUser1,"North");
		jp6.add(jspUser);
		jp6.add(jpUser2,"South");
		
		jtp=new JTabbedPane();

		jtp.add("在线用户管理", jp5);
		jtp.add("用户界面", jp6);

		this.add(jtp);
		this.setTitle("服务器的控制界面");
		this.setSize(530, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
    
		if (e.getSource() == jp1_jb1) {
			JOptionPane.showMessageDialog(null, "服务器启动了！");
			new MyQqServer();

		} 
		else if (e.getSource() == jp1_jb2) {

			JOptionPane.showMessageDialog(null, "服务器关闭了！");
			this.dispose();

		}

		else if (e.getSource() == jpUser1_jb1) {// 当用户点击查询按钮时，事件的处理
			// 因为把对表的数据封装到MyTable中，我们就可以比较简单的完成查询任务

			String name = this.jtfUser.getText().trim();
			// 写一个sql语句
			String sql = "select * from qqList where qqName=?";
			String[] paras = { name };
			// 构建新的数据模型类，并更新
			mymodel = new MyQqModel();
			mymodel.queryQq(sql, paras);
			// 跟新JTable
			jtUser.setModel(mymodel);
		} 
		else if (e.getSource() == jpUser2_jb1) 
		{// 当用户点击添加按钮时

			//这部分代码是为了找出当前号码编号的最大值
			MyQqModel mymodel1 = new MyQqModel();
			String[] paras = { "1" };
			mymodel1.queryQq("select * from qqList where 1=?", paras);
			String number = (String) mymodel1. getValueAt(mymodel1.getRowCount()-1, 0);		
			
			QqAdd qa = new QqAdd(this, "添加好友", number,true);
			// 构建新的数据模型类，并更新
			mymodel = new MyQqModel();
		
			mymodel.queryQq("select * from qqList where 1=?", paras);
			// 跟新JTable
			jtUser.setModel(mymodel);
		} 
		else if (e.getSource() == jpUser2_jb2) 
		{// 当用户点击修改按钮时

			int rowNum = this.jtUser.getSelectedRow();
			if (rowNum == -1)
			{
				// 显示用户一行没有选，需要选择一行删除
				JOptionPane.showMessageDialog(this, "请选择一行");
				return;// 谁调用就返回到调用它的那个函数
			}
			// 得到qq编号
			mymodel = new MyQqModel();// 如果没有这句话，会出现空指针异常
			String[] paras1 = { "1" };
			mymodel.queryQq("select * from qqList where 1=?", paras1);
			EditQq eq = new EditQq(this, "修改好友信息", true, mymodel, rowNum);
			// 构建新的数据模型类，并更新
			String paras[] = { "1" };

			mymodel.queryQq("select * from qqList where 1=?", paras);
			// 跟新JTable
			jtUser.setModel(mymodel);

		} 
		if (e.getSource() == jpUser2_jb3) 
		{// 当用户点击删除按钮时
				// 说明用户希望删除记录
				// 得到选的qq的id
				// 可以返回用户点击的行，如果用户一项都没有选，会返回-1
			int rowNum = this.jtUser.getSelectedRow();
			if (rowNum == -1) 
			{
				// 显示用户一行没有选，需要选择一行删除
				JOptionPane.showMessageDialog(this, "请选择一行");
				return;// 谁调用就返回到调用它的那个函数
			}
			// 得到qq编号
			System.out.println("选中的行为：" + rowNum);
			mymodel = new MyQqModel();// 如果没有这句话，会出现空指针异常
			String[] paras1 = { "1" };
			mymodel.queryQq("select * from qqList where 1=?", paras1);
			String qqId = (String) mymodel.getValueAt(rowNum, 0);
			System.out.println("选中的用户id为：" + qqId);

			// 创建一个sql语句
			String sql1 = "delete from qqList where qqId=?";
			String[] paras = { qqId };
			MyQqModel temp = new MyQqModel();
			temp.updateQq(sql1, paras);

			// 更新数据模型
			mymodel = new MyQqModel();
			String[] paras2 = { "1" };
			mymodel.queryQq("select * from qqList where 1=?", paras2);
			// 跟新JTable
			jtUser.setModel(mymodel);
		}

	
	}

}
