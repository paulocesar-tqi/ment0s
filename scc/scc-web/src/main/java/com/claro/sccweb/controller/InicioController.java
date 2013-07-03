package com.claro.sccweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.claro.scc.spring.ApplicationContextProvider;
import com.claro.sccweb.service.SessionDataManager;

/**
 * Controler responsável pela página inicial para qual o usuário
 * é direcionado logo após o login.
 *
 */
@Controller
@RequestMapping("/inicio")
public class InicioController /* extends BaseFormController*/ {

	@RequestMapping(value="/scc" , method = RequestMethod.GET)
	public String initialPage() {
		//FIXME esse bloco simplesmente carrega o bean e coloca ele na sessão. Deve existir um 
		//jeito mais "spring" de se fazer isso, mas no momento não sei como, então simplesmente
		//chamo qualquer método só pra forçar a inicialização e carga na sessão.
		SessionDataManager sdm = (SessionDataManager)ApplicationContextProvider.getApplicationContext().getBean("sessionDataManager");
		System.err.println(sdm.getUserDisplayName());
		//+++
		return "scc.inicial";
	}
	
//	@RequestMapping(value="/scc" , method = RequestMethod.GET)
//	public ModelAndView initialPage() {
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName("scc.inicial");
//		mav.addObject("sessionDataManager", getSessionDataManager());
//		return mav;
//		//return "scc.inicial";
//	}
	
}
