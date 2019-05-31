package com.jing.cloud.module;

import java.io.Serializable;

public abstract class ProtocolCode implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -917352039031427401L;
	
	public static final int SSH = 0;
	
	public static final int RDP = 1;
	
	public static final int VNC = 2;
	
	public static final int X11 = 3;
	
	public static final int SFTP = 4;
	
	public static final int HTTP = 5;
	
	public static final int HTTPS = 6;
	
	public static final int FTP = 7;
	
	public static final int TELNET = 8;

}
