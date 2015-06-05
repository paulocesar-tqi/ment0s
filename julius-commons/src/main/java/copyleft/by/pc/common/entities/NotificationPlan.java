package copyleft.by.pc.common.entities;

import java.util.ArrayList;

public class NotificationPlan {
	
	private ArrayList<String> idsAndroidNoFilter;
	private ArrayList<String> idsIosNoFilter;
	private ArrayList<String> idsWpNoFilter;
	private ArrayList<Word> words;
	
	public ArrayList<String> getIdsAndroidNoFilter() {
		return idsAndroidNoFilter;
	}
	public void setIdsAndroidNoFilter(ArrayList<String> idsAndroidNoFilter) {
		this.idsAndroidNoFilter = idsAndroidNoFilter;
	}
	public ArrayList<String> getIdsIosNoFilter() {
		return idsIosNoFilter;
	}
	public void setIdsIosNoFilter(ArrayList<String> idsIosNoFilter) {
		this.idsIosNoFilter = idsIosNoFilter;
	}
	public ArrayList<String> getIdsWpNoFilter() {
		return idsWpNoFilter;
	}
	public void setIdsWpNoFilter(ArrayList<String> idsWpNoFilter) {
		this.idsWpNoFilter = idsWpNoFilter;
	}
	public ArrayList<Word> getWords() {
		return words;
	}
	public void setWords(ArrayList<Word> words) {
		this.words = words;
	}
	
	
}