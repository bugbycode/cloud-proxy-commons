package com.jing.cloud.module;

import java.util.LinkedList;

import org.json.JSONArray;

public class ScanHostResult {
	
	private LinkedList<HostInfo> list;
	
	public ScanHostResult() {
		this.list = new LinkedList<HostInfo>();
	}
	
	public void push(HostInfo host) {
		list.addFirst(host);
	}
	
	public HostInfo pop() {
		return list.removeFirst();
	}
	
	public int length() {
		return list.size();
	}
	
	public HostInfo get(int index) {
		return list.get(index);
	}
	
	public boolean isEmpty() {
		return list == null || list.isEmpty();
	}
	
	public boolean isNotEmpty() {
		return !isEmpty();
	}
	
	@Override
	public String toString() {
		JSONArray arr = new JSONArray(list);
		return arr.toString();
	}
	
}
