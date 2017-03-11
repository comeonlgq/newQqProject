/**
 * ����qq�û���Ϣ�༭�Ľ���
 */
package com.qq.server.tools;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.qq.server.model.MyQqModel;

public class EditQq extends JDialog implements ActionListener  {

	// ����һЩ�ؼ�
		JPanel jp1, jp2, jp3;
		JLabel jl1, jl2, jl3, jl4, jl5, jl6;
		JTextField jtf1, jtf2, jtf3, jtf4, jtf5, jtf6;
		JButton jp3_jb1, jp3_jb2;

		// ���幹�캯�����г�ʼ��
		// ownerָ���ĸ�����
		// title ������
		// model ָ����ģ̬���ڻ��Ƿ�ģ̬����
		public EditQq(Frame owner, String title, boolean model,MyQqModel mt,int rowNum) {
			// ���ø���Ĺ��췽�����ﵽģʽ�Ի���Ч��
			super(owner, title, model);
			// ���山�������
			jp1 = new JPanel(new GridLayout(6, 1));

			jl1 = new JLabel("qq����:");
			jl2 = new JLabel("�ǳ�:");
			jl3 = new JLabel("�Ա�:");
			jl4 = new JLabel("����:");
			jl5 = new JLabel("��ʵ����:");
			jl6 = new JLabel("qq����:");

			// �������������
			jp1.add(jl1);
			jp1.add(jl2);
			jp1.add(jl3);
			jp1.add(jl4);
			jp1.add(jl5);
			jp1.add(jl6);

			// ���屣��ȡ���İ�ť��壬�ϲ����
			jp2 = new JPanel(new GridLayout(6, 1));
			//��ʼ������
			jtf1=new JTextField();
			jtf1.setText((String)mt.getValueAt(rowNum, 0));
			jtf1.setEditable(false);
			jtf2=new JTextField();
			jtf2.setText((String)mt.getValueAt(rowNum, 1));
			jtf3=new JTextField();
			jtf3.setText((String)mt.getValueAt(rowNum, 2)); 
			jtf4=new JTextField();
			jtf4.setText(mt.getValueAt(rowNum, 3).toString()); 
			jtf5=new JTextField();
			jtf5.setText((String)mt.getValueAt(rowNum, 4)); 
			jtf6=new JTextField();
			jtf6.setText((String)mt.getValueAt(rowNum, 5));

			jp2.add(jtf1);
			jp2.add(jtf2);
			jp2.add(jtf3);
			jp2.add(jtf4);
			jp2.add(jtf5);
			jp2.add(jtf6);

			jp3 = new JPanel();
			jp3_jb1 = new JButton("�޸�");
			jp3_jb1.addActionListener(this);
			jp3_jb2 = new JButton("ȡ��");
			jp3_jb2.addActionListener(this);

			jp3.add(jp3_jb1);
			jp3.add(jp3_jb2);

			this.add(jp1, BorderLayout.WEST);
			this.add(jp2, BorderLayout.CENTER);
			this.add(jp3, BorderLayout.SOUTH);
			this.setSize(350, 300);
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			this.setVisible(true);
		}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == jp3_jb1) {// ���û�������水ť
			// ����������ݱ�������
			
		//����������
			String sql = "update qqList set qqName=?," +
					"qqSex=?,qqAge=?,qqRealName=?,qqPasswd =? ,isOnLine =? where qqId=?";
			String b="0";
			String[] paras = {   jtf2.getText(), jtf3.getText(),
					jtf4.getText(), jtf5.getText(), jtf6.getText() ,b,jtf1.getText()};
			MyQqModel temp = new MyQqModel();
			if(!temp.updateQq(sql, paras))
			{
				//��ʾ
				JOptionPane.showMessageDialog(this,"�޸�ʧ��");
			}
			//�رնԻ���
			this.dispose();

			// �������������ӵ���������ȥ

		} else {// ���û����ȡ����ťʱ
			//�رնԻ���
			this.dispose();

		}
	}

}
