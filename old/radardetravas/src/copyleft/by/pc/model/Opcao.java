package copyleft.by.pc.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Opcao {

	public static final String TIPO_CALL = "070";
	public static final String TIPO_PUT = "080";
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	
	String nomeAtivo;
	String nomeOpcao;
	Date dataVencimento;
	String tipo;
	Double strike;
	Date dataAtualizacao;
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

	@Override
	public String toString() {
		return "nomeAtivo: " + nomeAtivo + 
				" | nomeOpcao: " + nomeOpcao +
				" | dataVencimento: " + dataVencimento +
				" | tipo: " + tipo +
				" | strike: " + strike +
				" | dataAtualizacao: " + dataAtualizacao;
	}
	
}
