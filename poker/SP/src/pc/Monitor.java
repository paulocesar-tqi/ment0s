package pc;

import java.io.IOException;
import java.sql.Date;

import vk.api.Api;
import vk.api.utils.P;
import vk.api.utils.exceptions.AuthorizationException;
import vk.auth.Auth;
import vk.auth.Token;


public class Monitor {

	public static void main(String[] args) {
		Token it = null;
	     try {
	    	 //Recupera o token
	         //it = Auth.logIn("4448828", "groups", "contato@paulocesar.tk", "Yusuke123");
	         it = new Token("260181154", "286adcc16aec6f6408fcff960662c48f0f3df558914e69e69f1281849c05f80b7ee022bbef57a5aad2121", new Date(System.currentTimeMillis() + Long.parseLong("86400")));
	         
//	         while(true) {
//	        	 if(it.isExpired())
//	        		 it = Auth.logIn("4448828", "groups", "contato@paulocesar.tk", "Yusuke123");
	        	 
	        	 //Recupera o ultimo comentario do topico
	        	 System.out.println(Api.make(it, "board.getComments", new P("group_id=60157925,topic_id=30270057,count=1,extended=0,sort=desc")));
	        	 
//	        	 Thread.sleep(60000);
//	        	 
//	         }
	         
	     } catch (Exception e) {
	         e.printStackTrace();
	     }
	}

}
