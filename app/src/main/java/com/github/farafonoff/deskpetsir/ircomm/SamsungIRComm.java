package com.github.farafonoff.deskpetsir.ircomm;

import android.content.Context;
import android.text.TextUtils;

import java.lang.reflect.Method;

public class SamsungIRComm implements IIRComm {

	private Object irService;
	private Class irClass;
	private Method sendIR;
	private Method readIR;
	
	public SamsungIRComm(Context context) {
		try{
			irService = context.getSystemService("irda");
	    	irService.getClass();
	    	irClass = irService.getClass();
		
			readIR = irClass.getMethod("read_irsend");
			sendIR = irClass.getMethod("write_irsend", new Class[]{String.class});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendIRCode(String irCode) {
		try {
			sendIR.invoke(irService, irCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	
	public String readIRCode() {
		try {
			return readIR.invoke(irService).toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void sendIRCode(int frequency, int[] command) {
		StringBuilder sb = new StringBuilder();
		sb.append(frequency);
		for(int num: command) {
			sb.append(",").append(num);
		}
		sendIRCode(sb.toString());
	}
}
