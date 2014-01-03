package copyleft.by.pc.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Ativo {

	public static final String TIPO_ON = "3";
	public static final String TIPO_PN = "4";
	public static final String TIPO_PNA = "5";
	public static final String TIPO_PNB = "6";
	public static final String TIPO_UNT = "11";

	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

	String codigoAtivo;
	String nomeAtivo;
	Double abertura;
	Double minimo;
	Double maximo;
	Double medio;
	Double ultimo;
	Double oscilacao;
	Date dataAtualizacao;
	Date dataCriacao;
	List<Opcao> opcoesPut;
	List<Opcao> opcoesCall;

	public Ativo(String nome, String tipo, Date now) {
		codigoAtivo = nome.trim() + tipo;
		dataCriacao = now;
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

	private void addOpcaoPut(Opcao opcao) {
		if(opcoesPut == null)
			opcoesPut = new ArrayList<Opcao>();
		opcoesPut.add(opcao);
	}

	private void addOpcaoCall(Opcao opcao) {
		if(opcoesCall == null)
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
	
	public String getCodes() {
		String result = getCodigoAtivo();
		
		if(getOpcoesCall() != null && getOpcoesCall().size() > 0)
			for(Opcao opcao : getOpcoesCall())
				result += "|" + opcao.getNomeOpcao();
		
		if(getOpcoesPut() != null && getOpcoesPut().size() > 0)
			for(Opcao opcao : getOpcoesPut())
				result += "|" + opcao.getNomeOpcao();
		
		return result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codigoAtivo == null) ? 0 : codigoAtivo.hashCode());
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
	
}
