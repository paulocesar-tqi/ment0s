<%@ page session="true" isELIgnored="false"%>
<%@ taglib uri="/tags/ajaxtags" prefix="ajax" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="/tags/spring-form"%>
<%@ taglib prefix="spring" uri="/tags/spring"%>
<link rel="stylesheet" href="/scc/css/jquery-ui-1.8.18.custom.css" type="text/css"/>
<link rel="stylesheet" href="/scc/css/table_jui.css" type="text/css"/>
 <script type="text/javascript" src="<c:url value="/js/jquery-ui.js"/>"></script>
 <script type="text/javascript" src="<c:url value="/js/jquery.ui.datepicker-pt-BR.js"/>"></script>
 <script type="text/javascript" src="<c:url value="/js/jquery.tmpl.js"/>"></script>
 <script type="text/javascript" src="<c:url value="/js/jquery.tmpl.min.js"/>"></script>
 <script type="text/javascript" src="<c:url value="/js/jquery.tmplPlus.js"/>"></script>
 <script type="text/javascript" src="<c:url value="/js/scc/grupo.js"/>"></script>
<%@ taglib uri="/tags/displaytag" prefix="display" %>


<div id="tabs">
	<ul>
		<li><a href="#tabs-1"><spring:message code="crud.titulo.pesquisar"/></a></li>
		<li><a href="#tabs-2"><spring:message code="crud.titulo.editar"/></a></li>
	</ul>
	<form:form modelAttribute="filtro" method="post" action="grupo.scc" id="form1">
		<form:hidden path="operacao" id="operacao"/>
		<form:hidden path="itemSelecionado" id="itemSelecionado"/>
		<form:hidden path="mensagem" id="mensagem"/>
		<div id="tabs-1">
		
			<table width="100%" border="0" cellspacing="0" cellpadding="0" id="filtroPesquisaTable">
				<tr>
				    <td colspan="3" class="TdFormularioUp">&nbsp;</td>    
				    <td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
					    <input id="novo_button" type="button" value=<spring:message code="crud.botao.novo"/> />
					    <input id="pesquisar_button" type="button" value="Pesquisar" />
					    <!-- <input id="excel_button" type="button" value=<spring:message code="comum.botao.excel"/> />-->
				    </td>
				</tr>
				<table  width="100%" border="0" cellspacing="0" cellpadding="0" >
					 <tr><td>                            
						<display:table style="width:90%"  name="requestScope.filtro.listGrupo"   pagesize="20"  id="repasses" requestURI="/scc/user/admin/grupo/tab1.scc" class="ui-state-default">
						<display:column property="noGrupo" title="Grupo" />
						<display:column title="Editar"><a href="editarGrupo.scc?sqGrupo=${repasses.sqGrupo}" 
						    							  class="edit_grupo" >
																<img  src="/scc/images/editIcon.gif"></a>
						</display:column>
							
						<display:column title="Remover"><a href="removerGrupo.scc?sqGrupo=${repasses.sqGrupo}" 
														   class="delete_grupo" >
																<img  src="/scc/images/delete.gif"></a>
						</display:column>
						</display:table>
					</td></tr>
				</table>
				
			</table>
			<br/>
			<c:if test="${!empty requestScope.filtro.listGrupo}">
			</c:if>
		
		</div>
		
		<div id="tabs-2" style="height: 500px;">
			<form:hidden path="entity.dtCriacao" id="dtCriacao"/>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" >
			
				<tr>
					<td width="15%"><spring:message code="admin.label.tab2.grupo"/></td>
					<td>
						<form:input path="entity.noGrupo" id="descricao" size="61" maxlength="60"/><form:errors path="entity.noGrupo"/>
					</td>
				</tr>
			</table>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
				    <td colspan="3" class="TdFormularioUp">&nbsp;</td>    
				    <td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
					    <input id="atualizar_button" type="button" value=<spring:message code="crud.botao.atualizar"/> />
					    <input id="cancelar_button" type="button" value="Cancelar" />        
					    <input id="salvar_button" type="button" value=<spring:message code="crud.botao.salvar"/> />
			    	</td>
				</tr>
			</table>
		</div>
	</form:form>
</div>

