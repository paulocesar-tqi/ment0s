package br.com.sky.web;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SkyCookieHandler extends CookieHandler {

	private static Map<String, List<String>> cookies = new HashMap<String,List<String>>();
	private static SkyCookieHandler instance = new SkyCookieHandler();
	
	public static SkyCookieHandler getInstance() {
		return instance;
	}
	
	@Override
	public Map<String, List<String>> get(URI uri,
			Map<String, List<String>> requestHeaders) throws IOException {

		Map<String, List<String>> cookieMap = new HashMap<String, List<String>>(
				requestHeaders);

		String path = uri.toURL().toString();
		List<String> originalWord = requestHeaders.get("OriginalWord");
		if (originalWord != null) {
			path = originalWord.get(0);
		}
		
		List<String> lst = cookies.get(path);
		
		if (lst != null && lst.size() > 0) {
			cookieMap.put("Cookie", lst);
			cookieMap.put("Set-Cookie", lst);
			
		}
		//System.out.println(this + " GET-cookie " + uri.toString() + " - " + lst + " - " + path);
		return Collections.unmodifiableMap(cookieMap);
	}

	@Override
	public void put(URI uri, Map<String, List<String>> responseHeaders)
			throws IOException {
		List<String> setCookieList = responseHeaders.get("Set-Cookie");
		//System.out.println(this + " SET-cookie " + uri.toString() + " - " + setCookieList);
		if (setCookieList != null) {
			cookies.put(uri.toString(), setCookieList);
		}
	}

}
