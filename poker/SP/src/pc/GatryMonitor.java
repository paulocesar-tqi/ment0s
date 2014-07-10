package pc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import vk.auth.AcceptAllSpecFactory;

public class GatryMonitor {
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
    
    private static ArrayList<String> currentOffers = new ArrayList<String>();

    
	public static void main(String[] args) throws TwitterException {
		try {
//			 while(true) {
				 try {
					 getOffers();
				 } catch (Exception e) {
					e.printStackTrace();
				}
				//Thread.sleep(1000 * 60 * 2);
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void mountSendTweet(String text) throws ClientProtocolException, IOException, TwitterException {
		HttpResponse response = null;
		HttpPost post = new HttpPost("http://shortText.com/api.aspx");
        List<NameValuePair> postform = new ArrayList<NameValuePair>();
        postform.add(new BasicNameValuePair("shorttext", text));
       	post.setEntity(new UrlEncodedFormEntity(postform, HTTP.UTF_8));
       	
        response = httpclient.execute(post);
        HttpEntity entity = response.getEntity();
        String url = EntityUtils.toString(entity, "UTF-8");
        post.abort();
        
        if(text.length() > 100)
			text = text.substring(0, 100); 
		//Tweeta!
        System.out.println("Publicando tweet: " + text + "... " + url);
		twitter.updateStatus(text + " | " + url);

	}

	
	private static void getOffers() throws ClientProtocolException, IOException, TwitterException {
		HttpResponse response = null;
		HttpPost post = new HttpPost("http://www.intensedebate.com/js/wordpressTemplateCommentWrapper2.php?acct=152aad78d7b44d72a43402870c2d1c89&postid=http://gatry.com/comprei/&title=Gatry%2520-%2520Promo%25C3%25A7%25C3%25B5es&url=http://gatry.com/comprei/");
       	
        response = httpclient.execute(post);
        HttpEntity entity = response.getEntity();
        String responseString = EntityUtils.toString(entity, "UTF-8");
        post.abort();
        responseString = responseString.replaceAll("\\\\", "");
        responseString = responseString.substring(responseString.indexOf("<div id=\"idc-cover\" class=\"idc-comments\">"), responseString.indexOf("<div id=\"IDCommentsNewThreadCover\""));
        //System.out.println(responseString);
        
        Document document = Jsoup.parse(responseString);
        
        Elements elements = document.select("div[class=idc-c-t-inner]");
        
        Map<String,String> offersMap = new HashMap<String,String>();
        for(Element el : elements) {
        	Elements links = el.select("a[href]");
        	String strLinks = "";
        	for(Element link : links) {
        		strLinks += link.attr("abs:href") + " ";
        	}
        	//System.out.println(el.text() + " | " + strLinks);
        	offersMap.put(el.attr("id"), el.text() + " | " + strLinks);
        	System.out.println(el.attr("id"));
        }
        processOffers(offersMap);
	}
	
	private static void processOffers(Map<String,String> map) throws ClientProtocolException, IOException, TwitterException {
		//Se a list for vazia, popula a lista e não printa
		if (currentOffers.size() == 0) {
			for(String key : map.keySet()) {
				currentOffers.add(key);
			}
		}
		
		//Remove do currentOffers todos que não vieram no map
		@SuppressWarnings("unchecked")
		ArrayList<String> newOffers = (ArrayList<String>) currentOffers.clone();
		for(String offer : newOffers) {
			if(!map.containsKey(offer))
				currentOffers.remove(offer);
		}
		
		//twitta as novas offers e adiciona na lista
		for(String key : map.keySet()) {
			if(!currentOffers.contains(key)) {
				currentOffers.add(key);
				mountSendTweet(map.get(key));
			}
		}
	}

}
