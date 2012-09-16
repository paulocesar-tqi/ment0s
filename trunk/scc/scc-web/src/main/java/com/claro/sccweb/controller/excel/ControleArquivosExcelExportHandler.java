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

import com.claro.cobillingweb.persistence.entity.external.ControlConnectFile;
import com.claro.sccweb.controller.BaseExcelFormHandler;

/**
 * Handler para conversão da pesquisa de arquivos do Connect em Excel.
 *
 */
public class ControleArquivosExcelExportHandler extends BaseExcelFormHandler {

	
	
	private final String [] _columnTitle = {
            "Id",                   
            "Nome do Arquivo",      
            "Código de Saída",      
            "Info de Saída",        
            "Início do Processo",   
            "Final do Processo"     
    };
	
	private final  int [] _columnSize = {
             7,      
             40,     
             7,      
             40,     
             14,     
             14      
     };
	 
	private final String [] _styleName = {
             ExcelGeneratorConstants.DEFAULT,        
             ExcelGeneratorConstants.DEFAULT,        
             ExcelGeneratorConstants.DEFAULT,        
             ExcelGeneratorConstants.DEFAULT,        
             ExcelGeneratorConstants.SIMPLE_DATE,    
             ExcelGeneratorConstants.SIMPLE_DATE     
     };
	
	 
	private final String _title = "Relatório de Controle de Arquivos";
	
	 
	protected void buildExcelDocument(Map<String, Object> model,HSSFWorkbook workbook, HttpServletRequest request,HttpServletResponse response) throws Exception {
		HSSFSheet sheet;		
		HSSFCellStyle style;
		String currStyle = null;
		HSSFCell cell;
		
		List<ControlConnectFile> items = getSessionDatamanager(request).getSearchResultList().getResult();		
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
            
         
            if (style==null) {
                style = (HSSFCellStyle) getStyleMap()
                    .get(ExcelGeneratorConstants.DEFAULT);
            }
            cell.setCellStyle(style);
			}
		/*Final da criação das colunas.*/
		
		/*Publicação de valores.*/
		for (int i=0;i<items.size();i++){
			ControlConnectFile item = items.get(i);
		short column = 0;
		
		  if (currStyle!=null) {
              if (!isEven) {
              	currStyle = ExcelGeneratorConstants.ODD+currStyle;
              }
              style = (HSSFCellStyle) getStyleMap().get(currStyle);
          }
		
		cell = row.getCell(column);
        cell.setCellValue(item.getPk().getPROC_NUMB());
        column++;
        
        cell = row.getCell(column);         
        if (item.getDEST_FILE() == null) {
        	cell.setCellValue("-");
        }else
        	{
        	cell.setCellValue(item.getDEST_FILE());
        	}
        	
        
        column++;
        
        cell = row.getCell(column);
        
        cell.setCellValue(item.getEXIT_CODE());
        column++;
        
        cell = row.getCell(column);
        
        if (item.getEXIT_DESC() == null) {
        	cell.setCellValue("-");
        }else
        	{
        	cell.setCellValue(item.getEXIT_DESC());
        	}
        
        column++;
        
        if (item.getPk().getSTRT_DATE()!=null) {
            cell = row.getCell(column);
            cell.setCellValue(item.getPk().getSTRT_DATE());
        }
        column++;
        
        if (item.getSTOP_DATE()!=null) {
            cell = row.getCell(column);
            cell.setCellValue(item.getSTOP_DATE());
        }
        
        cursor++;
		/*Final da publicação de valores.*/
		}
		
	}

	
	
	
	
}
