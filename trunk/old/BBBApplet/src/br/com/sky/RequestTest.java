package br.com.sky;

import java.util.ArrayList;
import java.util.List;

import br.com.sky.web.CaptchaLoader;
import br.com.sky.web.HttpData;
import br.com.sky.web.HttpRequestPoster;

public class RequestTest {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		//CaptchaLoader cLoader = new CaptchaLoader();
		
		//String urlImg2 = Configuracao.URL_CAPTCHA + "123";
		//HttpData dataImg = cLoader.load(urlImg2, Configuracao.URL_REFERRER);
		String data = "teste=123";
		String url = "http://paulocesar.tk/BBBSite/Default.aspx";
		//String cookies = "c=1;c2=3";
		List<String> cookies = new ArrayList<String>();
		cookies.add("c1=3");
		cookies.add("c2=355");
		String urlRef = "http://bbb.globo.com.br/teste.html";
		HttpData dataOut = HttpRequestPoster
				.postData(url, data, cookies, null, urlRef);
		String resp = new String(dataOut.getData());
		System.out.println(resp);
		
		CaptchaLoader loader = new CaptchaLoader();
		String urlImg = "http://webspace.webring.com/people/be/eternity500/Swing.gif";
		loader.load(urlImg, null);
	}

}
