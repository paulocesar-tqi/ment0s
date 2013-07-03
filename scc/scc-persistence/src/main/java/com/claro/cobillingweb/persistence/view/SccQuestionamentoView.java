package com.claro.cobillingweb.persistence.view;

import java.io.Serializable;
import java.util.Date;

public class SccQuestionamentoView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -128164693928821107L;

	
    private Long sqQuestionamento ;
    private String cdEotLD = "";
    private String descricaoEotLD;
    private String cdEotClaro;
    private String sgUf;
    private String noArquivo;
    
	private Long qtdCdrQuest;
	private Double miReaisQuest;
	private Double miArredondadosQuest;
	private Double vlrLiquidoQuest;
	
	private Long qtdCdrCsa;
	private Double miReaisCsa;
	private Double miArredondadosCsa;
	private Double vlrLiquidoCsa;

	private Long qtdCdrCcap;
	private Double miReaisCcap;
	private Double miArredondadosCcap;
	private Double vlrLiquidoCcap;

	private Long qtdCdrCanp;
	private Double miReaisCanp;
	private Double miArredondadosCanp;
	private Double vlrLiquidoCanp;

	private Long qtdCdrClaro;
	private Double miReaisClaro;
	private Double miArredondadosClaro;
	private Double vlrLiquidoClaro;
	
	private Long qtdCdrLd;
	private Double miReaisLd;
	private Double miArredondadosLd;
	private Double vlrLiquidoLd;

    private Long sqArquivo;
    
    
    private String cdStatusQuestionamento = "";
    private Date dtEvento;    
    private Date dtProtocoloClaro;    
    private Date dtProtocoloLD;    
    private Date dtAnalise;    
    private Date dtCorrecao;    
    private String txComentarioClaro = "";
    private String txComentarioLD = "";
    private String txAnalise = "";
    private String txCorrecao = "";
    private String txMotivosRejeicao = "";   	
    private String txArquivos = "";
    private String cdIdentificacaoCartaClaro = "";
    private String cdIdentificacaoCartaLD = "";
    private String noResponsavelClaro = "";
    private String noResponsavelLD = "";
    private String cdProcesso = "";
    private String criacaoDt = "";
    private String atualizacaoDt = "";
    private String usuarioManutCd = "";
    
    private double miDuracaoTarifada;
    private double vlLiquidoChamada;
    private double vlBrutoChamada;
    private double vlPotencialMulta;    
    
    private long qtde = 0;
    private long position = 0;
    private String cdStatusQuestionamentoNovo = "";
    
    private SccQuestionamentoValoresView sccQuestionamentoValoresView = new SccQuestionamentoValoresView();
    
    private Long sqRemessaQuestionamento;
    
    
	public Long getSqQuestionamento() {
		return sqQuestionamento;
	}
	public void setSqQuestionamento(Long sqQuestionamento) {
		this.sqQuestionamento = sqQuestionamento;
	}
	public String getCdEotLD() {
		return cdEotLD;
	}
	public void setCdEotLD(String cdEotLD) {
		this.cdEotLD = cdEotLD;
	}
	
	public String getDescricaoEotLD() {
		return descricaoEotLD;
	}
	public void setDescricaoEotLD(String descricaoEotLD) {
		this.descricaoEotLD = descricaoEotLD;
	}
	
	public String getCdEotClaro() {
		return cdEotClaro;
	}
	public void setCdEotClaro(String cdEotClaro) {
		this.cdEotClaro = cdEotClaro;
	}
	
	public String getSgUf() {
		return sgUf;
	}
	public void setSgUf(String sgUf) {
		this.sgUf = sgUf;
	}
	
	public String getNoArquivo() {
		return noArquivo;
	}
	public void setNoArquivo(String noArquivo) {
		this.noArquivo = noArquivo;
	}
	

	
	public Long getQtdCdrQuest() {
		return qtdCdrQuest;
	}
	public void setQtdCdrQuest(Long qtdCdrQuest) {
		this.qtdCdrQuest = qtdCdrQuest;
	}
	public Double getMiReaisQuest() {
		return miReaisQuest;
	}
	public void setMiReaisQuest(Double miReaisQuest) {
		this.miReaisQuest = miReaisQuest;
	}
	public Double getMiArredondadosQuest() {
		return miArredondadosQuest;
	}
	public void setMiArredondadosQuest(Double miArredondadosQuest) {
		this.miArredondadosQuest = miArredondadosQuest;
	}
	public Double getVlrLiquidoQuest() {
		return vlrLiquidoQuest;
	}
	public void setVlrLiquidoQuest(Double vlrLiquidoQuest) {
		this.vlrLiquidoQuest = vlrLiquidoQuest;
	}
	public Long getQtdCdrCsa() {
		return qtdCdrCsa;
	}
	public void setQtdCdrCsa(Long qtdCdrCsa) {
		this.qtdCdrCsa = qtdCdrCsa;
	}
	public Double getMiReaisCsa() {
		return miReaisCsa;
	}
	public void setMiReaisCsa(Double miReaisCsa) {
		this.miReaisCsa = miReaisCsa;
	}
	public Double getMiArredondadosCsa() {
		return miArredondadosCsa;
	}
	public void setMiArredondadosCsa(Double miArredondadosCsa) {
		this.miArredondadosCsa = miArredondadosCsa;
	}
	public Double getVlrLiquidoCsa() {
		return vlrLiquidoCsa;
	}
	public void setVlrLiquidoCsa(Double vlrLiquidoCsa) {
		this.vlrLiquidoCsa = vlrLiquidoCsa;
	}
	public Long getQtdCdrCcap() {
		return qtdCdrCcap;
	}
	public void setQtdCdrCcap(Long qtdCdrCcap) {
		this.qtdCdrCcap = qtdCdrCcap;
	}
	public Double getMiReaisCcap() {
		return miReaisCcap;
	}
	public void setMiReaisCcap(Double miReaisCcap) {
		this.miReaisCcap = miReaisCcap;
	}
	public Double getMiArredondadosCcap() {
		return miArredondadosCcap;
	}
	public void setMiArredondadosCcap(Double miArredondadosCcap) {
		this.miArredondadosCcap = miArredondadosCcap;
	}
	public Double getVlrLiquidoCcap() {
		return vlrLiquidoCcap;
	}
	public void setVlrLiquidoCcap(Double vlrLiquidoCcap) {
		this.vlrLiquidoCcap = vlrLiquidoCcap;
	}
	public Long getQtdCdrCanp() {
		return qtdCdrCanp;
	}
	public void setQtdCdrCanp(Long qtdCdrCanp) {
		this.qtdCdrCanp = qtdCdrCanp;
	}
	public Double getMiReaisCanp() {
		return miReaisCanp;
	}
	public void setMiReaisCanp(Double miReaisCanp) {
		this.miReaisCanp = miReaisCanp;
	}
	public Double getMiArredondadosCanp() {
		return miArredondadosCanp;
	}
	public void setMiArredondadosCanp(Double miArredondadosCanp) {
		this.miArredondadosCanp = miArredondadosCanp;
	}
	public Double getVlrLiquidoCanp() {
		return vlrLiquidoCanp;
	}
	public void setVlrLiquidoCanp(Double vlrLiquidoCanp) {
		this.vlrLiquidoCanp = vlrLiquidoCanp;
	}
	public Long getQtdCdrClaro() {
		return qtdCdrClaro;
	}
	public void setQtdCdrClaro(Long qtdCdrClaro) {
		this.qtdCdrClaro = qtdCdrClaro;
	}
	public Double getMiReaisClaro() {
		return miReaisClaro;
	}
	public void setMiReaisClaro(Double miReaisClaro) {
		this.miReaisClaro = miReaisClaro;
	}
	public Double getMiArredondadosClaro() {
		return miArredondadosClaro;
	}
	public void setMiArredondadosClaro(Double miArredondadosClaro) {
		this.miArredondadosClaro = miArredondadosClaro;
	}
	public Double getVlrLiquidoClaro() {
		return vlrLiquidoClaro;
	}
	public void setVlrLiquidoClaro(Double vlrLiquidoClaro) {
		this.vlrLiquidoClaro = vlrLiquidoClaro;
	}
	public Long getQtdCdrLd() {
		return qtdCdrLd;
	}
	public void setQtdCdrLd(Long qtdCdrLd) {
		this.qtdCdrLd = qtdCdrLd;
	}
	public Double getMiReaisLd() {
		return miReaisLd;
	}
	public void setMiReaisLd(Double miReaisLd) {
		this.miReaisLd = miReaisLd;
	}
	public Double getMiArredondadosLd() {
		return miArredondadosLd;
	}
	public void setMiArredondadosLd(Double miArredondadosLd) {
		this.miArredondadosLd = miArredondadosLd;
	}
	public Double getVlrLiquidoLd() {
		return vlrLiquidoLd;
	}
	public void setVlrLiquidoLd(Double vlrLiquidoLd) {
		this.vlrLiquidoLd = vlrLiquidoLd;
	}
	
	public Long getSqArquivo() {
		return sqArquivo;
	}
	public void setSqArquivo(Long sqArquivo) {
		this.sqArquivo = sqArquivo;
	}
	
	
	public String getCdStatusQuestionamento() {
		return cdStatusQuestionamento;
	}
	public void setCdStatusQuestionamento(String cdStatusQuestionamento) {
		this.cdStatusQuestionamento = cdStatusQuestionamento;
	}
	public Date getDtEvento() {
		return dtEvento;
	}
	public void setDtEvento(Date dtEvento) {
		this.dtEvento = dtEvento;
	}
	public Date getDtProtocoloClaro() {
		return dtProtocoloClaro;
	}
	public void setDtProtocoloClaro(Date dtProtocoloClaro) {
		this.dtProtocoloClaro = dtProtocoloClaro;
	}
	public Date getDtProtocoloLD() {
		return dtProtocoloLD;
	}
	public void setDtProtocoloLD(Date dtProtocoloLD) {
		this.dtProtocoloLD = dtProtocoloLD;
	}
	public Date getDtAnalise() {
		return dtAnalise;
	}
	public void setDtAnalise(Date dtAnalise) {
		this.dtAnalise = dtAnalise;
	}
	public Date getDtCorrecao() {
		return dtCorrecao;
	}
	public void setDtCorrecao(Date dtCorrecao) {
		this.dtCorrecao = dtCorrecao;
	}
	public String getTxComentarioClaro() {
		return txComentarioClaro;
	}
	public void setTxComentarioClaro(String txComentarioClaro) {
		this.txComentarioClaro = txComentarioClaro;
	}
	public String getTxComentarioLD() {
		return txComentarioLD;
	}
	public void setTxComentarioLD(String txComentarioLD) {
		this.txComentarioLD = txComentarioLD;
	}
	public String getTxAnalise() {
		return txAnalise;
	}
	public void setTxAnalise(String txAnalise) {
		this.txAnalise = txAnalise;
	}
	public String getTxCorrecao() {
		return txCorrecao;
	}
	public void setTxCorrecao(String txCorrecao) {
		this.txCorrecao = txCorrecao;
	}
	public String getTxMotivosRejeicao() {
		return txMotivosRejeicao;
	}
	public void setTxMotivosRejeicao(String txMotivosRejeicao) {
		this.txMotivosRejeicao = txMotivosRejeicao;
	}
	public String getTxArquivos() {
		return txArquivos;
	}
	public void setTxArquivos(String txArquivos) {
		this.txArquivos = txArquivos;
	}
	public String getCdIdentificacaoCartaClaro() {
		return cdIdentificacaoCartaClaro;
	}
	public void setCdIdentificacaoCartaClaro(String cdIdentificacaoCartaClaro) {
		this.cdIdentificacaoCartaClaro = cdIdentificacaoCartaClaro;
	}
	public String getCdIdentificacaoCartaLD() {
		return cdIdentificacaoCartaLD;
	}
	public void setCdIdentificacaoCartaLD(String cdIdentificacaoCartaLD) {
		this.cdIdentificacaoCartaLD = cdIdentificacaoCartaLD;
	}
	public String getNoResponsavelClaro() {
		return noResponsavelClaro;
	}
	public void setNoResponsavelClaro(String noResponsavelClaro) {
		this.noResponsavelClaro = noResponsavelClaro;
	}
	public String getNoResponsavelLD() {
		return noResponsavelLD;
	}
	public void setNoResponsavelLD(String noResponsavelLD) {
		this.noResponsavelLD = noResponsavelLD;
	}
	public String getCdProcesso() {
		return cdProcesso;
	}
	public void setCdProcesso(String cdProcesso) {
		this.cdProcesso = cdProcesso;
	}
	public String getCriacaoDt() {
		return criacaoDt;
	}
	public void setCriacaoDt(String criacaoDt) {
		this.criacaoDt = criacaoDt;
	}
	public String getAtualizacaoDt() {
		return atualizacaoDt;
	}
	public void setAtualizacaoDt(String atualizacaoDt) {
		this.atualizacaoDt = atualizacaoDt;
	}
	public String getUsuarioManutCd() {
		return usuarioManutCd;
	}
	public void setUsuarioManutCd(String usuarioManutCd) {
		this.usuarioManutCd = usuarioManutCd;
	}
	public double getMiDuracaoTarifada() {
		return miDuracaoTarifada;
	}
	public void setMiDuracaoTarifada(double miDuracaoTarifada) {
		this.miDuracaoTarifada = miDuracaoTarifada;
	}
	public double getVlLiquidoChamada() {
		return vlLiquidoChamada;
	}
	public void setVlLiquidoChamada(double vlLiquidoChamada) {
		this.vlLiquidoChamada = vlLiquidoChamada;
	}
	public double getVlBrutoChamada() {
		return vlBrutoChamada;
	}
	public void setVlBrutoChamada(double vlBrutoChamada) {
		this.vlBrutoChamada = vlBrutoChamada;
	}
	public double getVlPotencialMulta() {
		return vlPotencialMulta;
	}
	public void setVlPotencialMulta(double vlPotencialMulta) {
		this.vlPotencialMulta = vlPotencialMulta;
	}
	public long getQtde() {
		return qtde;
	}
	public void setQtde(long qtde) {
		this.qtde = qtde;
	}
	public long getPosition() {
		return position;
	}
	public void setPosition(long position) {
		this.position = position;
	}
	public String getCdStatusQuestionamentoNovo() {
		return cdStatusQuestionamentoNovo;
	}
	public void setCdStatusQuestionamentoNovo(String cdStatusQuestionamentoNovo) {
		this.cdStatusQuestionamentoNovo = cdStatusQuestionamentoNovo;
	}
	public SccQuestionamentoValoresView getSccQuestionamentoValoresView() {
		return sccQuestionamentoValoresView;
	}
	public void setSccQuestionamentoValoresView(SccQuestionamentoValoresView sccQuestionamentoValoresView) {
		this.sccQuestionamentoValoresView = sccQuestionamentoValoresView;
	}
	public Long getSqRemessaQuestionamento() {
		return sqRemessaQuestionamento;
	}
	public void setSqRemessaQuestionamento(Long sqRemessaQuestionamento) {
		this.sqRemessaQuestionamento = sqRemessaQuestionamento;
	}

}
