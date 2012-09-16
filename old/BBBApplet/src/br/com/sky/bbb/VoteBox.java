package br.com.sky.bbb;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import br.com.sky.Configuracao;
import br.com.sky.Cookie;
import br.com.sky.VoteEntry;
import br.com.sky.web.CaptchaLoader;
import br.com.sky.web.HttpData;
import br.com.sky.web.HttpRequestPoster;

public class VoteBox extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6095571572529475020L;
	private JLabel imgField;
	private JTextField txtImgUrl;
	private JTextField txtField;
	private VoteEntry voteEntry;
	private boolean isLoaded = false;
	private Random random = new Random();
	private List<ActionListener> listeners = new ArrayList<ActionListener>();
	
	public VoteBox() {
		super();
		BorderLayout bl = new BorderLayout();
		this.setLayout(bl);
		
		JPanel pnl = new JPanel();
		pnl.setSize(new Dimension(1, 10));
		this.add(pnl, BorderLayout.WEST);
		
		pnl = new JPanel();
		pnl.setSize(new Dimension(1, 10));
		this.add(pnl, BorderLayout.EAST);
		
		imgField = new JLabel();
		this.add(imgField, BorderLayout.CENTER);
		
		txtField = new JTextField();
		this.add(txtField, BorderLayout.SOUTH);
		
		final VoteBox box = this;
		txtField.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if (isLoaded && txtField.getText().length() > 0) {
					Runnable run = new Runnable() {
						
						@Override
						public void run() {
							box.send();							
						}
					};
					Thread t = new Thread(run);
					t.start();
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				txtField.setText("");				
			}
		});
		
		txtImgUrl = new JTextField();
		//this.add(txtImgUrl, BorderLayout.NORTH);
	}
	
	public void load() {
		imgField.setIcon(null);
		imgField.setText("Loading...");
		CaptchaLoader cLoader = new CaptchaLoader();
		
		String urlImg = Configuracao.URL_CAPTCHA + random.nextLong();
		try {
			HttpData dataImg = cLoader.load(urlImg, Configuracao.URL_REFERRER);
			imgField.setIcon(new ImageIcon(dataImg.getData()));
			imgField.setHorizontalAlignment(SwingConstants.CENTER);
			imgField.setText("");
			imgField.repaint();
			repaint();			
			
			txtImgUrl.setText(dataImg.getUrl());
			voteEntry = new VoteEntry(dataImg);
			
			Map<String,List<String>> map = dataImg.getHeaders();
			for (String key : map.keySet()) {				
				if (key != null && key.equalsIgnoreCase("Set-Cookie")) {
					List<String> lst = map.get(key);
					voteEntry.setCookies(lst);
				}
			}
			isLoaded = true;
		} catch (Exception ex) {
			ex.printStackTrace();
			txtField.setText("Erro");
		}
	}
	
	public void send() {
		try {
			txtField.setEnabled(false);
			
			String answer = txtField.getText();
			String postData = "poll_id=" + Configuracao.getPoolId() +
            				"&param_votacao=" + Configuracao.PARAM_VOTACAO +
            				"&url_transacao=" + URLEncoder.encode(Configuracao.URL_TRANSACAO, "UTF-8") +
            				"&success_url=" + URLEncoder.encode(Configuracao.URL_SUCESSO, "UTF-8") +
            				"&error_url=" + URLEncoder.encode(Configuracao.URL_ERRO, "UTF-8") +
            				"&block_url=" + URLEncoder.encode(Configuracao.URL_BLOCK, "UTF-8") +
            				"&opt=" + Configuracao.getOpt() +
            				"&answer=" + answer +
            				"&check=" + voteEntry.getCookie("check").getValue();
			
			String url = Configuracao.URL_VOTACAO;
			
			List<Cookie> lstCookies = voteEntry.getCookies();
			HttpData dataImg = voteEntry.getDataImg();
			List<String> cookies = new Vector<String>();
			for (Cookie cookie : lstCookies) {				
				cookies.add(cookie.getKey() + "=" + cookie.getValue());
			}
			
			String urlRef = Configuracao.URL_REFERRER;
			HttpData dataOut = HttpRequestPoster.postData(url, postData, cookies, dataImg,
					urlRef);
			String resp = new String(dataOut.getData(), "UTF-8");

			//System.out.println(resp);
			
			if (resp.indexOf("encerrada") > 0) {
				txtField.setText("Votação encerrada");
			} else {
				if (dataOut.getUrl().equalsIgnoreCase(Configuracao.URL_SUCESSO)) {
					System.out.println(answer + " - OK");
					txtField.setText("OK");
					fireEvent(true);
				} else {
					System.out.println(answer + " - ERRO");
					txtField.setText("Incorreto " + answer);
					fireEvent(false);
				}
			}
			load();
		} catch (Exception ex) {
			txtField.setText("Erro");
			ex.printStackTrace();
		}
		txtField.setEnabled(true);
	}
	
	public void addActionListener(ActionListener listener) {
		listeners.add(listener);
	}
	
	private void fireEvent(boolean ok) {
		ActionEvent evt = new ActionEvent(this, 1, Boolean.toString(ok));
		for (ActionListener listener : listeners) {
			listener.actionPerformed(evt);
		}
	}
}
