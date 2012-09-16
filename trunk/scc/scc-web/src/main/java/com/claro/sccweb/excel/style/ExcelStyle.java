package com.claro.sccweb.excel.style;


/**
 * Estilo de formatação de uma célula. Um ExcelStyle é associado a uma coluna e aplicado em
 * todas as células abaixo dela.
 *
 */
public interface ExcelStyle {

	/**
	 *	  @return Nome da fonte
	 */
	public  String getFontName();
	
	/**
	 * 
	 * @return Cor da fonte.
	 */
	public  short getFontColor();
	
	/**
	 * 
	 * @return Tamanho da fonte.
	 */
	public  short getFontHeight();
	
	/**
	 * 
	 * @return Formatação de negrito.
	 */
	public  short getBoldweight();	
	
	/**
	 * 
	 * @return Alinhamento dentro da célula.
	 */
	public  short getAlignment();
	
	/**
	 * 
	 * @return Flag wrap text.
	 */
	public  boolean getWrapText();
	
	/**
	 * 
	 * @return Formato/Máscara da célula.
	 */
	public String getFormat();
}
