package br.com.sky;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import br.com.sky.web.CaptchaLoader;
import br.com.sky.web.HttpData;

public class Applet2 extends JApplet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5531433461609668767L;

	public void init() {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {
					BorderLayout layout = new BorderLayout();
					setLayout(layout);

					JButton btn = new JButton("Teste");
					btn.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							testeAction();
						}
					});
					add(btn, "East");

				}
			});
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void testeAction() {
		CaptchaLoader cLoader = new CaptchaLoader();
		String urlImg = "http://votacao.globo.com/rsvotacao/word.jpg?_=0.33212297908961773";
		try {
			HttpData dataImg = cLoader.load(urlImg, null);

			JLabel p = new JLabel();
			add(p, "Center");
			p.setIcon(new ImageIcon(dataImg.getData()));
			// p.getGraphics().drawImage(image, 0, 0, p);
			p.repaint();
			repaint();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
