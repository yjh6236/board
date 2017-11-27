<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="include/header.jsp"%><br />
	<section class="content"> 
		<h3>${msg }</h3>
		<c:if test="${LOGIN != null}">
			<span class="badge bg-green"> ${LOGIN.name}</span>님
		</c:if>
		
	<div class="box">
		<div class="box-header with-border">
			<a href=board/register><h3 class="box-title" />
			<strong><spring:message code="label.2" /></strong></h3></a>
		</div>

		<div class="box-header with-border">
			<a href=board/list><h3 class="box-title" /><strong><spring:message code="label.1" /></strong></h3></a>
		</div>
		
		<div class="box-header with-border">
			<a href="http://localhost:9003"><h3 class="box-title" /><strong><spring:message code="label.3" /></strong></h3></a>
		</div>
		

		<c:if test="${LOGIN==null}">
			<div class="box-header with-border">
				<a href="user/login"><h3 class="box-title"><strong><spring:message code="label.4" /></strong></h3></a>
			</div>
		</c:if>



		<c:if test="${LOGIN!=null}">
			<div class="box-header with-border">
				<a href="user/logout"><h3 class="box-title"><strong><spring:message code="label.5" /></strong></h3></a>
			</div>
		</c:if>
		
		
			<div class="box-header with-border">
				<a href="user/register"><h3 class="box-title"><strong><spring:message code="label.9" /></strong></h3></a>
			</div>

	</div>
	</section>
	<%@ include file="include/footer.jsp"%>
</body>
</html>