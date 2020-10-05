<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1 style="color:red;text-align:center">Report Generation Page</h1>
<br>
<br>
<c:choose>
	<c:when test="${listPolicies ne null && !empty listPolicies}">
		<table border="1" align="center">
			<tr>
				<th>Serial No.</th>
				<th>Policy Id</th>
				<th>Policy Name</th>
				<th>Policy Type</th>
				<th>Company</th>
				<th>Tenure</th>
			</tr>
			<c:forEach var="policy" items="${listPolicies}">
			<tr bgcolor="yellow">
				<td>${policy.serialNo }</td>
				<td>${policy.policyId }</td>
				<td>${policy.policyName }</td>
				<td>${policy.policyType }</td>
				<td>${policy.company }</td>
				<td>${policy.tenure }</td>
			</tr>
			</c:forEach>				
		</table>
		<br>
		<center>
			<c:if test="${pageNo>1}">
				<b><a href="controller?s1=link&pageNo=${pageNo-1}">previous</a></b>&nbsp;&nbsp;
			</c:if>
		
			<c:forEach var="i" begin="1" end="${pagesCount}" step="1">
				<b><a href="controller?s1=link&pageNo=${i}">[ ${i} ]</a></b>&nbsp;&nbsp;
			</c:forEach>
			
			<c:if test="${pageNo<pagesCount}">
				<b><a href="controller?s1=link&pageNo=${pageNo+1}">next</a></b>
			</c:if>
		</center>
	</c:when>
	<c:otherwise>
		<h1 style="color:red;text-align:center">Records not found</h1>
	</c:otherwise>
</c:choose>