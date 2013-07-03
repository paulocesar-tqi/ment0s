package com.claro.sccweb.controller.contabil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import com.claro.cobillingweb.persistence.entity.SccArquivoCobilling;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.BasicIntegerItem;
import com.claro.sccweb.controller.validator.RelatorioContabilTransicaoValidator;
import com.claro.sccweb.form.RelatorioContabilTransicaoForm;
import com.claro.sccweb.service.ArquivosService;
import com.claro.sccweb.service.FTPService;

@Controller
@RequestMapping(value="/user/contabil/transicao")
public class RelatorioContabilTransicaoController extends BaseOperationController<RelatorioContabilTransicaoForm> {
	
	public static final String FWD_VIEW_REL_CONTABIL_TRANSICAO = "relatorio_contabil_transicao_filtro";
	public static final String FWD_VIEW_REL_CONTABIL_TRANSICAO_DOWNLOAD = "relatorio_contabil_transicao_download";
	
	@Autowired
	private ArquivosService arquivosService;

	@Autowired
	private FTPService ftpService;
	
	private final RelatorioContabilTransicaoValidator validator = new RelatorioContabilTransicaoValidator();
	
	@RequestMapping(value="listar", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody ModelAndView pesquisarByFiltro(HttpServletRequest request, HttpServletResponse response, RelatorioContabilTransicaoForm form) throws Exception {
		
		List<SccArquivoCobilling> list = this.arquivosService.pesquisaRelatoriosTransicao(form.getCdTipoArquivo(), form.getDataInicial(), form.getDataFinal());
		
		form.setListArquivoCobilling(list);
		
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, list, request);
		ModelAndView mav = new ModelAndView(FWD_VIEW_REL_CONTABIL_TRANSICAO, "filtro", form);
		mav.addObject("list", form.getListArquivoCobilling());
		return mav;

	}

	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/download", method=RequestMethod.GET)
	public ModelAndView efetuarDownload(@RequestParam("nomeArquivo") String nomeArquivo, @RequestParam("diretorio") String diretorio , HttpServletRequest request,HttpServletResponse response, RelatorioContabilTransicaoForm form, Map<String, Object> model) throws Exception {

		ModelAndView mav = new ModelAndView(FWD_VIEW_REL_CONTABIL_TRANSICAO);

			if(this.ftpService.fileExists(diretorio, nomeArquivo) == null){
				form.setErro(CobillingConstants.FILE_NOT_FOUND);
				form.setOperacao("download");
				List<SccArquivoCobilling> list = (List<SccArquivoCobilling>) model.get("list");
				form.setListArquivoCobilling(list);
				mav = new ModelAndView(FWD_VIEW_REL_CONTABIL_TRANSICAO, "filtro", form);
				
			}else{
				mav = new ModelAndView(FWD_VIEW_REL_CONTABIL_TRANSICAO_DOWNLOAD, "filtro", form);
			}
		
		return mav;
	}
	
	protected String getViewName() {
		return FWD_VIEW_REL_CONTABIL_TRANSICAO;
	}

	 
	protected RelatorioContabilTransicaoForm getForm() {		
		return new RelatorioContabilTransicaoForm();
	}

	 
	protected Validator getValidator() {		
		return this.validator;
	}

	@ModelAttribute("tiposArquivo")
	public List<BasicIntegerItem> populaTiposArquivo() 
	{
		List<BasicIntegerItem>  comboList = new ArrayList<BasicIntegerItem>();
		comboList.add(new BasicIntegerItem(900L, "Relatório Interface Contábil"));
		comboList.add(new BasicIntegerItem(905L, "Relatório Eventos Recebidos do Mobile"));
		return comboList;
	}

	public void setFtpService(FTPService ftpService) {
		this.ftpService = ftpService;
	}

	public void setArquivosService(ArquivosService arquivosService) {
		this.arquivosService = arquivosService;
	}
	
}
