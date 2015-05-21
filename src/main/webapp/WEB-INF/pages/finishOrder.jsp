<%@include file="/WEB-INF/jspf/generaJstl.jspf" %>
<%@ taglib prefix="cardInf" uri="http://koraytugay.com/cardInfoTag"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="/WEB-INF/jspf/head.jspf" %>
</head>
<%@include file="/WEB-INF/jspf/header.jspf" %>
<section id="cart_items">
    <div class="container">
        <div class="breadcrumbs">
            <ol class="breadcrumb">
                <li><a href="products">Home</a></li>
                <li class="active">Order created</li>
            </ol>
        </div>
    </div>
</section>
<section id="do_action">
    <div class="container">
        <div class="row">
            <div class="col-sm-12">
                <div class="chose_area">
                    <div class="card-continue">
                        <div class="orderCreated">
                            <p>Thank you for your purchase.</p>
                            <p>The order are in processing.</p>
                        </div>
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