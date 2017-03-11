/**
 * �ҵĺ����б�,(Ҳ����İ���ˣ�������)
 */
package com.qq.client.view;

import com.client.commonTools.Calculator;
import com.client.commonTools.Liuyb;
import com.qq.client.tools.*;
import com.qq.common.Message;
import com.qq.client.model.MyQqModel;
import com.qq.server.db.SqlHelper;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class QqFriendList extends JFrame implements ActionListener,
		MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String ownId, ownName1;// ���ڴ�ŵ�ǰ�û���id���ǳ�

	// �����һ�ſ�Ƭ.

	// jp1��ʾ��һ�ſ�Ƭ�������
	JPanel jp1, jp2, jp3;
	JPanel jpmain, jptou, jpwenzi;// jpmain�û�������Ϣ���

	JButton jb, jbcomputer, jbemail, jbtext;
	JLabel jlbname;// �û��ǳ�
	JTextField jtf;// �û�����

	JPanel jhy, jhm, jmainfunction;

	// jphy1��ʾ���������Ǹ���壬
	// jphy2��ʾ�����м�����û�����Ϣ���
	// jphy3��ʾ����������ķ���İ�����������������ť�����
	JPanel jphy1, jphy2, jphy3;
	JButton jphy_jb1, jphy_jb2, jphy_jb3;
	String owner;

	MyQqModel myTable, myTable1, myTable2;

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
		QqFriendList qqFriendList = new QqFriendList("1", "����");
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
			// e.printStackTrace();
		}
	}

	public QqFriendList(String ownerId, String ownName) {
		this.ownId = ownerId;
		this.ownName1 = ownName;
		jp1 = new JPanel(new BorderLayout());
		this.owner = ownerId;
		// �����һ�ſ�Ƭ(��ʾ�����б�)
		jphy_jb1 = new JButton("�ҵĺ���");
		jphy_jb1.addActionListener(this);
		jphy_jb1.setBorderPainted(false);
		jphy_jb1.setContentAreaFilled(false);
		jphy_jb2 = new JButton("����ְԱ");
		jphy_jb2.setBorderPainted(false);
		jphy_jb2.setContentAreaFilled(false);
		jphy_jb2.addActionListener(this);
		jphy_jb3 = new JButton("��˾�쵼");
		jphy_jb3.setBorderPainted(false);
		jphy_jb3.setContentAreaFilled(false);
		jphy_jb3.addActionListener(this);
		jphy1 = new JPanel(new BorderLayout());

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
					"image/mm.jpg"), JLabel.LEFT);

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

		jptou = new JPanel();
		jpwenzi = new JPanel(new BorderLayout());

		// �����������������ť��ɵ����
		jphy3 = new JPanel(new BorderLayout());
		jhm = new JPanel(new GridLayout(2, 1));
		// ��������ť���뵽jphy3
		jhm.add(jphy_jb2);
		jhm.add(jphy_jb3);

		jphy3.add(jhm, "West");

		jhy = new JPanel(new BorderLayout());
		jhy.add(jphy_jb1, "West");

		// //��jphy1,��ʼ�����������м���������
		jphy1.add(jhy, "North");
		jphy1.add(jsp, "Center");
		jphy1.add(jphy3, "South");

		jpmain = new JPanel(new BorderLayout());
		jb = new JButton(new ImageIcon("image/renwu.jpg"));
		jb.setBorderPainted(false);// ��ť�ޱ߿�
		jb.setContentAreaFilled(false);
		jb.addActionListener(this);
		jb.setMargin(new Insets(0, 0, 0, 0));// �ð�ť�水ť�ϵ�ͼ���仯
		jptou.add(jb);

		jpmain.add(jptou, BorderLayout.WEST);
		jpmain.add(jpwenzi, BorderLayout.CENTER);

		jmainfunction = new JPanel(new GridLayout(1, 5, 4, 4));

		jbcomputer = new JButton(new ImageIcon("image/computer.jpg"));
		jbcomputer.addActionListener(this);
		jbemail = new JButton(new ImageIcon("image/email.jpg"));
		jbemail.addActionListener(this);
		jbtext = new JButton(new ImageIcon("image/say.jpg"));
		jbtext.addActionListener(this);
		jbcomputer.setMargin(new Insets(0, 0, 0, 0));// �ð�ť�水ť�ϵ�ͼ���仯
		jbemail.setMargin(new Insets(0, 0, 0, 0));
		jbtext.setMargin(new Insets(0, 0, 0, 0));

		jmainfunction.add(jbcomputer);
		jmainfunction.add(jbemail);
		jmainfunction.add(jbtext);

		jlbname = new JLabel(ownName);
		jtf = new JTextField("���������õģ�����ÿһ�죡");
		jtf.setOpaque(false); // ���ı�������Ϊ͸����
		jtf.setBorder(null);

		jpwenzi.add(jlbname, "North");
		jpwenzi.add(jtf, "Center");
		jpwenzi.add(jmainfunction, "South");

		jp1.add(jpmain, "North");
		jp1.add(jphy1, "Center");

		// ����ڶ��ſ�Ƭ

		jpmsr_jb1 = new JButton("�ҵĺ���");
		jpmsr_jb1.addActionListener(this);
		jpmsr_jb2 = new JButton("����ְԱ");
		jpmsr_jb3 = new JButton("��˾�쵼");
		jpmsr1 = new JPanel(new BorderLayout());
		// �ٶ���100��ְԱ
		jpmsr2 = new JPanel(new GridLayout(100, 1, 4, 4));

		// ��jphy2����ʼ��100İ����.
		JLabel[] jb1s2 = new JLabel[100];

		// ������Ϣ�б����������У���������Ҫ�õ����ݿ�
		// ��isOnLineΪ0ʱ��ʾ�����ߣ�Ϊ1ʱ��ʾ����
		// ����һ������ģ�Ͷ���
		myTable2 = new MyQqModel();
		myTable2.queryQqList(" select * from qqList");

		for (int i = 0; i < myTable2.getRowCount(); i++) {

			// myTable.getValueAt(i, 1)�ǵõ����ѵ��ǳ�
			jb1s2[i] = new JLabel(myTable2.getValueAt(i, 4) + "",
					new ImageIcon("image/mm.jpg"), JLabel.LEFT);

			ManageQqFriendList.addWhereFriendPut(myTable2.getValueAt(i, 2), i);
			jb1s2[i].setEnabled(false);
			if (myTable2.getValueAt(i, 4).equals("1")) {
				jb1s2[i].setEnabled(true);

			}
			jb1s2[i].addMouseListener(this);
			jpmsr2.add(jb1s2[i]);

		}

		jpmsr3 = new JPanel(new GridLayout(2, 1));
		// ��������ť���뵽jphy3
		jpmsr3.add(jpmsr_jb1);
		jpmsr3.add(jpmsr_jb2);

		jsp2 = new JScrollPane(jpmsr2);

		// ��jphy1,��ʼ��
		jpmsr1.add(jpmsr3, "North");
		jpmsr1.add(jsp2, "Center");
		jpmsr1.add(jpmsr_jb3, "South");

		// ��������ſ�Ƭ����ʾ�������б�
		jphmd1_jb1 = new JButton("�ҵĺ���");
		jphmd1_jb1.addActionListener(this);
		jphmd1_jb2 = new JButton("����ְԱ");
		jphmd1_jb2.addActionListener(this);
		jphmd1_jb3 = new JButton("��˾�쵼");
		jphmd1_jb3.addActionListener(this);
		jphmd1 = new JPanel(new BorderLayout());

		jphmd2 = new JPanel(new GridLayout(10, 1, 4, 4));
		JLabel[] jlbs3 = new JLabel[10];
		for (int i = 0; i < jlbs3.length; i++) {
			jlbs3[i] = new JLabel(i + 1 + "", new ImageIcon("image/qq.gif"),
					JLabel.LEFT);
			jlbs3[i].addMouseListener(this);
			jphmd2.add(jlbs3[i]);
		}

		jphmd3 = new JPanel(new GridLayout(2, 1));
		jphmd3.add(jphmd1_jb1);
		jphmd3.add(jphmd1_jb2);

		jsp3 = new JScrollPane(jphmd2);

		jphmd1.add(jphmd3, "North");
		jphmd1.add(jphmd1_jb3, "Center");
		jphmd1.add(jsp3, "South");

		cl = new CardLayout();
		this.setLayout(cl);
		this.add(jp1, "1");
		this.add(jpmsr1, "2");
		this.setIconImage((new ImageIcon("image/qq.gif").getImage()));
		// �ڴ�����ʾ�Լ��ı��.
		this.setTitle(owner);
		this.setSize(314, 696);
		this.setVisible(true);

		// ���ϽǺ�ɫ���رհ�ť���¼�
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// �˳�ʱ����ѵ�¼��Ϣ
				SqlHelper sl = new SqlHelper();
				String[] paras = { "0", ownId };
				sl.updateExec("update qqList set isOnLine =? where qqId=? ",
						paras);
				sl.updateExec(
						"update qqFriendList set isOnLine =? where frienId=?",
						paras);
				System.exit(0);
			}
		});
	}

	private int count = 0;// ���ڼ�¼�û����ͷ��Ĵ���

	public void actionPerformed(ActionEvent e) {

		// ��������İ���˰�ť������ʾ�ڶ��ſ�Ƭ
		if (e.getSource() == jphy_jb1)
			cl.show(this.getContentPane(), "1");
		if (e.getSource() == jphy_jb2)
			cl.show(this.getContentPane(), "2");
		if (e.getSource() == jphy_jb3)
			cl.show(this.getContentPane(), "3");
		// �û����ͷ����Ůͷ����仯
		if (e.getSource() == jb) {
			count++;
			if (count % 2 == 1) {
				jb.setIcon(new ImageIcon("image/renwunv.jpg"));
			} else {
				jb.setIcon(new ImageIcon("image/renwu.jpg"));
			}
		}
		// ��������������
		if (e.getSource() == jbcomputer) {
			Calculator calculator1 = new Calculator();
			calculator1.setVisible(true);
			calculator1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
		// �����������
		if (e.getSource() == jbemail) {

		}
		// �������԰����
		if (e.getSource() == jbtext) {
			new Liuyb(ownId, ownName1);
		}

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

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		JLabel jl = (JLabel) arg0.getSource();
		jl.setForeground(Color.red);
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		JLabel jl = (JLabel) arg0.getSource();
		jl.setForeground(Color.black);
	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
