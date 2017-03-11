/**
 * ����һ�������ݿ���в�������
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

	// ����������ݿ���Ҫ�Ķ���
	PreparedStatement ps = null;
	Connection ct = null;
	ResultSet rs = null;
	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String url = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=joinUser";
	String user = "sa";
	String passwd = "123456";

	//���캯��
	public SqlHelper() {
		try {
			// ��������
			Class.forName(driver);
			// �õ�����
			ct = DriverManager.getConnection(url, user, passwd);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
		}
	}

	

	

//	//�޸��û�����������Ϣ
//		public boolean updateExec(String sql,String []paras){
//			boolean b = true;
//			
//			try {
//				ps = ct.prepareStatement(sql);
//				// ��������ֵ
//				for (int i = 0; i < paras.length; i++) {
//					ps.setString(i + 1, paras[i]);
//				}
//				// ִ�в���
//				int count = ps.executeUpdate();
//				if (count != 1) {
//					b = false;// û�м��룬����ʧ��
//				}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				b = false;
//				e.printStackTrace();
//			}
//			return b;
//		}
//	
	// ����ɾ�ĺ���һ��
	public boolean updateExec(String sql, String[] paras) {
		boolean b = true;
		
		try {
			ps = ct.prepareStatement(sql);
			// ��������ֵ
			for (int i = 0; i < paras.length; i++) {
				ps.setString(i + 1, paras[i]);
			}
			// ִ�в���
			int count = ps.executeUpdate();
			if (count != 1) {
				b = false;// û�м��룬����ʧ��
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			b = false;
			e.printStackTrace();
		}
		return b;
	}

	public void close() {
		// �ر���Դ
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

	// ��ѯ���ݿ�Ĳ���
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
		
		
		
		// ��ѯ���ݿ�Ĳ���
		public ResultSet queryExec(String sql, String[] paras) {
			try {
			
				ps = ct.prepareStatement(sql);
				// ��������ֵ
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
