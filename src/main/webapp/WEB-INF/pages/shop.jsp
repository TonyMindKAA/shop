<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="reg" uri="http://koraytugay.com/paginationTags" %>
<%@ taglib prefix="sel" uri="http://koraytugay.com/ItemNumbersOrderTag"%>
<%@ taglib prefix="chb" uri="http://koraytugay.com/productTypeTag"%>
<%@ taglib prefix="chbm" uri="http://koraytugay.com/manufactureTag"%>
<%@ taglib prefix="cardInf" uri="http://koraytugay.com/cardInfoTag"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="shop"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Shop | E-Shopper</title>
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="resources/css/font-awesome.min.css" rel="stylesheet">
    <link href="resources/css/prettyPhoto.css" rel="stylesheet">
    <link href="resources/css/price-range.css" rel="stylesheet">
    <link href="resources/css/animate.css" rel="stylesheet">
    <link href="resources/css/main.css" rel="stylesheet">
    <link href="resources/css/responsive.css" rel="stylesheet">
    <link href="resources/css/main-page.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
    <link rel="shortcut icon" href="resources/images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144"
          href="resources/images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114"
          href="resources/images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72"
          href="resources/images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="resources/images/ico/apple-touch-icon-57-precomposed.png">
</head>
<!--/head-->
<body>
<shop:locale />
<%@include file="/WEB-INF/jspf/header.jspf" %>
<section>
    <form method="get" action="products" id="filterForm">
        <div class="container">
            <div class="row">
                <div class="col-sm-3">
                    <div class="left-sidebar">
                        <div class="price-range"><!-- find-->
                            <h2>Title</h2>

                            <div class="well">
                                <div class="price-main-page">
                                    <input type="text" name="title" value="<c:out value=" ${productFormBean.title}" />"
                                    size="20">
                                </div>
                            </div>
                        </div>
                        <!--/price-range-->
                        <div class="price-range"><!-- find-->
                            <h2>Price Range</h2>

                            <div class="well">
                                <div class="price-main-page">
                                    <input type="text" value="<c:out value=" ${productFormBean.priceFrom}"/>"
                                    name="priceFrom" size="6">
                                    to
                                    <input type="text" value="<c:out value=" ${productFormBean.priceTo}"/>"
                                    name="priceTo" size="6">
                                </div>
                            </div>
                        </div>
                        <chb:productTypeTag/>
                        <chbm:manufactureTag/>
                        <div class="price-range"><!--submit-button-->
                            <h2>Filtrate</h2>
                            <div class="well">
                                <div class="price-main-page">
                                    <input type="hidden" id="orderHidden" name="order" value="${productFormBean.order}">
                                    <input type="hidden" id="resultHidden" name="numberItems"
                                           value="${productFormBean.numberItems}">
                                    <input type="hidden" id="currentPageHidden" name="currentPage"
                                           value="${productFormBean.currentPage}">
                                    <input type="submit" id="submit-button-main-page" class="btn-default add-to-cart"
                                           value="filtrate">
                                </div>
                            </div>
                        </div>
                        <!--submit-button-->
                    </div>
                </div>
    </form>
    <div class="col-sm-9 padding-right">
        <br class="features_items"><!--features_items-->
        <h2 class="title text-center">Features Items(
            <c:out value='${productNumber}'/>
            )
        </h2>
        <c:forEach items="${products}" var="product">
            <div class="col-sm-4">
                <div class="product-image-wrapper">
                    <div class="single-products">
                        <div class="productinfo text-center">
                            <img src="resources/products/<c:out value='${product.img}'/>" alt=""/>

                            <h2>
                                <c:out value="${product.price}"/>
                            </h2>
                            <p>
                                <c:out value="${product.name}"/>
                            </p>
                            <form class="item-form-product">
                                <input type="hidden" name="productId" value="${product.id}">
                                <button class="btn btn-default add-to-cart item-form-product-submit">
                                    <i class="fa fa-shopping-cart"></i>
                                    Add to cart
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <div id="pagination-footer">
        <reg:paginationTags/>
    </div>
</section>
<%@include file="/WEB-INF/jspf/fotter.jspf" %>
<%@include file="/WEB-INF/jspf/jsLinks.jspf" %>
</body>
</html>