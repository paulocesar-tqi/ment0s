<%@ page session="true" isELIgnored="false"%>
<%@ taglib uri="/tags/ajaxtags" prefix="ajax"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="/tags/spring-form"%>
<%@ taglib prefix="spring" uri="/tags/spring"%>
<link rel="stylesheet" href="/scc/css/jquery-ui.css" type="text/css"/>
 <script type="text/javascript" src="<c:url value="/js/jquery-1.8.2.js"/>"></script>
 <script type="text/javascript" src="<c:url value="/js/jquery-ui.js"/>"></script>
 <script type="text/javascript" src="<c:url value="/js/jquery.ui.datepicker-pt-BR.js"/>"></script>
 <script type="text/javascript" src="<c:url value="/js/jquery.tmpl.js"/>"></script>
 <script type="text/javascript" src="<c:url value="/js/jquery.tmpl.min.js"/>"></script>
 <script type="text/javascript" src="<c:url value="/js/jquery.tmplPlus.js"/>"></script>
 <script type="text/javascript" src="<c:url value="/js/scc/indicadorpos.js"/>"></script>
<link rel="stylesheet" href="/scc/css/jquery-ui-1.8.18.custom.css" type="text/css"/>
<link rel="stylesheet" href="/scc/css/table_jui.css" type="text/css">
<link rel="stylesheet" href="/scc/css/styles.css" type="text/css">
<%@ taglib uri="/tags/displaytag" prefix="display"%>


<div id="tabs">
	<ul>
		<li><a href="#tabs-1"><spring:message code="crud.titulo.pesquisar" /></a></li>
		<li><a href="#tabs-2"><spring:message code="crud.titulo.editar"/></a></li>
	</ul>
	<form:form modelAttribute="filtro" method="post" action="/scc/user/indicador/indicador/pos/listar.scc" id="form1">
		<form:hidden path="operacao" id="operacao" />
		<form:hidden path="itemSelecionado" id="itemSelecionado" />
		<form:hidden path="entidade.cdUsuarioManut" id="cdUsuarioManut"/>
		<form:hidden path="entidade.dtCriacao" id="dtCriacao"/>
		<form:hidden path="idIndicador" id="idIndicadorform"/>
		
		
		<div id="tabs-1">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" id="filtroPesquisaTable">
				
				<tr>
					<td width="15%"><spring:message code="relatorio.indicador.aging.label.indicador" /></td>
					<td><form:select path="filtro.idIndicador" id="idIndicador"  itemLabel="label" itemValue="key">
							<c:forEach var="item" items="${indicadores}">
								<c:choose>
									<c:when test="${item.label == indicador}">
										<form:option selected="true" value="${item.label}">
									
										</form:option>
									</c:when>
 									<c:otherwise>
            							<form:option value="${item.label}">
                						
            							</form:option>
        							</c:otherwise>								
								</c:choose>	
							</c:forEach>
						</form:select>
					</td>
				</tr>
				
			</table>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td colspan="3" class="TdFormularioUp">&nbsp;</td>
					<td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
						<input id="pesquisar_button" class="btn_pesquisar" type="button" value=<spring:message code="comum.botao.pesquisar"/> />
						<input id="excel_button" type="button" value=<spring:message code="comum.botao.excel"/> />
					</td>
				</tr>
			</table>
			<br />
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td>
					
						<display:table style="width:90%" name="requestScope.filtro.listIndicador" pagesize="20" 
									   id="repasses" 
									   requestURI="${pageContext.request.contextPath}/user/indicador/indicador/pos/listar.scc"
									   class="ui-state-default" 
									   decorator="com.claro.sccweb.decorator.view.SccIndicadorDecorator">
							<display:column property="cdTipoContrato" title="Tipo"/>
							<display:column property="cdIndicador" title="Indicador"/>
							<display:column property="dsIndicador" title="Descrição"/>
							<display:column property="dsFormatoIndicador" title="Formato"/>
							<display:column property="cdDwlind" title="DsWlind"/>
							<display:column property="dsPeriodicidade" title="Periodicidade"/>
							<display:column property="cdTipoIndicador" title="Tipo Indicador"/>
							<display:column property="cdStatusIndicador" title="Status"/>	
							<display:column property="dtReferencia" title="Dt. Referencia" format="{0,date,dd/MM/yyyy}" style="width:10%; text-align:left;"/>
							<display:column title="Editar"><a href="editarIndicador.scc?indicador=${repasses.cdIndicador}" 
																class="edit_indicadores" >
																<img  src="/scc/images/editIcon.gif"></a>
							</display:column>
							
							<display:column title="Remover"><a href="removerIndicador.scc?indicador=${repasses.cdIndicador}" 
																class="delete_indicadores" >
																<img  src="/scc/images/delete.gif"></a>
							</display:column>
						</display:table>
					</td>
				</tr>
			</table>
		</div>
		<div id="tabs-2" style="height: 500px;">
			
			<table width="100%" border="0" cellspacing="0" cellpadding="0" >
				<tr>
					<td width="15%"><spring:message code="relatorio.indicador.aging.label.indicador" /></td>
					<td><form:input path="entidade.cdIndicador" id="cdIndicador" /></td>
				</tr>
				
				<tr>
					<td width="15%"><spring:message code="relatorio.indicador.indicador.label.descricao" /></td>
					<td><form:input path="entidade.dsIndicador" id="dsIndicador" /></td>
				</tr>

				<tr>
					<td width="15%"><spring:message code="relatorio.indicador.indicador.label.dswlind" /></td>
					<td><form:input path="entidade.cdDwlind" id="cdDwlind" cssClass="text_dw" /></td>
				</tr>
				
				<tr>
					<td width="15%"><spring:message code="relatorio.indicador.indicador.label.periodicidade" /></td>
					<td><form:select path="entidade.dsPeriodicidade" id="dsPeriodicidade" items="${periodicidade}" itemLabel="label" itemValue="label" /></td>
				</tr>

				<tr>
					<td width="15%"><spring:message code="relatorio.indicador.indicador.label.tipo.valor" /></td>
					<td><form:select path="entidade.cdTipoIndicador" id="cdTipoIndicador" items="${tipo}" itemLabel="label" itemValue="label" /></td>
				</tr>

				<tr>
					<td width="15%"><spring:message code="relatorio.indicador.indicador.label.formato" /></td>
					<td><form:select path="entidade.dsFormatoIndicador" id="dsFormatoIndicador" items="${formato}" itemLabel="label" itemValue="label" /></td>
				</tr>

				<tr>
					<td width="15%"><spring:message code="relatorio.indicador.indicador.label.status" /></td>
					<td><form:select path="entidade.cdStatusIndicador" id="cdStatusIndicador" items="${status}" itemLabel="label" itemValue="label" /></td>
				</tr>


					<tr>
						<td width="15%"><spring:message code="relatorio.indicador.indicador.label.dt.referencia" /></td>
						<td><form:input path="entidade.dtReferencia" id="dtReferencia" cssClass="datepicker"/>
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
							<input type="button" value="Salvar" class="button_salvar" onclick="salvar()"  style="width:90px;"/>
						</center>
					</td>
				</tr>
				
			
			</table>
		</div>
		
	</form:form>
</div>
<script>
	$(document).ready(function(){
		$('#pesquisar_button').removeAttr('disabled');
		$('#excel_button').removeAttr('disabled');
	});
</script>
