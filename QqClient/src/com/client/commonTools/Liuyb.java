package com.client.commonTools;

import javax.swing.JPanel;
import javax.swing.JFrame;

import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

//������
public class Liuyb extends JFrame {
	/**
	 * ����˽�б���
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JTextArea jTextArea = null;
	private JScrollPane jScrollPane = null;
	private JLabel jLabel1 = null;
	private JComboBox jComboBox = null;
	private JLabel jLabel2 = null;
	private JTextField jTextField = null;
	private JButton jButton = null;
	private JButton jButton1 = null;
	private JButton jButtonSelect = null;
	
	private String id;
	private String name;

	/**
	 * ����
	 */
	public Liuyb(String id,String name) {
		super();
        this.id=id;
        this.name=name;
		initialize();
	}

	/**
	 * ���԰�
	 */
	private void initialize() {
		this.setContentPane(getJContentPane());
		this.setTitle(name+"�����԰�");
		this.setBounds(new Rectangle(0, 0, 640, 480));
		this.setVisible(true);
	}

	/**
	 * ���԰��·�����
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(214, 407, 45, 24));
			jLabel2.setFont(new Font("Dialog", Font.BOLD, 14));
			jLabel2.setText("˵��");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(32, 407, 15, 24));
			jLabel1.setFont(new Font("Dialog", Font.BOLD, 14));
			jLabel1.setText("��");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(283, 15, 45, 20));
			jLabel.setHorizontalAlignment(SwingConstants.CENTER);
			jLabel.setFont(new Font("Dialog", Font.BOLD, 14));
			jLabel.setText("���԰�");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(getJComboBox(), null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(getJTextField(), null);
			jContentPane.add(getJButton(), null);
			jContentPane.add(getJButton1(), null);
			jContentPane.add(getJButtonSelect(), null);
		}
		return jContentPane;
	}

	/**
	 * �ı���
	 */
	private JTextArea getJTextArea() {
		if (jTextArea == null) {
			jTextArea = new JTextArea();
			jTextArea.setText("�������ݣ�");
//			getRecord();
			jTextArea.setEditable(false);
		}
		return jTextArea;
	}

	/**
	 * �ı��������
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(22, 49, 534, 347));
			jScrollPane.setViewportView(getJTextArea());
		}
		return jScrollPane;
	}

	/**
	 * ����ѡ��
	 */
	private JComboBox getJComboBox() {
		if (jComboBox == null) {
			jComboBox = new JComboBox();
			jComboBox.setBounds(new Rectangle(54, 407, 149, 24));
			String[] mycbox = { "", "΢Ц", "��Ц", "ʹ��" };
			jComboBox.addItem(mycbox[0]);
			jComboBox.addItem(mycbox[1]);
			jComboBox.addItem(mycbox[2]);
			jComboBox.addItem(mycbox[3]);
		}
		return jComboBox;
	}

	/**
	 * �ı���ʾ��
	 */
	private JTextField getJTextField() {
		if (jTextField == null) {
			jTextField = new JTextField();
			jTextField.setBounds(new Rectangle(265, 407, 231, 24));
		}
		return jTextField;
	}
	//���������ݿ����Ӳ���
	public static Connection getConnection(){
		Connection conn=null;
		try {	
			String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
			String url = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=joinUser";
			String user = "sa";
			String password = "123456";
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * �ύ��ť
	 */
	
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(505, 407, 70, 24));
			jButton.setText("�ύ");
			jButton.setMnemonic('\n');
			jButton.addActionListener(new java.awt.event.ActionListener() {
				String content = null;
				String time = null;

				public void actionPerformed(java.awt.event.ActionEvent e) {
					final Calendar rightNow = Calendar.getInstance();
					String dats = rightNow.get(1) + "��" + (rightNow.get(2) + 1)
							+ "��" + rightNow.get(5) + "��" + rightNow.get(11)
							+ "Сʱ" + rightNow.get(12) + "����" + rightNow.get(13)
							+ "��";
					time = dats;
					if (jComboBox.getSelectedItem() == "") {
						content = "��" + jComboBox.getSelectedItem().toString()
								+ "˵" + jTextField.getText();
					} else {
						content = "��" + jComboBox.getSelectedItem().toString()
								+ "˵" + jTextField.getText();
					}
					String sql = "INSERT INTO liuyanb (id,nickName,content,time) VALUES ('"+id+"','"+name+"','"+content+"','"+time+"')";
					Statement pst = null;
					Connection con = null;
					try {
						con=getConnection();
						pst = con.createStatement();
						pst.executeUpdate(sql);
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
					try {
						pst.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					try {
						con.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					jTextField.setText(null);
					jTextArea.setText(jTextArea.getText()+"\r\n" + content + "\tʱ�䣺" + time);
				}
			});
		}
		return jButton;
	}
	/*
	 * ���ݿ��ѯ��ѯ
	 */
	private JButton getJButtonSelect(){
		if (jButtonSelect == null) {
			jButtonSelect = new JButton();
			jButtonSelect.setBounds(new Rectangle(565, 220, 60, 32));
			jButtonSelect.setText("��ѯ");
			jButtonSelect.setMnemonic('s');
			jButtonSelect.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					jTextArea.setText("�������ݣ�");
					getRecord();
				}
			});
		}
		return jButtonSelect;
	}

	public void getRecord(){
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		String content=null;
		String time=null;
		conn=getConnection();
		String sql="SELECT * FROM liuyanb";
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				content=rs.getString("content");
				time=rs.getString("time");
				jTextArea.setText(jTextArea.getText()+"\r\n" + content + "\tʱ�䣺" + time);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}
	/**
	 * ������ť
	 */
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setBounds(new Rectangle(565, 51, 60, 32));
			jButton1.setText("����");
			jButton1.setMnemonic('c');
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					jTextArea.setText("�������ݣ�");
				}
			});
		}
		return jButton1;
	}

	/**
	 * ������
	 */
	public static void main(String[] args) {
		//new Liuyb("1", "С��");
	}


}
