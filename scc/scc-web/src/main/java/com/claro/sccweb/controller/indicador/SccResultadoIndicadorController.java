package com.claro.sccweb.controller.indicador;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.constants.CobillingConstants;
import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccIndicador;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.view.SccResultadoIndicadorView;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.validator.ResultadoIndicadorValidator;
import com.claro.sccweb.form.ResultadoIndicadorForm;
import com.claro.sccweb.form.SccIndicadorForm;
import com.claro.sccweb.service.SccIndicadorService;
import com.claro.sccweb.service.SccResultadoIndicadorService;
import com.claro.sccweb.service.ServiceException;

@Controller
@RequestMapping(value="/user/indicador/resultado")
public class SccResultadoIndicadorController extends BaseOperationController<ResultadoIndicadorForm> {
	
	@SuppressWarnings("unused")
	private static final String TIPO_RELATORIO_PRE ="P";
	
	private static final String FWD_RESULTADO_INDICADOR = "relatorio_resultado_indicador";
	private static final String FWD_RESULTADO_INDICADOR_ARQ = "relatorio_resultado_indicador_arq";
	
	private static final String OPERACAO_EXCEL =	"excel";
	public static final String FWD_EXCEL_RESULTADO_INDICADOR = "relatorio_resultado_indicador_excel";
	
	private ResultadoIndicadorValidator validator = new ResultadoIndicadorValidator();
	
	@Autowired
	private SccIndicadorService sccIndicadorService;
	
	@Autowired
	private SccResultadoIndicadorService sccResultadoIndicadorService; 
	
	
	@RequestMapping(value="listar", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView pesquisarByFiltro(HttpServletRequest request, HttpServletResponse response, ResultadoIndicadorForm form) throws Exception {
		
		ModelAndView mav = null;
		
		if(!form.getDsPeriodicidade().equalsIgnoreCase(CobillingConstants.BSC_PERIODICIDADE_DIARIA)){
			form.setListResultadoIndicadorViewMensal(listaMensal(form));
		}else{
			form.setListResultadoIndicadorView(listaDiaria(form));
			
		}
		
		if(form.getOperacao().equals(OPERACAO_EXCEL)){
			if(form.getListResultadoIndicadorView() != null && form.getListResultadoIndicadorView().size() > 0){
				mav = new ModelAndView(FWD_EXCEL_RESULTADO_INDICADOR, "filtro", form);
			}
			
		}else{
			mav = new ModelAndView(FWD_RESULTADO_INDICADOR, "filtro", form);
		}
		
		return mav;
		

		
	}
	
	@RequestMapping(value = "/listarArquivos2", method=RequestMethod.GET)
	public ModelAndView  gerarArquivoIndicador2(@RequestParam("cdEotLd")String cdEotLd, @RequestParam("cdEotClaro") String cdEotClaro, @RequestParam("dataInicial")String dataInicial, @RequestParam("dataFinal")String dataFinal, HttpServletRequest request,HttpServletResponse response, ResultadoIndicadorForm form ) throws DAOException, ParseException{
		
		
		form.setListArq(this.sccResultadoIndicadorService.gerarListaFinal(cdEotLd, cdEotClaro, strToDate(dataInicial), strToDate(dataFinal)));
		ModelAndView mav = new ModelAndView(FWD_RESULTADO_INDICADOR_ARQ, "filtro", form.getListArq());
		
		return mav; 
	}

	
	@RequestMapping(value = "/listarArquivos", method=RequestMethod.GET)
	public @ResponseBody List<SccResultadoIndicadorView> gerarArquivoIndicador(@RequestParam("cdEotLd")String cdEotLd, @RequestParam("cdEotClaro") String cdEotClaro, @RequestParam("dataInicial")String dataInicial, @RequestParam("dataFinal")String dataFinal, HttpServletRequest request,HttpServletResponse response, ResultadoIndicadorForm form ) throws DAOException, ParseException{
	
		return this.sccResultadoIndicadorService.gerarListaFinal(cdEotLd, cdEotClaro, strToDate(dataInicial), strToDate(dataFinal));
	}
	
	private List<SccResultadoIndicadorView> listaDiaria(ResultadoIndicadorForm form) throws DAOException{
		
		boolean tipo = form.getTipoContrato().equalsIgnoreCase("PRE")? true: false;
		return this.sccResultadoIndicadorService.gerarRelatorioDiario(form.getCdEotClaro(), form.getCdEotClaro(), form.getCdIndicador(), tipo, form.getDataInicial(), form.getDataFinal());
	}
	
	private List<SccResultadoIndicadorView> listaMensal(ResultadoIndicadorForm form) throws DAOException{
		
		boolean tipo = form.getTipoContrato().equalsIgnoreCase("PRE")? true: false;
		//form.setListResultadoIndicadorView(this.sccResultadoIndicadorService.pesquisaByFiltro(form.getCdEotLd(), form.getCdEotClaro(), form.getCdIndicador(), tipo , form.getDataInicial(), form.getDataFinal()));
		return this.sccResultadoIndicadorService.pesquisaByFiltro(form.getCdEotLd(), form.getCdEotClaro(), form.getCdIndicador(), tipo , form.getDataInicial(), form.getDataFinal());
	}
	
	@ModelAttribute("periodicidades")
	public List<BasicStringItem> popularListaTipo(){
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem(CobillingConstants.BSC_PERIODICIDADE_DIARIA, CobillingConstants.BSC_PERIODICIDADE_DIARIA));
		comboList.add(new BasicStringItem(CobillingConstants.BSC_PERIODICIDADE_MENSAL, CobillingConstants.BSC_PERIODICIDADE_MENSAL));
		return comboList;
		
	}
	
	@ModelAttribute("tipos")
	public List<BasicStringItem> populaTiposContrato() {
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem("POS", "Pós-Pago"));
		comboList.add(new BasicStringItem("PRE", "Pré-Pago"));		
		return comboList;
	}

/*	
	@ModelAttribute("indicadores")
	public List<SccIndicador> carregarCombo(SccIndicadorForm form) throws ServiceException, DAOException{
		
		List<SccIndicador> list = null;
		
		form.getFiltro().setCdTipoContrato(TIPO_RELATORIO_PRE);
		
		list = (List<SccIndicador>) this.sccIndicadorService.gerarCombo(form.getFiltro());
		form.setListIndicador(list);
		return (List<SccIndicador>) form.getListIndicador();
		
	}
*/	
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
		allValues.setCdEot("*");
		allValues.setDsOperadora("Todas");
		comboList.add(0,allValues);
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pesquisaOperadorasExternas());
		return comboList;
	}
	
	
	@RequestMapping(value="/carregarIndicadores", method = RequestMethod.GET)
	public @ResponseBody List<SccIndicador> montarComboBox(@RequestParam("dsPeriodicidade") String dsPeriodicidade, @RequestParam("tipoContrato") String tipoContrato, 
														   HttpServletRequest request,HttpServletResponse response, ResultadoIndicadorForm form) throws ServiceException, DAOException {
		
		List<SccIndicador> list = (List<SccIndicador>) this.sccIndicadorService.montarComboBox(dsPeriodicidade, tipoContrato);
		form.setListIndicadores(list);
		return list;
	}
	
	@RequestMapping(value="/carregarDescricao", method = RequestMethod.GET)
	public @ResponseBody SccIndicador gerarDescricao(@RequestParam("cdIndicador") String cdIndicador, ResultadoIndicadorForm form ) throws DAOException{
		
		return this.sccIndicadorService.findById(cdIndicador);
	}
	
	public void setSccIndicadorService(SccIndicadorService sccIndicadorService) {
		this.sccIndicadorService = sccIndicadorService;
	}

	@Override
	protected String getViewName() {
		
		return FWD_RESULTADO_INDICADOR;
	}

	@Override
	protected ResultadoIndicadorForm getForm() {
		
		return new ResultadoIndicadorForm();
	}

	@Override
	protected Validator getValidator() {
		
		return this.validator;
	}

	public SccIndicadorService getSccIndicadorService() {
		return sccIndicadorService;
	}

	public void setSccResultadoIndicadorService(
			SccResultadoIndicadorService sccResultadoIndicadorService) {
		this.sccResultadoIndicadorService = sccResultadoIndicadorService;
	}


	private Date strToDate(String data) throws ParseException{
		
		SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");
		return formatar.parse(data);
	}
	
	public static void main(String[] args) throws ParseException{
		
		
	      Date data = new Date();
          //arqui vamos configurar o simple date formate
          // d = dia, M (maiusculo) = mes e y = ano (obs: m = minutos)
          SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");
          //imprimir a data
          System.out.println(formatar.format(data));

          //agora vamos converter a String para Date
          String dataString = "01/01/1995";
          data = formatar.parse(dataString);
          System.out.println(data);

		
/*		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");      
		Date data = fmt.parse("17/12/2007 19:30:20");   
		String str = fmt.format(data);   // isto faz com que mostre do jeito que você quer  
		System.out.println(str);*/
	}
	

}
