/**
 * 这是我的一个qq表的模型
 */
package com.qq.server.model;

import com.qq.server.db.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class MyQqModel extends AbstractTableModel {

	// rowData用来存放行数据
	// columnNames用来存放列名
	Vector rowData, columnNames;
	SqlHelper sh = null;

	// 更改在线好友的上线信息
	public boolean updateQqIsOn(String sql, String[] paras) {
		// 创建一个SqlHelper，如果程序并发性不考虑，可把他做成静态的
		 sh = new SqlHelper();
		return sh.updateIsOnLine(sql, paras);
	}

	// 把增删改都可以用这个函数处理
	public boolean updateQq(String sql, String[] paras) {
		// 创建一个SqlHelper，如果程序并发性不考虑，可把他做成静态的
        sh = new SqlHelper();
		return sh.updateExec(sql, paras);
	}

	//判断在线的人中是否有是自己的好友
	public void isMyOnlineFriend(String sql, String[] paras){
	
		columnNames = new Vector();
		// 设置列名
		columnNames.add("qq号码");
		columnNames.add("好友昵称");
		columnNames.add("好友号码");
		columnNames.add("qq昵称");
		columnNames.add("是否在线");

		rowData = new Vector();

		try {
			sh = new SqlHelper();
			ResultSet rs = sh.queryExec(sql, paras);

			while (rs.next()) {
				Vector hang = new Vector();
				hang.add(rs.getString(1));// 定义QQ号码
				hang.add(rs.getString(2));// 好友昵称
				hang.add(rs.getString(3));// 好友号码
				hang.add(rs.getString(4));//qq昵称
				hang.add(rs.getNString(5));//是否在线 
				// 将行数据添加入rowData中
				rowData.add(hang);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			sh.close();
		}
	}
	
	// 添加qq,paras数组是存储多个sql数据。

	// 查询的本质就是初始化
	public void queryQq(String sql, String[] paras) {
		columnNames = new Vector();
		// 设置列名
		columnNames.add("qq号码");
		columnNames.add("昵称");
		columnNames.add("性别");
		columnNames.add("年龄");
		columnNames.add("真实姓名");
		columnNames.add("qq密码");
		columnNames.add("是否在线");

		rowData = new Vector();

		try {
			sh = new SqlHelper();
			ResultSet rs = sh.queryExec(sql, paras);

			while (rs.next()) {
				Vector hang = new Vector();
				hang.add(rs.getString(1));// 定义QQ号码
				hang.add(rs.getNString(2));// 昵称
				hang.add(rs.getNString(3));// 性别
				hang.add(rs.getNString(4));// 年龄
				hang.add(rs.getString(5));// 真实姓名
				hang.add(rs.getString(6));// QQ密码
				hang.add(rs.getNString(7));// 显示用户是否在线
				// 将行数据添加入rowData中
				rowData.add(hang);
//				System.out.println("登录者的信息：");
//				System.out.println(rs.getString(1) + "\t" + rs.getNString(2)
//						+ "\t" + rs.getNString(3) + "\t" + rs.getNString(4)
//						+ "\t" + rs.getString(5) + "\t"+ rs.getString(6)+"\t"+ rs.getNString(7));
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			sh.close();
		}
	}

	   //这个函数用于将用户发送的信息存入数据库中
			public void storeChatMessage(){
				
			}
	
	// 重载列名方法使得能够显示你要的列名
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return (String) this.columnNames.get(column);
	}

	// 得到共有多少行
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.rowData.size();
	}

	// 得到共有多少列
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.columnNames.size();
	}

	// 得到某行某列的数据
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return ((Vector) this.rowData.get(rowIndex)).get(columnIndex);
	}

}
