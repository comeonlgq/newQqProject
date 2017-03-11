/**
 * 我的好友列表,(也包括陌生人，黑名单)
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

	String ownId, ownName1;// 用于存放当前用户的id，昵称

	// 处理第一张卡片.

	// jp1表示第一张卡片最大的面板
	JPanel jp1, jp2, jp3;
	JPanel jpmain, jptou, jpwenzi;// jpmain用户个人信息面板

	JButton jb, jbcomputer, jbemail, jbtext;
	JLabel jlbname;// 用户昵称
	JTextField jtf;// 用户箴言

	JPanel jhy, jhm, jmainfunction;

	// jphy1表示的是最大的那个面板，
	// jphy2表示的是中间放有用户的信息面板
	// jphy3表示的是最下面的放有陌生人与黑名单两个按钮的面板
	JPanel jphy1, jphy2, jphy3;
	JButton jphy_jb1, jphy_jb2, jphy_jb3;
	String owner;

	MyQqModel myTable, myTable1, myTable2;

	// 用户好友列表，根据数据库得到的
	// （QQ好友信息）
	JLabel[] jbls;
	// JTable jt;
	JScrollPane jsp = null;

	// 处理第二张卡片(陌生人).

	JPanel jpmsr1, jpmsr2, jpmsr3;
	JButton jpmsr_jb1, jpmsr_jb2, jpmsr_jb3;
	JScrollPane jsp2;
	JLabel[] jb1s;

	// 处理第三张卡片
	JPanel jphmd1, jphmd2, jphmd3;
	JButton jphmd1_jb1, jphmd1_jb2, jphmd1_jb3;
	JScrollPane jsp3;

	// 把整个JFrame设置成CardLayout
	CardLayout cl;

	public static void main(String[] args) {
		QqFriendList qqFriendList = new QqFriendList("1", "花儿");
	}

	public void updateFriend(Message m) {
		// 查询数据库,看自己的好友是否在线，如果在线就更新一下自己的好友列表
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
		// 处理第一张卡片(显示好友列表)
		jphy_jb1 = new JButton("我的好友");
		jphy_jb1.addActionListener(this);
		jphy_jb1.setBorderPainted(false);
		jphy_jb1.setContentAreaFilled(false);
		jphy_jb2 = new JButton("所有职员");
		jphy_jb2.setBorderPainted(false);
		jphy_jb2.setContentAreaFilled(false);
		jphy_jb2.addActionListener(this);
		jphy_jb3 = new JButton("公司领导");
		jphy_jb3.setBorderPainted(false);
		jphy_jb3.setContentAreaFilled(false);
		jphy_jb3.addActionListener(this);
		jphy1 = new JPanel(new BorderLayout());

		// //给jphy2，初始化50好友.
		jbls = new JLabel[50];

		// 好友信息列表在这个面板中，这个面板需要用到数据库
		// 当isOnLine为0时表示不在线，为1时表示在线
		// 创建一个数据模型对象
		myTable = new MyQqModel();
		String[] paras = { ownerId };
		myTable.queryQq(" select * from qqFriendList where  qqId=? ", paras);

		jphy2 = new JPanel(new GridLayout(50, 1, 4, 4));
		for (int i = 0; i < myTable.getRowCount(); i++) {

			// myTable.getValueAt(i, 1)是得到好友的昵称
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

		// 初始化jsp JScrollPane
		jsp = new JScrollPane(jphy2);

		jptou = new JPanel();
		jpwenzi = new JPanel(new BorderLayout());

		// 这是最下面的两个按钮组成的面板
		jphy3 = new JPanel(new BorderLayout());
		jhm = new JPanel(new GridLayout(2, 1));
		// 把两个按钮加入到jphy3
		jhm.add(jphy_jb2);
		jhm.add(jphy_jb3);

		jphy3.add(jhm, "West");

		jhy = new JPanel(new BorderLayout());
		jhy.add(jphy_jb1, "West");

		// //对jphy1,初始化，向大面板中加入各个组件
		jphy1.add(jhy, "North");
		jphy1.add(jsp, "Center");
		jphy1.add(jphy3, "South");

		jpmain = new JPanel(new BorderLayout());
		jb = new JButton(new ImageIcon("image/renwu.jpg"));
		jb.setBorderPainted(false);// 按钮无边框
		jb.setContentAreaFilled(false);
		jb.addActionListener(this);
		jb.setMargin(new Insets(0, 0, 0, 0));// 让按钮随按钮上的图案变化
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
		jbcomputer.setMargin(new Insets(0, 0, 0, 0));// 让按钮随按钮上的图案变化
		jbemail.setMargin(new Insets(0, 0, 0, 0));
		jbtext.setMargin(new Insets(0, 0, 0, 0));

		jmainfunction.add(jbcomputer);
		jmainfunction.add(jbemail);
		jmainfunction.add(jbtext);

		jlbname = new JLabel(ownName);
		jtf = new JTextField("生活是美好的，加油每一天！");
		jtf.setOpaque(false); // 将文本框设置为透明的
		jtf.setBorder(null);

		jpwenzi.add(jlbname, "North");
		jpwenzi.add(jtf, "Center");
		jpwenzi.add(jmainfunction, "South");

		jp1.add(jpmain, "North");
		jp1.add(jphy1, "Center");

		// 处理第二张卡片

		jpmsr_jb1 = new JButton("我的好友");
		jpmsr_jb1.addActionListener(this);
		jpmsr_jb2 = new JButton("所有职员");
		jpmsr_jb3 = new JButton("公司领导");
		jpmsr1 = new JPanel(new BorderLayout());
		// 假定有100个职员
		jpmsr2 = new JPanel(new GridLayout(100, 1, 4, 4));

		// 给jphy2，初始化100陌生人.
		JLabel[] jb1s2 = new JLabel[100];

		// 好友信息列表在这个面板中，这个面板需要用到数据库
		// 当isOnLine为0时表示不在线，为1时表示在线
		// 创建一个数据模型对象
		myTable2 = new MyQqModel();
		myTable2.queryQqList(" select * from qqList");

		for (int i = 0; i < myTable2.getRowCount(); i++) {

			// myTable.getValueAt(i, 1)是得到好友的昵称
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
		// 把两个按钮加入到jphy3
		jpmsr3.add(jpmsr_jb1);
		jpmsr3.add(jpmsr_jb2);

		jsp2 = new JScrollPane(jpmsr2);

		// 对jphy1,初始化
		jpmsr1.add(jpmsr3, "North");
		jpmsr1.add(jsp2, "Center");
		jpmsr1.add(jpmsr_jb3, "South");

		// 处理第三张卡片，显示黑名单列表
		jphmd1_jb1 = new JButton("我的好友");
		jphmd1_jb1.addActionListener(this);
		jphmd1_jb2 = new JButton("所有职员");
		jphmd1_jb2.addActionListener(this);
		jphmd1_jb3 = new JButton("公司领导");
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
		// 在窗口显示自己的编号.
		this.setTitle(owner);
		this.setSize(314, 696);
		this.setVisible(true);

		// 右上角红色打叉关闭按钮的事件
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// 退出时清空已登录信息
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

	private int count = 0;// 用于记录用户点击头像的次数

	public void actionPerformed(ActionEvent e) {

		// 如果点击了陌生人按钮，就显示第二张卡片
		if (e.getSource() == jphy_jb1)
			cl.show(this.getContentPane(), "1");
		if (e.getSource() == jphy_jb2)
			cl.show(this.getContentPane(), "2");
		if (e.getSource() == jphy_jb3)
			cl.show(this.getContentPane(), "3");
		// 用户点击头像男女头像交替变化
		if (e.getSource() == jb) {
			count++;
			if (count % 2 == 1) {
				jb.setIcon(new ImageIcon("image/renwunv.jpg"));
			} else {
				jb.setIcon(new ImageIcon("image/renwu.jpg"));
			}
		}
		// 弹出计算器界面
		if (e.getSource() == jbcomputer) {
			Calculator calculator1 = new Calculator();
			calculator1.setVisible(true);
			calculator1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
		// 弹出邮箱界面
		if (e.getSource() == jbemail) {

		}
		// 弹出留言板界面
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
		// 响应用户双击的事件，并得到好友的编号.
		// 得到该好友的编号 jb1s[i].getText().equals(ownerId)
		String friendNo = ((JLabel) arg0.getSource()).getText();

		if (arg0.getClickCount() == 2) {
			System.out.println("你希望和 " + friendNo + " 聊天");
			QqChat qqChat = new QqChat(this.owner, friendNo);

			// 把聊天界面加入到管理类
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
