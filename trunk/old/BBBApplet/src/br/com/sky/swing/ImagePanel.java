package br.com.sky.swing;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1184368316734666378L;
	private Image image;
	
	public ImagePanel(URL url) throws IOException {
		loadImage(url);
	}
		
	public ImagePanel(byte[] bytes) throws IOException {
		loadImage(bytes);
	}
	
	private void loadImage(URL url) throws IOException {
		image = ImageIO.read(url);	
	}

	private void loadImage(byte[] bytes) throws IOException {
		image = Toolkit.getDefaultToolkit().createImage(bytes);
		/*
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		image = ImageIO.read(bais);*/
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(image, 0, 0, null);
	}
}
