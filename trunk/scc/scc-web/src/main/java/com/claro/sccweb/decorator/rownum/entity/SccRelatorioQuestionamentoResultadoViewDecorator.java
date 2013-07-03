package com.claro.sccweb.decorator.rownum.entity;

import org.apache.commons.lang.StringUtils;

import com.claro.cobillingweb.persistence.view.SccRelatorioQuestionamentoResultadoView;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

/**
* @author 92038883
*
*/
public class SccRelatorioQuestionamentoResultadoViewDecorator extends RownumDecorator<SccRelatorioQuestionamentoResultadoView> {
			

	public SccRelatorioQuestionamentoResultadoViewDecorator(SccRelatorioQuestionamentoResultadoView entity,
			int rownum) {
		super(entity, rownum);
	}

	@Override
	protected boolean isDeleteEnabled() {
		return false;
	}
	
	
	public String getSqQuestionamento(){
		String value = "";
		if(getRow().getSqQuestionamento()!= null){
			value = getRow().getSqQuestionamento().toString();
		}
		return value;
	}
	
	public String getSqArquivo(){
		String value = "";
		if(getRow().getSqArquivo()!= null){
			value = getRow().getSqArquivo().toString();
		}
		return value;
	}
	
	public String getCdEotLD(){
		String value = "";
		if(StringUtils.isNotEmpty(getRow().getCdEotLD())){
			value = getRow().getCdEotLD();
		}
		return value;
	}
	
	public String getCdEotClaro(){
		String value = "";
		if(StringUtils.isNotEmpty(getRow().getCdEotClaro())){
			value = getRow().getCdEotClaro();
		}
		return value;
	}
	
	public String getDtProtocoloLD(){
		String value = "";
		if(getRow().getDtProtocoloLD() != null){
			value = getRow().getDtProtocoloLD();
		}
		return value;
	}
	
	public String getDtProtocoloClaro(){
		String value = "";
		if(getRow().getDtProtocoloClaro() != null){
			value = getRow().getDtProtocoloClaro();
		}
		return value;
	}
		
	public String getDtCorrecao(){
		String value = "";
		if(getRow().getDtCorrecao() != null){
			value = getRow().getDtCorrecao();
		}
		return value;
	}
		
	public String getDtAnalise(){
		String value = "";
		if(getRow().getDtAnalise() != null){
			value = getRow().getDtAnalise();
		}
		return value;
	}
	
	public String getVlPotencialLD(){
		String value = "";
		if(getRow().getVlPotencialLD() != null){
			value = formataDouble(getRow().getVlPotencialLD());
		}
		return value;
	}
	
	public String getVlPotencialClaro(){
		String value = "";
		if(getRow().getVlPotencialClaro() != null){
			value = formataDouble(getRow().getVlPotencialClaro());
		}
		return value;
	}
	
	public String getVlPenalidadeLD(){
		String value = "";
		if(getRow().getVlPenalidadeLD() != null){
			value = formataDouble(getRow().getVlPenalidadeLD());
		}
		return value;
	}
	
	public String getVlPenalidadeClaro(){
		String value = "";
		if(getRow().getVlPenalidadeClaro() != null){
			value = formataDouble(getRow().getVlPenalidadeClaro());
		}
		return value;
	}
	
	public String getPeProcedente(){
		String value = "";
		if(getRow().getPeProcedente() != null){
			value = formataDouble(getRow().getPeProcedente());
		}
		return value;
	}
	
	public String getPeSemAnalise(){
		String value = "";
		if(getRow().getPeSemAnalise() != null){
			value = formataDouble(getRow().getPeSemAnalise());
		}
		return value;
	}
	
	public String getQtCdrs(){
		String value = "";
		if(getRow().getQtCdrs()!= null){
			value = getRow().getQtCdrs().toString();
		}
		return value;
	}
	
	public String getMiTarifados(){
		String value = "";
		if(getRow().getMiTarifados()!= null){
			value = getRow().getMiTarifados().toString();
		}
		return value;
	}
	
	public String getDtRepasse(){
		String value = "";
		if(getRow().getDtRepasse() != null){
			value = getRow().getDtRepasse();
		}
		return value;
	}
	
	public String getCriacaoDt(){
		String value = "";
		if(getRow().getCriacaoDt() != null){
			value = formataDate(getRow().getCriacaoDt());
		}
		return value;
	}
	
	public String getAtualizacaoDt(){
		String value = "";
		if(getRow().getAtualizacaoDt() != null){
			value = formataDate(getRow().getAtualizacaoDt());
		}
		return value;
	}

	public String getUsuarioManutCd(){
		String value = "";
		if(StringUtils.isNotEmpty(getRow().getUsuarioManutCd())){
			value = getRow().getUsuarioManutCd();
		}
		return value;
	}

	public String getNoArquivo(){
		String value = "";
		if(StringUtils.isNotEmpty(getRow().getNoArquivo())){
			value = getRow().getNoArquivo();
		}
		return value;
	}

	
}
