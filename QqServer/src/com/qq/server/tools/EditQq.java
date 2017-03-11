/**
 * 这是qq用户信息编辑的界面
 */
package com.qq.server.tools;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.qq.server.model.MyQqModel;

public class EditQq extends JDialog implements ActionListener  {

	// 定义一些控件
		JPanel jp1, jp2, jp3;
		JLabel jl1, jl2, jl3, jl4, jl5, jl6;
		JTextField jtf1, jtf2, jtf3, jtf4, jtf5, jtf6;
		JButton jp3_jb1, jp3_jb2;

		// 定义构造函数进行初始化
		// owner指它的父窗口
		// title 窗口名
		// model 指定是模态窗口还是非模态窗口
		public EditQq(Frame owner, String title, boolean model,MyQqModel mt,int rowNum) {
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
			//初始化数据
			jtf1=new JTextField();
			jtf1.setText((String)mt.getValueAt(rowNum, 0));
			jtf1.setEditable(false);
			jtf2=new JTextField();
			jtf2.setText((String)mt.getValueAt(rowNum, 1));
			jtf3=new JTextField();
			jtf3.setText((String)mt.getValueAt(rowNum, 2)); 
			jtf4=new JTextField();
			jtf4.setText(mt.getValueAt(rowNum, 3).toString()); 
			jtf5=new JTextField();
			jtf5.setText((String)mt.getValueAt(rowNum, 4)); 
			jtf6=new JTextField();
			jtf6.setText((String)mt.getValueAt(rowNum, 5));

			jp2.add(jtf1);
			jp2.add(jtf2);
			jp2.add(jtf3);
			jp2.add(jtf4);
			jp2.add(jtf5);
			jp2.add(jtf6);

			jp3 = new JPanel();
			jp3_jb1 = new JButton("修改");
			jp3_jb1.addActionListener(this);
			jp3_jb2 = new JButton("取消");
			jp3_jb2.addActionListener(this);

			jp3.add(jp3_jb1);
			jp3.add(jp3_jb2);

			this.add(jp1, BorderLayout.WEST);
			this.add(jp2, BorderLayout.CENTER);
			this.add(jp3, BorderLayout.SOUTH);
			this.setSize(350, 300);
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			this.setVisible(true);
		}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == jp3_jb1) {// 当用户点击保存按钮
			// 将加入的数据保存下来
			
		//编译语句对象
			String sql = "update qqList set qqName=?," +
					"qqSex=?,qqAge=?,qqRealName=?,qqPasswd =? ,isOnLine =? where qqId=?";
			String b="0";
			String[] paras = {   jtf2.getText(), jtf3.getText(),
					jtf4.getText(), jtf5.getText(), jtf6.getText() ,b,jtf1.getText()};
			MyQqModel temp = new MyQqModel();
			if(!temp.updateQq(sql, paras))
			{
				//提示
				JOptionPane.showMessageDialog(this,"修改失败");
			}
			//关闭对话框
			this.dispose();

			// 将保存的数据添加到主界面中去

		} else {// 当用户点击取消按钮时
			//关闭对话框
			this.dispose();

		}
	}

}
