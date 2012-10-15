package com.claro.cobillingweb.persistence.constants;

import java.util.Arrays;
import java.util.List;

public enum StatusCdrEnum {
	
	

	CDRSTATUS_INDEFINIDO(0, "Status indefinido"),
	CDRSTATUS_ENCAMINHADO_ESB(5, "Encaminhado para Ensemble"),
	
    
    CDRSTATUS_ENCAMINHADO_MOB(6, "Encaminhado para Mobile"),
    CDRSTATUS_REJEITADO_C1(10, "Rejeitado C1"),
    CDRSTATUS_REJEITADO_C2_ESB(11,"Criticado no Ensemble"),
    CDRSTATUS_REJEITADO_C2_MOB(12,"Criticado no Mobile"),
    CDRSTATUS_ALOCADO_ESB(15,"A Faturar no Ensemble"),
    CDRSTATUS_ALOCADO_MOB(16,"A Faturar no Mobile"),
    CDRSTATUS_FATURADO_ESB(20,"Faturado no Ensemble"),
    CDRSTATUS_FATURADO_MOB(21,"Faturado no Mobile"),
    CDRSTATUS_EM_PARCELAMENTO(22,"Em Parcelamento"),
    CDRSTATUS_RETARIF(23,"Chamada Re-tarifada"),
    CDRSTATUS_CONTESTADO_ESB(25,"Contestado no Ensemble"),
    CDRSTATUS_CONTESTADO_MOB(26,"Contestado no Mobile"),
    CDRSTATUS_REVERSAO(27,"Reversao de Contestado"),
    CDRSTATUS_CONTESTADO_MOB_ARRECADADA(28,"Contestada Arrecada"),
    CDRSTATUS_CONTESTADO_MOB_REPASSADA(29,"Contestada Repassada"),
    CDRSTATUS_A_RECICLAR_ESB(30,"Reciclavel no Ensemble"),
    CDRSTATUS_A_RECICLAR_MOB(31,"Reciclavel no Mobile"),
    CDRSTATUS_PERDIDO_PPC(40,"Perda no PPC"),
    CDRSTATUS_PERDIDO_ESB(41,"Perda no Ensemble"),
    CDRSTATUS_PERDIDO_MOB(42,"Perda no Mobile"),
    CDRSTATUS_EXCLUIDO_X1(50,"Excluido X1"),
    CDRSTATUS_EXCLUIDO_X2_ESB(51,"Excluida no Ensemble"),
    CDRSTATUS_EXCLUIDO_X2_MOB(52,"Excluida no Mobile"),
    CDRSTATUS_EXCLUIDO_MOB_CONTESTADO(53,""),
    CDRSTATUS_A_RECUPERAR_ESB(61,"A Recuperar no Ensemble"),
    CDRSTATUS_A_RECUPERAR_MOB(62,"A Recuperar no Mobile"),
    CDRSTATUS_ARRECADADA(70,"Chamada Arrecadada"),
    CDRSTATUS_EXPURGADA (71,"Chamada Expurgada"),
    CDRSTATUS_REVERSAO_PGTO(72,"Reversão de Pagamento"),
    CDRSTATUS_INADIMPLENTE(73,"Chamada Inadimplente"),
    CDRSTATUS_REPASSADA(80,"Chamada Repassada"),
    CDRSTATUS_PARCELADA(85,"Chamada Parcelada"),
    CDRSTATUS_ALTERACAO_VCTO(90,"Alteração de Vencimento"),
    
    CDRSTATUS_REJEITADO(-10,"Rejeitado C1-C2"),
    CDRSTATUS_REJEITADO_C2(-11,"Rejeitado C2"),
    CDRSTATUS_EXCLUIDO(-50,"Excluido X1-X2"),
    CDRSTATUS_EXCLUIDO_X2(-51,"Excluido X2"),
    CDRSTATUS_PERDIDO(-40,"Perda P1-P7"),
    CDRSTATUS_FATURADO(-20,"Faturado"),
    CDRSTATUS_CONTESTADO(-25,"Contestado"),
    CDRSTATUS_FATURADO_FINAL(-21,"Faturado Final"),
	CDRSTATUS_FATURADO_INICIAL(-22,"Faturado Inicial");
	
	
	
	StatusCdrEnum(int valor, String descricao){
		this.valor = valor;
		this.descricao = descricao;
	}
	
	private final int valor;
	private final String descricao;

	public int getValor() {
		return valor;
	}
	
	public String getDescricao() {
		return descricao;
	}



	public static List<StatusCdrEnum> findAll(){
		
		return Arrays.asList(StatusCdrEnum.values());
	}
	
	public static StatusCdrEnum findById(Integer valor){
		
		for (StatusCdrEnum status : StatusCdrEnum.values()) {
			if(valor != null && valor.equals(status.getValor())){
				return status;
			}
		}
		
		return null;
		
	}
	
	

	

}
