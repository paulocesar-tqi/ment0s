package com.claro.sccweb.controller.excel;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.claro.cobillingweb.persistence.entity.SccArquivoCobilling;
import com.claro.sccweb.controller.BaseExcelFormHandler;

public class RecepcaoTransmissaoExcelExportHandler extends BaseExcelFormHandler {

	 String [] _columnTitle = {
             "Dt Proc Claro",
             "Dt Proc PPC",
             "Dt Connect",
             "Dt Referencia",
             "Nome",
             "Remessa",
             "Retorno",
             "Protocolo",
             "Dummy",
             "Duração Tarifada",
             "Quantidade",
             "Valor Liquido",
             "Valor Bruto",  
             "Erro Protocolo",
             "Desc Erro Protocolo"
             };
	 
	 int [] _columnSize = {
             14, // "Dt Proc Claro",
             14, // "Dt Proc PPC",
             14, // "Dt Connect",
             14, // "Dt Referencia",
             45, // "Nome",
             50, // "Remessa",
             45, // "Retorno",
             45, // "Protocolo",
             10, // "Procotolo Dummy",
             20, // "Duração Tarifada",
             12, // "Quantidade",
             12, // "Valor Liquido",
             12, // "Valor Bruto",
             30, // "Erro Protocolo",
             45  // "Desc Erro Protocolo"
     };
	 
	 private final String _title = "Relatório de Recepção e Transmissão";
	 
	 String [] _styleName = {
     		ExcelGeneratorConstants.SIMPLE_DATE, // "Dt Proc Claro",
     		ExcelGeneratorConstants.SIMPLE_DATE, // "Dt Proc PPC",
     		ExcelGeneratorConstants.SIMPLE_DATE, // "Dt Connect",
             ExcelGeneratorConstants.SIMPLE_DATE, // "Dt Referencia",
     		ExcelGeneratorConstants.DEFAULT, 	 // "Nome",
     		ExcelGeneratorConstants.DEFAULT, 	 // "Remessa",
     		ExcelGeneratorConstants.DEFAULT, 	 // "Retorno",
     		ExcelGeneratorConstants.DEFAULT, 	 // "Protocolo",
     		ExcelGeneratorConstants.DEFAULT, 	 // "Procotolo Dummy",
     		ExcelGeneratorConstants.DEFAULT, 	 // "Duração Tarifada",
     		ExcelGeneratorConstants.DEFAULT, 	 // "Quantidade",
     		ExcelGeneratorConstants.CURRENCY, 	 // "Valor Liquido",
     		ExcelGeneratorConstants.CURRENCY, 	 // "Valor Bruto",
     		ExcelGeneratorConstants.DEFAULT, 	 // "Erro Protocolo",
     		ExcelGeneratorConstants.DEFAULT 	 // "Desc Erro Protocolo"
     };
	
	 
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {		
		
		HSSFSheet sheet;		
		HSSFCellStyle style;
		String currStyle = null;
		HSSFCell cell;
		
		List<SccArquivoCobilling> items = getSessionDatamanager(request).getSearchResultList().getResult();		
		if (items == null)
			return;
		
		int cursor = 5;
		boolean isEven = (cursor%2==0);
		
		setStyleMap(StyleDefinition.getDefinitions(workbook));
		setColumnTitle(_columnTitle);
		setColumnSize(_columnSize);
		setStyleName(_styleName);
		setTitle(_title);
		
		sheet = workbook.createSheet("Resultados");
		writeTitle(sheet);	
		
		HSSFRow row = sheet.createRow((short) cursor);
		
		/*Criação das colulas.*/
		for (short cont=0; cont < getColumnTitle().length; cont++)
			{
			style = null;
            cell = row.createCell(cont);
            currStyle = getStyleName()[cont];
            
            if (currStyle!=null) {
                if (!isEven) {
                	currStyle = ExcelGeneratorConstants.ODD+currStyle;
                }
                style = (HSSFCellStyle) getStyleMap().get(currStyle);
            }
            
            if (style==null) {
                style = (HSSFCellStyle) getStyleMap()
                    .get(ExcelGeneratorConstants.DEFAULT);
            }
            cell.setCellStyle(style);
			}
		/*Final da criação das colunas.*/
		
		/*Publicação de valores.*/
		
		String s = null;
		cursor++;
		for (int i=0;i<items.size();i++)
			{			
			 row = sheet.createRow((short) cursor);
			SccArquivoCobilling item = items.get(i);
			short column = 0;
			
			  if (currStyle!=null) {
	              if (!isEven) {
	              	currStyle = ExcelGeneratorConstants.ODD+currStyle;
	              }
	              style = (HSSFCellStyle) getStyleMap().get(currStyle);
	          }
			
			  if (item.getDtProcClaro()!=null) {
	            	cell = row.createCell(column);
	            	cell.setCellValue(item.getDtProcClaro());
	            }
	            column++;
	            
	            if (item.getDtCarga()!=null) {
	            	cell = row.createCell(column);
	            	cell.setCellValue(item.getDtCarga());
	            }
	            column++;
	            
	            if (item.getDtConnect()!=null) {
		            cell = row.createCell(column);
		            cell.setCellValue(item.getDtConnect());
	            }
	            column++;
	            
	            if (item.getDtProcExterna()!=null) {
	                cell = row.createCell(column);
	                cell.setCellValue(item.getDtProcExterna());
	            }
	            column++;
	            
	            cell = row.createCell(column);
	            s = item.getNoArquivo();
	            if (s == null) {
	            	s = "-";
	            }
	            cell.setCellValue(s);
	            column++;
	            
	            cell = row.createCell(column);
	            s = item.getNoArquivoIfc();
	            if (s == null) {
	            	s = "-";
	            }
	            cell.setCellValue(s);
	            column++;
	            
	            cell = row.createCell(column);
	            s = item.getNoArquivoTrans();
	            if (s == null) {
	            	s = "-";
	            }
	            cell.setCellValue(s);
	            column++;
	            
	            cell = row.createCell(column);
	            if (item.getArquivoOrigem() != null)
	            	{
	            	s = item.getArquivoOrigem().getNoArquivo();
	            	}
	            else
	            	{
	            	s = "-";
	            	}	            
	            cell.setCellValue(s);
	            column++;
	            
	            cell = row.createCell(column);
	            s = "-";
	            
	            cell.setCellValue(s);
	            column++;
	                        
	            cell = row.createCell(column);
	            if (item.getQtDuracaoTarifada() != null)
	            	cell.setCellValue(item.getQtDuracaoTarifada());
	            else
	            	cell.setCellValue("-");
	            column++;
	        	
	            cell = row.createCell(column);
	            cell.setCellValue(item.getQtRegistros());
	            column++;
	        	
	            cell = row.createCell(column);
	            if (item.getVlLiquidoArquivo() != null)
	            	cell.setCellValue(item.getVlLiquidoArquivo());
	            else
	            	cell.setCellValue(0);
	            column++;
	        	
	            cell = row.createCell(column);
	            
	            if (item.getVlBrutoArquivo() != null)
	            	cell.setCellValue(item.getVlBrutoArquivo());
	            else
	            	cell.setCellValue(0);
	            column++;
	        	
	            cell = row.createCell(column);
	            s = item.getCdErroProtocolo();
	            if (s == null) {
	            	s = "-";
	            }
	            cell.setCellValue(s);
	            column++;
	        	
	            cell = row.createCell(column);
	            s = item.getCdErroProtocolo();
	            if (s == null) {
	            	s = "-";
	            }
	            cell.setCellValue(s);
	            column++;
	                        
	            cursor++;
	           
			}
		}
		
	}


