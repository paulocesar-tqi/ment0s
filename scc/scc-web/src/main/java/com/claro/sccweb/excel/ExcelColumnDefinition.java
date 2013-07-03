package com.claro.sccweb.excel;

import com.claro.sccweb.excel.style.ExcelStyle;

/**
 * Define uma coluna a ser criada na planilha Excel.
 * 
 */
public class ExcelColumnDefinition {
	
	/**
	 * M�todo que ir� fornecer a propriedade associada � coluna.
	 * Esse m�todo devera retornar o objeto Lang e n�o possuir argumentos.
	 */
	private String property;
	
	/**
	 * Estilo associado �s c�lulas dessa coluna.
	 */
	private ExcelStyle style;
	
	/**
	 * T�tulo da coluna.
	 */
	private String title;
	
	/**
	 * Largura da coluna em pixels.
	 */
	private int width;
	
	private String[] properties;
	
	public ExcelColumnDefinition(String property,String title,ExcelStyle style,int width)
	{
		this.property = property;
		this.title = title;
		this.style = style;
		this.width = width;
	}
	
	public ExcelColumnDefinition(String[] properties, String title, ExcelStyle style, int width){
		this.properties = properties;
		this.title = title;
		this.style = style;
		this.width = width;

	}
	
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public ExcelStyle getStyle() {
		return style;
	}
	public void setStyle(ExcelStyle style) {
		this.style = style;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public String[] getProperties() {
		return properties;
	}

	public void setProperties(String[] properties) {
		this.properties = properties;
	}
	
	
	
}
