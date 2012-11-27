package com.claro.sccweb.excel;

import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;

import com.claro.sccweb.excel.style.ExcelStyle;
import com.claro.sccweb.excel.style.SubTitleStyle;

/**
 * Exportador para planilhas Excel.
 * Componente genérico que gera planilhas Excel no padrão do SCC Evolution.
 *
 */
public class ExcelPrinter {
	
	/**
	 * Linhas do cabeçalho.
	 */
	private List<String> headerLines = new ArrayList<String>();
	
	/**
	 * Fontes inicializadas no sistema.
	 */
	private HSSFFont[] sheetFonts;
	
	/**
	 * Estilos iniciados no sistema para linhas pares.
	 */
	private HSSFCellStyle[] sheetStyles;
	
	/**
	 * Estilos iniciados no sistema para linhas ímpares.
	 */
	private HSSFCellStyle[] sheetOddStyles;
	
	/**
	 * Definição do conteúdo.
	 */
	private List<ExcelColumnDefinition> columns = new ArrayList<ExcelColumnDefinition>();
	
	/**
	 * Dados a serem transformados. Dados que serão listados na tabela.
	 */
	private List<Object> data = null;
	
	
	
	/**
	 * Estilo padrão para subtítulos.
	 */
	private ExcelStyle subtitleStyle = new SubTitleStyle();
	
	/**
	 * Objeto de dados que representa a pasta de trabalho atual.
	 */
	private HSSFWorkbook workbook;
	
	/**
	 * Linha atual dentro da planilha.
	 */
	private int currentRow;
	
	/**
	 * Planilha atual dentro da pasta de trabalho.
	 */
	private int currentSheet;
	
	/**
	 * Lista de planilhas.
	 */
	private List<HSSFSheet> sheetList = new ArrayList<HSSFSheet>();
	
	/**
	 * Definição do formato de exportação.
	 */
	private List<ExcelColumnDefinition> columnsDefinition = new ArrayList<ExcelColumnDefinition>();
	
	/**
	 * Quantidade de linhas para cor even e cor odd
	 */
	private int evenOddGroupQuantity = 1;
	
	/**
	 * Construtor
	 * @param columnsDefinition Definição de colunas.
	 */
	public ExcelPrinter(List<ExcelColumnDefinition> columnsDefinition)
	{
		this.workbook = new HSSFWorkbook();
		this.columnsDefinition = columnsDefinition;	
		sheetFonts = new HSSFFont[columnsDefinition.size()];
		initFonts();
		initStyles();
		initOddStyles();
	}
	
	/**
	 * Carrega uma nova definição de colunas e estilos sem criar uma nova pasta de trabalho.
	 * @param columnsDefinition Nova definição de colunas.
	 * @param reset Caso seja true a posição atual do cursos será reinicializada.
	 */
	public void setNewDefinition(List<ExcelColumnDefinition> columnsDefinition,boolean reset)
	{
		this.columnsDefinition = columnsDefinition;	
		sheetFonts = new HSSFFont[columnsDefinition.size()];
		initFonts();
		initStyles();
		initOddStyles();
		if (reset)
			reset();
	}

	
	/**
	 * Construtor com workbook já inicializado.
	 * @param columnsDefinition
	 * @param workbook
	 */
	public ExcelPrinter(List<ExcelColumnDefinition> columnsDefinition,HSSFWorkbook workbook)
	{
		this.workbook = workbook;
		this.columnsDefinition = columnsDefinition;		
		sheetFonts = new HSSFFont[columnsDefinition.size()];
		initFonts();
		initStyles();
		initOddStyles();
	}
	
	
	/**
	 * Inicia as fontes na pasta de trabalho.
	 */
	private void initFonts()
		{
		for (int i=0;i<columnsDefinition.size();i++)
			{
			ExcelColumnDefinition columnDefinition = columnsDefinition.get(i);
			HSSFFont font = workbook.createFont();
			font.setColor(columnDefinition.getStyle().getFontColor());
			font.setFontName(columnDefinition.getStyle().getFontName());
			font.setFontHeightInPoints(columnDefinition.getStyle().getFontHeight());
			font.setBoldweight(columnDefinition.getStyle().getBoldweight());
			sheetFonts[i] = font;
			}
		}
	
	/**
	 * Inicia os estilos de linhas pares na pasta de trabalho.
	 */
	private void initStyles()
		{
		sheetStyles = new HSSFCellStyle[columnsDefinition.size()];		
		for (int i=0;i<columnsDefinition.size();i++)
			{
			ExcelColumnDefinition columnDefinition = columnsDefinition.get(i);
			HSSFCellStyle style = workbook.createCellStyle();
			HSSFFont font = sheetFonts[i];			
			HSSFDataFormat format;
			style.setFont(font);
			style.setAlignment(columnDefinition.getStyle().getAlignment());
			style.setWrapText(columnDefinition.getStyle().getWrapText());
				if (columnDefinition.getStyle().getFormat() != null)
					{
					format = workbook.createDataFormat();
					style.setDataFormat(format.getFormat(columnDefinition.getStyle().getFormat()));
					}	
			sheetStyles[i] = style;
			}
		}
	
	/**
	 * Inicia estilo das linhas ímpares na pasta de trabalho.
	 */
	private void initOddStyles()
	{
	sheetOddStyles = new HSSFCellStyle[columnsDefinition.size()];		
	for (int i=0;i<columnsDefinition.size();i++)
		{
		ExcelColumnDefinition columnDefinition = columnsDefinition.get(i);
		HSSFCellStyle style = workbook.createCellStyle();
		HSSFFont font = sheetFonts[i];			
		HSSFDataFormat format;
		style.setFont(font);
		style.setAlignment(columnDefinition.getStyle().getAlignment());
		style.setWrapText(columnDefinition.getStyle().getWrapText());
		style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			if (columnDefinition.getStyle().getFormat() != null)
				{
				format = workbook.createDataFormat();
				style.setDataFormat(format.getFormat(columnDefinition.getStyle().getFormat()));
				}	
			sheetOddStyles[i] = style;
		}
	}
	
	/**
	 * Reinicia a posição do cursor.
	 */
	public void reset()
	{
		this.currentRow = 0;
		this.currentSheet = 0;
	}
	
	/**
	 * Reinicializa a posição da linha atual.
	 */
	public void resetRow()
	{
		this.currentRow = 0;
	}
	
	
	/**
	 * Define ('bufferiza') os dados que serão listados na tabela.
	 * @param data
	 */
	public void addData(List data)
	{
		this.data = data;
	}
	
	/**
	 * Escreve os dados 'bufferizados' na tabela.
	 * @throws Exception
	 */
	public void writeData() throws Exception
	{
		if (data == null)
			return;
		int relativePosition = 0;
		int mod = getEvenOddGroupQuantity();
		for (int i=0;i<data.size();i++)
			{
			if (i%mod == 0)
				relativePosition++;
			boolean isEven = (relativePosition%2==0);	
			Class clazz = data.get(i).getClass();			
			for (int d=0;d<columnsDefinition.size();d++)
				{
				clazz = data.get(i).getClass();
				Object theObject = null;
				ExcelColumnDefinition columnDefinition = columnsDefinition.get(d);
				String[] methodTree = columnDefinition.getProperty().split("\\.");
				if (methodTree.length > 1)
					{
					for (int j=0;j<methodTree.length-1;j++)
					{
					Method method = clazz.getDeclaredMethod(methodTree[j], null);
					if (theObject == null)
						theObject = method.invoke(data.get(i), null);
					else
						theObject = method.invoke(theObject, null);
					}	
					}
				else
					{
					theObject = data.get(i);
					}
				clazz = theObject.getClass();
				Method method = clazz.getDeclaredMethod(methodTree[methodTree.length-1], null);
				Object methodReturn = method.invoke(theObject, null);				
				HSSFRow row = sheetList.get(currentSheet).createRow((short) currentRow);
				HSSFCell cell = row.createCell((short)d);
				HSSFCellStyle style = null;														
				if (!isEven)					
					style = sheetOddStyles[d];					
				else					
					style = sheetStyles[d];
					
				cell.setCellStyle(style);
				if (methodReturn == null)
					cell.setCellValue("");
				else if (methodReturn instanceof String)
					cell.setCellValue((String)methodReturn);
				else if (methodReturn instanceof Date)
					cell.setCellValue((Date)methodReturn);
				else
					cell.setCellValue(methodReturn.toString());
				
				}			

			currentRow++;
			}
	}
	
	/**
	 * Escreve os dados 'bufferizados' na formatação de somatório (linhas brancas e última linha escura).
	 * @throws Exception
	 */
	public void writeSum() throws Exception
	{
		if (data == null)
			return;
		int relativePosition = 1;
			
		for (int i=0;i<data.size();i++)
			{	
			Class clazz = data.get(i).getClass();			
			for (int d=0;d<columnsDefinition.size();d++)
				{				
				ExcelColumnDefinition columnDefinition = columnsDefinition.get(d);
				Method method = clazz.getDeclaredMethod(columnDefinition.getProperty(), null);
				Object methodReturn = method.invoke(data.get(i), null);				
				HSSFRow row = sheetList.get(currentSheet).createRow((short) currentRow);
				HSSFCell cell = row.createCell((short)d);
				HSSFCellStyle style = null;														
				if (i == data.size()-1)					
					style = sheetOddStyles[d];					
				else					
					style = sheetStyles[d];					
				cell.setCellStyle(style);
				if (methodReturn == null)
					cell.setCellValue("");
				else if (methodReturn instanceof String)
					cell.setCellValue((String)methodReturn);
				else if (methodReturn instanceof Date)
					cell.setCellValue((Date)methodReturn);
				else
					cell.setCellValue(methodReturn.toString());
				
				}			
			relativePosition++;
			currentRow++;
			}
	}
	
	
	
	
	/**
	 * Configura as linhas de cabeçalho.
	 * @param headerLines
	 */
	public void setHeaderLines(List<String> headerLines) {
		this.headerLines = headerLines;
	}

	/**
	 * Escreve o cabeçalho na planilha ativa.
	 */
	public void generateHeader()
	{		
		if (headerLines != null)
			{			
			HSSFCellStyle style = workbook.createCellStyle();
			HSSFFont font = workbook.createFont();
			font.setColor(subtitleStyle.getFontColor());
			font.setFontName(subtitleStyle.getFontName());
			font.setFontHeightInPoints(subtitleStyle.getFontHeight());
			font.setBoldweight(subtitleStyle.getBoldweight());
			style.setFont(font);
			style.setAlignment(subtitleStyle.getAlignment());
			style.setWrapText(subtitleStyle.getWrapText());
			for (int i=0;i<headerLines.size();i++)
				{
				HSSFRow row = sheetList.get(currentSheet).createRow((short) currentRow);
				HSSFCell cell = row.createCell((short)0);		
				cell.setCellStyle(style);
				cell.setCellValue(headerLines.get(i));
				currentRow++;
				}
			}		
	}
	

	/**
	 * Gera os títulos das colunas na planilha ativa.
	 */
	public void generateColumnsTitle()
	{
		HSSFRow row = sheetList.get(currentSheet).createRow((short) currentRow);
		HSSFCellStyle style = workbook.createCellStyle();
		HSSFFont font = workbook.createFont();
		font.setColor(subtitleStyle.getFontColor());
		font.setFontName(subtitleStyle.getFontName());
		font.setFontHeightInPoints(subtitleStyle.getFontHeight());
		font.setBoldweight(subtitleStyle.getBoldweight());
		style.setFont(font);
		style.setAlignment(subtitleStyle.getAlignment());
		style.setWrapText(subtitleStyle.getWrapText());
		for (int i=0;i<columnsDefinition.size();i++)
			{			
			HSSFCell cell = row.createCell((short)i);
			cell.setCellStyle(style);
			cell.setCellValue(columnsDefinition.get(i).getTitle());
			sheetList.get(currentSheet).setColumnWidth((short)i, (short)(columnsDefinition.get(i).getWidth()*256));			
			}
		currentRow++;
	}
	
	/**
	 * Adiciona uma definição de coluna.
	 * @param columnDefinition
	 */
	public void addColumn(ExcelColumnDefinition columnDefinition)
	{
		columns.add(columnDefinition);
	}
	
	/**
	 * Cria uma nova planilha na pasta de trabalho e a torna ativa.
	 * @param sheetname
	 * @return
	 */
	public int addSheet(String sheetname)
	{
		reset();
		HSSFSheet sheet = workbook.createSheet(sheetname);		
		sheetList.add(sheet);
		currentSheet = sheetList.size()-1;
		return currentSheet;
		
	}
	
	
	/**
	 * Insere linhas em brancos
	 * @param size Quantidade a serem inseridas.
	 */
	public void addBlankLines(int size)
	{
		for (int i=0;i<size;i++)
		{
		HSSFRow row = sheetList.get(currentSheet).createRow((short) currentRow);
		HSSFCell cell = row.createCell((short)0);
		cell.setCellValue(" ");				
		currentRow++;
		}
	}
	

	/**
	 * 	
	 * Insere uma linha com os dados da lista de entrada
	 * @param line
	 * @param mergeRanges
	 * @param lineStyle
	 */
	public void addLine(List<String> line, List<Short> mergeRanges, ExcelStyle lineStyle)
	{
		if(line != null && line.size() > 0) 
		{
			HSSFRow row = sheetList.get(currentSheet).createRow((short) currentRow);
			HSSFCellStyle style = workbook.createCellStyle();
			HSSFFont font = workbook.createFont();
			
			ExcelStyle myStyle = null;
			if(lineStyle != null)
				myStyle = lineStyle;
			else
				myStyle = subtitleStyle;
			
			font.setColor(myStyle.getFontColor());
			font.setFontName(myStyle.getFontName());
			font.setFontHeightInPoints(myStyle.getFontHeight());
			font.setBoldweight(myStyle.getBoldweight());
			style.setFont(font);
			style.setAlignment(myStyle.getAlignment());
			style.setWrapText(myStyle.getWrapText());

			for (int i=0;i<line.size();i++)
			{
				HSSFCell cell = row.createCell((short)i);
				cell.setCellStyle(style);
				cell.setCellValue(line.get(i));				
			}
			
			if(mergeRanges != null)
				for(int i=0; i< mergeRanges.size(); i+=2)
					sheetList.get(currentSheet).addMergedRegion(new Region(currentRow, mergeRanges.get(i),currentRow,mergeRanges.get(i+1)));
			
			currentRow++;
		}
	}
	
	/**
	 * Exporta a pasta de trabalho para um arquivo.
	 * @param fileName
	 * @throws Exception
	 */
	public void generateFile(String fileName) throws Exception
	{
		FileOutputStream fos = new FileOutputStream(fileName);
		workbook.write(fos);
		fos.close();
	}

	public int getEvenOddGroupQuantity() {
		return evenOddGroupQuantity;
	}

	public void setEvenOddGroupQuantity(int evenOddGroupQuantity) {
		this.evenOddGroupQuantity = evenOddGroupQuantity;
	}
	
}
