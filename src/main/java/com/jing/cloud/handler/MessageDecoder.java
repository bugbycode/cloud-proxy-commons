package com.jing.cloud.handler;


import org.json.JSONArray;
import org.json.JSONObject;

import com.jing.cloud.module.Authentication;
import com.jing.cloud.module.ConnectionInfo;
import com.jing.cloud.module.HostInfo;
import com.jing.cloud.module.Message;
import com.jing.cloud.module.MessageCode;
import com.jing.cloud.module.ScanHostResult;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

public class MessageDecoder extends LengthFieldBasedFrameDecoder {

	private final int HEADER_SIZE = 40;
	
	public MessageDecoder(int maxFrameLength, 
			int lengthFieldOffset, 
			int lengthFieldLength, 
			int lengthAdjustment,
			int initialBytesToStrip) {
		super(maxFrameLength, lengthFieldOffset, lengthFieldLength, 
				lengthAdjustment, initialBytesToStrip);
	}

	@Override
	protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
		
		in = (ByteBuf) super.decode(ctx,in);  
		
		if(in == null){
            return null;
        }
		
		if(in.readableBytes() < HEADER_SIZE){
            throw new Exception("头部信息长度错误");
        }
		//读取token信息 总共32个字节
		byte[] token_buff = new byte[32];
		in.readBytes(token_buff);
		//读取消息类型总共4字节
		int type = in.readInt();
		//读取长度 总共4个字节
		int length = in.readInt();
		
		if(in.readableBytes() != length) {
			 throw new Exception("标记的长度不符合实际长度");
		}
		byte[] data = {};
		if(length > 0) {
			data = new byte[length];
			in.readBytes(data);
		}
		
		Message message = new Message();
		
		if(!(type == MessageCode.HEARTBEAT || type == MessageCode.REGISTER ||
				type == MessageCode.REGISTER_ERROR || type == MessageCode.REGISTER_SUCCESS)) {
			String token = new String(token_buff);
			message.setToken(token);
		}
		
		message.setType(type);
		
		//以下是消息内容
		if(type == MessageCode.REGISTER) {
			String authInfo = new String(data);
			
			JSONObject json = new JSONObject(authInfo);
			Authentication auth = new Authentication(json.getString("secret"), json.getString("clientId"));
			message.setData(auth);
		}else if(type == MessageCode.CONNECTION) {
			String connInfo = new String(data);
			
			JSONObject json = new JSONObject(connInfo);
			
			ConnectionInfo conn = new ConnectionInfo(json.getString("host"), json.getInt("port"));
			message.setData(conn);
		}else if(type == MessageCode.TRANSFER_DATA) {
			message.setData(data);
		}else if(type == MessageCode.SCAN_OS || type == MessageCode.SAVE_TOKEN_CLIENT) {
			String host = new String(data);
			message.setData(host);
		}else if(type == MessageCode.SCAN_OS_RESULT) {
			String result = new String(data);
			JSONArray arr = new JSONArray(result);
			int len = arr.length();
			ScanHostResult hr = new ScanHostResult();
			if(len > 0) {
				for(int index = 0;index < len;index++) {
					String jsonStr = arr.getString(index);
					JSONObject json = new JSONObject(jsonStr);
					HostInfo host = new HostInfo(json.getString("ip"), json.getString("os"));
					hr.push(host);
				}
			}
			message.setData(hr);
		}
		
		in.release();
		return message;
	}

	

}
