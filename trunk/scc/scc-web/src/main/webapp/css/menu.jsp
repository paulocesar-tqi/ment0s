<%@ page session="true" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="/tags/spring"%>
<link rel="stylesheet" href="/scc/css/menu.css" type="text/css">


<div class="menu-container" >
<ul class="menu js-enabled">
<li class="menu-root">
<a href="#">Arquivos</a>
<ul class="menu">

<c:if test="${sessionScope['scopedTarget.sessionDataManager'].posPago}">
<li><a href="/scc/user/controle/arquivos/new.scc"><spring:message code="menu.arquivo.comum.controle"/></a></li>
</c:if>


<c:if test="${sessionScope['scopedTarget.sessionDataManager'].posPago}">
<li><a href="/scc/user/recepcao_transmissao/new.scc"><spring:message code="menu.arquivo.recepcao_transmissao"/></a></li>
</c:if>

 
<c:if test="${sessionScope['scopedTarget.sessionDataManager'].posPago}">
<li><a href="/scc/user/pos/processados/pesquisa/new.scc"><spring:message code="menu.arquivo.processados_pos"/></a></li>
</c:if>
 
 <c:if test="${sessionScope['scopedTarget.sessionDataManager'].prePago}">
<li><a href="/scc/user/pre/processados/pesquisa/new.scc"><spring:message code="menu.arquivo.processados_pre"/></a></li>
</c:if>
 
 
 <c:if test="${sessionScope['scopedTarget.sessionDataManager'].posPago}">
	<li>
		<a class="has-children" href="#"><spring:message code="menu.arquivo.controleremessa"/></a>
		<ul>
			<li><a href="/scc/user/arquivo/controle/ciclo/new.scc"><spring:message code="menu.arquivo.controleremessa.ciclo"/></a>	</li>
			<li><a href="/scc/user/arquivo/controle/evento/new.scc"><spring:message code="menu.arquivo.controleremessa.evento"/></a>	</li>
		</ul>
	</li>
 </c:if>

<c:if test="${sessionScope['scopedTarget.sessionDataManager'].prePago}">
<li><a href="/scc/user/arquivo/controle/cdr/new.scc">Controle de Remessa - CDR</a></li>
</c:if>


<c:if test="${sessionScope['scopedTarget.sessionDataManager'].posPago}">
	<li>
		<a class="has-children" href="#"><spring:message code="menu.arquivo.batimento"/></a>
		<ul>
			<li><a href="/scc/user/arquivo/batimento/interface/new.scc"><spring:message code="menu.arquivo.batimento.interface"/></a>	</li>
		</ul>
	</li>
</c:if>



<li><a class="has-children" href="#">Arquivo de Retorno</a>
<ul>
<li><a href="/scc/user/arquivo/retorno/eventos/new.scc">Eventos</a></li>
<li><a href="/scc/user/arquivo/retorno/new.scc">Arquivo de Retorno</a></li>
<li><a href="/scc/user/arquivo/retorno/critica/new.scc">Críticas</a></li>
<c:if test="${sessionScope['scopedTarget.sessionDataManager'].posPago}">
	<li><a href="/scc/user/arquivo/retorno/drilldown/new.scc"><spring:message code="menu.arquivo.retorno.drilldown"/></a></li>
</c:if>
</ul>
</li>


<li><a class="has-children" href="#">Assinante Suspeito</a>
	<ul>	
	<li><a href="/scc/user/arquivo/suspeito/solicitacao/new.scc">Solicitação</a></li>	
	<li><a href="/scc/user/arquivo/suspeito/solicitacao/new.scc">Relatórios</a></li>	
	<ul>	
</li>

<c:if test="${sessionScope['scopedTarget.sessionDataManager'].prePago}">
<li><a href="/scc/user/arquivos/credito/new.scc">Arquivos de Crédito</a></li>
</c:if>


</ul>
</li>

				

<li class="menu-root">
<a href="#">Gerencial</a>
<ul>

<c:if test="${sessionScope['scopedTarget.sessionDataManager'].prePago}">
<li><a href="/scc/user/contrato/pre/new.scc">Cadastro de Contrato</a></li>
</c:if>

<c:if test="${sessionScope['scopedTarget.sessionDataManager'].posPago}">
<li><a href="/scc/user/contrato/pos/new.scc">Cadastro de Contrato</a></li>
</c:if>


<c:if test="${sessionScope['scopedTarget.sessionDataManager'].posPago}">
<li><a href="/scc/user/contrato/operadoras/new.scc">Operadoras do Contrato</a></li>
</c:if>

<c:if test="${sessionScope['scopedTarget.sessionDataManager'].posPago}">
<li><a href="/scc/user/contrato/produtos/new.scc">Produtos do Contrato</a></li>
</c:if>

<c:if test="${sessionScope['scopedTarget.sessionDataManager'].posPago}">
<li><a href="/scc/user/produto/item/new.scc">Itens do Produto</a></li>
</c:if>


<c:if test="${sessionScope['scopedTarget.sessionDataManager'].posPago}">
<li><a href="/scc/user/produto/cadastro/new.scc">Cadastro de Produto</a></li>
</c:if>


<c:if test="${sessionScope['scopedTarget.sessionDataManager'].prePago}">
<li><a href="/scc/user/produto/pre/cadastro/new.scc">Cadastro de Produto</a></li>
</c:if>



<c:if test="${sessionScope['scopedTarget.sessionDataManager'].posPago}">
<li><a href="/scc/user/produto/chargecode/new.scc">Cadastro de Charge Code</a></li>
</c:if>


<c:if test="${sessionScope['scopedTarget.sessionDataManager'].prePago}">
<li><a href="/scc/user/produto/pre/pacote/new.scc">Cadastro de Pacote</a></li>
</c:if>

<c:if test="${sessionScope['scopedTarget.sessionDataManager'].prePago}">
<li><a href="/scc/user/produto/pre/evento/new.scc">Cadastro de Tipo de Evento</a></li>
</c:if>

<c:if test="${sessionScope['scopedTarget.sessionDataManager'].prePago}">
<li><a href="/scc/user/contrato/produtos/pre/new.scc">Contratos X Produtos</a></li>
</c:if>

<li><a href="/scc/user/cdr/distribuicao/new.scc">Distribuição de CDRs</a></li>
<li><a href="/scc/user/cdr/evolucao/new.scc">Evolução de Status</a></li>
<li><a href="/scc/user/saldo/demonstrativo/new.scc">Demonstrativo de Saldo de Lotes</a></li>

</ul>
</li>

<li class="menu-root">
<a href="#">Repasse</a>
<ul class="menu">
<c:if test="${sessionScope['scopedTarget.sessionDataManager'].posPago}">
<li><a href="/scc/user/repasse/pos/solicitacao/new.scc"><spring:message code="repasse_pos_solicitacao.solicitacao"/></a></li>
</c:if>

<c:if test="${sessionScope['scopedTarget.sessionDataManager'].posPago}">
<li><a href="/scc/user/repasse/pos/consulta/new.scc"><spring:message code="menu.repasse.pos.consulta"/></a></li>
</c:if>

<c:if test="${sessionScope['scopedTarget.sessionDataManager'].posPago}">
<li><a href="/scc/user/repasse/pos/demonstrativo/new.scc"><spring:message code="repasse_pos_demonstrativo.demonstrativo"/></a></li>
</c:if>

<c:if test="${sessionScope['scopedTarget.sessionDataManager'].posPago}">
<li><a href="/scc/user/repasse/pos/retorno/new.scc"><spring:message code="menu.repasse.pos.retorno"/></a></li>
</c:if>

<c:if test="${sessionScope['scopedTarget.sessionDataManager'].posPago}">
<li><a href="/scc/user/repasse/pos/penalidade_rejeicao/new.scc"><spring:message code="menu.repasse.pos.penalidade_rejeicao"/></a></li>
</c:if>

<c:if test="${sessionScope['scopedTarget.sessionDataManager'].prePago}">
<li><a href="/scc/user/repasse/pre/solicitacao/new.scc"><spring:message code="repasse_pre_solicitacao.solicitacao"/></a></li>
</c:if>

<c:if test="${sessionScope['scopedTarget.sessionDataManager'].prePago}">
<li><a href="/scc/user/repasse/pre/consulta/new.scc"><spring:message code="menu.repasse.pre.consulta"/></a></li>
</c:if>

<c:if test="${sessionScope['scopedTarget.sessionDataManager'].prePago}">
<li><a href="/scc/user/repasse/pre/demonstrativo/new.scc"><spring:message code="repasse_pre_demonstrativo.demonstrativo"/></a></li>
</c:if>

<c:if test="${sessionScope['scopedTarget.sessionDataManager'].prePago}">
<li><a href="/scc/user/repasse/pre/relatorios/new.scc"><spring:message code="menu.repasse.pre.relatorios"/></a></li>
</c:if>

<c:if test="${sessionScope['scopedTarget.sessionDataManager'].prePago}">
<li><a href="/scc/user/repasse/pre/creditos/new.scc"><spring:message code="menu.repasse.pre.creditos"/></a></li>
</c:if>

<c:if test="${sessionScope['scopedTarget.sessionDataManager'].prePago}">
<li><a href="/scc/user/repasse/consolidado/produto/pre/new.scc"><spring:message code="menu.repasse.pre.consolidado.produto"/></a></li>
</c:if>

<li><a class="has-children" href="#">Pagamento</a>
	<ul>	
	<li><a href="/scc/user/pagamento/cadastro/new.scc"><spring:message code="menu.repasse.pagamento.cadastro"/></a></li>	
	<li><a href="/scc/user/pagamento/relatorio/new.scc"><spring:message code="menu.repasse.pagamento.relatorio"/></a></li>	
	<ul>	
</li>

</ul>
</li>

<li class="menu-root">
<a href="#">Administração</a>
<ul>
<li><a href="/scc/user/admin/operadora/new.scc">Cadastro Operadoras</a></li>
<li><a href="/scc/user/admin/penalidade/new.scc">Cadastro Penalidades</a></li>
<li><a href="/scc/user/admin/critica/new.scc">Crítica Assinante</a></li>
<li><a href="/scc/user/admin/critica/prebilling/listar.scc">Crítica Prebilling</a></li>
<li><a href="/scc/user/admin/mapa/new.scc">Mapa de Status</a></li>
<li><a href="/scc/user/admin/rejeicao/new.scc">Motivo de Rejeição</a></li>
<li><a href="/scc/user/admin/configrejeicao/new.scc">Configuração de Rejeições</a></li>
<li><a href="/scc/user/admin/estorno/new.scc">Estorno de Débito</a></li>
<li><a href="/scc/user/admin/aliquotas/new.scc">Alíquotas e Tributos</a></li>
<li><a href="/scc/user/admin/tarifas/cobrar/new.scc">Tarifas a Cobrar</a></li>
<li><a href="/scc/user/admin/paralisacao/new.scc">Controle de Paralisação</a></li>
<c:if test="${sessionScope['scopedTarget.sessionDataManager'].prePago}">
	<li><a href="/scc/user/admin/dominio/new.scc">Domínio Pré Pago</a></li>
</c:if>
<li><a href="/scc/user/admin/contas/new.scc">Contas Contábeis</a></li>
<li><a href="/scc/user/admin/dados/bancarios/listar.scc">Dados Bancários</a></li>
<li><a href="/scc/user/admin/centros/new.scc">Centros de Custo</a></li>
<li><a href="/scc/user/admin/atividade/new.scc">Atividade Contábil</a></li>
	<li>
		<a class="has-children" href="#"><spring:message code="menu.administracao.acompanhamento"/></a>
		<ul>
			<li><a href="/scc/user/admin/grupo/grupo.scc"><spring:message code="menu.administracao.acompanhamento.grupo"/></a>	</li>
			<li><a href="/scc/user/admin/rel/associado/listar.scc"><spring:message code="menu.administracao.acompanhamento.relatorio.associado"/></a>	</li>
			<li><a href="/scc/user/admin/relatorio/new.scc"><spring:message code="menu.administracao.acompanhamento.relatorio"/></a>	</li>
			<li><a href="/scc/user/admin/email/new.scc"><spring:message code="menu.administracao.acompanhamento.email"/></a>	</li>
			<li><a href="/scc/user/admin/email/grupo/new.scc"><spring:message code="menu.administracao.email.grupo"/></a>	</li>
		</ul>
	</li>
<li><a class="has-children" href="#">VUM</a>
	<ul>	
	<li><a href="/scc/user/admin/vum/cadastro/new.scc"><spring:message code="menu.administracao.vum.cadastro"/></a></li>	
	<li><a href="/scc/user/admin/vum/download/new.scc"><spring:message code="menu.administracao.vum.download"/></a></li>	
	<ul>	
</li>

</ul>
</li>

<li class="menu-root">
	<a href="#"><spring:message code="menu.indicador" /></a>
	<ul>
		<c:if test="${sessionScope['scopedTarget.sessionDataManager'].prePago}">
			<li><a href="/scc/user/indicador/aging/pre/new.scc"><spring:message code="menu.indicador.aging"/></a></li>
		</c:if>
		<c:if test="${sessionScope['scopedTarget.sessionDataManager'].posPago}">
			<li><a href="/scc/user/indicador/aging/pos/new.scc"><spring:message code="menu.indicador.aging"/></a></li>
		</c:if>
		<c:if test="${sessionScope['scopedTarget.sessionDataManager'].prePago}">
			<li><a href="/scc/user/indicador/indicador/pre/new.scc"><spring:message code="menu.indicador.indicador"/></a></li>
		</c:if>
		<c:if test="${sessionScope['scopedTarget.sessionDataManager'].posPago}">
			<li><a href="/scc/user/indicador/indicador/pos/new.scc"><spring:message code="menu.indicador.indicador"/></a></li>
		</c:if>

		<li><a href="/scc/user/indicador/resultado/new.scc"><spring:message code="menu.indicador.resultado"/></a></li>
		
	</ul>
</li>


<li class="menu-root">
<a href="#"><spring:message code="menu.questionamento"/></a>
<ul>
	<li><a href="/scc/user/relatorio/questionamento/new.scc"><spring:message code="menu.relatorio.questionamento"/></a></li>
	<li><a href="/scc/user/questionamento/requisicao/listar.scc"><spring:message code="menu.questionamento.requisicao"/></a></li>
	<li><a href="/scc/user/relatorio/questionamento/resultado/new.scc"><spring:message code="menu.relatorio.questionamento.resultado"/></a></li>
	<li><a href="/scc/user/questionamento/financeiro/new.scc"><spring:message code="menu.questionamento.financeiro"/></a></li>	
	<li><a href="/scc/user/questionamento/penalidade/new.scc"><spring:message code="menu.questionamento.penalidade"/></a></li>	
	<li><a href="/scc/user/questionamento/arquivo/new.scc"><spring:message code="menu.questionamento.arquivo"/></a></li>
	<c:if test="${sessionScope['scopedTarget.sessionDataManager'].posPago}">
		<li><a href="/scc/user/relatorio/disputadetalhe/new.scc"><spring:message code="menu.relatorio.disputa.detalhe"/></a></li>
	</c:if>
	<c:if test="${sessionScope['scopedTarget.sessionDataManager'].posPago}">
		<li><a href="/scc/user/disputa/cadastro/new.scc"><spring:message code="menu.disputa.cadastro"/></a></li>	
	</c:if>
	<c:if test="${sessionScope['scopedTarget.sessionDataManager'].prePago}">
		<li><a href="/scc/user/disputa/pre/cadastro/new.scc"><spring:message code="menu.disputa.pre.cadastro"/></a></li>	
	</c:if>

	<c:if test="${sessionScope['scopedTarget.sessionDataManager'].prePago}">
		<li><a href="/scc/user/contestacao/pre/cadastro/new.scc"><spring:message code="menu.contestacao.pre.cadastro"/></a></li>	
	</c:if>


</ul>
</li>

<li class="menu-root">
	<a href="#"><spring:message code="relatorio.menu.relatorio"/></a>
	<ul>
		<li><a href="/scc/user/relatorio/batimento/arquivos/new.scc"><spring:message code="menu.relatorio.batimento.arquivos"/></a></li>
		<c:if test="${sessionScope['scopedTarget.sessionDataManager'].prePago}">
			<li><a href="/scc/user/relatorio/eventos/new.scc"><spring:message code="menu.relatorio.eventos"/></a></li>
		</c:if>
		
		<c:if test="${sessionScope['scopedTarget.sessionDataManager'].prePago}">
			<li><a href="/scc/user/relatorio/criticas/new.scc"><spring:message code="menu.relatorio.criticas"/></a></li>
		</c:if>
		
		<c:if test="${sessionScope['scopedTarget.sessionDataManager'].prePago}">
			<li><a href="/scc/user/relatorio/chamadas/credito/new.scc"><spring:message code="menu.relatorio.chamadas.credito"/></a></li>
		</c:if>
		<c:if test="${sessionScope['scopedTarget.sessionDataManager'].prePago}">
			<li><a href="/scc/user/relatorio/batimento/wrupp/pre/new.scc"><spring:message code="menu.relatorio.batimento.wrupp"/></a></li>
		</c:if>
		<c:if test="${sessionScope['scopedTarget.sessionDataManager'].prePago}">
			<li><a href="/scc/user/relatorio/disponibilizacaoPacotes/pre/new.scc"><spring:message code="menu.relatorio.disponibilizacao.pacotes"/></a></li>
		</c:if>
		<c:if test="${sessionScope['scopedTarget.sessionDataManager'].posPago}">
			<li><a href="/scc/user/relatorio/alarmeoperacional/new.scc"><spring:message code="menu.relatorio.alarme.operacional"/></a></li>
		</c:if>
		
		<li>
			<a class="has-children" href="#"><spring:message code="menu.relatorio.processo"/></a>
			<ul>
				<li><a href="/scc/user/relatorio/processo/parametro/new.scc"><spring:message code="menu.relatorio.processo.parametro"/></a>	</li>
			</ul>
		</li>
		<li><a href="/scc/user/relatorio/confirmacao/repasse/new.scc"><spring:message code="menu.relatorio.confirmacao.repasse"/></a></li>
		
		
	<li><a class="has-children" href="#">Contábil</a>
		<ul>	
		<li><a href="/scc/user/contabil/relatorio/new.scc">Relatório</a></li>
		<li><a href="/scc/user/relatorio/conciliacao/pre/new.scc"><spring:message code="menu.relatorio.contabil.conciliacao"/></a></li>	
		<li><a href="/scc/user/contabil/transicao/new.scc">Relatório Transição</a></li>	
		<ul>	
	</li>


	<c:if test="${sessionScope['scopedTarget.sessionDataManager'].posPago}">
		<li>
			<a class="has-children" href="#"><spring:message code="menu.relatorio.financeiro"/></a>
			<ul>
				<c:if test="${sessionScope['scopedTarget.sessionDataManager'].posPago}">
					<li><a href="/scc/user/relatorio/parcelamento/acordo/new.scc"><spring:message code="menu.relatorio.financeiro.relatorio"/></a></li>
				</c:if>
				<c:if test="${sessionScope['scopedTarget.sessionDataManager'].posPago}">
					<li><a href="/scc/user/financeiro/perda/faturamento/new.scc"><spring:message code="menu.relatorio.financeiro.perda.faturamento"/></a></li>
				</c:if>
					
			</ul>
		</li>
	</c:if>

	<li><a class="has-children" href="#">Fiscal</a>
		<ul>
		<li><a href="/scc/user/relatorio/contingenciaFiscal/new.scc"><spring:message code="menu.relatorio.contingencia.fiscal"/></a></li>
		<li><a href="/scc/user/relatorio/batimentoFiscal/new.scc"><spring:message code="menu.relatorio.batimento.fiscal"/></a></li>		
		<li><a href="/scc/user/relatorio/arquivosFiscais/new.scc"><spring:message code="menu.relatorio.arquivos.fiscais"/></a></li>		
		<li><a href="/scc/user/fiscal/relatorio/batimento/estorno/debito/new.scc">Relatório de Batimento de Estorno de Débito</a></li>	
		<ul>	
	</li>
		
		<li><a href="/scc/user/relatorio/arquivosNaoProcessados/new.scc"><spring:message code="menu.relatorio.arquivos.nao.processados"/></a></li>
		
		<c:if test="${sessionScope['scopedTarget.sessionDataManager'].posPago}">
			<li><a href="/scc/user/relatorio/assexuados/new.scc"><spring:message code="menu.relatorio.assexuados"/></a></li>
		</c:if>
		
		<c:if test="${sessionScope['scopedTarget.sessionDataManager'].posPago}">
			<li><a href="/scc/user/relatorio/encaminhadoMobile/new.scc"><spring:message code="menu.relatorio.encaminhado.mobile"/></a></li>
		</c:if>
		<c:if test="${sessionScope['scopedTarget.sessionDataManager'].posPago}">
			<li><a href="/scc/user/relatorio/alocadasMobile/new.scc"><spring:message code="menu.relatorio.alocadas.mobile"/></a></li>
		</c:if>
		<c:if test="${sessionScope['scopedTarget.sessionDataManager'].posPago}">
			<li><a href="/scc/user/relatorio/alocadasMobileSemProcessamento/new.scc"><spring:message code="menu.relatorio.alocadas.mobile.sem.processamento"/></a></li>
		</c:if>
		
		<li><a href="/scc/user/relatorio/DemonstrativoDesempenhoPenalidade/new.scc">Demonstrativo de Desempenho e Penalidade(SLA)</a></li>
		<c:if test="${sessionScope['scopedTarget.sessionDataManager'].posPago}">			
			<li><a href="/scc/user/relatorio/extracaoCDRs/new.scc"><spring:message code="menu.relatorio.extracao.cdrs"/></a></li>
		</c:if>
		<c:if test="${sessionScope['scopedTarget.sessionDataManager'].prePago}">
			<li><a href="/scc/user/relatorio/pre/creditos/new.scc"><spring:message code="menu.relatorio.prepago.creditos"/></a></li>
		</c:if>
		<c:if test="${sessionScope['scopedTarget.sessionDataManager'].posPago}">
			<li>
				<a class="has-children" href="#"><spring:message code="menu.relatorio.naoconf"/></a>
				<ul>
					<c:if test="${sessionScope['scopedTarget.sessionDataManager'].posPago}">
						<li><a href="/scc/user/relatorio/naoconf/ajustes/new.scc"><spring:message code="menu.relatorio.naoconf.ajustes"/></a></li>
					</c:if>
					<c:if test="${sessionScope['scopedTarget.sessionDataManager'].posPago}">
						<li><a href="/scc/user/relatorio/naoconf/faturamento/new.scc"><spring:message code="menu.relatorio.naoconf.faturamento"/></a></li>
					</c:if>
					
					<c:if test="${sessionScope['scopedTarget.sessionDataManager'].posPago}">
						<li><a href="/scc/user/relatorio/naoconf/batimento/estorno/new.scc"><spring:message code="menu.relatorio.naoconf.batimento"/></a></li>
					</c:if>
					
					
				</ul>
			</li>

		</c:if>

		<c:if test="${sessionScope['scopedTarget.sessionDataManager'].posPago}">
			<li>
				<a class="has-children" href="#"><spring:message code="menu.relatorio.faturas"/></a>
				<ul>
					<c:if test="${sessionScope['scopedTarget.sessionDataManager'].posPago}">
						<li><a href="/scc/user/relatorio/faturas/controle/new.scc"><spring:message code="menu.relatorio.faturas.controle"/></a></li>
					</c:if>
					<c:if test="${sessionScope['scopedTarget.sessionDataManager'].posPago}">
						<li><a href="/scc/user/relatorio/juros/multas/new.scc"><spring:message code="menu.relatorio.juros.multas"/></a></li>
					</c:if>
					<c:if test="${sessionScope['scopedTarget.sessionDataManager'].posPago}">
						<li><a href="/scc/user/relatorio/faturas/acoes/cobranca/new.scc"><spring:message code="menu.relatorio.acoes.cobranca"/></a></li>
					</c:if>
					<c:if test="${sessionScope['scopedTarget.sessionDataManager'].posPago}">
						<li><a href="/scc/user/relatorio/faturas/aging/new.scc"><spring:message code="menu.relatorio.aging.faturas"/></a></li>
					</c:if>
					
				</ul>
			</li>
		</c:if>
		
		<c:if test="${sessionScope['scopedTarget.sessionDataManager'].posPago}">
			<li>
				<a class="has-children" href="#"><spring:message code="menu.relatoiro.parcelamento"/></a>
				<ul>
					<c:if test="${sessionScope['scopedTarget.sessionDataManager'].posPago}">
						<li><a href="/scc/user/relatorio/parcelamento/acordo/new.scc"><spring:message code="menu.relatorio.parcelamento.acordo"/></a></li>
					</c:if>
					<c:if test="${sessionScope['scopedTarget.sessionDataManager'].posPago}">
						<li><a href="/scc/user/relatorio/parcelamento/acompanhamento/new.scc"><spring:message code="menu.relatorio.parcelamento.acompanhamento"/></a></li>
					</c:if>
					
				</ul>
			</li>
		</c:if>
		
		<li><a class="has-children" href="#">Refaturamento</a>
			<ul>	
				<li><a href="/scc/user/relatorio/chamadas/refaturamento/new.scc">Chamadas</a></li>
				<li><a href="/scc/user/relatorio/refaturamento/chamadasRefaturamento/new.scc"><spring:message code="menu.relatorio.chamadas.refaturamento"/></a></li>
			<ul>	
		</li>
		
		<c:if test="${sessionScope['scopedTarget.sessionDataManager'].posPago}">
				<li><a href="/scc/user/relatorio/servico/pos/new.scc"><spring:message code="menu.relatorio.prestacao.servico"/></a>	</li>
		</c:if>
		<c:if test="${sessionScope['scopedTarget.sessionDataManager'].prePago}">
				<li><a href="/scc/user/relatorio/servico/pre/new.scc"><spring:message code="menu.relatorio.prestacao.servico"/></a>	</li>
		</c:if>

	</ul>
</li>



<li class="menu-root">
<a href="/scc/login.scc">Sair</a>
</li>
</ul>
</div>
