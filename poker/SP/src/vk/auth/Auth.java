package vk.auth;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.cookie.CookieSpecRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import vk.api.utils.exceptions.AuthorizationException;

import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;


public class Auth {

    private static HttpClient httpclient = new DefaultHttpClient() {
        @Override
        protected CookieSpecRegistry createCookieSpecRegistry() {
            CookieSpecRegistry registry = new CookieSpecRegistry();

            registry.register(
                    CookiePolicy.BEST_MATCH,
                    new AcceptAllSpecFactory());
            return registry;
        }
    };

    private static HttpPost post;
    private static HttpResponse response;

    private static void makeRequest(int id, String nextRequest, HttpEntity entity){
    	System.out.println(id + " | " + new Date() + " | " +" POST========");
    	System.out.println("URL: " + nextRequest);
        post = new HttpPost(nextRequest);
        if(entity != null)
        	post.setEntity(entity);
        try {
            response = httpclient.execute(post);
        } catch (IOException e) {
            e.printStackTrace();
        }
        post.abort();
        for(Header h : response.getAllHeaders() ) {
        	System.out.println(h.getName() + " : " + h.getValue());
        }
        System.out.println("FIM POST========");
    }

    /**
     *
     * @param idapp  Your application's identifier
     * @param settings  Access rights requested by your application
     * @param login Your e-mail
     * @param pass  Your password
     * @return P object, which defines access to api
     * @throws AuthorizationException
     */
    public static Token logIn(String idapp, String settings, String login, String pass) throws AuthorizationException {
        try{

            //First request

            makeRequest(0, "https://oauth.vk.com/authorize?" +
                    "client_id=" + idapp +
                    "&scope=" + settings +
                    "&display=wap" +
                    "&v=5.21" +
                    "&response_type=token" +
                    "&redirect_uri=https://oauth.vk.com/blank.html", null);

            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");
            Document document = Jsoup.parse(responseString);
            String ip_h = document.select("input[name=ip_h]").first().val();
            String to = document.select("input[name=to]").first().val();
            String _origin = document.select("input[name=_origin]").first().val();

            //Post login and password
            List<NameValuePair> postform = new ArrayList<NameValuePair>();
            postform.add(new BasicNameValuePair("ip_h", ip_h));
            postform.add(new BasicNameValuePair("to", to));
            postform.add(new BasicNameValuePair("_origin", _origin));
            postform.add(new BasicNameValuePair("email", login));
            postform.add(new BasicNameValuePair("pass", pass));

            //segundo
            makeRequest(3, "https://login.vk.com/?act=login&soft=1&utf8=1", new UrlEncodedFormEntity(postform, HTTP.UTF_8));
            String nextRequest = response.getFirstHeader("location").getValue();

            //Get page with allow_perm function or page with access_token

            //terceiro
            makeRequest(4, nextRequest, null);

            String body = EntityUtils.toString(response.getEntity());

            if(body.length() == 0){
                nextRequest = response.getFirstHeader("location").getValue();
                makeRequest(1000, nextRequest, null);

            } else {
            	document = Jsoup.parse(body);
                nextRequest = document.select("form").first().attr("action");

                makeRequest(5, nextRequest, null);
            }
            nextRequest = response.getFirstHeader("location").getValue();

            String accessToken = nextRequest.split("&")[0].split("#")[1].split("=")[1];
            String id = nextRequest.split("=")[nextRequest.split("=").length - 1];
            String expires = nextRequest.split("&")[1].split("=")[1];

            return new Token(id, accessToken, new Date(System.currentTimeMillis() + (Long.parseLong(expires)) * 1000));
        } catch (Exception e) {
        	e.printStackTrace();
            throw new AuthorizationException();
        }
    }

}