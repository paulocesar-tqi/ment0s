package br.com.sky;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import br.com.sky.bbb.VoteBox;
import br.com.sky.web.JSHelper;
import br.com.sky.web.VoteAppender;

public class HelloWorld extends JApplet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 31681503089979285L;

	private List<VoteBox> lstVotes = new ArrayList<VoteBox>();

	// Called when this applet is loaded into the browser.
	public void init() {
		// Execute a job on the event-dispatching thread; creating this applet's
		// GUI.
		try {
			final JSHelper jsHelper = new JSHelper(this);
			SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {
					setLayout(new BorderLayout());

					try {
						Configuracao.load(getDocumentBase());
					} catch (Exception ex) {
						JLabel lbl = new JLabel();
						lbl.setText("Erro: " + ex.getMessage());
						System.out.println(ex);
						lbl.setHorizontalAlignment(SwingConstants.CENTER);
						add(lbl, BorderLayout.CENTER);
						return;
					}

					if (Configuracao.isEnabled()) {					
						buildScreen(jsHelper);
						String titulo = Configuracao.getTitulo();
						JLabel lbl = new JLabel(titulo);
						lbl.setHorizontalAlignment(SwingConstants.CENTER);
						add(lbl, BorderLayout.NORTH);
					} else {
						JLabel lbl = new JLabel();
						lbl.setHorizontalAlignment(SwingConstants.CENTER);
						lbl.setText("Sistema indisponível");
						add(lbl, BorderLayout.CENTER);
						return;
					}					
				}
			});
		} catch (Exception e) {
			System.err.println("createGUI didn't complete successfully");
			e.printStackTrace();
		}
	}
	
	private void buildScreen(JSHelper js) {
		GridLayout layout = new GridLayout();
		final JPanel pnlBotoes = new JPanel();
		pnlBotoes.setLayout(layout);

		final JButton btn = new JButton("Carregar");
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JLabel lblTitOk = new JLabel("Qtd OK:");
				lblTitOk.setHorizontalAlignment(SwingConstants.RIGHT);
				final JLabel lblOk = new JLabel("");
				lblOk.setHorizontalAlignment(SwingConstants.LEFT);
				JLabel lblTitErro = new JLabel("Qtd Erro:");
				lblTitErro.setHorizontalAlignment(SwingConstants.RIGHT);
				final JLabel lblErro = new JLabel("");
				lblErro.setHorizontalAlignment(SwingConstants.LEFT);
				
				pnlBotoes.remove(btn);

				pnlBotoes.add(lblTitOk);
				pnlBotoes.add(lblOk);
				pnlBotoes.add(lblTitErro);
				pnlBotoes.add(lblErro);
				
				for (VoteBox voteBox : lstVotes) {
					final VoteBox v = voteBox;
					Runnable r = new Runnable() {
						@Override
						public void run() {
							v.load();
						}
					};
					Thread t = new Thread(r);
					t.start();
										
					v.addActionListener(new ActionListener() {						
						@Override
						public void actionPerformed(ActionEvent e) {
							String command = e.getActionCommand();
							boolean ok = Boolean.parseBoolean(command);
							JLabel lbl = null;
							if (ok) {
								lbl = lblOk;
							} else {
								lbl = lblErro;
							}
							int qtd = 0;
							synchronized (lbl) {
								String strQtd = lbl.getText();								
								if (strQtd.length() > 0) {
									qtd = Integer.parseInt(strQtd);
								}
								qtd++;
								lbl.setText(qtd + "");
							}
							
							if (ok && (qtd % Configuracao.QTD_VOTOS_OK == 0)) {
								VoteAppender.addVotes(Configuracao.QTD_VOTOS_OK);
							}
						}
					});
					
					
				}
			}
		});
		pnlBotoes.add(btn);		

		add(pnlBotoes, BorderLayout.SOUTH);

		int width = js.getWidth();
		int height = js.getHeight();
		int maxPerRow = width / Configuracao.IMG_WIDTH;
		int boxHeight = 90;
		int maxRows = height / boxHeight;
		
		int qtdRows = maxRows;
		
		layout = new GridLayout();
		layout.setColumns(maxPerRow);
		layout.setRows(maxRows);

		JPanel pnlVotes = new JPanel();
		layout.setHgap(10);
		layout.setVgap(10);
		pnlVotes.setLayout(layout);		

		add(pnlVotes, BorderLayout.CENTER);

		for (int i = 0; i < qtdRows * maxPerRow; ++i) {
			VoteBox box = new VoteBox();
			lstVotes.add(box);
			pnlVotes.add(box);
			box.setMinimumSize(new Dimension(Configuracao.IMG_WIDTH, boxHeight));
			box.setSize(Configuracao.IMG_WIDTH, boxHeight);
		}
	}

}
