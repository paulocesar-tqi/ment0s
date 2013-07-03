<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/includes/tags.jsp"%>
<%@ taglib uri="/tags/tiles" prefix="tiles" %>
<head>
<title>SCC - <tiles:getAsString name="tituloTela"/></title>
<link rel="stylesheet" href="/scc/css/menu.css" type="text/css">
<link rel="stylesheet" href="/scc/css/body.css" type="text/css">
<script language="javascript" src="/scc/js/jquery.js"></script>
<script language="javascript" src="/scc/js/jquery.maskedinput-1.3.js"></script>


</head>
<body style="margin:0">
<table border="0" width="100%">
<tr>
<td>
<img  src="/scc/images/header.gif" width="100%" height="5%"/>
</td>
</tr>
<tr>
<td>
<p></p>
<div class="TituloTela" align="center"><u><tiles:getAsString name="tituloTela"/></u></div>
	<tr>
		<td align="right" ><font face="Verdana" color="#000"><a style=font-size:10px>Versão: 8.0.2</a></font></td>
	</tr>
<tiles:insertAttribute name="body" />
</td>
</tr>

</table>
</body>
</html>