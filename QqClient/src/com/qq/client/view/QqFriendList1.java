/**
 * �ҵĺ����б�,(Ҳ����İ���ˣ�������)
 */
package com.qq.client.view;

import com.qq.client.tools.*;
import com.qq.common.Message;
import com.qq.client.model.MyQqModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QqFriendList1 extends JFrame implements ActionListener,
		MouseListener {

	// �����һ�ſ�Ƭ.

	// jphy1��ʾ���������Ǹ���壬
	// jphy2��ʾ�����м�����û�����Ϣ���
	// jphy3��ʾ����������ķ���İ�����������������ť�����
	JTabbedPane jtp1;
	JPanel jphy1, jphy2, jphy3;
	JButton jphy_jb1, jphy_jb2, jphy_jb3;
	String owner;


	MyQqModel myTable, myTable1;

	// �û������б��������ݿ�õ���
	// ��QQ������Ϣ��
	JLabel[] jbls;
	// JTable jt;
	JScrollPane jsp = null;

	// ����ڶ��ſ�Ƭ(İ����).

	JPanel jpmsr1, jpmsr2, jpmsr3;
	JButton jpmsr_jb1, jpmsr_jb2, jpmsr_jb3;
	JScrollPane jsp2;
	JLabel[] jb1s;
	

	// ��������ſ�Ƭ
	JPanel jphmd1, jphmd2, jphmd3;
	JButton jphmd1_jb1, jphmd1_jb2, jphmd1_jb3;
	JScrollPane jsp3;

	// ������JFrame���ó�CardLayout
	CardLayout cl;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 QqFriendList1 qqFriendList = new QqFriendList1("1");
	}


	public void updateFriend(Message m) {
		// ��ѯ���ݿ�,���Լ��ĺ����Ƿ����ߣ�������߾͸���һ���Լ��ĺ����б�
		String onLineFriend[] = m.getCon().split(" ");
		try {
			if (onLineFriend.length != 0) {

				for (int i = 0; i < onLineFriend.length; i++) {
					int hang = ManageQqFriendList
							.getWhereFriendIs(onLineFriend[i]);
					jbls[hang].setEnabled(true);
				
				}
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		
	}


	public QqFriendList1(String ownerId) {
		this.owner = ownerId;
		jphy1 = new JPanel(new GridLayout(10,1,4,4));
		// �����һ�ſ�Ƭ(��ʾ�����б�)
		jphy_jb1 = new JButton("�ҵĺ���");
		jphy_jb1.setPreferredSize(new Dimension(100,24));
        
		jphy_jb1.setContentAreaFilled(false);  //ֻ����ϴ˾�
		jphy_jb2 = new JButton("İ����");
		jphy_jb2.setSize(108, 27);
		jphy_jb2.setContentAreaFilled(false);  //ֻ����ϴ˾�
		jphy_jb2.addActionListener(this);
		jphy_jb3 = new JButton("������");
		jphy_jb1.setPreferredSize(new Dimension(100,24));
		jphy_jb3.setContentAreaFilled(false);  //ֻ����ϴ˾�
		

		// //��jphy2����ʼ��50����.
		jbls = new JLabel[50];

		// ������Ϣ�б����������У���������Ҫ�õ����ݿ�
		// ��isOnLineΪ0ʱ��ʾ�����ߣ�Ϊ1ʱ��ʾ����
		// ����һ������ģ�Ͷ���
		myTable = new MyQqModel();
		String[] paras = { ownerId };
		myTable.queryQq(" select * from qqFriendList where  qqId=? ", paras);

	

		jphy2 = new JPanel(new GridLayout(50, 1, 4, 4));
		for (int i = 0; i < myTable.getRowCount(); i++) {

			// myTable.getValueAt(i, 1)�ǵõ����ѵ��ǳ�
			jbls[i] = new JLabel(myTable.getValueAt(i, 2) + "", new ImageIcon(
					"image/mm.jpg"), SwingConstants.LEFT);
			
			ManageQqFriendList.addWhereFriendPut(myTable.getValueAt(i, 2), i);
			jbls[i].setEnabled(false);
			if (myTable.getValueAt(i, 4).equals("1")) {
				jbls[i].setEnabled(true);
				
			}

		
			jbls[i].addMouseListener(this);
			jphy2.add(jbls[i]);

		}

		// ��ʼ��jsp JScrollPane
		jsp = new JScrollPane(jphy2);

		// �����������������ť��ɵ����
		jphy3 = new JPanel(new GridLayout(2, 1));
		// ��������ť���뵽jphy3
		jphy3.add(jphy_jb2);
		jphy3.add(jphy_jb3);

		// //��jphy1,��ʼ�����������м���������
		jphy1.add(jphy_jb1);
		jphy1.add(jsp);
		jphy1.add(jphy3);

		// ����ڶ��ſ�Ƭ

		jpmsr_jb1 = new JButton("�ҵĺ���");
		jpmsr_jb1.addActionListener(this);
		jpmsr_jb2 = new JButton("İ����");
		jpmsr_jb3 = new JButton("������");
		jpmsr1 = new JPanel(new GridLayout(10,1,4,4));
		// �ٶ���20��İ����
		jpmsr2 = new JPanel(new GridLayout(20, 1, 4, 4));

		// ��jphy2����ʼ��20İ����.
		JLabel[] jb1s2 = new JLabel[20];

		for (int i = 0; i < jb1s2.length; i++) {
			jb1s2[i] = new JLabel(i + 1 + "", new ImageIcon("image/mm.jpg"),
					SwingConstants.LEFT);
			jpmsr2.add(jb1s2[i]);
		}

		jpmsr3 = new JPanel(new GridLayout(2, 1));
		// ��������ť���뵽jphy3
		jpmsr3.add(jpmsr_jb1);
		jpmsr3.add(jpmsr_jb2);

		jsp2 = new JScrollPane(jpmsr2);

		// ��jphy1,��ʼ��
		jpmsr1.add(jpmsr3);
		jpmsr1.add(jsp2);
		jpmsr1.add(jpmsr_jb3);

		// ��������ſ�Ƭ����ʾ�������б�
		jphmd1_jb1 = new JButton("�ҵĺ���");
		jphmd1_jb1.addActionListener(this);
		jphmd1_jb2 = new JButton("İ����");
		jphmd1_jb2.addActionListener(this);
		jphmd1_jb3 = new JButton("������");
		jphmd1_jb3.addActionListener(this);
		jphmd1 = new JPanel(new GridLayout(10,1,4,4));

		jphmd2 = new JPanel(new GridLayout(10, 1, 4, 4));
		JLabel[] jlbs3 = new JLabel[10];
		for (int i = 0; i < jlbs3.length; i++) {
			jlbs3[i] = new JLabel(i + 1 + "", new ImageIcon("image/qq.gif"),
					SwingConstants.LEFT);
			jlbs3[i].addMouseListener(this);
			jphmd2.add(jlbs3[i]);
		}

		jphmd3 = new JPanel(new GridLayout(2, 1));
		jphmd3.add(jphmd1_jb1);
		jphmd3.add(jphmd1_jb2);

		jsp3 = new JScrollPane(jphmd2);

		jphmd1.add(jphmd3);
		jphmd1.add(jphmd1_jb3);
		jphmd1.add(jsp3);

		cl = new CardLayout();
		this.setLayout(cl);
		this.add(jphy1, "1");
		this.add(jpmsr1, "2");
		this.setIconImage((new ImageIcon("image/qq.gif").getImage()));
		// �ڴ�����ʾ�Լ��ı��.
		this.setTitle(owner);
		this.setSize(281, 696);
		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// ��������İ���˰�ť������ʾ�ڶ��ſ�Ƭ
		if (e.getSource() == jphy_jb1)
			cl.show(this.getContentPane(), "1");
		if (e.getSource() == jphy_jb2)
			cl.show(this.getContentPane(), "2");
		if (e.getSource() == jphy_jb3)
			cl.show(this.getContentPane(), "3");

		if (e.getSource() == jpmsr_jb1)
			cl.show(this.getContentPane(), "1");
		if (e.getSource() == jpmsr_jb2)
			cl.show(this.getContentPane(), "2");
		if (e.getSource() == jpmsr_jb3)
			cl.show(this.getContentPane(), "3");
		if (e.getSource() == jphmd1_jb1)
			cl.show(this.getContentPane(), "1");
		if (e.getSource() == jphmd1_jb2)
			cl.show(this.getContentPane(), "2");
		if (e.getSource() == jphmd1_jb3)
			cl.show(this.getContentPane(), "3");
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		// ��Ӧ�û�˫�����¼������õ����ѵı��.
		// �õ��ú��ѵı�� jb1s[i].getText().equals(ownerId)
		String friendNo = ((JLabel) arg0.getSource()).getText();
			
		if (arg0.getClickCount() == 2) {
			System.out.println("��ϣ���� " + friendNo + " ����");
			QqChat qqChat = new QqChat(this.owner, friendNo);

			// �����������뵽������
			ManageQqChat.addQqChat(this.owner + " " + friendNo, qqChat);

		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		JLabel jl = (JLabel) arg0.getSource();
		jl.setForeground(Color.red);
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		JLabel jl = (JLabel) arg0.getSource();
		jl.setForeground(Color.black);
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
