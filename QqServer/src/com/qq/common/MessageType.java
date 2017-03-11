/**
 * 定义包的种类
 */
package com.qq.common;

public interface MessageType {
	
	String message_succeed="1";//表明是登陆成功
	String message_login_fail="2";//表明登录失败
	String message_comm_mes="3";//普通信息包
	String message_get_onLineFriend="4";//要求在线好友的包
	String message_ret_onLineFriend="5";//返回在线好友的包
	String message_login_again="6";//重复登录
	String message_exit ="7";//退出
	String message_login="8";//登录请求
	String message_regist="9";//注册
	String message_regist_succeed="10";//注册成功
	String message_regist_fail="11";//注册失败
	String message_search="12";//查询
	String message_search_return_exit ="13";//返回查询到信息
	String message_search_return_notExit="14";//返回没有查询到信息
	String message_addFriend="15";// 添加好友
	String message_addFriend_succeed="16";//添加好友成功
	String message_addFriend_fail="17";//添加好友失败
	String message_exit_confirm="18";//服务器对客户端退出的确认
}
