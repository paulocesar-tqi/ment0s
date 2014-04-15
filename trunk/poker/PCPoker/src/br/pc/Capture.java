package br.pc;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.GrayFilter;

import org.sikuli.script.Screen;
import org.sikuli.script.ScreenImage;
import org.sikuli.script.TextRecognizer;

public class Capture {

	public static void main(String[] args) {
		Screen s = new Screen();
		Scanner userInputScanner = new Scanner(System.in);
//		try {
			while (true) {
				try {
			        userInputScanner.nextLine();
					ScreenImage img = s.capture(527, 550, 126, 24);
					ImageIO.write(toBinaryImage(img.getImage()), "png", new File("D:/workspace/PCPoker/imgs/" + ((int)(Math.random()*100000000)) + ".png"));
					//System.out.println(TextRecognizer.getInstance().recognize(toBinaryImage(img.getImage())));
			        //ImageIO.write(img.getImage(), "png", new File("D:/workspace/PCPoker/imgs/" + ((int)(Math.random()*100000000)) + ".png"));
					img = s.capture(664, 550, 126, 24);
					//System.out.println(TextRecognizer.getInstance().recognize(toBinaryImage(img.getImage())));
					ImageIO.write(toBinaryImage(img.getImage()), "png", new File("D:/workspace/PCPoker/imgs/" + ((int)(Math.random()*100000000)) + ".png"));
					//ImageIO.write(img.getImage(), "png", new File("D:/workspace/PCPoker/imgs/" + ((int)(Math.random()*100000000)) + ".png"));
					Toolkit.getDefaultToolkit().beep();
					Thread.sleep(7000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// s.find("imgs/windows.png");
			// s.
			// s.click("imgs/windows.png", 0);
//		} catch (FindFailed e) {
//			e.printStackTrace();
//		}
	}

	public static BufferedImage imageToBufferedImage(Image im) {
	     BufferedImage bi = new BufferedImage
	        (im.getWidth(null),im.getHeight(null),BufferedImage.TYPE_INT_RGB);
	     Graphics bg = bi.getGraphics();
	     bg.drawImage(im, 0, 0, null);
	     bg.dispose();
	     return bi;
	  }
	
	public static BufferedImage toBinaryImage(final BufferedImage image) {
	    final BufferedImage blackAndWhiteImage = new BufferedImage(
	            image.getWidth(null), 
	            image.getHeight(null), 
	            BufferedImage.TYPE_BYTE_BINARY);
	    final Graphics2D g = (Graphics2D) blackAndWhiteImage.getGraphics();
	    g.drawImage(image, 0, 0, null);
	    g.dispose();
	    
	    for (int x = 0; x < blackAndWhiteImage.getWidth(); x++) {
            for (int y = 0; y < blackAndWhiteImage.getHeight(); y++) {
                int rgba = blackAndWhiteImage.getRGB(x, y);
                Color col = new Color(rgba, true);
                col = new Color(255 - col.getRed(),
                                255 - col.getGreen(),
                                255 - col.getBlue());
                blackAndWhiteImage.setRGB(x, y, col.getRGB());
            }
        }
	    return blackAndWhiteImage;
	}
}
