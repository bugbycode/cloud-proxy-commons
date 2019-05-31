package com.jing.cloud.handler;

import com.jing.cloud.module.Authentication;
import com.jing.cloud.module.ConnectionInfo;
import com.jing.cloud.module.Message;
import com.jing.cloud.module.MessageCode;
import com.jing.cloud.module.ScanHostResult;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MessageEncoder extends MessageToByteEncoder<Message> {

	@Override
	protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {
		String token = msg.getToken();
		//首先发送token
		byte[] token_buf;
		if(token == null) {
			token_buf = new byte[32];
		}else {
			token_buf = token.getBytes();
		}
		out.writeBytes(token_buf);
		//发送消息类型
		int type = msg.getType();
		out.writeInt(type);
		//计算长度
		Object obj = msg.getData();
		byte[] body;
		if(type == MessageCode.REGISTER) {
			Authentication auth = (Authentication) obj;
			String authInfo = auth.toString();
			body = authInfo.getBytes();
		}else if(type == MessageCode.CONNECTION) {
			ConnectionInfo conn = (ConnectionInfo) obj;
			String connInfo = conn.toString();
			body = connInfo.getBytes();
		}else if(type == MessageCode.TRANSFER_DATA) {
			body = (byte[]) obj;
		}else if(type == MessageCode.SCAN_OS || type == MessageCode.SAVE_TOKEN_CLIENT){
			body = obj.toString().getBytes();
		}else if(type == MessageCode.SCAN_OS_RESULT){
			ScanHostResult result = (ScanHostResult) obj;
			body = result.toString().getBytes();
		}else {
			body = new byte[0];
		}
		int length = body.length;
		//发送消息长度和内容
		out.writeInt(length);
		if(length > 0) {
			out.writeBytes(body);
		}
	}

}
