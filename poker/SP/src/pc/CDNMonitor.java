package pc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.cookie.CookieSpecRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import twitter4j.JSONObject;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import vk.api.Api;
import vk.api.utils.P;
import vk.auth.AcceptAllSpecFactory;
import vk.auth.Auth;
import vk.auth.Token;

public class CDNMonitor implements Runnable {
    
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

    private static ConfigurationBuilder cb = new ConfigurationBuilder().setDebugEnabled(true)
														.setOAuthConsumerKey("zWhiTGukkQ3xaBiznvz7Q")
														.setOAuthConsumerSecret("SOyyvIQfT6ptiJvgZ1jwoZmfPFKu9asjxtFCVXnpLbY")
														.setOAuthAccessToken("609336042-u3JeXoI5tzVTUPnVUaaE4H5haGkTK8ByxLgqbP2G")
														.setOAuthAccessTokenSecret("EPaBNwtKVT2mjwpdAFNcGtxb0CUf5vRIWxxX0CzJNQs");
	
    private static TwitterFactory tf = new TwitterFactory(cb.build());
    private static Twitter twitter = tf.getInstance();

    
	public void run() {
		Token it = null;
		int oldId = 0;
		
		try {
			// Recupera o token
			it = Auth.logIn("4448828", "groups", "contato@paulocesar.tk", "Yusuke123");
			//it = new Token("260181154", "086ab8b869442249489a11ed888c6785bb9a538d609d1cbb745abfe010b2e5c8ce0ba1fdad33c5dab7f69", new Date(System.currentTimeMillis() + Long.parseLong("86400")));
			 while(true) {
				 try {
					 if(it.isExpired())
						 it = Auth.logIn("4448828", "groups", "contato@paulocesar.tk", "Yusuke123");

					// Recupera o ultimo comentario do topico
					String retorno = Api.make(it, "board.getComments", new P("group_id=60157925,topic_id=30270057,count=1,extended=0,sort=desc"));
					//String retorno = "{\"response\":{\"comments\":[7,{\"id\":25267,\"from_id\":228422840,\"date\":1404935850,\"text\":\"ctrl c ctrl v <br><br>Bug Paqueta &gt; Loja confiavel com v?rias lojas fisicas pelo pa?s<br><br>Anunciam o modelo Phantom, chuteira de 1000 conto que o neymar usa e a foto ? do modelo Phelon :D x?ii isso vai dar merda pra eles hen<br><br>http:\\/\\/www.paquetaesportes.com.br\\/Chuteira-de-Campo-Hypervenom-Phantom-FG-Dourado-2000948088\\/p<br><br>http:\\/\\/www.nike.com.br\\/Chuteira-Hypervenom-Gold-Phantom-FG-301850.html\"}]}}";
					System.out.println(new Date() + " | " + retorno);
					JSONObject json = new JSONObject(retorno);
					json = json.getJSONObject("response").getJSONArray("comments").getJSONObject(1);
					if(oldId != json.getInt("id")){
						oldId = json.getInt("id");
						//System.out.println("Enviando tweet: " + json.getString("text"));
						mountSendTweet(json.getString("text"));
					}					
				} catch (Exception e) {
					e.printStackTrace();
				}
				Thread.sleep(1000 * 60 * 2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void mountSendTweet(String text) throws ClientProtocolException, IOException, TwitterException {
		text = StringEscapeUtils.unescapeHtml4(Jsoup.clean(text.replaceAll("<br>", "\n"), Whitelist.none()));
		
		HttpResponse response = null;
		HttpPost post = new HttpPost("http://shortText.com/api.aspx");
        List<NameValuePair> postform = new ArrayList<NameValuePair>();
        postform.add(new BasicNameValuePair("shorttext", text));
       	post.setEntity(new UrlEncodedFormEntity(postform, HTTP.UTF_8));
       	
        response = httpclient.execute(post);
        HttpEntity entity = response.getEntity();
        String url = EntityUtils.toString(entity, "UTF-8");
        post.abort();
        
        if(text.length() > 105)
			text = text.substring(0, 105); 
		//Tweeta!
        System.out.println("Publicando tweet: " + text + "... " + url);
		twitter.updateStatus(text + "... " + url);

	}

}
