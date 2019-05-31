package com.jing.cloud.module;

public abstract class MessageCode {
	
	public final static int HEARTBEAT = 0; 	//心跳通信
	
	public final static int REGISTER = 1;	//注册
	
	public final static int CONNECTION = 2;	//连接目标设备
	
	public final static int CONNECTION_SUCCESS = 7; //建立连接结果
	
	public final static int CONNECTION_ERROR = 8;
	
	public final static int TRANSFER_DATA = 3; //流量转发
	
	public final static int CLOSE_CONNECTION = 4; //断开与目标设备的连接
	
	public final static int REGISTER_ERROR = 5; //注册失败
	
	public final static int REGISTER_SUCCESS = 6; //注册成功
	
	public final static int START_TCP = 9; //开始数据传输
	
	public final static int SCAN_OS = 10;//探测主机并获取操作系统
	
	public final static int SCAN_OS_RESULT = 11;//探测主机结果
	
	public final static int SAVE_TOKEN_CLIENT = 12;//标记token对应的代理客户端
}
