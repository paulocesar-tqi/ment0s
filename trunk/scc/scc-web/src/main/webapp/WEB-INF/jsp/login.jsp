<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="/tags/spring-form"%>
<%@ taglib prefix="spring" uri="/tags/spring"%>

<div class="Conteudo">
<div class="FormularioBody">


<form method="post" name="form1" id="form1" action="j_scc_security_check.scc">
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="TabelaFormulario">
<tr>
    <td width="100%"  colspan=2><u><b><spring:message code="login.titulo"/></b></u></td>
</tr>

<tr>
    <td width="10%"><spring:message code="login.username"/>:</td>
    <td><input type="text" id="j_username" name="j_username" cssClass="combo" value=""/></td>
</tr>

<tr>
    <td width="10%"><spring:message code="login.password"/>:</td>
    <td><input type="password" id="j_password" name="j_password" value="" cssClass="combo"/></td>
</tr>


</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
    <td colspan="3" class="TdFormularioUp">&nbsp;</td>    
    <td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">    
    <input type="submit" value=<spring:message code="comum.botao.entrar"/> class="submitButton"/>        
    </td>
</tr>
</table>
<form>

<c:if test="${not empty param.error}">
<style type="text/css">
.login-error {
    display: block;
    padding: 8px 35px 8px 14px;
    margin: 20px 0px 20px;
    color: #b94a48;
    background-color: #f2dede;
    border: 1px solid #eed3d7;
    -webkit-border-radius: 4px;
    -moz-border-radius: 4px;
    border-radius: 4px;
}
</style>
<div class="login-error"><p>
<c:choose>
 <c:when test="${param.error eq '1'}">
  <spring:message code="login.invalido"/>
 </c:when>
 <c:otherwise>
  <spring:message code="login.negado"/>
 </c:otherwise>
</c:choose>
</p></div>
</c:if>

<script language="javascript">
document.form1.j_username.focus();
</script>
</div>
</div>
