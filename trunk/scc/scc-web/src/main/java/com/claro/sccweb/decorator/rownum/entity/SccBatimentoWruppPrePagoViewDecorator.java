package com.claro.sccweb.decorator.rownum.entity;

import java.text.NumberFormat;
import java.util.Locale;

import com.claro.cobillingweb.persistence.view.BatimentoWruppPrePagoView;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

public class SccBatimentoWruppPrePagoViewDecorator extends RownumDecorator<BatimentoWruppPrePagoView> {

	private String data;
	private String cdEOTLD;
	private String cdEOTClaro;
	private String qtdChamadasWrupp;
	private String vlrBrutoWrupp;
	private String qtdChamadasScc;
	private String vlrBrutoScc;
	private String qtdChamadasDif;
	private String vlrBrutoDif;

	public SccBatimentoWruppPrePagoViewDecorator(BatimentoWruppPrePagoView entity, int rownum) {
		super(entity, rownum);
		formatarCampos(entity);

	}

	@Override
	protected boolean isDeleteEnabled() {
		return false;
	}

	private void formatarCampos(BatimentoWruppPrePagoView entity) {
		Locale loc = new Locale("pt","BR");
		NumberFormat sccCurrencyFormat = NumberFormat.getInstance(loc);
		sccCurrencyFormat.setMaximumFractionDigits(2);
		sccCurrencyFormat.setGroupingUsed(true);
		
		this.setData(formataDate(entity.getData()));
		this.setCdEOTLD(formataString(entity.getCdEOTLD()));
		this.setCdEOTClaro(formataString(entity.getCdEOTClaro()));
		this.setQtdChamadasScc(sccCurrencyFormat.format(entity.getQtdChamadasScc()));
		this.setQtdChamadasWrupp(sccCurrencyFormat.format(entity.getQtdChamadasWrupp()));

		this.setVlrBrutoScc(NumberFormat.getCurrencyInstance(loc).format(entity.getVlrBrutoScc()));
		this.setVlrBrutoWrupp(NumberFormat.getCurrencyInstance(loc).format(entity.getVlrBrutoWrupp()));

		long qtdDiff = (entity.getQtdChamadasWrupp() != null ? entity.getQtdChamadasWrupp() : 0L)
				- (entity.getQtdChamadasScc() != null ? entity.getQtdChamadasScc() : 0L);
		double vlrDiff = (entity.getVlrBrutoWrupp() != null ? entity.getVlrBrutoWrupp() : 0)
				- (entity.getVlrBrutoScc() != null ? entity.getVlrBrutoScc() : 0);

		this.setQtdChamadasDif(sccCurrencyFormat.format(qtdDiff));
		this.setVlrBrutoDif(NumberFormat.getCurrencyInstance(loc).format(vlrDiff));
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getCdEOTLD() {
		return cdEOTLD;
	}

	public void setCdEOTLD(String cdEOTLD) {
		this.cdEOTLD = cdEOTLD;
	}

	public String getCdEOTClaro() {
		return cdEOTClaro;
	}

	public void setCdEOTClaro(String cdEOTClaro) {
		this.cdEOTClaro = cdEOTClaro;
	}

	public String getQtdChamadasWrupp() {
		return qtdChamadasWrupp;
	}

	public void setQtdChamadasWrupp(String qtdChamadasWrupp) {
		this.qtdChamadasWrupp = qtdChamadasWrupp;
	}

	public String getVlrBrutoWrupp() {
		return vlrBrutoWrupp;
	}

	public void setVlrBrutoWrupp(String vlrBrutoWrupp) {
		this.vlrBrutoWrupp = vlrBrutoWrupp;
	}

	public String getQtdChamadasScc() {
		return qtdChamadasScc;
	}

	public void setQtdChamadasScc(String qtdChamadasScc) {
		this.qtdChamadasScc = qtdChamadasScc;
	}

	public String getVlrBrutoScc() {
		return vlrBrutoScc;
	}

	public void setVlrBrutoScc(String vlrBrutoScc) {
		this.vlrBrutoScc = vlrBrutoScc;
	}

	public String getQtdChamadasDif() {
		return qtdChamadasDif;
	}

	public void setQtdChamadasDif(String qtdChamadasDif) {
		this.qtdChamadasDif = qtdChamadasDif;
	}

	public String getVlrBrutoDif() {
		return vlrBrutoDif;
	}

	public void setVlrBrutoDif(String vlrBrutoDif) {
		this.vlrBrutoDif = vlrBrutoDif;
	}


}
