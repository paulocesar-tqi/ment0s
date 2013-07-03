/**
 * 
 */
package com.claro.sccweb.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccAssociacaoRelatorioGrupo;
import com.claro.cobillingweb.persistence.entity.SccRelatorioCobilling;
import com.claro.sccweb.controller.validator.CadastroGrupoRelatorioValidator;
import com.claro.sccweb.form.CadastroGrupoRelatorioForm;
import com.claro.sccweb.service.SccAssociacaoRelatorioGrupoService;
import com.claro.sccweb.service.SccRelatorioCobillingService;

/**
 * @author vagner.souza
 *
 */

//@Controller
//@RequestMapping(value="/user/admin/rel/associado")
public class CadastroRelatorioGrupoController {
	
	@Autowired
	private SccAssociacaoRelatorioGrupoService sccAssociacaoRelatorioGrupoService;
											   
	
	@Autowired
	private SccRelatorioCobillingService sccRelatorioCobillingService;
	

	private static final String FWD_VIEW_GRUPO_RELATORIO ="cadastro_grupo_relatorio_cobilling_filtro";
	@SuppressWarnings("unused")
	private static final String FWD_EXCEL_GRUPO_RELATORIO ="cadastro_grupo_relatorio_cobilling_filtro_excel";

	private CadastroGrupoRelatorioValidator validator = new CadastroGrupoRelatorioValidator();
	
	@RequestMapping(value="listar", method = { RequestMethod.GET, RequestMethod.POST })
	public String listar(Model uiModel, CadastroGrupoRelatorioForm form) throws DAOException{
		List<SccRelatorioCobilling> rows = sccRelatorioCobillingService.findAll();
		form.setListRelatorios(rows);
		
		uiModel.addAttribute("filtro", form);
		return FWD_VIEW_GRUPO_RELATORIO;
				
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/editarListGrupoAssociado", method=RequestMethod.GET)
	public @ResponseBody List<SccAssociacaoRelatorioGrupo> editarListaGrupoAssociado(@RequestParam("sqRelatorio") Long sqRelatorio, HttpServletRequest request,HttpServletResponse response, CadastroGrupoRelatorioForm form) throws Exception {
		
		ModelAndView mav = null;
		List<SccAssociacaoRelatorioGrupo> list = this.sccAssociacaoRelatorioGrupoService.buscarGrupoAssociado(sqRelatorio);
		form.setListAssociados(list);
		mav = new ModelAndView(FWD_VIEW_GRUPO_RELATORIO, "filtro", form);
		return form.getListAssociados();
	}
	

}
