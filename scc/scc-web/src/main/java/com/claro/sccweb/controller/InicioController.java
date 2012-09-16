package com.claro.sccweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controler responsável pela página inicial para qual o usuário
 * é direcionado logo após o login.
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
