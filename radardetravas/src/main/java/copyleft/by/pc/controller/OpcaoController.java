package copyleft.by.pc.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import copyleft.by.pc.model.Ativo;
import copyleft.by.pc.model.Opcao;

@Controller
public class OpcaoController {
	private static final String OPTIONS_URL = "http://www.bmfbovespa.com.br/opcoes/opcoes.aspx?idioma=pt-br";
	private static final String QUOTES_URL = "http://www.bmfbovespa.com.br/Pregao-Online/ExecutaAcaoAjax.asp?CodigoPapel=";

	private static final Logger log = Logger.getLogger(OpcaoController.class.getName());
	
	private static List<Ativo> ativos = null;

    @RequestMapping(value = "/showOptions", method = RequestMethod.GET)
    @ResponseBody
	public static  List<Ativo> showOptions() {
    	return ativos;
    }
	
    @RequestMapping(value = "/updateOptions", method = RequestMethod.GET)
    @ResponseBody
	public static String loadOptions() {
    	ativos = null;
		BasicCookieStore cookieStore = new BasicCookieStore();

		CloseableHttpClient httpclient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
		try {
			HttpGet httpget = new HttpGet(OPTIONS_URL);

			httpget.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:26.0) Gecko/20100101 Firefox/26.0");
			httpget.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			httpget.addHeader("Accept-Language", "pt-BR,pt;q=0.8,en-US;q=0.5,en;q=0.3");
			httpget.addHeader("Accept-Encoding", "gzip, deflate");

			CloseableHttpResponse response1 = httpclient.execute(httpget);
			try {
				HttpEntity entity = response1.getEntity();

				HttpPost httpost = new HttpPost(OPTIONS_URL);

				httpost.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:26.0) Gecko/20100101 Firefox/26.0");
				httpost.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
				httpost.addHeader("Accept-Language", "pt-BR,pt;q=0.8,en-US;q=0.5,en;q=0.3");
				httpost.addHeader("Accept-Encoding", "gzip, deflate");
				httpost.addHeader("Referer", OPTIONS_URL);

				List<NameValuePair> postParams = getFormParams(entity.getContent());

				EntityUtils.consume(entity);

				httpost.setEntity(new UrlEncodedFormEntity(postParams, Consts.UTF_8));

				response1 = httpclient.execute(httpost);
				entity = response1.getEntity();

				System.out.println("content-type: " + entity.getContentType().toString());
				System.out.println("content-length: " + entity.getContentLength());

				formatStringFile(entity.getContent());
				
				if(ativos != null && ativos.size() > 0) {
					updateOptions(ativos);
				}

				EntityUtils.consume(entity);
				
			} catch (Exception e) {
				log.log(Level.SEVERE, "Erro no controller ao recuperar o arquivo das opcoes.",e);
			} finally {
				response1.close();
			}

		} catch (Exception e) {
			log.log(Level.SEVERE, "Erro no controller ao recuperar o arquivo das opcoes.",e);
		} finally {
			try {
				httpclient.close();
			} catch (Exception e) {}
		}
		
		return "OK";
	}

	private static void updateOptions(List<Ativo> opcoes) {
		
	}

	private static void formatStringFile(InputStream content) throws IOException {
		ativos = new ArrayList<Ativo>();
		
		StringWriter writer = new StringWriter();
		IOUtils.copy(content, writer, Charsets.UTF_8);
		String fullFile = writer.toString();

		String[] lines = fullFile.split("\n");
		Date now = new Date();
		for(String linha : lines) {
			if(linha.startsWith("01")) {
				String tipo = Ativo.getTipoAtivo(linha.substring(14, 17).trim());
				if(tipo != null) {
					Ativo ativo = new Ativo(linha.substring(02,07).trim(), tipo, now);
					if(!ativos.contains(ativo))
						ativos.add(ativo);
					
					Opcao opcao = new Opcao(linha, now, ativo.getCodigoAtivo());
					ativos.get(ativos.indexOf(ativo)).addOpcao(opcao);
				}
			}
		}
	}

	private static List<NameValuePair> getFormParams(InputStream content) throws IOException {

		BufferedReader rd = new BufferedReader(new InputStreamReader(content));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		Document doc = Jsoup.parse(result.toString());

		Element loginform = doc.getElementById("aspnetForm");
		Elements inputElements = loginform.getElementsByTag("input");

		List<NameValuePair> paramList = new ArrayList<NameValuePair>();

		for (Element inputElement : inputElements) {
			String key = inputElement.attr("name");
			String value = inputElement.attr("value");

			if ("ctl00$ucTopo$btnPesquisa".equals(key))
				continue;
			if ("ctl00$contentPlaceHolderConteudo$posicoesAbertoEmp$".equals(key))
				continue;
			if ("ctl00$contentPlaceHolderConteudo$posicoesAbertoEmp$btnBuscarEmpresa".equals(key))
				continue;
			if ("ctl00$botaoNavegacaoVoltar".equals(key))
				continue;

			if (!StringUtil.isBlank(key))
				paramList.add(new BasicNameValuePair(key, value));

		}

		paramList.add(new BasicNameValuePair("ctl00$contentPlaceHolderConteudo$posicoesAbertoEmp$", "rbTodos"));
		paramList.add(new BasicNameValuePair("cboAgentesCorretorasNome", "#"));
		paramList.add(new BasicNameValuePair("cboAgentesCorretorasCodigo", "#"));

		return paramList;

	}
	
	private void updateQuotes(Ativo ativo) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet httpget = new HttpGet(QUOTES_URL + ativo.getCodes());

			httpget.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:26.0) Gecko/20100101 Firefox/26.0");
			httpget.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			httpget.addHeader("Accept-Language", "pt-BR,pt;q=0.8,en-US;q=0.5,en;q=0.3");
			httpget.addHeader("Accept-Encoding", "gzip, deflate");

			CloseableHttpResponse response1 = httpclient.execute(httpget);
			try {
				HttpEntity entity = response1.getEntity();

				BufferedReader rd = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));

				StringBuffer result = new StringBuffer();
				String line = "";
				while ((line = rd.readLine()) != null) {
					result.append(line);
				}
				EntityUtils.consume(entity);
				
				Document doc = Jsoup.parse(result.toString());
				
				for(Element cotacao : doc.select("Papel")) {
					String codigo = cotacao.attr("Codigo");
					String nome = cotacao.attr("Nome");
					String data = cotacao.attr("Data");
					String abertura = cotacao.attr("Abertura");
					String minimo = cotacao.attr("Minimo");
					String maximo = cotacao.attr("Maximo");
					String medio = cotacao.attr("Medio");
					String ultimo = cotacao.attr("Ultimo");
					String oscilacao = cotacao.attr("Oscilacao");
					
					System.out.println("codigo: " + codigo + " | " +
							"nome: " + nome + " | " +
							"data: " + data + " | " +
							"abertura: " + abertura + " | " +
							"minimo: " + minimo + " | " +
							"maximo: " + maximo + " | " +
							"medio: " + medio + " | " +
							"ultimo: " + ultimo + " | " +
							"oscilacao: " + oscilacao + " | ");
				}

				
		

				
			} catch (Exception e) {
				log.log(Level.SEVERE, "Erro no controller ao recuperar as cotacoes.",e);
			} finally {
				response1.close();
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "Erro no controller ao recuperar as cotacoes.",e);
		} finally {
			try {
				httpclient.close();
			} catch (Exception e) {}
		}
		
	}

	public static void main(String[] args) {
		Ativo ativo = new Ativo("BBAS","3",new Date());
		OpcaoController controller = new OpcaoController();
		controller.updateQuotes(ativo);
	}
}
