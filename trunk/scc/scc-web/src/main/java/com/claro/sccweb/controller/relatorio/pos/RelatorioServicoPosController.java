package com.claro.sccweb.controller.relatorio.pos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.entity.SccPeriodicidadeRepasse;
import com.claro.cobillingweb.persistence.entity.SccProdutoCobilling;
import com.claro.cobillingweb.persistence.view.RelPrestacaoServicoView;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.BasicIntegerItem;
import com.claro.sccweb.controller.validator.RelatorioPrestacaoServicoPosValidator;
import com.claro.sccweb.decorator.RelatorioPrestacaoServicoPosDecorator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.RelPrestacaoServicoPosForm;
import com.claro.sccweb.service.to.ConsultaRepassePosTO;


@Controller
@RequestMapping(value="/user/relatorio/servico/pos")
public class RelatorioServicoPosController extends BaseOperationController<RelPrestacaoServicoPosForm>{
	
	
	
	private static final String FWD_EXCEL_PRESTACAO_SERVICO = "relatorio_prestacao_servico_pos_filtro_excel";
	private final RelatorioPrestacaoServicoPosValidator validator = new RelatorioPrestacaoServicoPosValidator();
	
	public ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response, BaseForm _form,	BindingResult bindingResult,Model model) throws Exception	{
		ModelAndView mav = new ModelAndView(getViewName());
		RelPrestacaoServicoPosForm form = (RelPrestacaoServicoPosForm)_form;
		
		gerarPeriodo(form);
		List<RelPrestacaoServicoView> rows = getServiceManager().getRepasseService().gerarRelatorioPrestacaoServicoPos(criarTo(form));
		
		
		List<RelatorioPrestacaoServicoPosDecorator> decoratorList = new ArrayList<RelatorioPrestacaoServicoPosDecorator>(rows.size());
		for (int i=0;i<rows.size();i++)	{
			RelatorioPrestacaoServicoPosDecorator decorator = new RelatorioPrestacaoServicoPosDecorator(rows.get(i), i);
			decoratorList.add(decorator);
			}
		cacheMyForm(getClass(), form);
		mav.addObject(FORM_NAME, form);
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		return mav;
	}
	
	public ModelAndView excel(HttpServletRequest request, HttpServletResponse response,
			@Valid @ModelAttribute(FORM_NAME)  BaseForm _form,BindingResult bindingResult,Model model) throws Exception	{
		ModelAndView mav = new ModelAndView(FWD_EXCEL_PRESTACAO_SERVICO);
		return mav;
	}
	

	
	
	private ConsultaRepassePosTO criarTo(RelPrestacaoServicoPosForm form) {
		ConsultaRepassePosTO consultaRepassePosTO = new ConsultaRepassePosTO();
		
		consultaRepassePosTO.setCdEOTClaro(form.getCdEOTClaro());
		consultaRepassePosTO.setCdEOTLD(form.getCdEOTLD());
		consultaRepassePosTO.setCdProdutoCobilling(form.getCdProdutoCobilling());
		consultaRepassePosTO.setDtInicialRepasse(form.getDataInicial());
		consultaRepassePosTO.setDtFinalRepasse(form.getDataFinal());
		
		return consultaRepassePosTO;
		
	}
	
	private void gerarPeriodo(RelPrestacaoServicoPosForm form) throws ParseException{
		
		if (form.getCdPeriodicidade().equals(1L)){
			form.setDataInicial(formatarDataSemHora(calculaDataInicialPeriodo(form.getMesRelatorio(), form.getAnoRelatorio())));
			form.setDataFinal(formatarDataSemHora(informarData(15, form.getMesRelatorio().intValue(), form.getAnoRelatorio().intValue())));
		}else{
			form.setDataInicial(formatarDataSemHora(informarData(16, form.getMesRelatorio().intValue(), form.getAnoRelatorio().intValue())));
			form.setDataFinal(formatarDataSemHora(calculaDataFinalPeriodo(form.getMesRelatorio(), form.getAnoRelatorio())));
		}
		
	}
	
	private Date informarData(int dia, int mes, int ano){
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, ano);
		cal.set(Calendar.MONTH,mes - 1);
		cal.set(Calendar.DAY_OF_MONTH, dia);
		return cal.getTime();

		
	}
	
	private String pegarData(Date data){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(data);
	}
	
	@SuppressWarnings("unused")
	private Date formatarData(int tipo, Date data) throws ParseException{
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		if (tipo == 0){
			return new Date(sdf.parse(pegarData(data).concat(" 00:00:00")).getTime());
		}
		return new Date(sdf.parse(pegarData(data).concat(" 23:59:59")).getTime());
	}
	
	private Date formatarDataSemHora(Date data) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return new Date(sdf.parse(sdf.format(data)).getTime());
	}
	
	
	protected String getViewName() {
		return "relatorio_prestacao_servico_pos_filtro";
	}

	 
	protected RelPrestacaoServicoPosForm getForm() {
		return new RelPrestacaoServicoPosForm();
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
	
	@ModelAttribute("operadorasExternas")
	public List<SccOperadora> populaOperadorasExternas() throws Exception {
		List<SccOperadora> comboList = new ArrayList<SccOperadora>();
		SccOperadora nullValue = new SccOperadora();
		nullValue.setCdEot(BasicDAO.GET_ALL_STRING);
		nullValue.setDsOperadora("Todas");
		comboList.add(0,nullValue);
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pesquisaOperadorasExternas());
		return comboList;
	}

	@ModelAttribute("produtos")
	public List<SccProdutoCobilling> populaProdutosCobilling() throws Exception {
		List<SccProdutoCobilling> comboList = new ArrayList<SccProdutoCobilling>();
		SccProdutoCobilling nullValue = new SccProdutoCobilling();
		nullValue.setNoProdutoCobilling("Selecione...");
		nullValue.setCdProdutoCobilling(BasicDAO.NULL);
		comboList.add(0,nullValue);
		return comboList;
	}
	
	
	
	@ModelAttribute("meses")
	public List<BasicIntegerItem> populaMeses() {
		return _populaComboMeses();
	}
	
	@RequestMapping(value="/json/lista_produtos/{cdEOTLD}/{cdEOTClaro}" , method=RequestMethod.GET)
	public void pesquisaProdutosLD(@PathVariable("cdEOTLD") String cdEOT,@PathVariable("cdEOTClaro") String cdEOTClaro, HttpServletRequest request, HttpServletResponse response,@ModelAttribute("form") RelPrestacaoServicoPosForm form) throws Exception {
		
		List<SccProdutoCobilling> produtos = null;
		
		if(cdEOT.equals("*") && cdEOTClaro.equals("*")){
			produtos = getServiceManager().getContratoService().pesquisaProdutosOperadoraLDTodas();
		}else{
			produtos = getServiceManager().getContratoService().pesquisaProdutosOperadoraLD(cdEOT);
			
		}
		JSONObject jsonResponse = new JSONObject();				
		jsonResponse.put("0L","Selecione....");		
		for (int i=0;i<produtos.size();i++) {			
			jsonResponse.put(produtos.get(i).getCdProdutoCobilling().toString(), produtos.get(i).getNoProdutoCobilling());			
		}
		response.setContentType("application/json");
		response.getWriter().print(jsonResponse.toString());
	}
	
	@RequestMapping(value="/json/lista_periodos/{cdProdutoCobilling}/{cdEOTLD}" , method=RequestMethod.GET)
	public void pesquisaPeriodos(@PathVariable("cdProdutoCobilling") Long cdProduto,@PathVariable("cdEOTLD") String cdEOT,HttpServletRequest request, HttpServletResponse response) throws Exception {			
		List<SccPeriodicidadeRepasse> repasses = getServiceManager().getContratoService().pesquisaPeriodicidadeRepasse(cdEOT, cdProduto);
		JSONObject jsonResponse = new JSONObject();				
		jsonResponse.put("0L","Selecione....");		
		for (int i=0;i<repasses.size();i++) {			
			jsonResponse.put(repasses.get(i).getCdPeriodicidadeRepasse().toString(), repasses.get(i).getNoPeriodicidadeRepasse());			
		}
		response.setContentType("application/json");
		response.getWriter().print(jsonResponse.toString());
	}

	
	@RequestMapping(value="/tab1" , method = RequestMethod.GET)
	public ModelAndView tab1(HttpServletRequest request, HttpServletResponse response) throws Exception {
			ModelAndView mav = new ModelAndView(getViewName());
			Object form = getMyFormFromCache(getClass());
			if (form != null)
				mav.addObject(FORM_NAME, form);
			else
				mav.addObject(FORM_NAME, getForm());
	    	return mav;  
	}


	
}
