/**
 * ����һ��ר�Ŵ����û���Ϣ�İ������ݲ�ͬ����趨��ͬ����Ϣ���ͣ�
 * �Ա��ڷ�����ͨ������Ϣ��������Ӧ�Ĵ���
 */
package com.qq.server.model;

import com.qq.common.RegistUser;
import com.qq.common.User;

public class QqServerUser {
	public String type;
	MyQqModel mymodel;
	

	//�������ݿ����û���������Ϣ
	public void updateIsOnLine(String qqId){
		
		//�޸�qqList��qqFriendList���������û�������Ϣ
		mymodel=new MyQqModel();
		String []paras={"1",qqId};
		mymodel.updateQqIsOn("update qqList set isOnLine=?  where qqId=? ", paras);
		mymodel.updateQqIsOn("update qqFriendList  set isOnLine=? where frienId=?", paras);
	}

   //�ж��Ƿ��¼�ɹ�
	public String isLoginOk(User o) {

		User u =  o;
		// ��Ϊ�ѶԱ�����ݷ�װ��MyTable�У����ǾͿ��ԱȽϼ򵥵���ɲ�ѯ����
		String qqId = u.getUserId();
		String passwd = u.getPasswd();

		// дһ��sql���
		String sql = "select * from qqList where qqId=? and  qqPasswd=?";
		String[] paras = { qqId, passwd };
		// �����µ�����ģ���࣬������
		mymodel = new MyQqModel();
	   mymodel.queryQq(sql, paras);
	   if(mymodel.getRowCount()==0){
		   type="2";
	   }else type="1";
       return type;
		
	}
	
	//�ж�ע���Ƿ�ɹ����ɹ�����10��ʧ�ܷ���11
	public String isRegistOk( RegistUser  o){
		RegistUser u = o;
		// ��Ϊ�ѶԱ�����ݷ�װ��MyTable�У����ǾͿ��ԱȽϼ򵥵���ɲ�ѯ����
		String qqId = u.getUserId();
        String qqName=u.getQqName();
        String qqSex=u.getQqSex();
        String qqAge=u.getQqAge();
        String qqRealName=u.getQqRealName();
        String passwd=u.getPasswd();
        
        MyQqModel temp = new MyQqModel();
		String sql = "insert into qqList values(?,?,?,?,?,?,?)";
		String[] paras = {qqId,qqName,qqSex,qqAge,qqRealName,passwd,"0"};
		if(!temp.updateQq(sql, paras))
		{
			//��ʾ
			type="11";
		}
		type="10";
		return type;
	}
	
}
