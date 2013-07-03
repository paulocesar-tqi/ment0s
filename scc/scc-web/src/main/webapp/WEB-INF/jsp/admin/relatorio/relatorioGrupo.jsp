<%@ page session="true" isELIgnored="false"%>
<%@ taglib uri="/tags/ajaxtags" prefix="ajax" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="/tags/spring-form"%>
<%@ taglib prefix="spring" uri="/tags/spring"%>
<link rel="stylesheet" href="/scc/css/jquery-ui-1.8.18.custom.css" type="text/css"/>
 <script type="text/javascript" src="<c:url value="/js/jquery.tmpl.js"/>"></script>
 <script type="text/javascript" src="<c:url value="/js/jquery.tmpl.min.js"/>"></script>
 <script type="text/javascript" src="<c:url value="/js/jquery.tmplPlus.js"/>"></script>
 <script type="text/javascript" src="<c:url value="/js/scc/indicadorpos.js"/>"></script>
<link rel="stylesheet" href="/scc/css/table_jui.css" type="text/css">
<%@ taglib uri="/tags/displaytag" prefix="display" %>

<script id="template_associado" type="text/x-jquery-tmpl">
	<tr>		
		<td align=right>\${sccGrupoCobilling.noGrupo}</td>         
		<td align=right>\${qtDiasEscalada}</td> 
		<td align=center> <a href="removerGrupoAssociado.scc?sqGrupo=\${id.sqGrupo}&sqRelatorio=\${id.sqRelatorio}&dtInicioVigencia= \${id.dtInicioVigencia}"   class="delete_entity" > <img  src="/scc/images/editIcon.gif"></a>
		</td>    

	</tr>

</script>

<script>
$(document).ready(function(){		
	$('#atualizar_button').hide();	
	$('#novo_button').click(novo);
	$('#atualizar_button').click(atualizar);
	$('#cancelar_button').click(cancelar);
	$('#pesquisar_button').click(pesquisar);	
	$('#salvar_button').click(salvar);
	$('#tbl_associado').hide();
	$('#tabs').tabs();
});

$(function() {
    $( "#tabs" ).tabs();
});


 
function salvar() {
	
	var r=confirm("Salvar?");
	if(r==true){
		
		var dados = $("#form1").serialize();		
		
		$.ajax({
			cache:false,
			type: "POST",
			url : "/scc/user/admin/grupo/salvarGrupo.scc",
			data: dados,
			success : function(data) {
				$('#salvar_button').hide();					
				$('#form1').submit();
				$('#tabs').tabs('select',0);	
			}
		});
	}
}



function atualizar() {
	
	var r=confirm("Salvar?");
	if(r==true){
		
		var dados = $("#form1").serialize();
		
		$.ajax({
			cache:false,
			type: "POST",
			url : "/scc/user/admin/grupo/atualizarGrupo.scc",
			data: dados,
			success : function(data) {
				$('#atualizar_button').hide();
				limpar();
				$('#form1').submit();
			
			}
		});
	}
}


$('body').delegate('.edit_relatorio', 'click', function() {
	 
		$.ajax({
			cache:false,
			type: "GET",
			dataType:"json",
			contentType: "application/json; charset=utf-8",
			url : this.href,
			success : function(data) {
				$('#tbl_associado').show();
								
				$('#tbl_associado_body').html($('#template_associado').tmpl(data));
				$('#tabs').tabs('select',1);
				$("#tbl_associado tr:odd").css("background-color", "#E2E4FF");
			}
		});

	return false;
});









$('body').delegate('.delete_entity', 'click', function() {
	 
	 var r=confirm("Remover?");
	 if(r==true){
		 	
		 	var sqRelatorio = getParameter('sqRelatorio', this.href);
			$.ajax({
				cache:false,
				type: "DELETE",
				url : this.href,
								
				success : function(data) {
					
					//$('#sqRelatorio').attr('value', sqRelatorio);
					//var sqRelatorio=$("#sqRelatorio").val();
					//editarEntity();
					$.ajax({
						cache:false,
						type: "GET",
						dataType:"json",
						contentType: "application/json; charset=utf-8",
						url : "/scc/user/admin/rel/associado/listGrupoAssociado.scc?sqRelatorio="+sqRelatorio,
						success : function(data) {
							$('#tbl_associado').show();
											
							$('#tbl_associado_body').html($('#template_associado').tmpl(data));
							$('#tabs').tabs('select',1);
							$("#tbl_associado tr:odd").css("background-color", "#E2E4FF");
						}
					});
					
					
				}
			});
		}
	 return false;
});


function editarEntity(){

	var sqRelatorio = getVar(sqRelatorio);
	
	$.ajax({
		cache:false,
		type: "GET",
		dataType:"json",
		contentType: "application/json; charset=utf-8",
		url : "/scc/user/admin/rel/associado/listGrupoAssociado.scc?sqRelatorio=sqRelatorio",
		success : function(data) {
			$('#tbl_associado').show();
			
			$('#tbl_associado_body').html($('#template_associado').tmpl(data));
			$('#tabs').tabs('select',1);
			$("#tbl_associado tr:odd").css("background-color", "#E2E4FF");
		}
	});

return false;
	
	
}

function atribuirValor(data){
	
	var sqGrupo = data.sqGrupo;
	var noGrupo = data.noGrupo;
	var dtInicioVigencia = data.dtInicioVigencia;
	var dtCriacao = data.dtCriacao;
	
	$('#sqGrupo').attr('value', sqGrupo);
	$('#descricao').attr('value', noGrupo);
	$('#dtInicioVigencia').attr('value', formatarDataPtBr(dtInicioVigencia));
	$('#dtCriacao').attr('value', formatarDataPtBr(dtCriacao));								   
}

function limpar(){
	$('#sqGrupo').attr('value', "");
	$('#descricao').attr('value', "");
	$('#dtReferencia').attr('value', "");	
}


function formatarDataPtBr(data){
	var currentDate = null;
	var dateTimeSplit =data.split(' ');
	var dateSplit = dateTimeSplit[0].split('-');
	currentDate = dateSplit[2] + '/' + dateSplit[1] + '/' + dateSplit[0];
	return currentDate;
	
}


function getParameter(p,href){
	var parName = p.replace(/[\[]/,"\\\[").replace(/[\]]/,"\\\]");
		var rx = new RegExp("[\\?&#]"+parName+"=([^&#]*)");
		var valor = rx.exec(href);
		if(valor == null){
		return "";
	}else{
		return valor[1];
	}
}

function queryString(parameter) {  
      var loc = location.search.substring(1, location.search.length);   
      var param_value = false;   
      var params = loc.split("&");   
      for (i=0; i<params.length;i++) {   
          param_name = params[i].substring(0,params[i].indexOf('='));   
          if (param_name == parameter) {   					  
              param_value = params[i].substring(params[i].indexOf('=')+1)   
          }   
      }   
      if (param_value) {   
          return alert(param_value);   
      }   
      else {   
          return false;   
      }   
}  


function urlDecode(string, overwrite){
	if(!string || !string.length){
		return {};
	}
	var obj = {};
	var pairs = string.split('&');
	var pair, name, value;
	var lsRegExp = /\+/g;
	for(var i = 0, len = pairs.length; i < len; i++){
		pair = pairs[i].split('=');
		name = unescape(pair[0]);
		value = unescape(pair[1]).replace(lsRegExp, " ");
		//value = decodeURIComponent(pair[1]).replace(lsRegExp, " ");
		if(overwrite !== true){
			if(typeof obj[name] == "undefined"){
				obj[name] = value;
			}else if(typeof obj[name] == "string"){
				obj[name] = [obj[name]];
				obj[name].push(value);
			}else{
				obj[name].push(value);
			}
		}else{
			obj[name] = value;
		}
	}
	return obj;
}


function getVar(param){
	var wl = window.location.href;
	var params = urlDecode(wl.substring(wl.indexOf("?")+1));
	return(params[param]);
}


function limparCampos(container) {
    $(container).find(":input, select").each(function () {
        switch (this.type) {
            case "file":
            case "password":
            case "text":
            case "textarea":
                $(this).val("");
                break;
            case "checkbox":
            case "radio":
                this.checked = false;
        }

        $(this).children("option:selected").removeAttr("selected").end()
               .children("option:first").attr("selected", "selected");
    });
}

function novo() {	
	
	$('#operacao').val("CRUD.novo");	
	$('#form1').submit();

}

function pesquisar() {
	$('#pesquisar_button').attr('disabled', 'disabled');	
	$('#operacao').val("pesquisar");	
	$('#form1').submit();
}

function cancelar() {
	$('#tabs').tabs('select',1);	
	$('#operacao').val("cancelar");	
	$('#form1').submit();
}

function num(dom){
    dom.value=dom.value.replace(/\D/g,""); 
}

</script>

<div id="tabs">
	<ul>
		<li><a href="#tabs-1"><spring:message code="crud.titulo.pesquisar"/></a></li>
		<li><a href="#tabs-2"><spring:message code="crud.titulo.editar"/></a></li>
	</ul>
	<form:form modelAttribute="filtro" method="post" action="listar.scc" id="form1">
		
		<div id="tabs-1">
			<br/>
			<c:if test="${!empty requestScope.filtro.listRelatorios}">
				<table  width="100%" border="0" cellspacing="0" cellpadding="0" >
					 <tr><td>                            
						<display:table style="width:90%"  name="requestScope.filtro.listRelatorios"   pagesize="20"  id="repasses" requestURI="/scc/user/admin/rel/associado/listar.scc" class="ui-state-default">
						<display:column property="noRelatorio" title="Relatório" />
						<display:column title="Editar"><a href="editarListGrupoAssociado.scc?sqRelatorio=${repasses.sqRelatorio}" 
						    							  class="edit_relatorio" >
																<img  src="/scc/images/editIcon.gif"></a>
						</display:column>
							
						</display:table>
					</td></tr>
				</table>
			</c:if>
		
		</div>
		
		<div id="tabs-2" style="height: 500px;">
		
			<table id="tbl_associado" style="width: 100%; border: 1px;" bgcolor="white" background="white">
				<td>
					<table id="tbl_associado" style="width: 50%; border: 1px;" class="ui-state-default">
						<thead>
							<tr>
								<th>Grupo</th>
								<th>Qtd Dias Escalado</th>
								<th>Editar</th>							
								<th>Remover</th>													
							</tr>
						</thead>
						<tbody id="tbl_associado_body"></tbody> 
					</table>
				</td>
				<td>
					<table id="tbl_associado" style="width: 50%; border: 1px;" bgcolor="white" background="white">
						<tr style="background-color: white !important">
    						<td width="20%" style="background-color: white !important"><spring:message code="relatorio.grupo.grupos"/></td>
    						<td ><form:select path="entity.sccGrupoCobilling" id="sqgrupos" items="${grupos}"  itemLabel="noGrupo" itemValue="sqGrupo" style="width:200px;" /></td>
						</tr>
						<tr>
    						<td width="20%"><spring:message code="relatorio.grupo.dias.escalado"/></td>
    						<td><form:input id="qtDiasEscalada" path="entity.qtDiasEscalada" style="width:200px;" />
						</tr>
					
					</table>
				</td>
			
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
<script>
$(function() {
	$('#tabs').tabs('select',${filtro.activeTab});
	var op = $('#operacao').val();
	if (op == 'CRUD.editar') {
		$('#tabs').tabs('select',1);
		$('#salvar_button').hide();
		$('#cancelar_button').show();
		$('#atualizar_button').show();
		//$('#descricao').attr('disabled', 'disabled');		
		$('#descricao').show();
	} else if (op == 'CRUD.novo') {	
		$('#tabs').tabs('select',1);
		$('#atualizar_button').hide();
		$('#salvar_button').show();	
	}
	else if (op == 'erro_validacao')
	{
		$('#tabs').tabs('select',1);
		$('#atualizar_button').hide();
		$('#salvar_button').show();
		alert($('#mensagem').val());
	}
	else if(op == 'CRUD.atualizar'){
		$('#salvar_button').hide();
		$('#cancelar_button').show();
		$('#atualizar_button').show();
	}
	else{		
		$('#salvar_button').show();
		$('#atualizar_button').hide();
		$('#cancelar_button').hide();		
	}
});
</script>
