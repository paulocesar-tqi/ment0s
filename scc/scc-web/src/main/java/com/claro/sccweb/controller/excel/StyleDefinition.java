package com.claro.sccweb.controller.excel;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

/**
 * Importado do SCC atual.
 *
 */
public class StyleDefinition {

	 public static Map getDefinitions(HSSFWorkbook wb) {
	        Map map = new HashMap();
	        HSSFCellStyle style;
	        HSSFDataFormat format;
	        short formatCode;
	        HSSFFont title;
	        HSSFFont subtitle;
	        HSSFFont subtitlecenter;
	        HSSFFont subtitlecenterblack;
	        HSSFFont font;
	        HSSFFont totalizador;

	        // TITLE Font
	        title = wb.createFont();
	        title.setColor(HSSFColor.RED.index);
	        title.setFontName(ExcelGeneratorConstants.FONT_NAME);
	        title.setFontHeightInPoints(ExcelGeneratorConstants
	                .TITLE_FONT_SIZE);
	        title.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

	        // SUBTITLE Font
	        subtitle = wb.createFont();
	        subtitle.setColor(HSSFColor.ROYAL_BLUE.index);
	        subtitle.setFontName(ExcelGeneratorConstants.FONT_NAME);
	        subtitle.setFontHeightInPoints(ExcelGeneratorConstants
	                .SUBTITLE_FONT_SIZE);
	        subtitle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

	        // SUBTITLECENTER Font
	        subtitlecenter = wb.createFont();
	        subtitlecenter.setColor(HSSFColor.ROYAL_BLUE.index);
	        subtitlecenter.setFontName(ExcelGeneratorConstants.FONT_NAME);
	        subtitlecenter.setFontHeightInPoints(ExcelGeneratorConstants
	                .SUBTITLE_FONT_SIZE);
	        subtitlecenter.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

	        // subtitlecenterblack Font
	        subtitlecenterblack = wb.createFont();
	        subtitlecenterblack.setColor(HSSFColor.BLACK.index);
	        subtitlecenterblack.setFontName(ExcelGeneratorConstants.FONT_NAME);
	        subtitlecenterblack.setFontHeightInPoints(ExcelGeneratorConstants
	                .SUBTITLE_FONT_SIZE);
	        subtitlecenterblack.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

	        // totalizador Font
	        totalizador = wb.createFont();
	        totalizador.setColor(HSSFColor.RED.index);
	        totalizador.setFontName(ExcelGeneratorConstants.FONT_NAME);
	        totalizador.setFontHeightInPoints(ExcelGeneratorConstants
	                .SUBTITLE_FONT_SIZE);
	        totalizador.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

	        // DEFAULT Font
	        font = wb.createFont();
	        font.setColor(HSSFColor.BLACK.index);
	        font.setFontName(ExcelGeneratorConstants.FONT_NAME);
	        font.setFontHeightInPoints(ExcelGeneratorConstants.FONT_SIZE);

	        // TITLE Style
	        style = wb.createCellStyle();
	        style.setFont(title);
	        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        style.setWrapText(true);
	        map.put(ExcelGeneratorConstants.TITLE, style);

	        // SUBTITLE Style
	        style = wb.createCellStyle();
	        style.setFont(subtitle);
	        style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
	        style.setWrapText(true);
	        map.put(ExcelGeneratorConstants.SUBTITLE, style);
	        
	        // SUBTITLECENTER Style
	        style = wb.createCellStyle();
	        style.setFont(subtitlecenter);
	        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        style.setWrapText(true);
	        map.put(ExcelGeneratorConstants.SUBTITLECENTER, style);
	        
	        // OS 60924 - Eduardo Chagas
	        // SUBTITLECENTERBORDER Style
	        style = wb.createCellStyle();
	        style.setFont(subtitlecenter);
	        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        style.setWrapText(true);
	        style.setBorderBottom((short)2);
	        style.setBorderLeft((short)2);
	        style.setBorderRight((short)2);
	        style.setBorderTop((short)2);
	        map.put(ExcelGeneratorConstants.SUBTITLECENTERBORDER, style);
	        
	        // SUBTITLECENTERBORDERBLACK Style
	        style = wb.createCellStyle();
	        style.setFont(subtitlecenterblack);
	        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        style.setWrapText(true);
	        style.setBorderBottom((short)2);
	        style.setBorderLeft((short)2);
	        style.setBorderRight((short)2);
	        style.setBorderTop((short)2);
	        map.put(ExcelGeneratorConstants.SUBTITLECENTERBORDERBLACK, style);
	        
	        // SUBTITLERIGHT Style
	        style = wb.createCellStyle();
	        style.setFont(subtitlecenter);
	        style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
	        style.setWrapText(true);
	        map.put(ExcelGeneratorConstants.SUBTITLERIGHT, style);
	        
	        // DEFAULT Style
	        style = wb.createCellStyle();
	        style.setFont(font);
	        style.setWrapText(true);
	        map.put(ExcelGeneratorConstants.DEFAULT, style);

	        // DEFAULTBORDER Style
	        style = wb.createCellStyle();
	        style.setFont(font);
	        style.setWrapText(true);
	        style.setBorderBottom((short)2);
	        style.setBorderLeft((short)2);
	        style.setBorderRight((short)2);
	        style.setBorderTop((short)2);
	        map.put(ExcelGeneratorConstants.DEFAULTBORDER, style);

	        // DEFAULTBORDER Style - RF4 OS69701
	        style = wb.createCellStyle();
	        format = wb.createDataFormat();
	        formatCode = format.getFormat("#,##0.00");
	        style.setDataFormat(formatCode);
	        style.setFont(font);
	        style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
	        style.setWrapText(true);
	        style.setBorderBottom((short)2);
	        style.setBorderLeft((short)2);
	        style.setBorderRight((short)2);
	        style.setBorderTop((short)2);
	        map.put(ExcelGeneratorConstants.DEFAULTBORDER+
	        		ExcelGeneratorConstants.RIGHTINTEGER, style);    
	        
	        // ODD DEFAULT Style
	        style = wb.createCellStyle();
	        style.setFont(font);
	        style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
	        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	        style.setWrapText(true);
	        map.put(ExcelGeneratorConstants.ODD+
	                ExcelGeneratorConstants.DEFAULT, style);
	        
	        // ODD DEFAULTBORDER Style
	        style = wb.createCellStyle();
	        style.setFont(font);
	        style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
	        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	        style.setWrapText(true);
	        style.setBorderBottom((short)2);
	        style.setBorderLeft((short)2);
	        style.setBorderRight((short)2);
	        style.setBorderTop((short)2);
	        map.put(ExcelGeneratorConstants.ODD+
	        		ExcelGeneratorConstants.DEFAULTBORDER, style);

	        // ODD DEFAULTBORDER Style - RF4 OS69701
	        style = wb.createCellStyle();
	        format = wb.createDataFormat();
	        formatCode = format.getFormat("#,##0.00");
	        style.setDataFormat(formatCode);
	        style.setFont(font);
	        style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
	        style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
	        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	        style.setWrapText(true);
	        style.setBorderBottom((short)2);
	        style.setBorderLeft((short)2);
	        style.setBorderRight((short)2);
	        style.setBorderTop((short)2);
	        map.put(ExcelGeneratorConstants.ODD+
	        		ExcelGeneratorConstants.DEFAULTBORDER+
	        		ExcelGeneratorConstants.RIGHTINTEGER, style);
	        
	        // DATE Style
	        style = wb.createCellStyle();
	        format = wb.createDataFormat();
	        formatCode = format.getFormat("dd/mm/yyyy hh:mm");
	        style.setDataFormat(formatCode);
	        style.setFont(font);
	        style.setWrapText(true);
	        map.put(ExcelGeneratorConstants.DATE, style);

	        // ODD DATE Style
	        style = wb.createCellStyle();
	        format = wb.createDataFormat();
	        formatCode = format.getFormat("dd/mm/yyyy hh:mm");
	        style.setDataFormat(formatCode);
	        style.setFont(font);
	        style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
	        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	        style.setWrapText(true);
	        map.put(ExcelGeneratorConstants.ODD+
	                ExcelGeneratorConstants.DATE, style);

	        // SIMPLE_DATE Style
	        style = wb.createCellStyle();
	        format = wb.createDataFormat();
	        formatCode = format.getFormat("dd/mm/yyyy");
	        style.setDataFormat(formatCode);
	        style.setFont(font);
	        style.setWrapText(true);
	        map.put(ExcelGeneratorConstants.SIMPLE_DATE, style);

	        // ODD SIMPLE_DATE Style
	        style = wb.createCellStyle();
	        format = wb.createDataFormat();
	        formatCode = format.getFormat("dd/mm/yyyy");
	        style.setDataFormat(formatCode);
	        style.setFont(font);
	        style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
	        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	        style.setWrapText(true);
	        map.put(ExcelGeneratorConstants.ODD+
	                ExcelGeneratorConstants.SIMPLE_DATE, style);

	        // CURRENCY Style
	        style = wb.createCellStyle();
	        format = wb.createDataFormat();
	        formatCode = format.getFormat("#,##0.00");
	        style.setDataFormat(formatCode);
	        style.setFont(font);
	        style.setWrapText(true);
	        map.put(ExcelGeneratorConstants.CURRENCY, style);

	        // CURRENCY Style
	        style = wb.createCellStyle();
	        format = wb.createDataFormat();
	        formatCode = format.getFormat("#,##0.0");
	        style.setDataFormat(formatCode);
	        style.setFont(font);
	        style.setWrapText(true);
	        map.put(ExcelGeneratorConstants.CURRENCY1, style);

	        // CURRENCY Style
	        style = wb.createCellStyle();
	        format = wb.createDataFormat();
	        formatCode = format.getFormat("#,##0.0");
	        style.setDataFormat(formatCode);
	        style.setFont(font);
	        style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
	        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	        style.setWrapText(true);
	        map.put(ExcelGeneratorConstants.ODD+
	                ExcelGeneratorConstants.CURRENCY1, style);
	        
	        // CURRENCY Style
	        style = wb.createCellStyle();
	        format = wb.createDataFormat();
	        formatCode = format.getFormat("#,##0.00");
	        style.setDataFormat(formatCode);
	        style.setFont(font);
	        style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
	        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	        style.setWrapText(true);
	        map.put(ExcelGeneratorConstants.ODD+
	                ExcelGeneratorConstants.CURRENCY, style);
	        
	        // CURRENCY1 Style Align Left
	        style = wb.createCellStyle();
	        format = wb.createDataFormat();
	        formatCode = format.getFormat("#,##0.0");
	        style.setFont(font);
	        style.setDataFormat(formatCode);
	        style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
	        style.setWrapText(true);
	        map.put(ExcelGeneratorConstants.CURRENCY1+
	        		ExcelGeneratorConstants.LEFTDOUBLE, style);

	        // DEFAULT Style Align Left
	        style = wb.createCellStyle();
	        format = wb.createDataFormat();
	        formatCode = format.getFormat("#,##0.00");
	        style.setFont(font);
	        style.setDataFormat(formatCode);
	        style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
	        style.setWrapText(true);
	        map.put(ExcelGeneratorConstants.DEFAULT+
	        		ExcelGeneratorConstants.LEFTDOUBLE, style);

	        // ODD DEFAULT Style Align Left
	        style = wb.createCellStyle();
	        format = wb.createDataFormat();
	        formatCode = format.getFormat("#,##0.00");
	        style.setDataFormat(formatCode);
	        style.setFont(font);
	        style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
	        style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
	        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	        style.setWrapText(true);
	        map.put(ExcelGeneratorConstants.ODD+
	                ExcelGeneratorConstants.DEFAULT+
	        		ExcelGeneratorConstants.LEFTDOUBLE, style);
	        
	        // ODD CURRENCY1 Style Align Left
	        style = wb.createCellStyle();
	        format = wb.createDataFormat();
	        formatCode = format.getFormat("#,##0.0");
	        style.setDataFormat(formatCode);
	        style.setFont(font);
	        style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
	        style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
	        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	        style.setWrapText(true);
	        map.put(ExcelGeneratorConstants.ODD+
	                ExcelGeneratorConstants.CURRENCY1+
	        		ExcelGeneratorConstants.LEFTDOUBLE, style);
	        
	        // DEFAULT Style Align Right
	        style = wb.createCellStyle();
	        format = wb.createDataFormat();
	        formatCode = format.getFormat("#,##0.00");
	        style.setDataFormat(formatCode);
	        style.setFont(font);
	        style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
	        style.setWrapText(true);
	        map.put(ExcelGeneratorConstants.DEFAULT+
	        		ExcelGeneratorConstants.RIGHTDOUBLE, style);

	        // CURRENCY1 Style Align Right
	        style = wb.createCellStyle();
	        format = wb.createDataFormat();
	        formatCode = format.getFormat("#,##0.0");
	        style.setDataFormat(formatCode);
	        style.setFont(font);
	        style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
	        style.setWrapText(true);
	        map.put(ExcelGeneratorConstants.CURRENCY1+
	        		ExcelGeneratorConstants.RIGHTDOUBLE, style);
	        
	        // ODD DEFAULT Style Align Right
	        style = wb.createCellStyle();
	        format = wb.createDataFormat();
	        formatCode = format.getFormat("#,##0.00");
	        style.setDataFormat(formatCode);
	        style.setFont(font);
	        style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
	        style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
	        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	        style.setWrapText(true);
	        map.put(ExcelGeneratorConstants.ODD+
	                ExcelGeneratorConstants.DEFAULT+
	        		ExcelGeneratorConstants.RIGHTDOUBLE, style);
	        
	        // ODD CURRENCY1 Style Align Right
	        style = wb.createCellStyle();
	        format = wb.createDataFormat();
	        formatCode = format.getFormat("#,##0.0");
	        style.setDataFormat(formatCode);
	        style.setFont(font);
	        style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
	        style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
	        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	        style.setWrapText(true);
	        map.put(ExcelGeneratorConstants.ODD+
	                ExcelGeneratorConstants.CURRENCY1+
	        		ExcelGeneratorConstants.RIGHTDOUBLE, style);
	        
	        // DEFAULT Style Align Left
	        style = wb.createCellStyle();
	        style.setDataFormat(formatCode);
	        style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
	        style.setWrapText(true);
	        map.put(ExcelGeneratorConstants.DEFAULT+
	        		ExcelGeneratorConstants.LEFTINTEGER, style);

	        // ODD DEFAULT Style Align Left
	        style = wb.createCellStyle();
	        style.setFont(font);
	        style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
	        style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
	        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	        style.setWrapText(true);
	        map.put(ExcelGeneratorConstants.ODD+
	                ExcelGeneratorConstants.DEFAULT+
	        		ExcelGeneratorConstants.LEFTINTEGER, style);
	        
	        // DEFAULT Style Align Right
	        style = wb.createCellStyle();
	        format = wb.createDataFormat();
	        formatCode = format.getFormat("###,##0");
	        style.setDataFormat(formatCode);
	        style.setFont(font);
	        style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
	        style.setWrapText(true);
	        map.put(ExcelGeneratorConstants.DEFAULT+
	        		ExcelGeneratorConstants.RIGHTINTEGER, style);
	        
	        // ODD DEFAULT Style Align Right
	        style = wb.createCellStyle();
	        format = wb.createDataFormat();
	        formatCode = format.getFormat("###,##0");
	        style.setDataFormat(formatCode);
	        style.setFont(font);
	        style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
	        style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
	        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	        style.setWrapText(true);
	        map.put(ExcelGeneratorConstants.ODD+
	                ExcelGeneratorConstants.DEFAULT+
	        		ExcelGeneratorConstants.RIGHTINTEGER, style);
	        
	        // ALIGN LEFT AND CENTRALIZE VERTICALLY CENTER
	        style = wb.createCellStyle();
	        style.setFont(font);
	        style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
	        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        style.setWrapText(true);
	        map.put(ExcelGeneratorConstants.LEFT_CENTER_VERTICALLY, style);
	        
	        // ALIGN LEFT AND CENTRALIZE VERTICALLY CENTER
	        style = wb.createCellStyle();
	        font.setUnderline(HSSFFont.U_SINGLE);
	        style.setFont(font);
	        style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
	        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        
	        map.put(ExcelGeneratorConstants.LEFT_CENTER_VERTICALLY, style);
	        
	        // ALIGN RIGHT
	        style = wb.createCellStyle();
	        font.setUnderline(HSSFFont.U_SINGLE);
	        style.setFont(font);
	        style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
	        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        
	        map.put(ExcelGeneratorConstants.RIGHT_CENTER_VERTICALLY, style);
	        
	        // DEFAULT Style
	        style = wb.createCellStyle();
	        font.setUnderline(HSSFFont.U_NONE);
	        style.setFont(font);
	        style.setWrapText(true);
	        map.put(ExcelGeneratorConstants.NORMAL_DEFAULT, style);
	        
	        // TOTAL DEFAULT Style
	        style = wb.createCellStyle();
	        style.setFont(totalizador);
	        style.setWrapText(true);
	        map.put(ExcelGeneratorConstants.TOTAL + ExcelGeneratorConstants.DEFAULT, style);
	        
	        // TOTAL CURRENCY1 Style
	        style = wb.createCellStyle();
	        style.setFont(totalizador);
	        style.setWrapText(true);
	        appendStyle(wb, style, ExcelGeneratorConstants.ODD);
	        appendStyle(wb, style, ExcelGeneratorConstants.CURRENCY);
	        map.put(ExcelGeneratorConstants.TOTAL + ExcelGeneratorConstants.CURRENCY1, style);

	        // TOTAL CURRENCY Style
	        style = wb.createCellStyle();
	        style.setFont(totalizador);
	        style.setWrapText(true);
	        appendStyle(wb, style, ExcelGeneratorConstants.ODD);
	        appendStyle(wb, style, ExcelGeneratorConstants.CURRENCY);
	        map.put(ExcelGeneratorConstants.TOTAL + ExcelGeneratorConstants.CURRENCY, style);
	        
	        // TOTAL ODD DEFAULT Style
	        style = wb.createCellStyle();
	        style.setFont(totalizador);
	        style.setWrapText(true);
	        appendStyle(wb, style, ExcelGeneratorConstants.ODD);
	        map.put(ExcelGeneratorConstants.TOTAL + ExcelGeneratorConstants.ODD + ExcelGeneratorConstants.DEFAULT, style);
	        
	        // TOTAL ODD CURRENCY1 Style
	        style = wb.createCellStyle();
	        style.setFont(totalizador);
	        style.setWrapText(true);
	        appendStyle(wb, style, ExcelGeneratorConstants.ODD);
	        appendStyle(wb, style, ExcelGeneratorConstants.CURRENCY1);
	        map.put(ExcelGeneratorConstants.TOTAL + ExcelGeneratorConstants.ODD + ExcelGeneratorConstants.CURRENCY1, style);

	        // TOTAL ODD CURRENCY Style
	        style = wb.createCellStyle();
	        style.setFont(totalizador);
	        style.setWrapText(true);
	        appendStyle(wb, style, ExcelGeneratorConstants.ODD);
	        appendStyle(wb, style, ExcelGeneratorConstants.CURRENCY);
	        map.put(ExcelGeneratorConstants.TOTAL + ExcelGeneratorConstants.ODD + ExcelGeneratorConstants.CURRENCY, style);

	        
	        return map;
	    }
	    
	    private static void appendStyle(HSSFWorkbook wb, HSSFCellStyle style, String styleAppend) {
	    	if (styleAppend.equalsIgnoreCase(ExcelGeneratorConstants.CURRENCY)) {
	            HSSFDataFormat format = wb.createDataFormat();
	            short formatCode = format.getFormat("#,##0.00");
	            style.setDataFormat(formatCode);
	    	} else  if (styleAppend.equalsIgnoreCase(ExcelGeneratorConstants.CURRENCY1)) {
	            HSSFDataFormat format = wb.createDataFormat();
	            short formatCode = format.getFormat("#,##0.0");
	            style.setDataFormat(formatCode);    		
	    	} else  if (styleAppend.equalsIgnoreCase(ExcelGeneratorConstants.ODD)) {
	            style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
	            style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);	
	    	}
	    }
}
