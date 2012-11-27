package com.claro.sccweb.decorator.rownum.view;

import com.claro.cobillingweb.persistence.view.ConsolidadoProdutoPreView;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

public class ConsolidadoProdutoPreViewDecorator extends RownumDecorator<ConsolidadoProdutoPreView> {
	
	private long duracaoTotalFaturada;
	private double vlTotalFaturada;
	

	public ConsolidadoProdutoPreViewDecorator(ConsolidadoProdutoPreView entity,
			int rownum) {
		super(entity, rownum);
		buildValues();
	}
	
	private void buildValues() {
		ConsolidadoProdutoPreView entity = getRow();
		duracaoTotalFaturada =
				(entity.getMiDuracaoPacote01() != null ? entity.getMiDuracaoPacote01() : 0) +
				(entity.getMiDuracaoPacote02() != null ? entity.getMiDuracaoPacote02() : 0) +
				(entity.getMiDuracaoPacote03() != null ? entity.getMiDuracaoPacote03() : 0) +
				(entity.getMiDuracaoPacote04() != null ? entity.getMiDuracaoPacote04() : 0) +
				(entity.getMiDuracaoPacote05() != null ? entity.getMiDuracaoPacote05() : 0) +
				(entity.getMiDuracaoPacote06() != null ? entity.getMiDuracaoPacote06() : 0) +
				(entity.getMiDuracaoPacote07() != null ? entity.getMiDuracaoPacote07() : 0) +
				(entity.getMiDuracaoPacote08() != null ? entity.getMiDuracaoPacote08() : 0) +
				(entity.getMiDuracaoPacote09() != null ? entity.getMiDuracaoPacote09() : 0) +
				(entity.getMiDuracaoPacote10() != null ? entity.getMiDuracaoPacote10() : 0);
		
		vlTotalFaturada =
				(entity.getVlPacote01() != null ? entity.getVlPacote01() : 0) +
				(entity.getVlPacote02() != null ? entity.getVlPacote02() : 0) +
				(entity.getVlPacote03() != null ? entity.getVlPacote03() : 0) +
				(entity.getVlPacote04() != null ? entity.getVlPacote04() : 0) +
				(entity.getVlPacote05() != null ? entity.getVlPacote05() : 0) +
				(entity.getVlPacote06() != null ? entity.getVlPacote06() : 0) +
				(entity.getVlPacote07() != null ? entity.getVlPacote07() : 0) +
				(entity.getVlPacote08() != null ? entity.getVlPacote08() : 0) +
				(entity.getVlPacote09() != null ? entity.getVlPacote09() : 0) +
				(entity.getVlPacote10() != null ? entity.getVlPacote10() : 0);
	}


	@Override
	protected boolean isDeleteEnabled() {
		return false;
	}

	public String getCspLD() {
		return this.getRow().getCdCSPLD();
	}
	
	public String getOperadoraClaro() {
		return this.getRow().getCdEOTClaro();
	}
	
	public String getCdProdutoPrepago() {
		return this.getRow().getCdProdutoPrepago();
	}
	public String getCdTipoEvento() {
		return this.getRow().getCdTipoEvento();
	}
	public String getDataChamada() {
		return formataDate(this.getRow().getDtChamada());
	}
	public String getInChamadaRepassada() {
		String s = this.getRow().getInChamadaRepassada();
		if(s != null && s.equals("S")) return "SIM";
		return "NÃO";
	}
	public String getDataRepasse() {
		return formataDate(this.getRow().getDtRepasse());
	}
	public Long getTotalDuracaoFaturada() {
		return this.duracaoTotalFaturada;
	}
	public Double getTotalValor() {
		return this.vlTotalFaturada;
	}
	public Long getDuracaoTarifada() {
		return this.getRow().getMiDuracaoTarifada();
	}
	public Double getValorBruto() {
		return this.getRow().getVlBruto();
	}
	
	public Integer getPacote01() {
		return this.getRow().getCdPacote01();
	}
	public Long getMinutosPacote01() {
		return this.getRow().getMiDuracaoPacote01();
	}
	public Double getValorPacote01() {
		return this.getRow().getVlPacote01();
	}


	public Integer getPacote02() {
		return this.getRow().getCdPacote02();
	}
	public Long getMinutosPacote02() {
		return this.getRow().getMiDuracaoPacote02();
	}
	public Double getValorPacote02() {
		return this.getRow().getVlPacote02();
	}


	public Integer getPacote03() {
		return this.getRow().getCdPacote03();
	}
	public Long getMinutosPacote03() {
		return this.getRow().getMiDuracaoPacote03();
	}
	public Double getValorPacote03() {
		return this.getRow().getVlPacote03();
	}


	public Integer getPacote04() {
		return this.getRow().getCdPacote04();
	}
	public Long getMinutosPacote04() {
		return this.getRow().getMiDuracaoPacote04();
	}
	public Double getValorPacote04() {
		return this.getRow().getVlPacote04();
	}


	public Integer getPacote05() {
		return this.getRow().getCdPacote05();
	}
	public Long getMinutosPacote05() {
		return this.getRow().getMiDuracaoPacote05();
	}
	public Double getValorPacote05() {
		return this.getRow().getVlPacote05();
	}


	public Integer getPacote06() {
		return this.getRow().getCdPacote06();
	}
	public Long getMinutosPacote06() {
		return this.getRow().getMiDuracaoPacote06();
	}
	public Double getValorPacote06() {
		return this.getRow().getVlPacote06();
	}


	public Integer getPacote07() {
		return this.getRow().getCdPacote07();
	}
	public Long getMinutosPacote07() {
		return this.getRow().getMiDuracaoPacote07();
	}
	public Double getValorPacote07() {
		return this.getRow().getVlPacote07();
	}

	public Integer getPacote08() {
		return this.getRow().getCdPacote08();
	}
	public Long getMinutosPacote08() {
		return this.getRow().getMiDuracaoPacote08();
	}
	public Double getValorPacote08() {
		return this.getRow().getVlPacote08();
	}
	
	public Integer getPacote09() {
		return this.getRow().getCdPacote09();
	}
	public Long getMinutosPacote09() {
		return this.getRow().getMiDuracaoPacote09();
	}
	public Double getValorPacote09() {
		return this.getRow().getVlPacote09();
	}
	
	public Integer getPacote10() {
		return this.getRow().getCdPacote10();
	}
	public Long getMinutosPacote10() {
		return this.getRow().getMiDuracaoPacote10();
	}
	public Double getValorPacote10() {
		return this.getRow().getVlPacote10();
	}
}
