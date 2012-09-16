package com.claro.sccweb.excel.style;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;

public class SubTitleStyle implements  ExcelStyle {

	 
	public String getFontName() {
		return "Arial";
	}

	 
	public short getFontColor() {
		return HSSFColor.BLUE.index;
	}

	 
	public short getFontHeight() {
		return 8;
	}

	 
	public short getBoldweight() {
		return HSSFFont.BOLDWEIGHT_NORMAL;
	}

	 
	public short getAlignment() {
		return HSSFCellStyle.ALIGN_LEFT;
	}

	 
	public boolean getWrapText() {
		return true;
	}

	 
	public String getFormat() {		
		return null;
	}

	
	
}
