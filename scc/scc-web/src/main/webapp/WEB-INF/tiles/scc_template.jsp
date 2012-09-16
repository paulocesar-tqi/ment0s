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
<body style="margin:0" onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="">
<table border="0" width="100%">
<tr>
<td>
<img  src="/scc/images/header.gif" width="100%" height="5%"/>
</td>
</tr>
<tr>
<td class="td_menu">
<tiles:insertAttribute name="menu" />
</td>
</tr>
<tr>
<c:if test="${sessionScope['scopedTarget.sessionDataManager'].posPago}"> 
<td align="right" size="20"><a href="/scc/inicio/modulo/switch.scc" class="Link">Módulo Pré-Pago</a></td>
</c:if>

<c:if test="${sessionScope['scopedTarget.sessionDataManager'].prePago}"> 
<td align="right" size="20"><a href="/scc/inicio/modulo/switch.scc" class="Link">Módulo Pós-Pago</a></td>
</c:if>



</td>
</tr>
<tr>
<td>
<tiles:insertAttribute name="body" />
</td>
</tr>

</table>
</body>
</html>