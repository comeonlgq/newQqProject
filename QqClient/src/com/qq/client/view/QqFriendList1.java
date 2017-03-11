/**
 * 我的好友列表,(也包括陌生人，黑名单)
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

	// 处理第一张卡片.

	// jphy1表示的是最大的那个面板，
	// jphy2表示的是中间放有用户的信息面板
	// jphy3表示的是最下面的放有陌生人与黑名单两个按钮的面板
	JTabbedPane jtp1;
	JPanel jphy1, jphy2, jphy3;
	JButton jphy_jb1, jphy_jb2, jphy_jb3;
	String owner;


	MyQqModel myTable, myTable1;

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
		// TODO Auto-generated method stub
		 QqFriendList1 qqFriendList = new QqFriendList1("1");
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
			e.printStackTrace();
		}

		
	}


	public QqFriendList1(String ownerId) {
		this.owner = ownerId;
		jphy1 = new JPanel(new GridLayout(10,1,4,4));
		// 处理第一张卡片(显示好友列表)
		jphy_jb1 = new JButton("我的好友");
		jphy_jb1.setPreferredSize(new Dimension(100,24));
        
		jphy_jb1.setContentAreaFilled(false);  //只须加上此句
		jphy_jb2 = new JButton("陌生人");
		jphy_jb2.setSize(108, 27);
		jphy_jb2.setContentAreaFilled(false);  //只须加上此句
		jphy_jb2.addActionListener(this);
		jphy_jb3 = new JButton("黑名单");
		jphy_jb1.setPreferredSize(new Dimension(100,24));
		jphy_jb3.setContentAreaFilled(false);  //只须加上此句
		

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
					"image/mm.jpg"), SwingConstants.LEFT);
			
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

		// 这是最下面的两个按钮组成的面板
		jphy3 = new JPanel(new GridLayout(2, 1));
		// 把两个按钮加入到jphy3
		jphy3.add(jphy_jb2);
		jphy3.add(jphy_jb3);

		// //对jphy1,初始化，向大面板中加入各个组件
		jphy1.add(jphy_jb1);
		jphy1.add(jsp);
		jphy1.add(jphy3);

		// 处理第二张卡片

		jpmsr_jb1 = new JButton("我的好友");
		jpmsr_jb1.addActionListener(this);
		jpmsr_jb2 = new JButton("陌生人");
		jpmsr_jb3 = new JButton("黑名单");
		jpmsr1 = new JPanel(new GridLayout(10,1,4,4));
		// 假定有20个陌生人
		jpmsr2 = new JPanel(new GridLayout(20, 1, 4, 4));

		// 给jphy2，初始化20陌生人.
		JLabel[] jb1s2 = new JLabel[20];

		for (int i = 0; i < jb1s2.length; i++) {
			jb1s2[i] = new JLabel(i + 1 + "", new ImageIcon("image/mm.jpg"),
					SwingConstants.LEFT);
			jpmsr2.add(jb1s2[i]);
		}

		jpmsr3 = new JPanel(new GridLayout(2, 1));
		// 把两个按钮加入到jphy3
		jpmsr3.add(jpmsr_jb1);
		jpmsr3.add(jpmsr_jb2);

		jsp2 = new JScrollPane(jpmsr2);

		// 对jphy1,初始化
		jpmsr1.add(jpmsr3);
		jpmsr1.add(jsp2);
		jpmsr1.add(jpmsr_jb3);

		// 处理第三张卡片，显示黑名单列表
		jphmd1_jb1 = new JButton("我的好友");
		jphmd1_jb1.addActionListener(this);
		jphmd1_jb2 = new JButton("陌生人");
		jphmd1_jb2.addActionListener(this);
		jphmd1_jb3 = new JButton("黑名单");
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
		// 在窗口显示自己的编号.
		this.setTitle(owner);
		this.setSize(281, 696);
		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// 如果点击了陌生人按钮，就显示第二张卡片
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
