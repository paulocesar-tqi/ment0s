package copyleft.by.pc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@SuppressWarnings("serial")
public class RadardetravasServlet extends HttpServlet {
	private static String OPTIONS_URL = "http://www.bmfbovespa.com.br/opcoes/opcoes.aspx?idioma=pt-br";

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
	}

	// private static String getOptionsFile() {
	// String result = null;
	//
	// HttpClient httpClient = new HttpClient();
	// PostMethod postMethod = new PostMethod(OPTIONS_URL);
	//
	// postMethod.addParameter("ctl00$ucTopo$btnBusca","Busca");
	// postMethod.addParameter("ctl00$menuBOVESPASecundario","");
	// postMethod.addParameter("ctl00$contentPlaceHolderConteudo$tabOpcoes","{\"State\":{},\"TabState\":{\"ctl00_contentPlaceHolderConteudo_tabOpcoes_tabPosicoesAberto\":{\"Selected\":true},\"ctl00_contentPlaceHolderConteudo_tabOpcoes_tabPosicoesAberto_tabOpcoesEmp\":{\"Selected\":true},\"ctl00_contentPlaceHolderConteudo_tabOpcoes_tabSeriesAutorizadas_tabSeriesAutEmp\":{\"Selected\":true}}}");
	// postMethod.addParameter("ctl00$contentPlaceHolderConteudo$posicoesAbertoEmp$","rbTodos");
	// postMethod.addParameter("ctl00$contentPlaceHolderConteudo$posicoesAbertoEmp$cmbVcto","0");
	// postMethod.addParameter("ctl00$contentPlaceHolderConteudo$posicoesAbertoEmp$txtConsultaData$txtConsultaData","2013-12-30");
	// postMethod.addParameter("ctl00$contentPlaceHolderConteudo$posicoesAbertoEmp$txtConsultaData$txtConsultaData$dateInput","2013-12-30-00-00-00");
	// postMethod.addParameter("ctl00_contentPlaceHolderConteudo_posicoesAbertoEmp_txtConsultaData_txtConsultaData_calendar_SD","[]");
	// postMethod.addParameter("ctl00_contentPlaceHolderConteudo_posicoesAbertoEmp_txtConsultaData_txtConsultaData_calendar_AD","[[2013,11,4],[2013,12,30],[2013,12,30]]");
	// postMethod.addParameter("ctl00$contentPlaceHolderConteudo$posicoesAbertoEmp$txtConsultaEmpresa","");
	// postMethod.addParameter("ctl00$contentPlaceHolderConteudo$posicoesAbertoEmp$txtConsultaDataDownload$txtConsultaDataDownload","2013-12-30");
	// postMethod.addParameter("ctl00$contentPlaceHolderConteudo$posicoesAbertoEmp$txtConsultaDataDownload$txtConsultaDataDownload$dateInput","2013-12-30-00-00-00");
	// postMethod.addParameter("ctl00_contentPlaceHolderConteudo_posicoesAbertoEmp_txtConsultaDataDownload_txtConsultaDataDownload_calendar_SD","[]");
	// postMethod.addParameter("ctl00_contentPlaceHolderConteudo_posicoesAbertoEmp_txtConsultaDataDownload_txtConsultaDataDownload_calendar_AD","[[2013,11,4],[2013,12,30],[2013,12,30]]");
	// postMethod.addParameter("ctl00$contentPlaceHolderConteudo$posicoesAbertoEmp$btnBuscarArquivos","buscar");
	// postMethod.addParameter("ctl00$contentPlaceHolderConteudo$mpgOpcoes_Selected","0");
	// postMethod.addParameter("cboAgentesCorretorasNome","#");
	// postMethod.addParameter("cboAgentesCorretorasCodigo","#");
	//
	// try {
	// httpClient.executeMethod(postMethod);
	// } catch (HttpException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	//
	// if (postMethod.getStatusCode() == HttpStatus.SC_OK) {
	// try {
	// result = postMethod.getResponseHeader("Content-Type").toString();
	// //ResponseBodyAsString();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// } else {
	// result = postMethod.getStatusText();
	// }
	//
	// return result;
	// }
	//
	// public static void main(String[] args) {
	// System.out.println(getOptionsFile());
	// }

	public static void main(String[] args) throws Exception {
		BasicCookieStore cookieStore = new BasicCookieStore();
		CloseableHttpClient httpclient = HttpClients.custom()
				.setDefaultCookieStore(cookieStore).build();
		try {
			HttpGet httpget = new HttpGet(OPTIONS_URL);

			CloseableHttpResponse response1 = httpclient.execute(httpget);
			try {
				HttpEntity entity = response1.getEntity();

				System.out.println("Login form get: "
						+ response1.getStatusLine());

				System.out.println("Initial set of cookies:");
				List<Cookie> cookies = cookieStore.getCookies();
				if (cookies.isEmpty()) {
					System.out.println("None");
				} else {
					for (int i = 0; i < cookies.size(); i++) {
						System.out.println("- " + cookies.get(i).toString());
					}
				}

				HttpPost httpost = new HttpPost(OPTIONS_URL);

				List<NameValuePair> postParams = getFormParams(entity
						.getContent());

				EntityUtils.consume(entity);

				httpost.setEntity(new UrlEncodedFormEntity(postParams, Consts.UTF_8));
				
				 response1 = httpclient.execute(httpost);
				 entity = response1.getEntity();
				
				 System.out.println("content-type: " + entity.getContentType().toString());
				 
				 EntityUtils.consume(entity);
				
			} finally {
				response1.close();
			}
		} finally {
			httpclient.close();
		}
	}

	private static List<NameValuePair> getFormParams(InputStream content)
			throws IOException {
		
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

			System.out.println(key + ": " + value);
			paramList.add(new BasicNameValuePair(key, value));

		}

		return paramList;

	}

}
