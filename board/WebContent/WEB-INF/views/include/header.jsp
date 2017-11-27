<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>    
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AdminLTE 2 | Dashboard</title>
<meta
	content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no'
	name='viewport'>
<link href="/project/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css"
	rel="stylesheet" type="text/css" />
<link href="/project/resources/dist/css/AdminLTE.min.css" rel="stylesheet"
	type="text/css" />
<link href="/project/resources/dist/css/skins/_all-skins.min.css"
	rel="stylesheet" type="text/css" />
    
  </head>
    <script src="/project/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
  <body class="skin-blue sidebar-mini">
    <div class="wrapper">
      
      <header class="main-header">
        <a href="http://localhost:9003" class="logo">
          <span class="logo-lg"><strong><spring:message code="label.3" /></strong></span>
        </a>
        <nav class="navbar navbar-static-top" role="navigation">
          <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </a>
          </nav>
          </header>
          </div>
          
      <aside class="main-sidebar">
        <section class="sidebar">
          <ul class="sidebar-menu">
            <li class="header"></li>
            <li class="treeview">
              <a href="#">
                <i class="fa fa-folder"></i> <span><spring:message code="label.10" /></span>
                <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li><a href="/project/board/list"><i class="fa fa-circle-o"></i> <spring:message code="label.1" /></a></li>
                <li><a href="/project/board/register"><i class="fa fa-circle-o"></i> <spring:message code="label.2" /></a></li>
              </ul>
            </li>
            
            <c:if test="${LOGIN==null}">
            <li><a href="/project/user/login"><i class="fa fa-circle-o text-red"></i> <span><spring:message code="label.4" /></span></a></li>
            </c:if>
            
            <c:if test="${LOGIN!=null}">
            <li><a href="/project/user/logout"><i class="fa fa-circle-o text-green"></i> <span><spring:message code="label.5" /></span></a></li>
            </c:if>
            
             <li><a href="/project/user/register"><i class="fa fa-circle-o text-yellow"></i> <span><spring:message code="label.9" /></span></a></li>
           
          </ul>
        </section>
      </aside>

      <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            	
          </h1>
          <ol class="breadcrumb">
            <li><a href="/project/?locale=en"><i class="fa fa-dashboard"></i><strong><spring:message
								code="label.7" /></strong></a></li>
				<li><a href="/project/?locale=ko"><i class="fa fa-dashboard"></i><strong><spring:message
								code="label.8" /></strong></a></li>
          </ol>
        </section>