package com.claro.sccweb.controller.arquivo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.external.ControlConnectFile;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.BaseFormController;
import com.claro.sccweb.controller.util.BasicIntegerItem;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.util.SearchResultList;
import com.claro.sccweb.controller.validator.ControleArquivosValidator;
import com.claro.sccweb.form.SearchResultForm;
import com.claro.sccweb.service.to.PesquisaArquivosConnectTO;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.ControleArquivoForm;
import com.claro.sccweb.service.ArquivosService;
import com.claro.sccweb.service.ServiceException;


/**
 * Funcionalidade : Arquivos/Controle de Arquivos
 * 
 * Páginas : /WEB-INF/jsp/arquivo/controle/
 * 
 * Descrição : Gerar relatório de Controle de Arquivos que permite consultar
 * os protocolos de arquivos SCC enviados/recebidos via Connect Direct.
 *
 */
@Controller
@RequestMapping("/user/controle/arquivos")
public class ControleArquivosController extends BaseOperationController<ControleArquivoForm>{
	
	private static final String FWD_VIEW_CONTROLE_ARQUIVOS = "controle_arquivos_pesquisa";
	private static final String FWD_EXCEL_CONTROLE_ARQUIVOS = "controle_arquivos_excel";
	
	private final ControleArquivosValidator validator = new ControleArquivosValidator();
	
	@Autowired
	private ArquivosService arquivosService;
	
	
	@ModelAttribute("tiposArquivo")
	public List<BasicStringItem> populaListaTiposArquivo() throws Exception {		
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem(BasicDAO.GET_ALL_STRING, "Todos"));
		comboList.add(new BasicStringItem("TCOT%", "Arquivo de Batimento"));
		comboList.add(new BasicStringItem("TCOU%", "Arquivo de Comprovação de Fraude/Cobrança"));
		comboList.add(new BasicStringItem("EST.%", "Arquivo de Estorno de Débitos"));
		comboList.add(new BasicStringItem("TCOP%", "Arquivos de Parcelamento/Multas & Juros"));
		comboList.add(new BasicStringItem("TCOE%", "Arquivos de Remessa"));
		comboList.add(new BasicStringItem("TCOR%", "Arquivos de Retorno"));
		comboList.add(new BasicStringItem("TCOS%", "Arquivos de Saldo de Lotes"));
		comboList.add(new BasicStringItem("TCOF%", "Arquivos Fiscais"));
		return comboList;
	}
	
	@ModelAttribute("statusArquivo")
	public List<BasicIntegerItem> populaListaStatusArquivo() throws Exception
	{		
		List<BasicIntegerItem> comboList = new ArrayList<BasicIntegerItem>();
		comboList.add(new BasicIntegerItem(BasicDAO.GET_ALL, "Todos"));
		comboList.add(new BasicIntegerItem(0L, "Arquivos com Sucesso"));
		comboList.add(new BasicIntegerItem(1L, "Arquivos com Falha"));
		return comboList;
	}
	
	/**
	 * Executa a pesquisa e popula a tabela de resultados.
	 * @param form Filtro de pesquisa populado pelo usuário.
	 * @return View com a tabela de resultados.
	 */
/*	
	@RequestMapping(value="/listar" , method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView mostraResultados(HttpServletRequest request, HttpServletResponse response, ControleArquivoForm form)
	throws Exception {	

		form.setListControlConnectFiles(gerarListConnectFile(form));
		ModelAndView mav = new ModelAndView(FWD_VIEW_CONTROLE_ARQUIVOS, "filtro", form);
		return mav;		
	}
*/	
	private List<ControlConnectFile> gerarListConnectFile(ControleArquivoForm form) throws ServiceException, DAOException{
		
		return this.arquivosService.pesquisaArquivosConnect(form.getFiltro());
	}

	
	public ModelAndView pesquisar(HttpServletRequest request,HttpServletResponse response,@ModelAttribute(FORM_NAME) BaseForm _form,BindingResult bindingResult, Model model) throws Exception{
		
		ControleArquivoForm form = (ControleArquivoForm)_form;
		form.setListControlConnectFiles(gerarListConnectFile(form));
		ModelAndView mav = new ModelAndView(FWD_VIEW_CONTROLE_ARQUIVOS, "filtro", form);
		return mav;		

		
	}
	
	

	public ModelAndView excel(HttpServletRequest request,HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME) BaseForm _form,BindingResult bindingResult, Model model) throws Exception{
		ControleArquivoForm form = (ControleArquivoForm)_form;
		form.setListControlConnectFiles(gerarListConnectFile(form));
		return new ModelAndView(FWD_EXCEL_CONTROLE_ARQUIVOS);
	}


	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/json" , method= RequestMethod.GET )
	public void getSearchResultList(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		List<ControlConnectFile> result = getSessionDataManager().getSearchResultList().getResult();
		JSONArray data = new JSONArray(); 
		for (int i=0;i<result.size();i++)
			{
			 JSONArray row = new JSONArray();
			 row.put(result.get(i).getDEST_FILE());
			 row.put(getSccDateFormat().format(result.get(i).getPk().getSTRT_DATE()));
			 row.put(getSccDateFormat().format(result.get(i).getSTOP_DATE()));
			 if (result.get(i).getEXIT_CODE().intValue() == 0)
				 row.put("Sucesso");
			 else
				 row.put("Falha");
			 row.put(result.get(i).getEXIT_DESC());
			 data.put(row);
			}
		 JSONObject jsonResponse = new JSONObject();
		 jsonResponse.put("aaData", data);
		 response.setContentType("application/json");
	     response.getWriter().print(jsonResponse.toString());		
	}


	public void setArquivosService(ArquivosService arquivosService) {
		this.arquivosService = arquivosService;
	}

	@Override
	protected String getViewName() {
		
		return FWD_VIEW_CONTROLE_ARQUIVOS;
	}

	@Override
	protected ControleArquivoForm getForm() {

		return new ControleArquivoForm();
	}

	@Override
	protected Validator getValidator() {
		
		return this.validator;
	}
	
}
