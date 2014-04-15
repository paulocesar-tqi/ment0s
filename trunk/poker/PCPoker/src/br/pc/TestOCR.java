package br.pc;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.sikuli.script.TextRecognizer;

public class TestOCR {
	public static void main(String[] args) throws IOException {
		System.out.println(TextRecognizer.getInstance().recognize(ImageIO.read(new File("D:/workspace/PCPoker/imgs/numbers.png"))));
	}
}
