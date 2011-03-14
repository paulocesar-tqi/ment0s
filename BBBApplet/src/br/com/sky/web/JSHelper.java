package br.com.sky.web;

import java.applet.Applet;

import netscape.javascript.JSObject;

public class JSHelper {
	
	private Applet applet = null;
	private JSObject win = null;
	
	public JSHelper(Applet app) {
		applet = app;
		win = JSObject.getWindow(applet);
	}
	
	public int getWidth() {				
		Number width = (Number)win.eval("$(document).width()");		
		System.out.println("Width: " + width);
		return width.intValue();
	}
	
	public int getHeight() {		
		Number h = (Number)win.eval("$(document).height()");
		System.out.println("Height: " + h);
		return h.intValue();
	}
	
	public String getCookie(String cookieId) {
		return null;
	}
}
