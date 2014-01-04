package copyleft.by.pc.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.util.StringUtils;

public class Opcao {
	private static final Logger log = Logger.getLogger(Opcao.class.getName());
	
	public static final String TIPO_CALL = "070";
	public static final String TIPO_PUT = "080";
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	
	String nomeAtivo;
	String nomeOpcao;
	Date dataVencimento;
	String tipo;
	Double strike;
	Double abertura;
	Double minimo;
	Double maximo;
	Double medio;
	Double ultimo;
	Double oscilacao;
	Integer negocios;
	Date dataAtualizacao;
	
	public Opcao(String linha, Date now, String codigoAtivo) {
		dataAtualizacao = now;
		setDataVencimentoFromString(linha.substring(24, 32));
		setNomeAtivo(codigoAtivo);
		setNomeOpcao(linha.substring(42,54).trim());
		setStrikeFromString(linha.substring(62,75).trim());
		setTipo(linha.substring(39,42).trim());
	}
	
	public String getNomeAtivo() {
		return nomeAtivo;
	}
	public void setNomeAtivo(String nomeAtivo) {
		this.nomeAtivo = nomeAtivo;
	}
	public String getNomeOpcao() {
		return nomeOpcao;
	}
	public void setNomeOpcao(String nomeOpcao) {
		this.nomeOpcao = nomeOpcao;
	}
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public void setDataVencimentoFromString(String dataVencimento) {
		try {
			this.dataVencimento = dateFormat.parse(dataVencimento);
		} catch (ParseException e) {}
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		if(TIPO_CALL.equals(tipo))
			this.tipo = TIPO_CALL;
		else if(TIPO_PUT.equals(tipo))
			this.tipo = TIPO_PUT;
	}
	public Double getStrike() {
		return strike;
	}
	public void setStrike(Double strike) {
		this.strike = strike;
	}
	public void setStrikeFromString(String strike) {
		this.strike = Double.parseDouble(strike)/100;
	}
	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}
	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
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

	public Integer getNegocios() {
		return negocios;
	}

	public void setNegocios(Integer negocios) {
		this.negocios = negocios;
	}

	@Override
	public String toString() {
		return "nomeAtivo: " + nomeAtivo + 
				" | nomeOpcao: " + nomeOpcao +
				" | dataVencimento: " + dataVencimento +
				" | tipo: " + tipo +
				" | strike: " + strike +
				" | dataAtualizacao: " + dataAtualizacao;
	}
	
	public void updateFromMap(Map<String,String> dados) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyyHH:mm:ss");
		
		try {
			if(dados != null) {
				abertura = !StringUtils.isEmpty(dados.get("abertura")) ? Double.parseDouble(dados.get("abertura").replace(",", ".")) : null;		
				minimo = !StringUtils.isEmpty(dados.get("minimo")) ? Double.parseDouble(dados.get("minimo").replace(",", ".")) : null;		
				maximo = !StringUtils.isEmpty(dados.get("maximo")) ? Double.parseDouble(dados.get("maximo").replace(",", ".")) : null;		
				medio = !StringUtils.isEmpty(dados.get("medio")) ? Double.parseDouble(dados.get("medio").replace(",", ".")) : null;		
				ultimo = !StringUtils.isEmpty(dados.get("ultimo")) ? Double.parseDouble(dados.get("ultimo").replace(",", ".")) : null;		
				oscilacao = !StringUtils.isEmpty(dados.get("oscilacao")) ? Double.parseDouble(dados.get("oscilacao").replace(",", ".")) : null;
				negocios = !StringUtils.isEmpty(dados.get("negocios")) ? Integer.parseInt(dados.get("negocios")) : null;

				if(!StringUtils.isEmpty(dados.get("dataAtualizacao")) && dados.get("dataAtualizacao").length() == 19)
					dataAtualizacao = dateFormat.parse(dados.get("dataAtualizacao"));
				else if(!StringUtils.isEmpty(dados.get("dataAtualizacao")) && dados.get("dataAtualizacao").length() == 18)
					dataAtualizacao = dateFormat2.parse(dados.get("dataAtualizacao"));
			}
		} catch (Exception e) {
			log.log(Level.SEVERE,"Erro ao atualizar opcao: " + nomeOpcao,e);
		}		
	}
}
