package br.com.sky;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class Configuracao {

	public static final String URL_REFERRER = "http://bbb.globo.com/votacao/18406.html";
	public static final String URL_SUCESSO = "http://bbb.globo.com/votacao/18406.html#sucesso";
	public static final String URL_ERRO = "http://bbb.globo.com/votacao/18406.html#erro";
	public static final String URL_BLOCK = "http://bbb.globo.com/votacao/18406.html#block";
	public static final String URL_CAPTCHA = "http://votacao.globo.com/rsvotacao/word.jpg?_=0.";
	public static final int PARAM_VOTACAO = 18406;
	public static final String URL_TRANSACAO = "http://votacao.globo.com/rsvotacao";
	public static final String URL_VOTACAO = "http://votacao.globo.com/rsvotacao/vote";
	
	public static final int IMG_WIDTH = 207;
	public static final int IMG_HEIGTH = 45;
	
	public static final int QTD_VOTOS_OK = 20;
	public static final String URL_VOTE_APPENDER = "http://paulocesar.tk/BBBSite/VoteAppender.aspx";
	
	private static int POOL_ID = 3258;
	private static String OPT = "1";
	private static boolean ENABLED = false;
	private static String TITULO = "";
	private static boolean DEBUG = false;

	public static void load(URL pageBase) throws MalformedURLException, IOException {
		URL configFile = new URL(pageBase, "config.properties");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(
				configFile.openStream()));

		Properties prop = new Properties();
		prop.load(br);

		br.close();
		
		POOL_ID = Integer.parseInt(prop.getProperty("POOL_ID", "3258"));
		OPT = prop.getProperty("OPT", "1");
		ENABLED = Boolean.parseBoolean(prop.getProperty("ENABLED", "false"));
		TITULO = prop.getProperty("MSG_TITULO", "BBB");
		DEBUG = Boolean.parseBoolean(System.getProperty("DEBUG_BBB", "false"));
	}
	
	public static boolean isEnabled() {
		return ENABLED;
	}
	
	public static String getTitulo() {
		return TITULO;
	}
	
	public static String getOpt() {
		return OPT;
	}
	
	public static int getPoolId() {
		return POOL_ID;
	}
	
	public static boolean isDebug() {
		return DEBUG;
	}
}
