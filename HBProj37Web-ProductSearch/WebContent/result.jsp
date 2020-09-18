<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

    <h1 style="color:blue;text-align:center">Product Details (Result Page)</h1>
    <br><br>
   
   <c:choose>
   	<c:when test="${!empty pDTO }">
   		<table border="1" align="center">
   				<tr>
   					<td>${pDTO.pid}</td>
   					<td>${pDTO.pname}</td>
   					<td>${pDTO.price}</td>
   					<td>${pDTO.qty}</td>
   					<td>${pDTO.category}</td>
   				</tr>
   			</table>
   	</c:when>
   	<c:otherwise>
   		<h2 style="color:red;text-align:center">No Product Found</h2>
   	</c:otherwise>
   </c:choose>
   	
   
   
  <%--  <%
   	if(request.getAttribute("pDTO")!=null){
   		%>
   			<table border="1" align="center">
   				<tr>
   					<td>${pDTO.pid}</td>
   					<td>${pDTO.pname}</td>
   					<td>${pDTO.price}</td>
   					<td>${pDTO.qty}</td>
   					<td>${pDTO.category}</td>
   				</tr>
   			</table>
   		<%
   	}else{
   		%>
   			<h2 style="color:red;text-align:center">No Product Found</h2>
   		<%
   	}
   %>  --%>
   <br><br>
   <a href="search.html">Home</a>