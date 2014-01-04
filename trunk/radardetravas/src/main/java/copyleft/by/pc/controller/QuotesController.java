package copyleft.by.pc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import copyleft.by.pc.model.Ativo;

@Component
@EnableAsync
public class QuotesController {
	private static final Logger log = Logger.getLogger(QuotesController.class
			.getName());

	private static final String QUOTES_URL = "http://www.bmfbovespa.com.br/Pregao-Online/ExecutaAcaoAjax.asp?CodigoPapel=";
	private static final String NUMERO_NEGOCIOS_URL = "http://www.bmfbovespa.com.br/cotacoes2000/formCotacoesMobile.asp?codsocemi=";

	@Async
	public void updateQuotes(Ativo ativo) {
		log.severe("INI - Atualizando ativo: " + ativo.getCodigoAtivo());

		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet httpget = null;
			List<String> codigos = ativo.getCodesAsList();
			Map<String, Map<String, String>> dados = new HashMap<String, Map<String, String>>();

			if (codigos != null && codigos.size() > 0) {
				for (String codigo : codigos) {
					httpget = new HttpGet(QUOTES_URL + codigo);

					httpget.addHeader("User-Agent",
							"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:26.0) Gecko/20100101 Firefox/26.0");
					httpget.addHeader("Accept",
							"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
					httpget.addHeader("Accept-Language",
							"pt-BR,pt;q=0.8,en-US;q=0.5,en;q=0.3");
					httpget.addHeader("Accept-Encoding", "gzip, deflate");

					CloseableHttpResponse response1 = httpclient
							.execute(httpget);
					try {

						Document doc = Jsoup
								.parse(response1.getEntity().getContent(),
										"UTF-8", "/", Parser.xmlParser());
						EntityUtils.consume(response1.getEntity());

						for (Element cotacao : doc.select("Papel")) {
							Map<String, String> papel = new HashMap<String, String>();
							papel.put("nomeAtivo", cotacao.attr("Nome"));
							papel.put("dataAtualizacao", cotacao.attr("Data"));
							papel.put("abertura", cotacao.attr("Abertura"));
							papel.put("minimo", cotacao.attr("Minimo"));
							papel.put("maximo", cotacao.attr("Maximo"));
							papel.put("medio", cotacao.attr("Medio"));
							papel.put("ultimo", cotacao.attr("Ultimo"));
							papel.put("oscilacao", cotacao.attr("Oscilacao"));
							// papel.put("negocios",
							// getNegocios(cotacao.attr("Codigo")));
							dados.put(cotacao.attr("Codigo"), papel);
						}

					} catch (Exception e) {
						log.log(Level.SEVERE,
								"Erro no controller ao recuperar as cotacoes.",
								e);
					} finally {
						response1.close();
					}
				}

				ativo.updateFromMap(dados);
			}
		} catch (Exception e) {
			log.log(Level.SEVERE,
					"Erro no controller ao recuperar as cotacoes.", e);
		} finally {
			try {
				httpclient.close();
			} catch (Exception e) {
			}
		}
		log.severe("FIM - Atualizando ativo: " + ativo.getCodigoAtivo());
	}

	public void updateQuotes2(Ativo ativo) {
		log.severe("INI - Atualizando ativo: " + ativo.getCodigoAtivo());

		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet httpget = new HttpGet(QUOTES_URL + ativo.getCodes());

			httpget.addHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:26.0) Gecko/20100101 Firefox/26.0");
			httpget.addHeader("Accept",
					"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			httpget.addHeader("Accept-Language",
					"pt-BR,pt;q=0.8,en-US;q=0.5,en;q=0.3");
			httpget.addHeader("Accept-Encoding", "gzip, deflate");

			CloseableHttpResponse response1 = httpclient.execute(httpget);
			try {

				Document doc = Jsoup.parse(response1.getEntity().getContent(),
						"UTF-8", "/", Parser.xmlParser());
				EntityUtils.consume(response1.getEntity());

				Map<String, Map<String, String>> dados = new HashMap<String, Map<String, String>>();
				for (Element cotacao : doc.select("Papel")) {
					Map<String, String> papel = new HashMap<String, String>();
					papel.put("nomeAtivo", cotacao.attr("Nome"));
					papel.put("dataAtualizacao", cotacao.attr("Data"));
					papel.put("abertura", cotacao.attr("Abertura"));
					papel.put("minimo", cotacao.attr("Minimo"));
					papel.put("maximo", cotacao.attr("Maximo"));
					papel.put("medio", cotacao.attr("Medio"));
					papel.put("ultimo", cotacao.attr("Ultimo"));
					papel.put("oscilacao", cotacao.attr("Oscilacao"));
					// papel.put("negocios",
					// getNegocios(cotacao.attr("Codigo")));
					dados.put(cotacao.attr("Codigo"), papel);
				}

				ativo.updateFromMap(dados);

			} catch (Exception e) {
				log.log(Level.SEVERE,
						"Erro no controller ao recuperar as cotacoes.", e);
			} finally {
				response1.close();
			}
		} catch (Exception e) {
			log.log(Level.SEVERE,
					"Erro no controller ao recuperar as cotacoes.", e);
		} finally {
			try {
				httpclient.close();
			} catch (Exception e) {
			}
		}
		log.severe("FIM - Atualizando ativo: " + ativo.getCodigoAtivo());
	}

	private static String getNegocios(String codigo) {
		String result = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet httpget = new HttpGet(NUMERO_NEGOCIOS_URL + codigo);

			httpget.addHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:26.0) Gecko/20100101 Firefox/26.0");
			httpget.addHeader("Accept",
					"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			httpget.addHeader("Accept-Language",
					"pt-BR,pt;q=0.8,en-US;q=0.5,en;q=0.3");
			httpget.addHeader("Accept-Encoding", "gzip, deflate");

			CloseableHttpResponse response1 = httpclient.execute(httpget);
			try {

				Document doc = Jsoup.parse(response1.getEntity().getContent(),
						"UTF-8", "/", Parser.xmlParser());
				for (Element cotacao : doc.select("Papel")) {
					result = cotacao.attr("QUANT_NEG");
				}
				EntityUtils.consume(response1.getEntity());

			} catch (Exception e) {
				log.log(Level.SEVERE,
						"Erro no controller ao recuperar a quantidade negociada.",
						e);
			} finally {
				response1.close();
			}
		} catch (Exception e) {
			log.log(Level.SEVERE,
					"Erro no controller ao recuperar a quantidade negociada.",
					e);
		} finally {
			try {
				httpclient.close();
			} catch (Exception e) {
			}
		}
		return result;
	}

}
