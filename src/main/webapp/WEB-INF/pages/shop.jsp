<%@include file="/WEB-INF/jspf/generaJstl.jspf" %>
<%@include file="/WEB-INF/jspf/shopTagLib.jspf" %>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="/WEB-INF/jspf/head.jspf" %>
</head>
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
        <h2 class="title text-center">Features Items(<c:out value='${productNumber}'/>)</h2>
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
<script type="text/javascript">
    $(document).ready(function () {
        turnOnOrderPaginationHandling();
        $(".item-form-product-submit").on( "click", function(e){
            e.preventDefault();
            var productId = $(this).prev().val();
            var json = "{ id: \"+productId+\" }";

           $.ajax({
             dataType: 'JSON',
             method: "POST",
             url: "card",
             data: json,
             data:{
                id:productId
             },
             success: function(cardInfo){
                 if(cardInfo != null){
                    $("#car-info-state")
                            .html(
                            "<i class=\"fa fa-shopping-cart\"></i><b> Cart("+cardInfo.productsNumber+") total cost: "+ cardInfo.totalCost+"</b>");
                     $( "#car-info-state" ).focus();
                 }else{
                     $("#car-info-state")
                             .html(
                             "<i class=\"fa fa-shopping-cart\"></i><b>Cart(0) total cost: 0 uah</b>");
                 }

                console.log(cardInfo);
             },
             error: function(msg){
                console.log("error");
             }
           })
        });
    });
</script>
</body>
</html>