<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="/tags/spring-form"%>
<%@ taglib prefix="spring" uri="/tags/spring"%>


<div class="Conteudo">
<div class="FormularioBody">
</div>
</div>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="TabelaFormulario">
<tr>
    <td width="100%"  align="center"><spring:message code="comum.inicial.titulo" arguments="${sessionScope['scopedTarget.sessionDataManager'].userDisplayName}"/></td>
</tr>
</table>