/**
 * 这是一个专门处理用户信息的包，根据不同情况设定不同的信息类型，
 * 以便于服务器通过该信息类型做相应的处理
 */
package com.qq.server.model;

import com.qq.common.RegistUser;
import com.qq.common.User;

public class QqServerUser {
	public String type;
	MyQqModel mymodel;
	

	//更新数据库中用户的在线信息
	public void updateIsOnLine(String qqId){
		
		//修改qqList与qqFriendList两个表中用户在线信息
		mymodel=new MyQqModel();
		String []paras={"1",qqId};
		mymodel.updateQqIsOn("update qqList set isOnLine=?  where qqId=? ", paras);
		mymodel.updateQqIsOn("update qqFriendList  set isOnLine=? where frienId=?", paras);
	}

   //判断是否登录成功
	public String isLoginOk(User o) {

		User u =  o;
		// 因为把对表的数据封装到MyTable中，我们就可以比较简单的完成查询任务
		String qqId = u.getUserId();
		String passwd = u.getPasswd();

		// 写一个sql语句
		String sql = "select * from qqList where qqId=? and  qqPasswd=?";
		String[] paras = { qqId, passwd };
		// 构建新的数据模型类，并更新
		mymodel = new MyQqModel();
	   mymodel.queryQq(sql, paras);
	   if(mymodel.getRowCount()==0){
		   type="2";
	   }else type="1";
       return type;
		
	}
	
	//判断注册是否成功，成功返回10，失败返回11
	public String isRegistOk( RegistUser  o){
		RegistUser u = o;
		// 因为把对表的数据封装到MyTable中，我们就可以比较简单的完成查询任务
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
			//提示
			type="11";
		}
		type="10";
		return type;
	}
	
}
