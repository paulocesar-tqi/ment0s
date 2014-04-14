package br.pc;

import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

import javax.imageio.ImageIO;

import org.sikuli.script.Screen;
import org.sikuli.script.ScreenImage;

public class Capture {

	public static void main(String[] args) {
		Screen s = new Screen();
		Scanner userInputScanner = new Scanner(System.in);
//		try {
			while (true) {
				try {
			        userInputScanner.nextLine();
//					ScreenImage img = s.capture(271, 220, 158, 4);
//			        ImageIO.write(img.getImage(), "png", new File("D:/workspace/PCPoker/imgs/" + ((int)(Math.random()*100000000)) + ".png"));
//					img = s.capture(271, 220, 212, 4);
//					ImageIO.write(img.getImage(), "png", new File("D:/workspace/PCPoker/imgs/" + ((int)(Math.random()*100000000)) + ".png"));
					ScreenImage img = s.capture(271, 220, 266, 4);
					ImageIO.write(img.getImage(), "png", new File("D:/workspace/PCPoker/imgs/" + ((int)(Math.random()*100000000)) + ".png"));
					Toolkit.getDefaultToolkit().beep();
					Thread.sleep(7000);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
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

}
