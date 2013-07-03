<%@ page session="true" isELIgnored="false"%>
<%@ taglib uri="/tags/ajaxtags" prefix="ajax" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="/tags/spring-form"%>
<%@ taglib prefix="spring" uri="/tags/spring"%>
<link rel="stylesheet" href="/scc/css/jquery-ui.css" type="text/css"/>
 <script type="text/javascript" src="<c:url value="/js/jquery-1.8.2.js"/>"></script>
 <script type="text/javascript" src="<c:url value="/js/jquery-ui.js"/>"></script>
 <script type="text/javascript" src="<c:url value="/js/jquery.ui.datepicker-pt-BR.js"/>"></script>
 <script type="text/javascript" src="<c:url value="/js/scc/contestacaoprepago.js"/>"></script>
<link rel="stylesheet" href="/scc/css/jquery-ui-1.8.18.custom.css" type="text/css"/>
<link rel="stylesheet" href="/scc/css/table_jui.css" type="text/css">
<link rel="stylesheet" href="/scc/css/styles.css" type="text/css">
<%@ taglib uri="/tags/displaytag" prefix="display" %>


<html>
	<div id="tabs">
		<ul>
			<li><a href="#tabs-1"><spring:message code="crud.titulo.pesquisar"/></a></li>
			<li><a href="#tabs-2"><spring:message code="menu.contestacao.pre.aba.registro.cartald"/></a></li>
			<li><a href="#tabs-3"><spring:message code="menu.contestacao.pre.aba.registro.arquivold"/></a></li>
			<li><a href="#tabs-4"><spring:message code="menu.contestacao.pre.aba.registro.prazos"/></a></li>
			<li><a href="#tabs-5"><spring:message code="menu.contestacao.pre.aba.analise.batimento"/></a></li>
			<li><a href="#tabs-6"><spring:message code="menu.contestacao.pre.aba.analise.carta.claro"/></a></li>
			<li><a href="#tabs-7"><spring:message code="menu.contestacao.pre.aba.conciliacao.acordo"/></a></li>
			<li><a href="#tabs-8"><spring:message code="menu.contestacao.pre.aba.conciliacao.valores"/></a></li>
			<li><a href="#tabs-9"><spring:message code="menu.contestacao.pre.aba.conciliacao.p1"/></a></li>
			<li><a href="#tabs-10"><spring:message code="menu.contestacao.pre.aba.conciliacao.p2"/></a></li>
			<li><a href="#tabs-11"><spring:message code="menu.contestacao.pre.aba.conciliacao.p3"/></a></li>
			<li><a href="#tabs-12"><spring:message code="menu.contestacao.pre.aba.conciliacao.p4"/></a></li>
			<li><a href="#tabs-13"><spring:message code="menu.contestacao.pre.aba.conciliacao.x1"/></a></li>
			<li><a href="#tabs-14"><spring:message code="menu.contestacao.pre.aba.conciliacao.calculos"/></a></li>
			<li><a href="#tabs-15"><spring:message code="menu.contestacao.pre.aba.repasse"/></a></li>
			
		</ul>
		<form:form modelAttribute="filtro" method="post" action="/scc/user/contestacao/pre/cadastro/listar.scc" id="form1">
			<form:hidden path="operacao" id="operacao"/>
			<form:hidden path="itemSelecionado" id="itemSelecionado"/>
			<form:hidden path="entity.cdTipoContestacao" id="cdTipoContestacao"/>
			
			<form:hidden path="mes" id="mes"/>
			<form:hidden path="ano" id="ano"/>
			<div id="tabs-1">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" >
				
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.label.operadorald" /></td>
						<td> <form:select path="filtro.cdEOTLD" items="${operadorasExternas}" itemLabel="dsOperadoraForCombos" itemValue="cdEot"></form:select> </td>
					</tr>
				
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.label.mes" /></td>
						<td> 
							<form:select id="filtro.mes" path="filtro.mes" itemValue="key" itemLabel="label" items="${meses}"/>
						</td>
					</tr>
								
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.label.ano" /></td>
						<td>
							<form:input path="filtro.ano" size="5" maxlength="4"/>
							<form:errors path="filtro.ano"/> 
						</td>
					</tr>
				
				</table>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
				    	<td colspan="3" class="TdFormularioUp">&nbsp;</td>    
					    <td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
						    <input id="pesquisar_button" type="button" value="Pesquisar" />
					    </td>
					</tr>
				</table>
				<br/>
				<table id="lista"  width="100%" border="0" cellspacing="0" cellpadding="0" >
					<tr>
						<td>                            
							<display:table style="width:90%"  name="requestScope.filtro.listSccContestacaoPrePago" 
								pagesize="20"  id="repasses" decorator="com.claro.sccweb.decorator.view.SccConstestacaoPrePagoDecorator"
								requestURI="/scc/user/disputa/pre/cadastro/listar.scc" class="ui-state-default">
								<display:column property="sqContestacaoPrePago"   href="editarContestacao.scc?sqContestacaoPrePago=${repasses.sqContestacaoPrePago}" titleKey="relatorio.contestacao.displaytag.nr.contestacao" style="text-align:right"/>
								<display:column property="operadora" titleKey="relatorio.contestacao.displaytag.operadora.ld" style="text-align:right"/>
								<display:column property="dtProtocoloLd" titleKey="relatorio.contestacao.displaytag.dt.carta.ld.protocolo" format="{0,date,dd-MM-yyyy}"/>
								<display:column property="dtRepasseContestacao" titleKey="relatorio.contestacao.displaytag.dt.dem.repasse.contestacao" format="{0,date,dd-MM-yyyy}" style="text-align:right"/>
								<display:column property="dtRepasseScc" titleKey="relatorio.contestacao.displaytag.dt.repasse.liberacao" format="{0,date,dd-MM-yyyy}" style="width:10%; text-align:left;"/>
								<display:column property="dtLiberacaoRepasse" titleKey="relatorio.contestacao.displaytag.dt.repasse.liberacao" style="width:10%; text-align:left;"/>
								<display:column property="cdStatusContestacao" titleKey="relatorio.contestacao.displaytag.status" style="width:10%; text-align:left;"/>
								<display:column property="fgRateioManual" titleKey="relatorio.contestacao.displaytag.rel.manual" style="width:10%; text-align:left;"/>
								<display:column title="Editar"><a href="editarContestacao.scc?sqContestacaoPrePago=${repasses.sqContestacaoPrePago}" 
												class="edit_contestacao" >
												<img  src="/scc/images/editIcon.gif"></a>
								</display:column>
								
							</display:table>
						</td>
					</tr>
				</table>
				
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
				    	<td colspan="3" class="TdFormularioUp">&nbsp;</td>    
					    <td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
						    <input id="excel_button" type="button" value=<spring:message code="comum.botao.excel"/> />
					    </td>
					</tr>
				</table>
			</div>
			<div id="tabs-2">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" >
				
					<tr>
						<td><label for="txtNome" class="SubtituloRed" >Fase Registro</label> </td>
					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.contestacao.aba.registro.usuario" /></td>
						<td><form:input id="cdAnalistaInputCartaLd" path="entity.cdAnalistaInputCartaLd" readonly="true"/></td>

					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.contestacao.aba.registro.dt.input.registro" /></td>
						<td><form:input path="entity.dtInputCartaLd" id="dtInputCartaLd" cssClass="datepicker"/>
					</tr>
				
					<tr>
						<td width="15%"><spring:message code="relatorio.contestacao.aba.registro.nr.contestacao" /></td>
						<td><form:input id="sqContestacaoPrePago" path="entity.sqContestacaoPrePago" />
					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.contestacao.aba.registro.operadorald" /></td>
						<td> <form:select id="opExterna" path="entity.sccOperadora.cdEot" items="${operadorasExternas}" itemLabel="dsOperadoraForCombos" itemValue="cdEot"></form:select></td>
					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.contestacao.aba.registro.ref.cartald" /></td>
						<td><form:input id="cdIdentificacaoCartaLd" path="entity.cdIdentificacaoCartaLd" />
					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.contestacao.aba.registro.data.cartald" /></td>
						<td><form:input cssClass="datepicker" path="entity.dtProtocoloLd"  id="dtProtocoloLd"/>
					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.contestacao.aba.registro.resp.cartald" /></td>
						<td><form:input id="noResponsavelCartaLd" path="entity.noResponsavelCartaLd" />
					</tr>

					<tr>
						<td width="15%"><spring:message code="relatorio.contestacao.aba.registro.dt.dem.repasse.contestacao" /></td>
						<td><form:input cssClass="datepicker" path="entity.dtRepasseContestacao"  id="dtRepasseContestacao"/>
					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.contestacao.aba.registro.valor.pleito" /></td>
						<td><form:input id="vlPleito" path="entity.vlPleito" />
					</tr>

					<tr>
						<td width="15%"><spring:message code="relatorio.contestacao.aba.registro.descricao.pleito" /></td>
						<td><form:input id="dsPleito" path="entity.dsPleito" />
					</tr>
					
					<tr>
						<td class='label' colspan='3'>
							&nbsp;
						</td>
					</tr>					
					<tr>
						<td class='label' colspan='3' align="center" valign="top">
							<center>
								<input type="button" value="Limpa/Novo" class="button_limpar" style="width:90px;"/>
								<input type="button" value="Salvar" class="button_salvar"  style="width:90px;"/>
							</center>
						</td>
					</tr>
					
									
				</table>				
			</div>
			<div id="tabs-3">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" >
				
					<tr>
						<td><label for="txtNome" class="SubtituloRed" >Fase Registro</label> </td>
					</tr>
				
					<tr>
						<td width="15%"><spring:message code="relatorio.contestacao.aba.registro.arquivold.nome" /></td>
						<td><form:input id="noArquivoLd" path="entity.noArquivoLd" />
					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.contestacao.aba.registro.arquivold.data" /></td>
						<td><form:input id="dtProtocoloArquivoLd" path="entity.dtProtocoloArquivoLd" cssClass="datepicker" />
					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.contestacao.aba.registro.arquivold.qte.chamada" /></td>
						<td><form:input  path="entity.qtChamadasArquivoLd" id="qtChamadasArquivoLd"  /></td>
					</tr>
					
					<tr>
						<td width="15%"><spring:message code="relatorio.contestacao.aba.registro.arquivold.min.reais.arquivold" /></td>
						<td><form:input  path="entity.qtMinutosReaisArquivoLd" id="qtMinutosReaisArquivoLd"  /></td>
					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.contestacao.aba.registro.arquivold.min.arred.arquivold" /></td>
						<td><form:input  path="entity.qtMinutosArredArquivoLd" id="qtMinutosArredArquivoLd"  /></td>
					</tr>
					
					<tr>
						<td width="15%"><spring:message code="relatorio.contestacao.aba.registro.arquivold.valor.bruto.arquivold" /></td>
						<td><form:input  path="entity.vlBrutoArquivoLd" id="vlBrutoArquivoLd"  /></td>
					</tr>
					<tr>
						<td class='label' colspan='3'>
							&nbsp;
						</td>
					</tr>					
					<tr>
						<td class='label' colspan='3' align="center" valign="top">
							<center>
								<input type="button" value="Limpa/Novo" class="button"  style="width:90px;"/>
								<input type="button" value="Salvar" class="button_salvar" style="width:90px;"/>
							</center>
						</td>
					</tr>
				</table>		
			</div>
			<div id="tabs-4">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" >
				
					<tr>
						<td><label for="txtNome" class="SubtituloRed" >Fase Registro</label> </td>
					</tr>
				
					<tr>
						<td width="15%"> <spring:message code="relatorio.contestacao.aba.registro.prazos.data.contestacao" /></td>
						<td><form:input id="dtContestacao" path="entity.dtContestacao" cssClass="datepicker" /></td>
					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.contestacao.aba.registro.prazos.prazo.contestacao" /></td>
						<td><form:input id="dtPrazoContestacao" path="entity.dtPrazoContestacao" cssClass="datepicker"  styleClass="text-mandatory"/></td>
					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.contestacao.aba.registro.prazos.fora.prazo" /></td>
						<td><form:checkbox id="fgContestacaoForaDoPrazo" path="entity.fgContestacaoForaDoPrazo" value="Sim" /></td>
					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.contestacao.aba.registro.prazos.confirma.analise" /></td>
						<td><form:checkbox id="fgConfirmacaoAnalise" path="entity.fgConfirmacaoAnalise" value="Sim" /></td>
					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.contestacao.aba.registro.prazos.prazo.resposta" /></td>
						<td><form:input id="dtPrazoResposta" path="entity.dtPrazoResposta" cssClass="datepicker"/></td>
					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.contestacao.aba.registro.prazos.sem.resposta" /></td>
						<td><form:checkbox id="fgContestacaoSemResposta" path="entity.fgContestacaoSemResposta" value="Sim" /></td>
					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.contestacao.aba.registro.prazos.confirma.resposta" /></td>
						<td><form:checkbox id="fgConfirmacaoResposta" path="entity.fgConfirmacaoResposta" value="Sim" /></td>
					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.contestacao.aba.registro.prazos.status.contestacao" /></td>
						<td> <form:select id="cdStatusContestacao2" path="entity.cdStatusContestacao" items="${statusContestacao}" itemLabel="label" itemValue="key"></form:select> </td>
					</tr>
					
					<tr>
					</tr>
					<tr>
						<td class='label' colspan='3' align="center" valign="top">
							<center>
								<input type="button" value="Limpa/Novo" class="button" style="width:90px;"/>
								<input type="button" value="Salvar" class="button_salvar" style="width:90px;"/>
							</center>
						</td>
					</tr>
				</table>		
			</div>
			<div id="tabs-5">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" >
				
					<tr>
						<td><label for="txtNome" class="SubtituloRed" >Fase de Análise</label> </td>
					</tr>

					<tr>
						<td width="15%"><spring:message code="relatorio.contestacao.aba.abatimento.analise.usuario.batimento" /></td>
						<td><form:input	id="cdAnalistaDownloadBatimento" path="entity.cdAnalistaDownloadBatimento"  /></td>
					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.contestacao.aba.abatimento.analise.data.input.batimento" /></td>
						<td><form:input  id="dtArquivoBatimento" path="entity.dtArquivoBatimento" cssClass="datepicker"/></td>
					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.contestacao.aba.abatimento.analise.qte.ch.arquivo.batimento" /></td>
						<td><form:input id="qtChamadasArqBatimento" path="entity.qtChamadasArqBatimento" /></td>
					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.contestacao.aba.abatimento.analise.min.reais.arquivo.batimento"/></td>
						<td><form:input	id="qtMinutosReaisArqBatimento" path="entity.qtMinutosReaisArqBatimento"/></td>
					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.contestacao.aba.abatimento.analise.min.arred.arquivo.batimento"/></td>
						<td><form:input	id="qtMinutosArredArqBatimento" path="entity.qtMinutosArredArqBatimento"/></td>
					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.contestacao.aba.abatimento.analise.valor.bruto.arquivo.batimento"/></td>
						<td><form:input	id="vlBrutoArqBatimento" path="entity.vlBrutoArqBatimento"/></td>
					</tr>
					<tr>
						<td class='label' colspan='3' align="center" valign="top">
							<center>
								<input type="button" value="Limpa/Novo" class="button" style="width:90px;"/>
								<input type="button" value="Salvar" class="button_salvar"  style="width:90px;"/>
							</center>
						</td>
					</tr>
				</table>			
			</div>
		</form:form>
	</div>
</html>
	
<script>
$(document).ready(function(){
	$('#pesquisar_button').removeAttr('disabled');
	$('#excel_button').removeAttr('disabled');
});


</script>
