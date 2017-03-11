/**
 * 这是一个对数据库进行操作的类
 */
package com.qq.server.db;

import java.awt.*;
import javax.swing.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class SqlHelper {

	// 定义操作数据库需要的东西
	PreparedStatement ps = null;
	Connection ct = null;
	ResultSet rs = null;
	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String url = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=joinUser";
	String user = "sa";
	String passwd = "123456";

	//构造函数
	public SqlHelper() {
		try {
			// 加载驱动
			Class.forName(driver);
			// 得到连接
			ct = DriverManager.getConnection(url, user, passwd);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
		}
	}

	

	

//	//修改用户好友在线信息
//		public boolean updateExec(String sql,String []paras){
//			boolean b = true;
//			
//			try {
//				ps = ct.prepareStatement(sql);
//				// 给参数赋值
//				for (int i = 0; i < paras.length; i++) {
//					ps.setString(i + 1, paras[i]);
//				}
//				// 执行操作
//				int count = ps.executeUpdate();
//				if (count != 1) {
//					b = false;// 没有加入，操作失败
//				}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				b = false;
//				e.printStackTrace();
//			}
//			return b;
//		}
//	
	// 把增删改合在一起
	public boolean updateExec(String sql, String[] paras) {
		boolean b = true;
		
		try {
			ps = ct.prepareStatement(sql);
			// 给参数赋值
			for (int i = 0; i < paras.length; i++) {
				ps.setString(i + 1, paras[i]);
			}
			// 执行操作
			int count = ps.executeUpdate();
			if (count != 1) {
				b = false;// 没有加入，操作失败
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			b = false;
			e.printStackTrace();
		}
		return b;
	}

	public void close() {
		// 关闭资源
		try {
			if (rs != null) {
				rs.close();
			}
			if (ct != null) {
				ct.close();
			}
			if (ps != null) {
				ps.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 查询数据库的操作
		public ResultSet queryExec(String sql) {
			try {
			
				ps = ct.prepareStatement(sql);
				rs=ps.executeQuery();
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				
			}
			return rs;
		}
		
		
		
		// 查询数据库的操作
		public ResultSet queryExec(String sql, String[] paras) {
			try {
			
				ps = ct.prepareStatement(sql);
				// 给参数赋值
				for (int i = 0; i < paras.length; i++) {
					ps.setString(i + 1, paras[i]);
				}
				rs=ps.executeQuery();
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				
			}
			return rs;
		}
	
}
