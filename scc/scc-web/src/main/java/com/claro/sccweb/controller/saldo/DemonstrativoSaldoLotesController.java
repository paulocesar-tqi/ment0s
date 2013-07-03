package com.claro.sccweb.controller.saldo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.entity.SccProdutoCobilling;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.BasicIntegerItem;
import com.claro.sccweb.controller.validator.DemonstrativoSaldoLotesValidator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.DemonstrativoSaldoLotesForm;
import com.claro.sccweb.service.data.saldos.DemonstrativoSaldoEvento;
import com.claro.sccweb.service.data.saldos.DemonstrativoSaldoRejeicao;
import com.claro.sccweb.service.data.saldos.TabelaDemonstrativoSaldo;

@Controller
@RequestMapping(value="/user/saldo/demonstrativo")
public class DemonstrativoSaldoLotesController extends BaseOperationController<DemonstrativoSaldoLotesForm> {

	private final DemonstrativoSaldoLotesValidator validator = new  DemonstrativoSaldoLotesValidator();
	
	private final String[][] index = {
		{"X", "  TOTAL DE CHAMADAS ACEITAS"},
		{"C", "  C  REJEITADO NA CRITICA INICIAL"},
		{"E", "  E  REJEITADO NO FATURAMENTO"},
		{"F", "  F  CHAMADA FATURADA"},
		{"A", "  A  CHAMADA ARRECADADA"},
		{"D", "  D  REVERSAO DE PAGAMENTO"},
		{"R", "  R  CHAMADA REPASSADA"},
		{"J", "  J  CHAMADA CONTESTADA NÃO ARRECADADA"},
		{"K", "  K  CHAMADA CONTESTADA ARRECADADA"},
		{"L", "  L  CHAMADA CONTESTADA REPASSADA"},
		{"H", "  H  INADIMPLENTE HÁ MAIS DE 90 DIAS"},
		{"I", "  I  INADIMPLENTE HÁ MAIS DE 180 DIAS"},
		{"W", "  W  EM PARCELAMENTO"},
		{"P", "  P  ARRECADADO VIA PARCELAMENTO"},
		{"B", "  B  FATURA CANCELADA"},
		{"V", "  V  ALTERAÇÃO DE VENCIMENTO"},
		{"Y", "  TOTAL DE CHAMADAS RETORNADAS"},
		{"Z", "  TOTAL DE CHAMADAS ACEITAS NÃO RETORNADAS (SALDO)"}};
	
	public ModelAndView gerar(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME)  BaseForm _form,BindingResult bindingResult,Model model) throws Exception
	{
		ModelAndView mav = new ModelAndView(getViewName());
		DemonstrativoSaldoLotesForm form = (DemonstrativoSaldoLotesForm)_form;
		cacheMyForm(getClass(), form);
		Date dataInicial = calculaDataInicialPeriodo(form.getMesInicio(), form.getAnoInicio());
		Date dataFinal = calculaDataFinalPeriodo(form.getMesFinal(), form.getAnoFinal());
		
		Map<String, DemonstrativoSaldoEvento> map = getServiceManager().getSaldosService().geraDemonstrativoSaldo(form.getCdEOTClaro(), form.getCdEOTLD(), form.getCdProdutoCobilling(), form.getCdTipoArquivo(), dataInicial, dataFinal);
		
		/* CESAR SMITH 20/07/2012 */
		List<TabelaDemonstrativoSaldo> tabela = new ArrayList<TabelaDemonstrativoSaldo>();
		
		for (int i=0;i<index.length;i++)
		{
			if (map.containsKey(index[i][0]))
			{
				map.get(index[i][0]).setDsMotivoEvento(index[i][1]);
				tabela.add(new TabelaDemonstrativoSaldo(map.get(index[i][0])));
				if (map.get(index[i][0]).getDetalhesRejeicao().size() > 0)
				{
					for (int j=0;j<map.get(index[i][0]).getDetalhesRejeicao().size();j++)
					{
						DemonstrativoSaldoRejeicao rejeicao = map.get(index[i][0]).getDetalhesRejeicao().get(j);
						tabela.add(new TabelaDemonstrativoSaldo(rejeicao));
					}
				}
			} else {
				DemonstrativoSaldoEvento eventoVazio = new DemonstrativoSaldoEvento();
				eventoVazio.setDsMotivoEvento(index[i][1]);
				tabela.add(new TabelaDemonstrativoSaldo(eventoVazio));
			}
		}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, tabela, request);
		/**/
		return mav;
	}
	
	public ModelAndView excel(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME)  BaseForm _form,BindingResult bindingResult,Model model) throws Exception
	{
		return new ModelAndView("demonstrativo_saldo_lotes_excel");
	}
	
	protected String getViewName() {
		return "demonstrativo_saldo_lotes_filtro";
	}
	
	protected DemonstrativoSaldoLotesForm getForm() {
		return new DemonstrativoSaldoLotesForm();
	}
	
	protected Validator getValidator() {		
		return this.validator;
	}
	
	@ModelAttribute("operadorasClaro")
	public List<SccOperadora> populaOperadorasClaro() throws Exception {
		List<SccOperadora> comboList = new ArrayList<SccOperadora>();
		SccOperadora allValues = new SccOperadora();
		allValues.setCdEot(BasicDAO.GET_ALL_STRING);
		allValues.setDsOperadora("Todas");
		comboList.add(0,allValues);
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pequisaOperadorasClaroComM());
		return comboList;
	}
	/*
	@ModelAttribute("operadorasClaro")
	public List<SccOperadora> populaOperadoras() throws Exception
	{
		return super.populaOperadorasClaro(false);
	}
	*/
	
	@ModelAttribute("operadorasExternas")
	public List<SccOperadora> populaOperadorasExternas() throws Exception {
		List<SccOperadora> comboList = new ArrayList<SccOperadora>();
		SccOperadora allValues = new SccOperadora();
		allValues.setCdEot(BasicDAO.GET_ALL_STRING);
		allValues.setDsOperadora("Todas");
		comboList.add(0,allValues);
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pesquisaOperadorasExternas());
		return comboList;
	}
	/*
	@ModelAttribute("operadorasExternas")
	public List<SccOperadora> populaOperadorasExternas() throws Exception
	{
		return super.populaOperadorasExternas(false);
	}
	*/
	

	@ModelAttribute("produtos")
	public List<SccProdutoCobilling> populaProdutos() throws Exception {
		return  getServiceManager().getContratoService().listaTodosProdutos();
		
	}
	
	@ModelAttribute("tiposArquivo")
	public List<BasicIntegerItem> populaTiposArquivo()
	{
		List<BasicIntegerItem> comboList = new ArrayList<BasicIntegerItem>();
		comboList.add(new BasicIntegerItem(5L, "Arquivo de Remessa"));
		comboList.add(new BasicIntegerItem(55L, "Arquivo de Assinatura"));
		comboList.add(new BasicIntegerItem(555L, "Arquivo de Remessa FAKE"));
		return comboList;
	}
	
	@ModelAttribute("meses")
	public List<BasicIntegerItem> populaMeses() 
	{
		return _populaComboMeses();
	}
	
}
