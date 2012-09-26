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


<li><a href="/scc/user/cdr/distribuicao/new.scc">Distribuição de CDRs</a></li>
<li><a href="/scc/user/cdr/evolucao/new.scc">Evolução de CDRs</a></li>
<li><a href="/scc/user/saldo/demonstrativo/new.scc">Demonstrativo de Saldo de Lotes</a></li>

<li><a class="has-children" href="#">Contábil</a>
	<ul>	
	<li><a href="/scc/user/contabil/relatorio/new.scc">Relatório</a></li>	
	<li><a href="/scc/user/contabil/transicao/new.scc">Relatório Transição</a></li>	
	<ul>	
</li>

</ul>
</li>

<li class="menu-root">
<a href="#">Repasse</a>
<ul class="menu">
<c:if test="${sessionScope['scopedTarget.sessionDataManager'].posPago}">
<li><a href="/scc/user/repasse/pos/solicitacao/new.scc"><spring:message code="repasse_pos_solicitacao.solicitacao"/></a></li>
</c:if>

<c:if test="${sessionScope['scopedTarget.sessionDataManager'].posPago}">
<li><a href="/scc/user/repasse/pos/demonstrativo/new.scc"><spring:message code="repasse_pos_demonstrativo.demonstrativo"/></a></li>
</c:if>

<c:if test="${sessionScope['scopedTarget.sessionDataManager'].prePago}">
<li><a href="/scc/user/repasse/pre/demonstrativo/new.scc"><spring:message code="repasse_pre_demonstrativo.demonstrativo"/></a></li>
</c:if>


<c:if test="${sessionScope['scopedTarget.sessionDataManager'].posPago}">
<li><a href="/scc/user/repasse/pos/consulta/new.scc"><spring:message code="menu.repasse.pos.consulta"/></a></li>
</c:if>



<c:if test="${sessionScope['scopedTarget.sessionDataManager'].prePago}">
<li><a href="/scc/user/repasse/pre/solicitacao/new.scc"><spring:message code="repasse_pre_solicitacao.solicitacao"/></a></li>
</c:if>

<c:if test="${sessionScope['scopedTarget.sessionDataManager'].prePago}">
<li><a href="/scc/user/repasse/pre/consulta/new.scc"><spring:message code="menu.repasse.pre.consulta"/></a></li>
</c:if>


<c:if test="${sessionScope['scopedTarget.sessionDataManager'].prePago}">
<li><a href="/scc/user/repasse/pre/relatorios/new.scc"><spring:message code="menu.repasse.pre.relatorios"/></a></li>
</c:if>

<c:if test="${sessionScope['scopedTarget.sessionDataManager'].prePago}">
<li><a href="/scc/user/repasse/pre/creditos/new.scc"><spring:message code="menu.repasse.pre.creditos"/></a></li>
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
<li><a href="/scc/user/admin/mapa/new.scc">Mapa de Status</a></li>
<li><a href="/scc/user/admin/rejeicao/new.scc">Motivo de Rejeição</a></li>
<li><a href="/scc/user/admin/estorno/new.scc">Estorno de Débito</a></li>
<li><a href="/scc/user/admin/aliquotas/new.scc">Alíquotas e Tributos</a></li>
<li><a href="/scc/user/admin/paralisacao/new.scc">Controle de Paralisação</a></li>
<li><a href="/scc/user/admin/dominio/new.scc">Domínio Pré Pago</a></li>
<li><a href="/scc/user/admin/contas/new.scc">Contas Contábeis</a></li>
<li><a href="/scc/user/admin/centros/new.scc">Centros de Custo</a></li>
<li><a href="/scc/user/admin/atividade/new.scc">Atividade Contábil</a></li>
	<li>
		<a class="has-children" href="#"><spring:message code="menu.administracao.acompanhamento"/></a>
		<ul>
			<li><a href="/scc/user/admin/grupo/new.scc"><spring:message code="menu.administracao.acompanhamento.grupo"/></a>	</li>
			<li><a href="/scc/user/admin/relatorio/new.scc"><spring:message code="menu.administracao.acompanhamento.relatorio"/></a>	</li>
			<li><a href="/scc/user/admin/email/new.scc"><spring:message code="menu.administracao.acompanhamento.email"/></a>	</li>
		</ul>
	</li>

</ul>
</li>

<li class="menu-root">
<a href="#">Questionamento</a>
<ul>
</ul>
</li>

<li class="menu-root">
	<a href="#"><spring:message code="relatorio.menu.relatorio"/></a>
	<ul>
		<li><a href="/scc/user/relatorio/batimento/arquivos/new.scc"><spring:message code="menu.relatorio.batimento.arquivos"/></a></li>
		<li><a href="/scc/user/relatorio/alarmeoperacional/new.scc"><spring:message code="menu.relatorio.alarme.operacional"/></a></li>
	
		<li>
			<a class="has-children" href="#"><spring:message code="menu.relatorio.processo"/></a>
			<ul>
				<li><a href="/scc/user/relatorio/servico/pos/new.scc"><spring:message code="menu.relatorio.prestacao.servico"/></a>	</li>
				<li><a href="/scc/user/relatorio/processo/parametro/new.scc"><spring:message code="menu.relatorio.processo.parametro"/></a>	</li>
			</ul>
		</li>
	</ul>
</li>



<li class="menu-root">
<a href="/scc/login.scc">Sair</a>
</li>
</ul>
</div>
