package com.claro.sccweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controler respons�vel pela p�gina inicial para qual o usu�rio
 * � direcionado logo ap�s o login.
 *
 */
@Controller
@RequestMapping("/inicio")
public class InicioController {

	@RequestMapping(value="/scc" , method = RequestMethod.GET)
	public String initialPage() {
		return "scc.inicial";
	}
	
}
