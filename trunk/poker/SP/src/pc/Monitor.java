package pc;

import java.util.Date;

import org.apache.commons.lang3.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import twitter4j.JSONObject;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import vk.api.Api;
import vk.api.utils.P;
import vk.auth.Auth;
import vk.auth.Token;

public class Monitor {

	public static void main(String[] args) throws TwitterException {
		Token it = null;
		int oldId = 0;
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey("zWhiTGukkQ3xaBiznvz7Q")
		  .setOAuthConsumerSecret("SOyyvIQfT6ptiJvgZ1jwoZmfPFKu9asjxtFCVXnpLbY")
		  .setOAuthAccessToken("609336042-u3JeXoI5tzVTUPnVUaaE4H5haGkTK8ByxLgqbP2G")
		  .setOAuthAccessTokenSecret("EPaBNwtKVT2mjwpdAFNcGtxb0CUf5vRIWxxX0CzJNQs");
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		
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
					System.out.println(new Date() + " | " + retorno);
					JSONObject json = new JSONObject(retorno);
					json = json.getJSONObject("response").getJSONArray("comments").getJSONObject(1);

					if(oldId != json.getInt("id")){
						String texto = StringEscapeUtils.unescapeHtml4(Jsoup.clean(json.getString("text"), Whitelist.none()));
						if(texto.length() > 127)
							texto = texto.substring(0, 127); 
						//Tweeta!
						twitter.updateStatus(texto);
						oldId = json.getInt("id");
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

}
