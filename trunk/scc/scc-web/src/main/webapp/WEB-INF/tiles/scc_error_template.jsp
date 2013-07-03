<%@ page session="true" isELIgnored="false"%>
 
<!DOCTYPE html>
<html>
 
<%@include file="/WEB-INF/includes/tags.jsp"%>
<%@ taglib uri="/tags/tiles" prefix="tiles" %>
<meta http-equiv="X-UA-Compatible" content="IE=8"/>
<head>
<title>SCC - ERRO</title>

<link rel="stylesheet" href="/scc/css/jquery-ui-1.8.18.custom.css" type="text/css">
<link rel="stylesheet" href="/scc/css/body.css" type="text/css"/>
<script language="javascript" src="/scc/js/jquery-1.7.1.min.js"></script>
<script language="javascript" src="/scc/js/jquery.maskedinput-1.3.js"></script>
<script type="text/javascript" src="/scc/js/jquery-ui-1.8.18.custom.min.js"></script>

</head>
<body style="margin:0">
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
<tr>
<td>
<tiles:insertAttribute name="body" />
</td>
</tr>

</table>
</body>
</html>