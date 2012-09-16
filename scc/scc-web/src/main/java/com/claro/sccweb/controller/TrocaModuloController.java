package com.claro.sccweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.claro.sccweb.service.SessionDataManager;

/**
 * Controller responsável pela troca de módulo (pré/pós pago) para alteração do menu. 
 *
 */
@Controller
@RequestMapping("/inicio/modulo")
public class TrocaModuloController extends BaseFormController {

	@RequestMapping(value="switch" , method = RequestMethod.GET)
	public String trocaModulo()
	{
		if (getSessionDataManager().isPosPago())
			getSessionDataManager().setModule(SessionDataManager.MODULO_PRE_PAGO);
		else
			getSessionDataManager().setModule(SessionDataManager.MODULO_POS_PAGO);
		return "scc.inicial";
	}
	
}
