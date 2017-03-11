/**
 * �����ҵ�һ��qq���ģ��
 */
package com.qq.client.model;

import com.qq.server.db.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class MyQqModel extends AbstractTableModel {

	// rowData�������������
	// columnNames�����������
	Vector rowData, columnNames;
	SqlHelper sh=null;
//	
	//����ɾ�Ķ������������������
	public boolean updateQq(String sql,String []paras){
		//����һ��SqlHelper��������򲢷��Բ����ǣ��ɰ������ɾ�̬��
        sh=new SqlHelper();
		return sh.updateExec(sql, paras);
	}
	// ���qq,paras�����Ǵ洢���sql���ݡ�
	
	
	//����������ҳ����ߵ��û��Ƿ������Լ��ĺ��ѣ��Ա����Լ����Ը���������
	public void  isMyFriend(String sql,String []paras){
	
		
		columnNames = new Vector();
		// ��������
		columnNames.add("qq����");
		columnNames.add("�����ǳ�");
		columnNames.add("���Ѻ���");
		columnNames.add("qq�ǳ�");
		columnNames.add("�û��Ƿ�����");
		rowData = new Vector();

		try {
	       sh=new SqlHelper();
			ResultSet rs=sh.queryExec(sql,paras);
			while (rs.next()) {
				Vector hang = new Vector();
				hang.add(rs.getString(1));// qq����
				hang.add(rs.getString(2));// �����ǳ�
				hang.add(rs.getString(3));//���Ѻ���
				hang.add(rs.getString(4));//�ҵ�qq�ǳ�
				hang.add(rs.getNString(5));//�����Ƿ�����
			 
				// �������������rowData��
				rowData.add(hang);
				System.out.println("�ҵĺ�����Ϣ�б����£�");
				System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getNString(5));
			}
		
		} catch (Exception e) {
			// TODO: handle exception
		
			e.printStackTrace();
		} finally {
			sh.close();
		}
         
	}
	
	
	//��ѯ�ı��ʾ��ǳ�ʼ��
	public void queryQq(String sql) {
		columnNames = new Vector();
		// ��������
		columnNames.add("qq����");
		columnNames.add("�����ǳ�");
		columnNames.add("���Ѻ���");
		columnNames.add("qq�ǳ�");
		columnNames.add("�Ƿ�����");
		rowData = new Vector();

		try {
	       sh=new SqlHelper();
			ResultSet rs=sh.queryExec(sql);
			while (rs.next()) {
				Vector hang = new Vector();
				hang.add(rs.getString(1));// qq����
				hang.add(rs.getString(2));// �����ǳ�
				hang.add(rs.getString(3));//���Ѻ���
				hang.add(rs.getString(4));//�ҵ�qq�ǳ�
				hang.add(rs.getNString(5));//�����Ƿ�����
			   
				// �������������rowData��
				rowData.add(hang);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			sh.close();
		}

	}

	//��ѯ�ı��ʾ��ǳ�ʼ��
		public void queryQqList(String sql) {
			columnNames = new Vector();
			// ��������
			columnNames.add("qq����");
			columnNames.add("�����ǳ�");
			columnNames.add("�Ա�");
			columnNames.add("����");
			columnNames.add("��ʵ����");
			columnNames.add("����");
			columnNames.add("�Ƿ�����");
			rowData = new Vector();

			try {
		       sh=new SqlHelper();
				ResultSet rs=sh.queryExec(sql);
				while (rs.next()) {
					Vector hang = new Vector();
					hang.add(rs.getString(1));// qq����
					hang.add(rs.getNString(2));// �û��ǳ�
					hang.add(rs.getNString(3));//�û��Ա�
					hang.add(rs.getNString(4));//�û�����
					hang.add(rs.getString(5));//�û���ʵ����
					hang.add(rs.getString(6));//�û�����
					hang.add(rs.getNString(7));//�Ƿ�����
				   
					// �������������rowData��
					rowData.add(hang);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} finally {
				sh.close();
			}

		}

	//��ѯ�ı��ʾ��ǳ�ʼ��
		public void queryQq(String sql,String []paras) {
			columnNames = new Vector();
			// ��������
			columnNames.add("qq����");
			columnNames.add("�����ǳ�");
			columnNames.add("���Ѻ���");
			columnNames.add("qq�ǳ�");
			columnNames.add("�����Ƿ�����");
			rowData = new Vector();

			try {
		       sh=new SqlHelper();
				ResultSet rs=sh.queryExec(sql,paras);
				while (rs.next()) {
					Vector hang = new Vector();
					hang.add(rs.getString(1));// qq����
					hang.add(rs.getString(2));// �����ǳ�
					hang.add(rs.getString(3));//���Ѻ���
					hang.add(rs.getString(4));//qq�ǳ�
				    hang.add(rs.getNString(5));//�����Ƿ�����
					// �������������rowData��
					rowData.add(hang);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} finally {
				sh.close();
			}

		}
		
	
	// ������������ʹ���ܹ���ʾ��Ҫ������
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return (String) this.columnNames.get(column);
	}

	// �õ����ж�����
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.rowData.size();
	}

	// �õ����ж�����
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.columnNames.size();
	}

	// �õ�ĳ��ĳ�е�����
	@Override
	public String getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return (String)((Vector) this.rowData.get(rowIndex)).get(columnIndex);
	}
	
	
	// �õ�ĳ��ĳ�е�����
	
	public Integer getValue( int rowIndex) {
		// TODO Auto-generated method stub
		return (Integer)((Vector) this.rowData.get(rowIndex)).get(4);
	}

}
