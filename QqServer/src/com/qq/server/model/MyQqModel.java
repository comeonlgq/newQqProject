/**
 * �����ҵ�һ��qq���ģ��
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

	// rowData�������������
	// columnNames�����������
	Vector rowData, columnNames;
	SqlHelper sh = null;

	// �������ߺ��ѵ�������Ϣ
	public boolean updateQqIsOn(String sql, String[] paras) {
		// ����һ��SqlHelper��������򲢷��Բ����ǣ��ɰ������ɾ�̬��
		 sh = new SqlHelper();
		return sh.updateIsOnLine(sql, paras);
	}

	// ����ɾ�Ķ������������������
	public boolean updateQq(String sql, String[] paras) {
		// ����һ��SqlHelper��������򲢷��Բ����ǣ��ɰ������ɾ�̬��
        sh = new SqlHelper();
		return sh.updateExec(sql, paras);
	}

	//�ж����ߵ������Ƿ������Լ��ĺ���
	public void isMyOnlineFriend(String sql, String[] paras){
	
		columnNames = new Vector();
		// ��������
		columnNames.add("qq����");
		columnNames.add("�����ǳ�");
		columnNames.add("���Ѻ���");
		columnNames.add("qq�ǳ�");
		columnNames.add("�Ƿ�����");

		rowData = new Vector();

		try {
			sh = new SqlHelper();
			ResultSet rs = sh.queryExec(sql, paras);

			while (rs.next()) {
				Vector hang = new Vector();
				hang.add(rs.getString(1));// ����QQ����
				hang.add(rs.getString(2));// �����ǳ�
				hang.add(rs.getString(3));// ���Ѻ���
				hang.add(rs.getString(4));//qq�ǳ�
				hang.add(rs.getNString(5));//�Ƿ����� 
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
	
	// ���qq,paras�����Ǵ洢���sql���ݡ�

	// ��ѯ�ı��ʾ��ǳ�ʼ��
	public void queryQq(String sql, String[] paras) {
		columnNames = new Vector();
		// ��������
		columnNames.add("qq����");
		columnNames.add("�ǳ�");
		columnNames.add("�Ա�");
		columnNames.add("����");
		columnNames.add("��ʵ����");
		columnNames.add("qq����");
		columnNames.add("�Ƿ�����");

		rowData = new Vector();

		try {
			sh = new SqlHelper();
			ResultSet rs = sh.queryExec(sql, paras);

			while (rs.next()) {
				Vector hang = new Vector();
				hang.add(rs.getString(1));// ����QQ����
				hang.add(rs.getNString(2));// �ǳ�
				hang.add(rs.getNString(3));// �Ա�
				hang.add(rs.getNString(4));// ����
				hang.add(rs.getString(5));// ��ʵ����
				hang.add(rs.getString(6));// QQ����
				hang.add(rs.getNString(7));// ��ʾ�û��Ƿ�����
				// �������������rowData��
				rowData.add(hang);
//				System.out.println("��¼�ߵ���Ϣ��");
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

	   //����������ڽ��û����͵���Ϣ�������ݿ���
			public void storeChatMessage(){
				
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
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return ((Vector) this.rowData.get(rowIndex)).get(columnIndex);
	}

}
