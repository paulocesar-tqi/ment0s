package com.claro.sccweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Spring bean onde os serviços serão injetados e disponibilizados para os controllers. 
 *
 */
@Service
public class ServiceManager {

	private PesquisaDominiosService pesquisaDominiosService;	
	@Autowired
	private ArquivosService arquivosService;
	
	@Autowired
	private SccResultadoIndicadorService sccResultadoIndicadorService;
	
	@Autowired
	private SccComposicaoGrupoEmailService sccComposicaoGrupoEmailService;
	
	private SumarioService sumarioService;
	private CDRService cdrService;
	private ContratoService contratoService;
	private RelatorioRepasseService repasseService;
	private PeriodicidadeService periodicidadeService;
	private OperadoraService operadoraService;
	private PagamentoRepasseService pagamentoRepasseService;
	private ContratoPrePagoService contratoPrePagoService;
	private RepassePreService repassePreService;
	private CreditosPrePagoService creditosPrePagoService;
	private RepassePosService repassePosService;
	private ProdutoService produtoService;
	private ProdutoPrepagoService produtoPrepagoService;
	private ControleRemessaService controleRemessaService;
	private VolumeSuspeitoService volumeSuspeitoService;
	private EvolucaoCDRService evolucaoCDRService;
	private SaldosService saldosService;
	
	@Autowired
	private FTPService ftpService;
	
	private AdminService adminService;
	private EstornoMobileService estornoMobileService;
	private ContabilService contabilService;
	private SccParamProcessoService sccParamProcessoService;
	
	@Autowired
	private SccContestacaoPrePagoService sccContestacaoPrePagoService;
	
	@Autowired
	private SccDisputaPrePagoService sccDisputaPrePagoService;
	
	@Autowired
	private SccDisputaService sccDisputaService;
	
	@Autowired
	private SccQuestionamentoService sccQuestionamentoService;
	
	@Autowired
	private SccRelBatimentoEstornoDebitoService sccRelBatimentoEstornoDebitoService;
	
	@Autowired
	private SccFinanceiroService sccFinanceiroService;
	
	@Autowired
	private SccAcordoParcelamentoService sccAcordoParcelamentoService;
	
	@Autowired
	private SccAcoesCobrancaService sccAcoesCobrancaService;
	
	@Autowired
	private SccFaturasService sccFaturasService;
	
	@Autowired
	private SccNaoConfAplBatimentoEstornoDebitoService sccNaoConfAplBatimentoEstornoDebitoService;
	
	@Autowired
	private SccPreRelatorioEventosService sccPreRelatorioEventosService;
	
	@Autowired
	private SccPreChamadasCreditoService sccPreChamadasCreditoService;
	
	@Autowired
	private SccNaoConfAplFaturamentoService sccNaoConfAplFaturamentoService;
	
	@Autowired
	private SccNaoConfAplAjustesService sccNaoConfAplAjustesService;
	
	@Autowired
	private SccGrupoCobillingService sccGrupoCobillingService;
	
	@Autowired
	private SccAssociacaoRelatorioGrupoService sccAssociacaoRelatorioGrupoService;
	
	@Autowired
	private SccEmailCobillingService sccEmailCobillingService;
	
	@Autowired
	private SccRelatorioCobillingService sccRelatorioCobillingService;
	
	@Autowired
	private SccBatimentoArquivosService sccBatimentoArquivosService;

	@Autowired
	private SccConfirmacaoRepasseService sccConfirmacaoRepasseService;
	
	@Autowired
	private SccArquivosNaoProcessadosService sccArquivosNaoProcessadosService;

	@Autowired
	private SccAssexuadosService sccAssexuadosService;

	@Autowired
	private SccEncaminhadoMobileService sccEncaminhadoMobileService;

	@Autowired
	private SccAlocadasMobileService sccAlocadasMobileService;

	@Autowired
	private SccAlocadasMobileSemProcessamentoService sccAlocadasMobileSemProcessamentoService;

	@Autowired
	private SccExtracaoCDRsService sccExtracaoCDRsService;
	
	@Autowired
	private SccRelatorioChamadasRefaturamentoService sccRelatorioChamadasRefaturamentoService;
	
	@Autowired
	private SccContingenciaFiscalService sccContingenciaFiscalService;

	@Autowired
	private SccBatimentoFiscalService sccBatimentoFiscalService;

	@Autowired
	private SccArquivosFiscaisService sccArquivosFiscaisService;
	
	@Autowired
	private SccBatimentoInterfaceService sccBatimentoInterfaceService;

	@Autowired
	private SccRelatorioConciliacaoService sccRelatorioConciliacaoService;
	
	@Autowired
	private SccBatimentoWruppService sccBatimentoWruppService;
	
	@Autowired
	private SccSolicitacaoVumService sccSolicitacaoVumService;
	
	@Autowired
	private SccAssinaturaPreService sccAssinaturaPreService;
	
	@Autowired
	private RelatorioAlarmeOperacionalService relatorioAlarmeOperacionalService;
	
	@Autowired
	private SccChamadasRefaturamentoService sccChamadasRefaturamentoService;
	
	@Autowired
	private SccPreFechamentoService sccPreFechamentoService;

	
	public RelatorioAlarmeOperacionalService getRelatorioAlarmeOperacionalService() {
		return relatorioAlarmeOperacionalService;
	}

	public void setRelatorioAlarmeOperacionalService(
			RelatorioAlarmeOperacionalService relatorioAlarmeOperacionalService) {
		this.relatorioAlarmeOperacionalService = relatorioAlarmeOperacionalService;
	}

	@Autowired
	private SccPreRelatorioCriticasService sccPreRelatorioCriticasService;
	
	@Autowired
	private SccRelatorioQuestionamentoService sccRelatorioQuestionamentoService;
	
	@Autowired
	private SccRelatorioQuestionamentoResultadoService sccRelatorioQuestionamentoResultadoService;
	
	@Autowired
	private SccPosRelatorioDisputaDetalheService sccPosRelatorioDisputaDetalheService;
	
	@Autowired
	private SccRelDemonstrativoDesempenhoPenalidadeService sccRelDemonstrativoDesempenhoPenalidadeService;
	
	@Autowired
	private CadastroCriticaPrebillingService cadastroCriticaPrebillingService;
	
	@Autowired
	private CadastroDadosBancariosService cadastroDadosBancariosService;
	
	@Autowired
	private SccPrePagoCreditosService sccPrePagoCreditosService;

	@Autowired
	private SccTarifasCobrarService sccTarifasCobrarService;
	

	public PesquisaDominiosService getPesquisaDominiosService() {
		return pesquisaDominiosService;
	}
	
	public void setPesquisaDominiosService(
			PesquisaDominiosService pesquisaDominiosService) {
		this.pesquisaDominiosService = pesquisaDominiosService;
	}
	
	public ArquivosService getArquivosService() {
		return arquivosService;
	}

	public void setArquivosService(ArquivosService arquivosService) {
		this.arquivosService = arquivosService;
	}

	public SumarioService getSumarioService() {
		return sumarioService;
	}

	public void setSumarioService(SumarioService sumarioService) {
		this.sumarioService = sumarioService;
	}

	public CDRService getCdrService() {
		return cdrService;
	}

	public void setCdrService(CDRService cdrService) {
		this.cdrService = cdrService;
	}

	public ContratoService getContratoService() {
		return contratoService;
	}

	public void setContratoService(ContratoService contratoService) {
		this.contratoService = contratoService;
	}

	public RelatorioRepasseService getRepasseService() {
		return repasseService;
	}

	public void setRepasseService(RelatorioRepasseService repasseService) {
		this.repasseService = repasseService;
	}

	public PeriodicidadeService getPeriodicidadeService() {
		return periodicidadeService;
	}

	public void setPeriodicidadeService(PeriodicidadeService periodicidadeService) {
		this.periodicidadeService = periodicidadeService;
	}

	public OperadoraService getOperadoraService() {
		return operadoraService;
	}

	public void setOperadoraService(OperadoraService operadoraService) {
		this.operadoraService = operadoraService;
	}

	public PagamentoRepasseService getPagamentoRepasseService() {
		return pagamentoRepasseService;
	}

	public void setPagamentoRepasseService(
			PagamentoRepasseService pagamentoRepasseService) {
		this.pagamentoRepasseService = pagamentoRepasseService;
	}

	public ContratoPrePagoService getContratoPrePagoService() {
		return contratoPrePagoService;
	}

	public void setContratoPrePagoService(
			ContratoPrePagoService contratoPrePagoService) {
		this.contratoPrePagoService = contratoPrePagoService;
	}

	public RepassePreService getRepassePreService() {
		return repassePreService;
	}

	public void setRepassePreService(RepassePreService repassePreService) {
		this.repassePreService = repassePreService;
	}

	public CreditosPrePagoService getCreditosPrePagoService() {
		return creditosPrePagoService;
	}

	public void setCreditosPrePagoService(
			CreditosPrePagoService creditosPrePagoService) {
		this.creditosPrePagoService = creditosPrePagoService;
	}

	public RepassePosService getRepassePosService() {
		return repassePosService;
	}

	public void setRepassePosService(RepassePosService repassePosService) {
		this.repassePosService = repassePosService;
	}

	public ProdutoService getProdutoService() {
		return produtoService;
	}

	public void setProdutoService(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	public ProdutoPrepagoService getProdutoPrepagoService() {
		return produtoPrepagoService;
	}

	public void setProdutoPrepagoService(ProdutoPrepagoService produtoPrepagoService) {
		this.produtoPrepagoService = produtoPrepagoService;
	}

	public ControleRemessaService getControleRemessaService() {
		return controleRemessaService;
	}

	public void setControleRemessaService(
			ControleRemessaService controleRemessaService) {
		this.controleRemessaService = controleRemessaService;
	}

	public VolumeSuspeitoService getVolumeSuspeitoService() {
		return volumeSuspeitoService;
	}

	public void setVolumeSuspeitoService(VolumeSuspeitoService volumeSuspeitoService) {
		this.volumeSuspeitoService = volumeSuspeitoService;
	}

	public EvolucaoCDRService getEvolucaoCDRService() {
		return evolucaoCDRService;
	}

	public void setEvolucaoCDRService(EvolucaoCDRService evolucaoCDRService) {
		this.evolucaoCDRService = evolucaoCDRService;
	}

	public SaldosService getSaldosService() {
		return saldosService;
	}

	public void setSaldosService(SaldosService saldosService) {
		this.saldosService = saldosService;
	}

	public FTPService getFtpService() {
		return ftpService;
	}

	public void setFtpService(FTPService ftpService) {
		this.ftpService = ftpService;
	}

	public AdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	public EstornoMobileService getEstornoMobileService() {
		return estornoMobileService;
	}

	public void setEstornoMobileService(EstornoMobileService estornoMobileService) {
		this.estornoMobileService = estornoMobileService;
	}

	public ContabilService getContabilService() {
		return contabilService;
	}

	public void setContabilService(ContabilService contabilService) {
		this.contabilService = contabilService;
	}

	public SccParamProcessoService getSccParamProcessoService() {
		return sccParamProcessoService;
	}

	public void setSccParamProcessoService(SccParamProcessoService sccParamProcessoService) {
		this.sccParamProcessoService = sccParamProcessoService;
	}

	public SccGrupoCobillingService getSccGrupoCobillingService() {
		return sccGrupoCobillingService;
	}

	public void setSccGrupoCobillingService(SccGrupoCobillingService sccGrupoCobillingService) {
		this.sccGrupoCobillingService = sccGrupoCobillingService;
	}

	public SccEmailCobillingService getSccEmailCobillingService() {
		return sccEmailCobillingService;
	}

	public void setSccEmailCobillingService(SccEmailCobillingService sccEmailCobillingService) {
		this.sccEmailCobillingService = sccEmailCobillingService;
	}

	public SccRelatorioCobillingService getSccRelatorioCobillingService() {
		return sccRelatorioCobillingService;
	}

	public void setSccRelatorioCobillingService(SccRelatorioCobillingService sccRelatorioCobillingService) {
		this.sccRelatorioCobillingService = sccRelatorioCobillingService;
	}

	public SccBatimentoArquivosService getSccBatimentoArquivosService() {
		return sccBatimentoArquivosService;
	}

	public void setSccBatimentoArquivosService(SccBatimentoArquivosService sccBatimentoArquivosService) {
		this.sccBatimentoArquivosService = sccBatimentoArquivosService;
	}

	public SccConfirmacaoRepasseService getSccConfirmacaoRepasseService() {
		return sccConfirmacaoRepasseService;
	}

	public void setSccConfirmacaoRepasseService(
			SccConfirmacaoRepasseService sccConfirmacaoRepasseService) {
		this.sccConfirmacaoRepasseService = sccConfirmacaoRepasseService;
	}

	public SccNaoConfAplAjustesService getSccNaoConfAplAjustesService() {
		return sccNaoConfAplAjustesService;
	}

	public void setSccNaoConfAplAjustesService(SccNaoConfAplAjustesService sccNaoConfAplAjustesService) {
		this.sccNaoConfAplAjustesService = sccNaoConfAplAjustesService;
	}

	public SccAcoesCobrancaService getSccAcoesCobrancaService() {
		return sccAcoesCobrancaService;
	}

	public void setSccAcoesCobrancaService(
			SccAcoesCobrancaService sccAcoesCobrancaService) {
		this.sccAcoesCobrancaService = sccAcoesCobrancaService;
	}

	public SccFaturasService getSccFaturasService() {
		return sccFaturasService;
	}

	public void setSccFaturasService(SccFaturasService sccFaturasService) {
		this.sccFaturasService = sccFaturasService;
	}

	public SccNaoConfAplBatimentoEstornoDebitoService getSccNaoConfAplBatimentoEstornoDebitoService() {
		return sccNaoConfAplBatimentoEstornoDebitoService;
	}

	public void setSccNaoConfAplBatimentoEstornoDebitoService(
			SccNaoConfAplBatimentoEstornoDebitoService sccNaoConfAplBatimentoEstornoDebitoService) {
		this.sccNaoConfAplBatimentoEstornoDebitoService = sccNaoConfAplBatimentoEstornoDebitoService;
	}

	public SccPreRelatorioEventosService getSccPreRelatorioEventosService() {
		return sccPreRelatorioEventosService;
	}

	public void setSccPreRelatorioEventosService(
			SccPreRelatorioEventosService sccPreRelatorioEventosService) {
		this.sccPreRelatorioEventosService = sccPreRelatorioEventosService;
	}

	public SccArquivosNaoProcessadosService getSccArquivosNaoProcessadosService() {
		return sccArquivosNaoProcessadosService;
	}

	public void setSccArquivosNaoProcessadosService(
			SccArquivosNaoProcessadosService sccArquivosNaoProcessadosService) {
		this.sccArquivosNaoProcessadosService = sccArquivosNaoProcessadosService;
	}

	public SccAssexuadosService getSccAssexuadosService() {
		return sccAssexuadosService;
	}

	public void setSccAssexuadosService(SccAssexuadosService sccAssexuadosService) {
		this.sccAssexuadosService = sccAssexuadosService;
	}

	public SccEncaminhadoMobileService getSccEncaminhadoMobileService() {
		return sccEncaminhadoMobileService;
	}

	public void setSccEncaminhadoMobileService(
			SccEncaminhadoMobileService sccEncaminhadoMobileService) {
		this.sccEncaminhadoMobileService = sccEncaminhadoMobileService;
	}

	public SccAlocadasMobileService getSccAlocadasMobileService() {
		return sccAlocadasMobileService;
	}

	public void setSccAlocadasMobileService(
			SccAlocadasMobileService sccAlocadasMobileService) {
		this.sccAlocadasMobileService = sccAlocadasMobileService;
	}

	public SccAlocadasMobileSemProcessamentoService getSccAlocadasMobileSemProcessamentoService() {
		return sccAlocadasMobileSemProcessamentoService;
	}

	public void setSccAlocadasMobileSemProcessamentoService(
			SccAlocadasMobileSemProcessamentoService sccAlocadasMobileSemProcessamentoService) {
		this.sccAlocadasMobileSemProcessamentoService = sccAlocadasMobileSemProcessamentoService;
	}

	public SccExtracaoCDRsService getSccExtracaoCDRsService() {
		return sccExtracaoCDRsService;
	}

	public void setSccExtracaoCDRsService(
			SccExtracaoCDRsService sccExtracaoCDRsService) {
		this.sccExtracaoCDRsService = sccExtracaoCDRsService;
	}

	public SccNaoConfAplFaturamentoService getSccNaoConfAplFaturamentoService() {
		return sccNaoConfAplFaturamentoService;
	}

	public void setSccNaoConfAplFaturamentoService(
			SccNaoConfAplFaturamentoService sccNaoConfAplFaturamentoService) {
		this.sccNaoConfAplFaturamentoService = sccNaoConfAplFaturamentoService;
	}
	
	
	public SccPreChamadasCreditoService getSccPreChamadasCreditoService() {
		return sccPreChamadasCreditoService;
	}

	public void setSccPreChamadasCreditoService(
			SccPreChamadasCreditoService sccPreChamadasCreditoService) {
		this.sccPreChamadasCreditoService = sccPreChamadasCreditoService;
	}

	public SccContingenciaFiscalService getSccContingenciaFiscalService() {
		return sccContingenciaFiscalService;
	}

	public void setSccContingenciaFiscalService(
			SccContingenciaFiscalService sccContingenciaFiscalService) {
		this.sccContingenciaFiscalService = sccContingenciaFiscalService;
	}

	public SccBatimentoFiscalService getSccBatimentoFiscalService() {
		return sccBatimentoFiscalService;
	}

	public void setSccBatimentoFiscalService(
			SccBatimentoFiscalService sccBatimentoFiscalService) {
		this.sccBatimentoFiscalService = sccBatimentoFiscalService;
	}

	public SccArquivosFiscaisService getSccArquivosFiscaisService() {
		return sccArquivosFiscaisService;
	}

	public void setSccArquivosFiscaisService(
			SccArquivosFiscaisService sccArquivosFiscaisService) {
		this.sccArquivosFiscaisService = sccArquivosFiscaisService;
	}

	public void setSccAcordoParcelamentoService(SccAcordoParcelamentoService sccAcordoParcelamentoService) {
		this.sccAcordoParcelamentoService = sccAcordoParcelamentoService;
	}

	public void setSccFinanceiroService(SccFinanceiroService sccFinanceiroService) {
		this.sccFinanceiroService = sccFinanceiroService;
	}

	public void setSccRelBatimentoEstornoDebitoService(
			SccRelBatimentoEstornoDebitoService sccRelBatimentoEstornoDebitoService) {
		this.sccRelBatimentoEstornoDebitoService = sccRelBatimentoEstornoDebitoService;
	} 

	public SccRelatorioChamadasRefaturamentoService getSccRelatorioChamadasRefaturamentoService() {
		return sccRelatorioChamadasRefaturamentoService;
	}

	public void setSccRelatorioChamadasRefaturamentoService(
			SccRelatorioChamadasRefaturamentoService sccRelatorioChamadasRefaturamentoService) {
		this.sccRelatorioChamadasRefaturamentoService = sccRelatorioChamadasRefaturamentoService;
	}

	public SccBatimentoInterfaceService getSccBatimentoInterfaceService() {
		return sccBatimentoInterfaceService;
	}

	public void setSccBatimentoInterfaceService(
			SccBatimentoInterfaceService sccBatimentoInterfaceService) {
		this.sccBatimentoInterfaceService = sccBatimentoInterfaceService;
	}

	public SccRelatorioConciliacaoService getSccRelatorioConciliacaoService() {
		return sccRelatorioConciliacaoService;
	}

	public void setSccRelatorioConciliacaoService(
			SccRelatorioConciliacaoService sccRelatorioConciliacaoService) {
		this.sccRelatorioConciliacaoService = sccRelatorioConciliacaoService;
	}

	public SccBatimentoWruppService getSccBatimentoWruppService() {
		return sccBatimentoWruppService;
	}

	public void setSccBatimentoWruppService(
			SccBatimentoWruppService sccBatimentoWruppService) {
		this.sccBatimentoWruppService = sccBatimentoWruppService;
	}

	public SccSolicitacaoVumService getSccSolicitacaoVumService() {
		return sccSolicitacaoVumService;
	}

	public void setSccSolicitacaoVumService(SccSolicitacaoVumService sccSolicitacaoVumService) {
		this.sccSolicitacaoVumService = sccSolicitacaoVumService;
	}

	public SccAssinaturaPreService getSccAssinaturaPreService() {
		return sccAssinaturaPreService;
	}

	public void setSccAssinaturaPreService(SccAssinaturaPreService sccAssinaturaPreService) {
		this.sccAssinaturaPreService = sccAssinaturaPreService;
	}

	public SccPreRelatorioCriticasService getSccPreRelatorioCriticasService() {
		return sccPreRelatorioCriticasService;
	}

	public void setSccPreRelatorioCriticasService(
			SccPreRelatorioCriticasService sccPreRelatorioCriticasService) {
		this.sccPreRelatorioCriticasService = sccPreRelatorioCriticasService;
	}

	public SccRelatorioQuestionamentoService getSccRelatorioQuestionamentoService() {
		return sccRelatorioQuestionamentoService;
	}

	public void setSccRelatorioQuestionamentoService(
			SccRelatorioQuestionamentoService sccRelatorioQuestionamentoService) {
		this.sccRelatorioQuestionamentoService = sccRelatorioQuestionamentoService;
	}

	public SccRelatorioQuestionamentoResultadoService getSccRelatorioQuestionamentoResultadoService() {
		return sccRelatorioQuestionamentoResultadoService;
	}

	public void setSccRelatorioQuestionamentoResultadoService(
			SccRelatorioQuestionamentoResultadoService sccRelatorioQuestionamentoResultadoService) {
		this.sccRelatorioQuestionamentoResultadoService = sccRelatorioQuestionamentoResultadoService;
	}
	
	public SccPosRelatorioDisputaDetalheService getSccPosRelatorioDisputaDetalheService() {
		return sccPosRelatorioDisputaDetalheService;
	}

	public void setSccPosRelatorioDisputaDetalheService(
			SccPosRelatorioDisputaDetalheService sccPosRelatorioDisputaDetalheService) {
		this.sccPosRelatorioDisputaDetalheService = sccPosRelatorioDisputaDetalheService;
	}

	public SccRelDemonstrativoDesempenhoPenalidadeService getSccRelDemonstrativoDesempenhoPenalidadeService() {
		return sccRelDemonstrativoDesempenhoPenalidadeService;
	}

	public void setSccRelDemonstrativoDesempenhoPenalidadeService(
			SccRelDemonstrativoDesempenhoPenalidadeService sccRelDemonstrativoDesempenhoPenalidadeService) {
		this.sccRelDemonstrativoDesempenhoPenalidadeService = sccRelDemonstrativoDesempenhoPenalidadeService;
	}

	public void setSccQuestionamentoService(SccQuestionamentoService sccQuestionamentoService) {
		this.sccQuestionamentoService = sccQuestionamentoService;
	}
	
	public void setSccDisputaService(SccDisputaService sccDisputaService){
		this.sccDisputaService = sccDisputaService;
	}

	public void setSccDisputaPrePagoService(SccDisputaPrePagoService sccDisputaPrePagoService) {
		this.sccDisputaPrePagoService = sccDisputaPrePagoService;
	}

	public void setSccContestacaoPrePagoService(SccContestacaoPrePagoService sccContestacaoPrePagoService) {
		this.sccContestacaoPrePagoService = sccContestacaoPrePagoService;
	}

	public SccContestacaoPrePagoService getSccContestacaoPrePagoService() {
		return sccContestacaoPrePagoService;
	}

	public void setSccAssociacaoRelatorioGrupoService(
			SccAssociacaoRelatorioGrupoService sccAssociacaoRelatorioGrupoService) {
		this.sccAssociacaoRelatorioGrupoService = sccAssociacaoRelatorioGrupoService;
	}

	public SccChamadasRefaturamentoService getSccChamadasRefaturamentoService() {
		return sccChamadasRefaturamentoService;
	}

	public void setSccChamadasRefaturamentoService(
			SccChamadasRefaturamentoService sccChamadasRefaturamentoService) {
		this.sccChamadasRefaturamentoService = sccChamadasRefaturamentoService;
	}

	public CadastroCriticaPrebillingService getCadastroCriticaPrebillingService() {
		return cadastroCriticaPrebillingService;
	}

	public void setCadastroCriticaPrebillingService(
			CadastroCriticaPrebillingService cadastroCriticaPrebillingService) {
		this.cadastroCriticaPrebillingService = cadastroCriticaPrebillingService;
	}

	public CadastroDadosBancariosService getCadastroDadosBancariosService() {
		return cadastroDadosBancariosService;
	}

	public void setCadastroDadosBancariosService(
			CadastroDadosBancariosService cadastroDadosBancariosService) {
		this.cadastroDadosBancariosService = cadastroDadosBancariosService;
	}

	public void setSccPreFechamentoService(
			SccPreFechamentoService sccPreFechamentoService) {
		this.sccPreFechamentoService = sccPreFechamentoService;
	}

	public void setSccResultadoIndicadorService(
			SccResultadoIndicadorService sccResultadoIndicadorService) {
		this.sccResultadoIndicadorService = sccResultadoIndicadorService;
	}

	public void setSccTarifasCobrarService(
			SccTarifasCobrarService sccTarifasCobrarService) {
		this.sccTarifasCobrarService = sccTarifasCobrarService;
	}
	
	
	
	
	public SccPrePagoCreditosService getSccPrePagoCreditosService() {
		return sccPrePagoCreditosService;
	}

	public void setSccPrePagoCreditosService(
			SccPrePagoCreditosService sccPrePagoCreditosService) {
		this.sccPrePagoCreditosService = sccPrePagoCreditosService;
	}

	public void setSccComposicaoGrupoEmailService(
			SccComposicaoGrupoEmailService sccComposicaoGrupoEmailService) {
		this.sccComposicaoGrupoEmailService = sccComposicaoGrupoEmailService;
	}

	public SccComposicaoGrupoEmailService getSccComposicaoGrupoEmailService() {
		return sccComposicaoGrupoEmailService;
	}
	
	
	
}
        
   	
