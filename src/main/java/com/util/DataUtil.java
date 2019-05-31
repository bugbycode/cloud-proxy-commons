package com.util;

public class DataUtil {
	
	private byte[] buff = null;
	
	public DataUtil(byte[] buff) {
		this.buff = buff;
	}
	
	public int getLength() {
		return (buff[0] & 0xFF) | ((buff[1] & 0xFF) << 0x08);
	}
	
	public int getType() {
		return buff[2] & 0xFF;
	}
	
	public byte[] getData() {
		int len = buff.length;
		int data_len = len - 0x03;
		byte[] data = new byte[len - 0x03];
		System.arraycopy(buff, 0, data, 0, data_len);
		return data;
	}
}
