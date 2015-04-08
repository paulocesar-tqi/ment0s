package copyleft.by.pc.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import copyleft.by.pc.model.Ativo;

@Controller
public class OpcaoController {

	@Autowired
	private QuotesController quotesController;

	private static final String OPTIONS_URL = "http://www.bmfbovespa.com.br/opcoes/opcoes.aspx?idioma=pt-br";

	private static final Logger log = Logger.getLogger(OpcaoController.class.getName());

	private static List<Ativo> ativos = null;
	private static Boolean ativosFiltrados = false;
	private static List<Ativo> ativosComNegocios = null;

	@RequestMapping(value = "/showOptions", method = RequestMethod.GET)
	@ResponseBody
	public static String showOptions() {
		return "oi";
	}
}
