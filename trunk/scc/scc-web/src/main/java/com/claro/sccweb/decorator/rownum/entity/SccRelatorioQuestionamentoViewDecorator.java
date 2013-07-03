package com.claro.sccweb.decorator.rownum.entity;

import org.apache.commons.lang.StringUtils;

import com.claro.cobillingweb.persistence.view.SccRelatorioQuestionamentoView;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

/**
* @author 92038883
*
*/
public class SccRelatorioQuestionamentoViewDecorator extends RownumDecorator<SccRelatorioQuestionamentoView> {
			

	public SccRelatorioQuestionamentoViewDecorator(SccRelatorioQuestionamentoView entity,
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
	
	public String getCdEotLD(){
		String value = "";
		if(StringUtils.isNotEmpty(getRow().getCdEotLD())){
			value = getRow().getCdEotLD();
		}
		return value;
	}
	
	public String getCdStatusQuestionamento(){
		String value = "";
		if(StringUtils.isNotEmpty(getRow().getCdStatusQuestionamento())){
			value = getRow().getCdStatusQuestionamento();
		}
		return value;
	}
	
	public String getDtCredito(){
		String value = "";
		if(getRow().getDtCredito() != null){
			value = formataDate(getRow().getDtCredito());
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
	
	
	public String getDtProtocoloLD(){
		String value = "";
		if(getRow().getDtProtocoloLD() != null){
			value = getRow().getDtProtocoloLD();
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
	
	public String getDtCorrecao(){
		String value = "";
		if(getRow().getDtCorrecao() != null){
			value = getRow().getDtCorrecao();
		}
		return value;
	}
	
	public String getTxComentarioClaro(){
		String value = "";
		if(StringUtils.isNotEmpty(getRow().getTxComentarioClaro())){
			value = getRow().getTxComentarioClaro();
		}
		return value;
	}
	
	public String getTxComentarioLD(){
		String value = "";
		if(StringUtils.isNotEmpty(getRow().getTxComentarioLD())){
			value = getRow().getTxComentarioLD();
		}
		return value;
	}
	
	public String getTxAnalise(){
		String value = "";
		if(StringUtils.isNotEmpty(getRow().getTxAnalise())){
			value = getRow().getTxAnalise();
		}
		return value;
	}
		
	public String getTxCorrecao(){
		String value = "";
		if(StringUtils.isNotEmpty(getRow().getTxCorrecao())){
			value = getRow().getTxCorrecao();
		}
		return value;
	}
	
	
	public String getTxMotivosRejeicao(){
		String value = "";
		if(StringUtils.isNotEmpty(getRow().getTxMotivosRejeicao())){
			value = getRow().getTxMotivosRejeicao();
		}
		return value;
	}
	
	public String getTxArquivos(){
		String value = "";
		if(StringUtils.isNotEmpty(getRow().getTxArquivos())){
			value = getRow().getTxArquivos();
		}
		return value;
	}
	
	public String getCdIdentificacaoCartaClaro(){
		String value = "";
		if(StringUtils.isNotEmpty(getRow().getCdIdentificacaoCartaClaro())){
			value = getRow().getCdIdentificacaoCartaClaro();
		}
		return value;
	}

	public String getCdIdentificacaoCartaLD(){
		String value = "";
		if(StringUtils.isNotEmpty(getRow().getCdIdentificacaoCartaLD())){
			value = getRow().getCdIdentificacaoCartaLD();
		}
		return value;
	}

	public String getNoResponsavelClaro(){
		String value = "";
		if(StringUtils.isNotEmpty(getRow().getNoResponsavelClaro())){
			value = getRow().getNoResponsavelClaro();
		}
		return value;
	}
	
	public String getNoResponsavelLD(){
		String value = "";
		if(StringUtils.isNotEmpty(getRow().getNoResponsavelLD())){
			value = getRow().getNoResponsavelLD();
		}
		return value;
	}

	public String getCdProcesso(){
		String value = "";
		if(StringUtils.isNotEmpty(getRow().getCdProcesso())){
			value = getRow().getCdProcesso();
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
			value = getRow().getAtualizacaoDt();
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

	public String getMiDuracaoTarifada(){
		String value = "";
		if(getRow().getMiDuracaoTarifada() != null){
			value = formataDouble(getRow().getMiDuracaoTarifada());
		}
		return value;
	}
	
	public String getVlLiquidoChamada(){
		String value = "";
		if(getRow().getVlLiquidoChamada() != null){
			value = formataDouble(getRow().getVlLiquidoChamada());
		}
		return value;
	}
	
	public String getVlBrutoChamada(){
		String value = "";
		if(getRow().getVlBrutoChamada() != null){
			value = formataDouble(getRow().getVlBrutoChamada());
		}
		return value;
	}
	
	public String getVlPotencialMulta(){
		String value = "";
		if(getRow().getVlPotencialMulta() != null){
			value = formataDouble(getRow().getVlPotencialMulta());
		}
		return value;
	}

	
}
