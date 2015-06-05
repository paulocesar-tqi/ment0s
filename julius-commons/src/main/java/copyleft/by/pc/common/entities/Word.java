package copyleft.by.pc.common.entities;

import java.util.ArrayList;

public class Word {
	private String word;
	private ArrayList<String> idsAndroid;
	private ArrayList<String> idsIos;
	private ArrayList<String> idsWp;

	
	
	public Word(String word, ArrayList<String> idsAndroid, ArrayList<String> idsIos, ArrayList<String> idsWp) {
		super();
		this.word = word;
		this.idsAndroid = idsAndroid;
		this.idsIos = idsIos;
		this.idsWp = idsWp;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public ArrayList<String> getIdsAndroid() {
		return idsAndroid;
	}

	public void setIdsAndroid(ArrayList<String> idsAndroid) {
		this.idsAndroid = idsAndroid;
	}

	public ArrayList<String> getIdsIos() {
		return idsIos;
	}

	public void setIdsIos(ArrayList<String> idsIos) {
		this.idsIos = idsIos;
	}

	public ArrayList<String> getIdsWp() {
		return idsWp;
	}

	public void setIdsWp(ArrayList<String> idsWp) {
		this.idsWp = idsWp;
	}

}