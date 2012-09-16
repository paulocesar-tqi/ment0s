package br.com.sky;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.sky.web.HttpData;

public class VoteEntry {
	
	private Map<String,Cookie> mapCookies;
	private HttpData dataImg = null;
	
	public VoteEntry(HttpData dataImg) {
		mapCookies = new HashMap<String, Cookie>();
		this.dataImg = dataImg;
	}
	
	public void setCookies(List<String> value) {
		for (String cookie : value) {
			int idx = cookie.indexOf("=");
			String cKey = null;
			String cValue = null;

			Cookie c = new Cookie();
			if (idx < 0) {
				cKey = cookie;
			} else {
				cKey = cookie.substring(0, idx);				
				cValue = cookie.substring(idx+1);
			}
			c.setKey(cKey);
			c.parseValue(cValue);
			
			mapCookies.put(cKey.toUpperCase(), c);
		}
	}
	
	public Cookie getCookie(String key) {
		return this.mapCookies.get(key.toUpperCase());
	}
	
	public List<Cookie> getCookies() {
		List<Cookie> lstC = new ArrayList<Cookie>();
		for (Cookie cookie : mapCookies.values()) {
			lstC.add(cookie);
		}
		return lstC;
	}
	
	public HttpData getDataImg() {
		return this.dataImg;
	}
}
