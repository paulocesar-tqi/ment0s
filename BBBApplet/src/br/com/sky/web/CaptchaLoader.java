package br.com.sky.web;


public class CaptchaLoader {

	
	
	public HttpData load(String url, String referrer) throws Exception {
		HttpData data = HttpRequestPoster.getData(url, null, referrer);
		return data;
	}
}
