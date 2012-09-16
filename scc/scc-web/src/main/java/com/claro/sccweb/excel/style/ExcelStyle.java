package com.claro.sccweb.excel.style;


/**
 * Estilo de formata��o de uma c�lula. Um ExcelStyle � associado a uma coluna e aplicado em
 * todas as c�lulas abaixo dela.
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
	 * @return Formata��o de negrito.
	 */
	public  short getBoldweight();	
	
	/**
	 * 
	 * @return Alinhamento dentro da c�lula.
	 */
	public  short getAlignment();
	
	/**
	 * 
	 * @return Flag wrap text.
	 */
	public  boolean getWrapText();
	
	/**
	 * 
	 * @return Formato/M�scara da c�lula.
	 */
	public String getFormat();
}
