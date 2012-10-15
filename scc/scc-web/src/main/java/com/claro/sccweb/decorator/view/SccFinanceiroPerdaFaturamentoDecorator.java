package com.claro.sccweb.decorator.view;

import org.apache.commons.lang.StringUtils;

import com.claro.cobillingweb.persistence.entity.SccArquivoSumarizado;
import com.claro.sccweb.decorator.rownum.RownumDecorator;
import com.claro.sccweb.vo.PerdaFaturamentoVO;


public class SccFinanceiroPerdaFaturamentoDecorator extends RownumDecorator<PerdaFaturamentoVO> {
	

	public SccFinanceiroPerdaFaturamentoDecorator(PerdaFaturamentoVO entity,
			int rownum) {
		super(entity, rownum);
		
	}
	
	public String getDtProcExterna(){
		String value = "";
		if(getRow().getDtProcExterna() != null){
			value = formataDate(getRow().getDtProcExterna());
		}
		
		return value;
	}
	
	public String getOperadoraLd(){
		String value = "";
		if (StringUtils.isNotEmpty(getRow().getOperadoraLd())){
			value = getRow().getOperadoraLd();
		}
		return value;
	}
	
	public String getOperadoraClaro(){
		String value = "";
		if(StringUtils.isNotEmpty(getRow().getOperadoraClaro())){
			value = getRow().getOperadoraClaro();
		}
		return value;
	}
	
	public String getValorLiquido(){
		String value = "";
		if(getRow().getValorLiquido() != null){
			value = formataDouble(getRow().getValorLiquido());
		}
		return value;
	}
	
	public String getValorBruto(){
		String value = "";
		if(getRow().getValorBruto() != null){
			value = formataDouble(getRow().getValorBruto());
		}
		return value;
	}
	
	public String getQtdCdr(){
		String value = "";
		if(getRow().getQtdCdr() != null){
			value = formataLong(getRow().getQtdCdr());
		}
		return value;
	}
	
	public String getStatus(){
		String value = "";
		if(StringUtils.isNotEmpty(getRow().getStatus())){
			value = getRow().getStatus();
		}
		return value;
	}
	
	public String getFileType(){
		String value = "";
		if(StringUtils.isNotEmpty(getRow().getFileType())){
			value = getRow().getFileType();
		}
		return value;
	}
	
	
	

	@Override
	protected boolean isDeleteEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

}
