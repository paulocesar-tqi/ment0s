package com.claro.sccweb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;
import com.claro.sccweb.excel.style.DateStyle;
import com.claro.sccweb.excel.style.DefaultStyle;
import com.claro.sccweb.excel.style.ExcelStyle;

public class TesteExcel {

	private HSSFWorkbook workbook;
	
	public void teste()	{
		
		List<Object> data = new ArrayList<Object>();
		SccOperadora operadora1 = new SccOperadora();
		operadora1.setCdEot("001");
		operadora1.setCdCsp("122");
		operadora1.setDsOperadora("Embratel");
		operadora1.setDtAtualizacao(new Date());
		data.add(operadora1);
		
		SccOperadora operadora2 = new SccOperadora();
		operadora2.setCdEot("006");
		operadora2.setCdCsp("685");
		operadora2.setDsOperadora("Claro Digiral");
		operadora2.setDtAtualizacao(new Date());
		data.add(operadora2);
		
		SccOperadora operadora3 = new SccOperadora();
		operadora3.setCdEot("437");
		operadora3.setCdCsp("877");
		operadora3.setDsOperadora("Tess");
		operadora3.setDtAtualizacao(new Date());
		data.add(operadora3);
		
		
		SccOperadora operadora4 = new SccOperadora();
		operadora4.setCdEot("888");
		operadora4.setCdCsp("698");
		operadora4.setDsOperadora("BCP - MA");
		operadora4.setDtAtualizacao(new Date());
		data.add(operadora4);
		
		ExcelStyle style = new DefaultStyle();
		ExcelStyle dateStyle = new DateStyle();
		
		List<ExcelColumnDefinition> stub = new ArrayList<ExcelColumnDefinition>();
		stub.add(new ExcelColumnDefinition("getCdEot","Cod. EOT",style,10));
		stub.add(new ExcelColumnDefinition("getCdCsp","Cod. CSP",style,10));
		stub.add(new ExcelColumnDefinition("getDsOperadora","Operadora",style,100));
		stub.add(new ExcelColumnDefinition("getDtAtualizacao","Atualização",dateStyle,100));
		
		try {
			ExcelPrinter definition = new ExcelPrinter(stub);
			definition.addSheet("Teste");
			definition.generateColumnsTitle();
			definition.addBlankLines(10);
			
			definition.generateColumnsTitle();
			definition.addData(data);
			definition.writeData();
			definition.generateFile("C:/teste1.xls");
		} catch (Exception e)
			{
			e.printStackTrace();
			}
	}
	
	public static void main(String[] args) {
		TesteExcel t = new TesteExcel();
		t.teste();
	}
	
}
