package br.com.sky.web;

import java.util.List;
import java.util.Map;

public class HttpData {

	private String url;
	private byte[] data;
	private Map<String, List<String>> headers;

	public HttpData() {
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public Map<String, List<String>> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, List<String>> headers) {
		this.headers = headers;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
}
