<%@ page session="true" isELIgnored="false"%>
 
<!DOCTYPE html>
<html>
 
<%@include file="/WEB-INF/includes/tags.jsp"%>
<%@ taglib uri="/tags/tiles" prefix="tiles" %>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=8"/>
<title>SCC - <tiles:getAsString name="tituloTela"/></title>

<link rel="stylesheet" href="/scc/css/jquery-ui-1.8.18.custom.css" type="text/css">
<link rel="stylesheet" href="/scc/css/body.css" type="text/css"/>
<script language="javascript" src="/scc/js/jquery-1.7.1.min.js"></script>
<script language="javascript" src="/scc/js/jquery.maskedinput-1.3.js"></script>
<script type="text/javascript" src="/scc/js/jquery-ui-1.8.18.custom.min.js"></script>


<SCRIPT type="text/javascript">    
window.history.forward();     
function noBack() 
	{ 
	window.history.forward(); 
	} 
</SCRIPT>

</head>
<html>
<div id="idTemplate">
	<body style="margin:0" onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="">
		<table border="0" width="100%">
			<tr>
				<td>
					<img  src="/scc/images/header.gif" width="100%" height="5%"/>
				</td>
			</tr>
			
			<tr>
			</tr>
			
			<tr>
				<c:if test="${sessionScope['scopedTarget.sessionDataManager'].posPago}"> 
					<td align="center" size="70"><b><font face="Verdana" color="#990000"><a>Módulo Pós-Pago</a></font></b></td>
				</c:if>
			
				<c:if test="${sessionScope['scopedTarget.sessionDataManager'].prePago}"> 
					<td align="center" size="70"><b><font face="Verdana" color="#990000"><a>Módulo Pré-Pago</a></font></b></td>
				</c:if>
			</tr>
			
			<tr>
				<td align="right" ><font face="Verdana" color="#000"><a style=font-size:10px>Versão: 8.0.2</a></font></td>
			</tr>
			
			<tr>
				<td class="td_menu">
					<tiles:insertAttribute name="menu" />
				</td>
			</tr>
			<tr>
				<c:if test="${sessionScope['scopedTarget.sessionDataManager'].posPago}"> 
					<td align="right" size="20"><a href="/scc/inicio/modulo/switch.scc" class="Link"><b><font face="Verdana" color="#990000">>> Acessar Módulo Pré-Pago</font></b></a></td>
				</c:if>
			
				<c:if test="${sessionScope['scopedTarget.sessionDataManager'].prePago}"> 
					<td align="right" size="20"><a href="/scc/inicio/modulo/switch.scc" class="Link"><b><font face="Verdana" color="#990000">>> Acessar Módulo Pós-Pago</font></b></a></td>
				</c:if>
			</tr>
			<div id="idBody">
				<tr>
					<td>
						<tiles:insertAttribute name="body" />
					</td>
				</tr>
			</div>
		</table>
	</body>
</div>
</html>