package com.claro.sccweb.controller.relatorio.pre;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
import com.claro.sccweb.controller.validator.RelatorioPrestacaoServicoPreValidator;
import com.claro.sccweb.decorator.RelatorioPrestacaoServicoPreDecorator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.RelPrestacaoServicoPosForm;
import com.claro.sccweb.form.RelatorioPrestacaoServicoPreForm;
import com.claro.sccweb.service.to.ConsultaRepassePosTO;


@Controller
@RequestMapping(value="/user/relatorio/servico/pre")
public class RelatorioServicoPreController extends BaseOperationController<RelatorioPrestacaoServicoPreForm>{
	
	
	private final RelatorioPrestacaoServicoPreValidator validator = new RelatorioPrestacaoServicoPreValidator();
	
	public ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response, BaseForm _form,	BindingResult bindingResult,Model model) throws Exception	{
		ModelAndView mav = new ModelAndView(getViewName());
		RelatorioPrestacaoServicoPreForm form = (RelatorioPrestacaoServicoPreForm)_form;
		
		gerarPeriodo(form);
		List<RelPrestacaoServicoView> rows = getServiceManager().getRepasseService().gerarRelatorioPrestacaoServicoPre(criarTo(form));
		
		List<RelatorioPrestacaoServicoPreDecorator> decoratorList = new ArrayList<RelatorioPrestacaoServicoPreDecorator>(rows.size());
		for (int i=0;i<rows.size();i++)	{
			RelatorioPrestacaoServicoPreDecorator decorator = new RelatorioPrestacaoServicoPreDecorator(rows.get(i), i);
			decoratorList.add(decorator);
			}
		cacheMyForm(getClass(), form);
		mav.addObject(FORM_NAME, form);
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		return mav;
	}
	
	private ConsultaRepassePosTO criarTo(RelatorioPrestacaoServicoPreForm form) {

		ConsultaRepassePosTO consultaRepassePosTO = new ConsultaRepassePosTO();
		consultaRepassePosTO.setCdEOTClaro(form.getCdEOTClaro());
		consultaRepassePosTO.setCdEOTLD(form.getCdEOTLD());
		consultaRepassePosTO.setMesAno(form.getMesAno());
		return consultaRepassePosTO;
		
	}
	
	private void gerarPeriodo(RelatorioPrestacaoServicoPreForm form){
		
		if(form.getMesRelatorio() != null && form.getAnoRelatorio() != null) {
			
			String mes =  form.getMesRelatorio().toString();
			String ano =  form.getAnoRelatorio().toString();
			if(mes.length() == 1 && ano.length() == 4){
				form.setMesAno("0"+ mes + ano);
			}else{
				form.setMesAno(mes + ano);
			}
			
		}
	}
	
/*	private void gerarPeriodo(RelatorioPrestacaoServicoPreForm form) throws ParseException{
		
		if(form.getMesRelatorio() != null && form.getAnoRelatorio() != null) {
			
			form.setDataInicial(formatarDataSemHora(calculaDataInicialPeriodo(form.getMesRelatorio(), form.getAnoRelatorio())));
			form.setDataFinal(formatarDataSemHora(pegarFimMes(form)));
		}
	}
	
*/	
		
	public Date pegarFimMes(RelatorioPrestacaoServicoPreForm form){

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, form.getAnoRelatorio().intValue());
		cal.set(Calendar.MONTH,form.getMesRelatorio().intValue() );
		cal.set(Calendar.DAY_OF_MONTH, 0);
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
	
	
	public ModelAndView excel(HttpServletRequest request, HttpServletResponse response,
			@Valid @ModelAttribute(FORM_NAME)  BaseForm _form,BindingResult bindingResult,Model model) throws Exception	{
		ModelAndView mav = new ModelAndView("relatorio_prestacao_servico_pre_filtro");
		return mav;
	}
	
	 
	protected String getViewName() {
		return "relatorio_prestacao_servico_pre_filtro";
	}

	 
	protected RelatorioPrestacaoServicoPreForm getForm() {
		return new RelatorioPrestacaoServicoPreForm();
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
		SccOperadora allValues = new SccOperadora();
		allValues.setCdEot(BasicDAO.GET_ALL_STRING);
		allValues.setDsOperadora("Todas");
		comboList.add(0,allValues);
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
	
	@RequestMapping(value="/json/lista_produtos/{cdEOTLD}" , method=RequestMethod.GET)
	public void pesquisaProdutosLD(@PathVariable("cdEOTLD") String cdEOT,HttpServletRequest request, HttpServletResponse response,@ModelAttribute("form") RelPrestacaoServicoPosForm form) throws Exception {		
		List<SccProdutoCobilling> produtos = getServiceManager().getContratoService().pesquisaProdutosOperadoraLD(cdEOT);		
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


	
}
