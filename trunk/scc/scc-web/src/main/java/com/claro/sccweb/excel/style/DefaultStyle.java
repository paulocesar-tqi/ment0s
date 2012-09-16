package com.claro.sccweb.excel.style;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.util.HSSFColor;

public class DefaultStyle implements ExcelStyle {

	 
	public String getFontName() {
		return "Arial";
	}

	 
	public short getFontColor() {
		return HSSFColor.BLACK.index;
	}

	 
	public short getFontHeight() {
		return (short)8;
	}

	 
	public short getBoldweight() {		
		return 0;
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
