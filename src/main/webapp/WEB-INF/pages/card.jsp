<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="cardTab" uri="http://koraytugay.com/productCardListTag" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>
<%@include file="/WEB-INF/jspf/header.jspf" %>
<section id="cart_items">
    <div class="container">
        <div class="breadcrumbs">
            <ol class="breadcrumb">
                <li><a href="products">Home</a></li>
                <li class="active">Shopping Cart</li>
            </ol>
        </div>
        <cardTab:productCardListTag/>
    </div>
</section>
<section id="do_action">
    <div class="container">
        <div class="row">
            <div class="col-sm-12">
                <div class="chose_area">
                    <div class="card-continue">
                        <c:choose>
                            <c:when test="${empty card || fn:length(card.all) == 0}">
                                <a class="btn btn-default update" href="products">To products</a>
                            </c:when>
                            <c:otherwise>
                                <a class="btn btn-default update" href="products">To products</a>
                                <a class="btn btn-default check_out" href="delivery">To next step</a>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>
</section>
<%@include file="/WEB-INF/jspf/fotter.jspf" %>
<%@include file="/WEB-INF/jspf/jsLinks.jspf" %>
</body>
</html>