package com.claro.sccweb.controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.util.Region;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.claro.sccweb.controller.excel.ExcelGeneratorConstants;
import com.claro.sccweb.service.SessionDataManager;

public abstract class BaseExcelFormHandler extends AbstractExcelView {

	protected static String creationDateText = "Data de Criação:";
	
	private String [] columnTitle;
	private int [] columnSize;
	private String [] styleName;
	private String title;
	private Map styleMap;
	
	 protected SessionDataManager getSessionDatamanager(HttpServletRequest request)
	 {
		 return (SessionDataManager)request.getSession(false).getAttribute("scopedTarget.sessionDataManager");
	 }

	public static String getCreationDateText() {
		return creationDateText;
	}

	public static void setCreationDateText(String creationDateText) {
		BaseExcelFormHandler.creationDateText = creationDateText;
	}

	public String[] getColumnTitle() {
		return columnTitle;
	}

	public void setColumnTitle(String[] columnTitle) {
		this.columnTitle = columnTitle;
	}

	public int[] getColumnSize() {
		return columnSize;
	}

	public void setColumnSize(int[] columnSize) {
		this.columnSize = columnSize;
	}

	public String[] getStyleName() {
		return styleName;
	}

	public void setStyleName(String[] styleName) {
		this.styleName = styleName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	


public Map getStyleMap() {
		return styleMap;
	}

	public void setStyleMap(Map styleMap) {
		this.styleMap = styleMap;
	}
	
	protected void writeTitle(HSSFSheet sheet) {
        

        short size = (short) getColumnTitle().length;

        Region region = new Region(0,(short)0, 0, (short)(size-1));
        sheet.addMergedRegion(region);

        HSSFCellStyle titleStyle = (HSSFCellStyle) styleMap
                .get(ExcelGeneratorConstants.TITLE);
        HSSFCellStyle subtitleStyle = (HSSFCellStyle) styleMap
                .get(ExcelGeneratorConstants.SUBTITLE);
        HSSFCellStyle defaultStyle = (HSSFCellStyle) styleMap
                .get(ExcelGeneratorConstants.DEFAULT);
        HSSFCellStyle dateStyle = (HSSFCellStyle) styleMap
                .get(ExcelGeneratorConstants.DATE);

        HSSFRow row = sheet.createRow((short) 0);
        HSSFCell cell = row.createCell((short) 0);

        if (getTitle() != null) {
            cell.setCellValue("Claro - " + getTitle());
        } else {
            cell.setCellValue("Claro");
        }
        cell.setCellStyle(titleStyle);

        row = sheet.createRow((short) 2);
        region = new Region(2,(short)0, 2, (short)1);
        sheet.addMergedRegion(region);
        region = new Region(2,(short)2, 2, (short)3);
        sheet.addMergedRegion(region);
        cell = row.createCell((short) 0);
        cell.setCellValue(creationDateText);
        cell.setCellStyle(defaultStyle);
        cell = row.createCell((short) 2);
        cell.setCellValue(new Date(System.currentTimeMillis()));
        cell.setCellStyle(dateStyle);

        row = sheet.createRow((short) 4);
        for (short column=0; column < size; column++) {
            cell = row.createCell(column);
            cell.setCellValue(getColumnTitle()[column]);
            cell.setCellStyle(subtitleStyle);
            sheet.setColumnWidth(column, (short)(columnSize[column]*256));
        }

    }
	 
}
