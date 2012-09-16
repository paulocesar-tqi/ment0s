package br.com.sky.web;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.CookieHandler;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.sky.Configuracao;

public class HttpRequestPoster {

	static {
		CookieHandler.setDefault(SkyCookieHandler.getInstance());
	}

	public static String sendGetRequest(String endpoint,
			String requestParameters) throws Exception {
		String result = null;
		if (endpoint.startsWith("http://")) {
			// Send a GET request to the servlet

			// Construct data
			// StringBuffer data = new StringBuffer();

			// Send data
			String urlStr = endpoint;
			if (requestParameters != null && requestParameters.length() > 0) {
				urlStr += "?" + requestParameters;
			}
			URL url = new URL(urlStr);
			URLConnection conn = url.openConnection();

			// Get the response
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			StringBuffer sb = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
			rd.close();
			result = sb.toString();
		}
		return result;
	}

	public static HttpData getData(String urlStr, String cookies,
			String referrer) throws Exception {
		HttpURLConnection urlc = null;

		try {
			URL url = new URL(urlStr);
			urlc = (HttpURLConnection) url.openConnection();
			try {
				urlc.setRequestMethod("GET");
			} catch (ProtocolException e) {
				throw new Exception(
						"Shouldn't happen: HttpURLConnection doesn't support POST??",
						e);
			}
			urlc.setDoOutput(true);
			urlc.setDoInput(true);
			urlc.setUseCaches(false);

			if (cookies != null)
				urlc.setRequestProperty("Cookie", cookies);
			if (referrer != null)
				urlc.setRequestProperty("Referer", referrer);

			InputStream in = null;
			try {
				ByteArrayOutputStream output = new ByteArrayOutputStream();

				in = urlc.getInputStream();
				pipe(in, output);

				HttpData dataOut = new HttpData();
				dataOut.setHeaders(urlc.getHeaderFields());
				dataOut.setData(output.toByteArray());
				dataOut.setUrl(urlc.getURL().toString());

				return dataOut;

			} catch (IOException e) {
				throw new Exception("IOException response", e);
			} finally {
				if (in != null)
					in.close();
			}

		} catch (IOException e) {
			throw new Exception("Connection error (is server running at "
					+ urlStr + " ?): " + e);
		} finally {
			if (urlc != null)
				urlc.disconnect();
		}
	}

	public static HttpData postData(String urlStr, String data,
			List<String> cookies, HttpData dataImg, String referrer)
			throws Exception {
		HttpURLConnection urlc = null;

		try {
			URL url = new URL(urlStr);
			urlc = (HttpURLConnection) url.openConnection();
			try {
				urlc.setRequestMethod("POST");
			} catch (ProtocolException e) {
				throw new Exception(
						"Shouldn't happen: HttpURLConnection doesn't support POST??",
						e);
			}
			urlc.setInstanceFollowRedirects(true);
			urlc.setDoOutput(true);
			urlc.setDoInput(true);
			urlc.setUseCaches(false);

			if (Configuracao.isDebug()) {
				System.out.println(cookies);
				System.out.println(referrer);
			}

			CookieHandler handler = CookieHandler.getDefault();
			if (cookies != null) {
				Map<String, List<String>> headers = new HashMap<String, List<String>>();
				headers.put("Set-Cookie", cookies);
				handler.put(url.toURI(), headers);
				urlc.setRequestProperty("OriginalWord", dataImg.getUrl());
			}
			if (referrer != null)
				urlc.setRequestProperty("Referer", referrer);

			OutputStream out = urlc.getOutputStream();
			InputStream in = null;
			try {
				ByteArrayOutputStream output = new ByteArrayOutputStream();
				if (data != null) {
					OutputStreamWriter writer = new OutputStreamWriter(out);
					if (Configuracao.isDebug()) {
						System.out.println(data);
					}
					writer.write(data);
					writer.flush();
				}

				in = urlc.getInputStream();
				pipe(in, output);

				HttpData dataOut = new HttpData();
				dataOut.setHeaders(urlc.getHeaderFields());
				dataOut.setData(output.toByteArray());

				if (Configuracao.isDebug()) {
					System.out.println(urlc.getURL().toString());
				}

				dataOut.setUrl(urlc.getURL().toString());

				return dataOut;

			} catch (IOException e) {
				throw new Exception("IOException response", e);
			} finally {
				if (out != null)
					out.close();
				if (in != null)
					in.close();
			}

		} catch (IOException e) {
			throw new Exception("Connection error (is server running at "
					+ urlStr + " ?): " + e);
		} finally {
			if (urlc != null)
				urlc.disconnect();
		}
	}

	/**
	 * Pipes everything from the reader to the writer via a buffer
	 */
	private static void pipe(InputStream reader, OutputStream writer)
			throws IOException {
		byte[] buf = new byte[1024];
		int read = 0;
		while ((read = reader.read(buf)) >= 0) {
			writer.write(buf, 0, read);
		}
		writer.flush();
	}
}
