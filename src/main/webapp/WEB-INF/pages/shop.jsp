<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                                    <input type="text" name="title" value="<c:out value="${productFormBean.title}" />"
                                           size="20">
                                </div>
                            </div>
                        </div>
                        <!--/price-range-->
                        <div class="price-range"><!-- find-->
                            <h2>Price Range</h2>

                            <div class="well">
                                <div class="price-main-page">
                                    <input type="text" value="<c:out value="${productFormBean.priceFrom}"/>"
                                           name="priceFrom" size="6">
                                    to
                                    <input type="text" value="<c:out value="${productFormBean.priceTo}"/>"
                                           name="priceTo" size="6">
                                </div>
                            </div>
                        </div>
                        <!--/price-range-->
                        <h2>Types</h2>

                        <div class="panel-group category-products" id="accordian"><!--category-productsr-->
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title"><input name="AMBIENT" type="checkbox" <c:out
                                            value="${empty productFormBean.ambientType ? ' ' : 'checked'}"/>> <a
                                            href="#"> AMBIENT </a></h4>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title"><input name="PROTECTED" type="checkbox" <c:out
                                            value="${empty productFormBean.protectedType ? ' ' : 'checked'}"/>><a
                                            href="#"> PROTECTED </a></h4>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title"><input name="CHEAP" type="checkbox" <c:out
                                            value="${empty productFormBean.cheapType ? ' ' : 'checked'}"/>> <a href="#">CHEAP</a>
                                    </h4>
                                </div>
                            </div>
                        </div>
                        <!--/category-productsr-->
                        <div class="brands_products"><!--brands_products-->
                            <h2>Brands</h2>

                            <div class="panel-group category-products" id="accordian"><!--category-productsr-->
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title"><input name="NOKIA" type="checkbox" <c:out
                                                value="${empty productFormBean.nokia ? ' ' : 'checked'}"/>> <a href="#">
                                            NOKIA </a></h4>
                                    </div>
                                </div>
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title"><input name="SIGMA" type="checkbox" <c:out
                                                value="${empty productFormBean.sigma ? ' ' : 'checked'}"/>><a href="#">
                                            SIGMA </a></h4>
                                    </div>
                                </div>
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title"><input name="APPLE" type="checkbox" <c:out
                                                value="${empty productFormBean.apple ? ' ' : 'checked'}"/>> <a href="#">APPLE</a>
                                        </h4>
                                    </div>
                                </div>
                            </div>
                            <!--/category-productsr-->
                        </div>

                        <div class="price-range"><!--submit-button-->
                            <h2>Filtrate</h2>

                            <div class="well">
                                <div class="price-main-page">
                                    <input type="hidden" id="orderHidden" name="order" value="priceLowHight">
                                    <input type="hidden" id="resultHidden" name="numberItems" value="5">
                                    <input type="hidden" id="currentPageHidden" name="currentPage" value="1">
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
        <h2 class="title text-center">Features Items</h2>
        <c:forEach items="${products}" var="product">
            <div class="col-sm-4">
                <div class="product-image-wrapper">
                    <div class="single-products">
                        <div class="productinfo text-center">
                            <img src="resources/products/<c:out value="${product.img}"/>" alt=""/>

                            <h2><c:out value="${product.price}"/></h2>

                            <p><c:out value="${product.name}"/></p>
                            <a href="#" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to
                                cart</a>
                        </div>
                        <div class="product-overlay">
                            <div class="overlay-content">
                                <h2>$56</h2>

                                <p>Easy Polo Black Edition</p>
                                <a href="#" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add
                                    to cart</a>
                            </div>
                        </div>
                    </div>
                    <div class="choose">
                        <ul class="nav nav-pills nav-justified">
                            <li><a href=""><i class="fa fa-plus-square"></i>Add to wishlist</a></li>
                            <li><a href=""><i class="fa fa-plus-square"></i>Add to compare</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <div id="pagination-footer">
        <ul class="pagination">
            <li class="active"><a class="paginationLi">1</a></li>
            <li><a class="paginationLi">2</a></li>
            <li><a class="paginationLi">3</a></li>
            <li><a class="paginationStub">...</a></li>
            <li><a class="paginationLi">27</a></li>
        </ul>
    </div>
</section>
<%@include file="/WEB-INF/jspf/fotter.jspf" %>
<%@include file="/WEB-INF/jspf/jsLinks.jspf" %>
</body>
</html>