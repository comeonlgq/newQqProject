/**
 * 这是服务器的用户界面
 */
package com.qq.server.view;
import java.awt.*;
import javax.swing.*;

import com.qq.server.tools.*;
import com.qq.server.model.*;

import java.sql.*;
import java.awt.event.*;

public class MyServerUserFrame extends JFrame  implements ActionListener{

	// 定义一些控件
		JPanel jp1, jp2;
		JLabel jl1;
		JTextField jtf;
		JButton jp1_jb1, jp2_jb1, jp2_jb2, jp2_jb3;
		JTable jt;
		JScrollPane jsp ;

		// 将它定义到外面是为了防止内存泄露
		MyQqModel mt;
		
		//构造函数初始化各个组件
		public MyServerUserFrame(){
			// 定义最上面的组件
			jp1 = new JPanel();
			jl1 = new JLabel("昵称：");
			jtf = new JTextField(10);
			jp1_jb1 = new JButton("查询");
			jp1_jb1.addActionListener(this);

			// 流布局，把各个控件加入jp1
			jp1.add(jl1);
			jp1.add(jtf);
			jp1.add(jp1_jb1);

			// 定义下面的控件
			jp2 = new JPanel();
			jp2_jb1 = new JButton("添加");
			jp2_jb1.addActionListener(this);// 监听

			jp2_jb2 = new JButton("修改");
			jp2_jb2.addActionListener(this);// 监听

			jp2_jb3 = new JButton("删除");
			jp2_jb3.addActionListener(this);// 监听

			// 将各个按钮加入到jp2中
			jp2.add(jp2_jb1);
			jp2.add(jp2_jb2);
			jp2.add(jp2_jb3);

			// 定义中间控件

			// 创建一个数据模型对象
			MyQqModel myTable = new MyQqModel();
			String []paras={"1"};
			myTable.queryQq("select * from qqList where 1=?", paras);
			// 初始化JTable
			jt = new JTable(myTable);

			// 初始化jsp JScrollPane
			jsp = new JScrollPane(jt);

			// 把jsp放入到JFrame中去
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
		if (e.getSource() == jp1_jb1) {// 当用户点击查询按钮时，事件的处理
			// 因为把对表的数据封装到MyTable中，我们就可以比较简单的完成查询任务

			String name = this.jtf.getText().trim();
			// 写一个sql语句
			String sql = "select * from qqList where qqName=?";
			String []paras={name};
			// 构建新的数据模型类，并更新
			mt = new MyQqModel();
			mt.queryQq(sql, paras);
			// 跟新JTable
			jt.setModel(mt);
		} else if (e.getSource() == jp2_jb1) {// 当用户点击添加按钮时

			
			QqAdd qa = new QqAdd(this, "添加好友", true);
			// 构建新的数据模型类，并更新
			mt = new MyQqModel();
			String []paras={"1"};
			mt.queryQq("select * from qqList where 1=?",paras);
			// 跟新JTable
			jt.setModel(mt);
		}
		else if (e.getSource() == jp2_jb2) {// 当用户点击修改按钮时
			
			int rowNum = this.jt.getSelectedRow();
			if (rowNum == -1) {
				// 显示用户一行没有选，需要选择一行删除
				JOptionPane.showMessageDialog(this, "请选择一行");
				return;// 谁调用就返回到调用它的那个函数
			}
			// 得到qq编号
			mt = new MyQqModel();// 如果没有这句话，会出现空指针异常
			mt = new MyQqModel();// 如果没有这句话，会出现空指针异常
			String []paras1={"1"};
			mt.queryQq("select * from qqList where 1=?", paras1);
			EditQq eq = new EditQq(this, "修改好友信息", true,mt,rowNum);
			// 构建新的数据模型类，并更新
		   String paras[]={"1"};
			
			mt.queryQq("select * from qqList where 1=?",paras);
			// 跟新JTable
			jt.setModel(mt);

		} 
			else {// 当用户点击删除按钮时
				// 说明用户希望删除记录
				// 得到选的qq的id
				// 可以返回用户点击的行，如果用户一项都没有选，会返回-1
			int rowNum = this.jt.getSelectedRow();
			if (rowNum == -1) {
				// 显示用户一行没有选，需要选择一行删除
				JOptionPane.showMessageDialog(this, "请选择一行");
				return;// 谁调用就返回到调用它的那个函数
			}
			// 得到qq编号
			System.out.println("选中的行为："+rowNum);
			mt = new MyQqModel();// 如果没有这句话，会出现空指针异常
			String []paras1={"1"};
			mt.queryQq("select * from qqList where 1=?", paras1);
			String qqId = (String) mt.getValueAt(rowNum, 0);
			System.out.println("选中的用户id为："+qqId);
			
			// 创建一个sql语句
			String sql1= "delete from qqList where qqId=?";
			String[] paras = { qqId };
			MyQqModel temp = new MyQqModel();
			temp.updateQq(sql1, paras);
			
           //更新数据模型
			mt = new MyQqModel();
			String []paras2={"1"};
			mt.queryQq("select * from qqList where 1=?",paras2);
			// 跟新JTable
			jt.setModel(mt);
		}
	
	}
	
     
}
