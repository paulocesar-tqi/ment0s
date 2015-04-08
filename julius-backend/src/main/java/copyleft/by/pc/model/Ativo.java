package copyleft.by.pc.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.springframework.util.StringUtils;

public class Ativo {

	private static final Logger log = Logger.getLogger(Ativo.class.getName());

	public static final String TIPO_ON = "3";
	public static final String TIPO_PN = "4";
	public static final String TIPO_PNA = "5";
	public static final String TIPO_PNB = "6";
	public static final String TIPO_UNT = "11";

	String codigoAtivo;
	String nomeAtivo;
	Double abertura;
	Double minimo;
	Double maximo;
	Double medio;
	Double ultimo;
	Double oscilacao;
	Integer negocios;
	Date dataAtualizacao;
	Date dataCriacao;
	Boolean possuiOpcoesNegociadas;
	List<Opcao> opcoesPut;
	List<Opcao> opcoesCall;

	public Ativo(String nome, String tipo, Date now) {
		codigoAtivo = nome.trim() + tipo;
		dataCriacao = now;
		possuiOpcoesNegociadas = false;
	}

	public String getCodigoAtivo() {
		return codigoAtivo;
	}

	public String getNomeAtivo() {
		return nomeAtivo;
	}

	public void setNomeAtivo(String nomeAtivo) {
		this.nomeAtivo = nomeAtivo;
	}

	public Double getAbertura() {
		return abertura;
	}

	public void setAbertura(Double abertura) {
		this.abertura = abertura;
	}

	public Double getMinimo() {
		return minimo;
	}

	public void setMinimo(Double minimo) {
		this.minimo = minimo;
	}

	public Double getMaximo() {
		return maximo;
	}

	public void setMaximo(Double maximo) {
		this.maximo = maximo;
	}

	public Double getMedio() {
		return medio;
	}

	public void setMedio(Double medio) {
		this.medio = medio;
	}

	public Double getUltimo() {
		return ultimo;
	}

	public void setUltimo(Double ultimo) {
		this.ultimo = ultimo;
	}

	public Double getOscilacao() {
		return oscilacao;
	}

	public void setOscilacao(Double oscilacao) {
		this.oscilacao = oscilacao;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public Integer getNegocios() {
		return negocios;
	}

	public void setNegocios(Integer negocios) {
		this.negocios = negocios;
	}

	public Boolean getPossuiOpcoesNegociadas() {
		return possuiOpcoesNegociadas;
	}

	public void setPossuiOpcoesNegociadas(Boolean possuiOpcoesNegociadas) {
		this.possuiOpcoesNegociadas = possuiOpcoesNegociadas;
	}

	private void addOpcaoPut(Opcao opcao) {
		if (opcoesPut == null)
			opcoesPut = new ArrayList<Opcao>();
		opcoesPut.add(opcao);
	}

	private void addOpcaoCall(Opcao opcao) {
		if (opcoesCall == null)
			opcoesCall = new ArrayList<Opcao>();
		opcoesCall.add(opcao);
	}

	public void addOpcao(Opcao opcao) {

		switch (opcao.getTipo()) {
		case Opcao.TIPO_CALL:
			addOpcaoCall(opcao);
			break;
		case Opcao.TIPO_PUT:
			addOpcaoPut(opcao);
			break;
		}
	}

	public List<Opcao> getOpcoesPut() {
		return opcoesPut;
	}

	public List<Opcao> getOpcoesCall() {
		return opcoesCall;
	}

	public static String getTipoAtivo(String codigo) {
		String result = null;
		switch (codigo) {
		case "ON":
			result = TIPO_ON;
			break;
		case "PN":
			result = TIPO_PN;
			break;
		case "PNA":
			result = TIPO_PNA;
			break;
		case "PNB":
			result = TIPO_PNB;
			break;
		case "UNT":
			result = TIPO_UNT;
			break;
		default:
			break;
		}

		return result;
	}

	public String listCodes() {
		String result = getCodigoAtivo();

		if (getOpcoesCall() != null && getOpcoesCall().size() > 0)
			for (Opcao opcao : getOpcoesCall())
				result += "%7C" + opcao.getNomeOpcao();

		if (getOpcoesPut() != null && getOpcoesPut().size() > 0)
			for (Opcao opcao : getOpcoesPut())
				result += "%7C" + opcao.getNomeOpcao();

		return result;
	}

	public List<String> listCodesAsList() {
		List<String> result = new ArrayList<String>();
		result.add(getCodigoAtivo());

		if (getOpcoesCall() != null && getOpcoesCall().size() > 0)
			for (Opcao opcao : getOpcoesCall())
				result.add(opcao.getNomeOpcao());

		if (getOpcoesPut() != null && getOpcoesPut().size() > 0)
			for (Opcao opcao : getOpcoesPut())
				result.add(opcao.getNomeOpcao());

		return result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoAtivo == null) ? 0 : codigoAtivo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Ativo))
			return false;
		Ativo other = (Ativo) obj;
		if (codigoAtivo == null) {
			if (other.codigoAtivo != null)
				return false;
		} else if (!codigoAtivo.equals(other.codigoAtivo))
			return false;
		return true;
	}

	public void updateFromMap(Map<String, Map<String, String>> dadosCompletos) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyyHH:mm:ss");

		try {
			// atualiza os dados do ativo
			Map<String, String> dados = dadosCompletos.get(codigoAtivo);
			if (dados != null) {
				nomeAtivo = dados.get("nomeAtivo");
				abertura = !StringUtils.isEmpty(dados.get("abertura")) ? Double.parseDouble(dados.get("abertura").replace(",", ".")) : null;
				minimo = !StringUtils.isEmpty(dados.get("minimo")) ? Double.parseDouble(dados.get("minimo").replace(",", ".")) : null;
				maximo = !StringUtils.isEmpty(dados.get("maximo")) ? Double.parseDouble(dados.get("maximo").replace(",", ".")) : null;
				medio = !StringUtils.isEmpty(dados.get("medio")) ? Double.parseDouble(dados.get("medio").replace(",", ".")) : null;
				ultimo = !StringUtils.isEmpty(dados.get("ultimo")) ? Double.parseDouble(dados.get("ultimo").replace(",", ".")) : null;
				oscilacao = !StringUtils.isEmpty(dados.get("oscilacao")) ? Double.parseDouble(dados.get("oscilacao").replace(",", ".")) : null;
				negocios = !StringUtils.isEmpty(dados.get("negocios")) ? Integer.parseInt(dados.get("negocios")) : 0;

				if (!StringUtils.isEmpty(dados.get("dataAtualizacao")) && dados.get("dataAtualizacao").length() == 19)
					dataAtualizacao = dateFormat.parse(dados.get("dataAtualizacao"));
				else if (!StringUtils.isEmpty(dados.get("dataAtualizacao")) && dados.get("dataAtualizacao").length() == 18)
					dataAtualizacao = dateFormat2.parse(dados.get("dataAtualizacao"));
			}
			// atualiza as opcoes
			if (opcoesCall != null && opcoesCall.size() > 0)
				for (Opcao opcao : opcoesCall)
					opcao.updateFromMap(dadosCompletos.get(opcao.nomeOpcao));
			if (opcoesPut != null && opcoesPut.size() > 0)
				for (Opcao opcao : opcoesPut)
					opcao.updateFromMap(dadosCompletos.get(opcao.nomeOpcao));

		} catch (Exception e) {
			log.log(Level.SEVERE, "Erro ao atualizar ativo: " + codigoAtivo, e);
		}
	}

	public void removeOpcoesSemNegocios() {

		if (opcoesCall != null && opcoesCall.size() > 0)
			CollectionUtils.filter(opcoesCall, new Predicate() {
				@Override
				public boolean evaluate(Object input) {
					return ((Opcao) input).getNegocios() > 0;
				}
			});

		if (opcoesPut != null && opcoesPut.size() > 0)
			CollectionUtils.filter(opcoesPut, new Predicate() {
				@Override
				public boolean evaluate(Object input) {
					return ((Opcao) input).getNegocios() > 0;
				}
			});
	}
}
