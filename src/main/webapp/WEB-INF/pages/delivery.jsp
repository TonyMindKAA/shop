<%@include file="/WEB-INF/jspf/generaJstl.jspf" %>
<%@include file="/WEB-INF/jspf/shopTagLib.jspf" %>
<%@ taglib prefix="cardInfList" uri="http://koraytugay.com/productCardInfoListTag" %>
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
                <li><a href="#">Home</a></li>
                <li class="active">Shopping Cart</li>
            </ol>
        </div>
        <cardInfList:productCardInfoListTag/>
    </div>
</section>
<section id="do_action">
    <div class="container">
        <div class="row">
            <div class="col-sm-12">
                <div class="chose_area">
                    <div class="card-continue">
                        <form>
                            <table>
                                <tr>
                                    <td>Type of delivery:</td>
                                    <td>
                                        <select id="typeDelivery">
                                            <option disabled>Select the type of delivery</option>
                                            <option>Pickup delivery</option>
                                            <option>Courier delivery</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Select type of payment:</td>
                                    <td>
                                        <select id="typeofPayment">
                                            <option disabled>Select the type of payment:</option>
                                            <option>cash</option>
                                            <option>card</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr id="writeBankCardNumber">
                                    <td>Write bank card number:</td>
                                    <td><input type="text" name="bankCardNumber"></td>
                                </tr>
                                <tr id="writeAddress">
                                    <td>Write address:</td>
                                    <td><input type="text" name="clientAddress"></td>
                                </tr>
                                <tr id="selectStore">
                                    <td>Select store:</td>
                                    <td>
                                        <select>
                                            <option> store one</option>
                                            <option> store two</option>
                                            <option> store three</option>
                                        </select>
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<section id="do_action">
    <div class="container">
        <div class="row">
            <div class="col-sm-12">
                <div class="chose_area">
                    <div class="card-continue">
                        <form action="order" method="post">
                            <a class="btn btn-default update" href="card">Prev step</a>
                            <input type="submit" class="btn btn-default check_out" value="Create an order">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>
</section>
<%@include file="/WEB-INF/jspf/fotter.jspf" %>
<%@include file="/WEB-INF/jspf/jsLinks.jspf" %>
<script type="text/javascript">
    $('#typeDelivery').on('change', function () {
        if ($(this).val() === "Pickup delivery") {
            $("#writeAddress").hide();
            $("#selectStore").show();
        }
        if ($(this).val() === "Courier delivery") {
            $("#selectStore").hide();
            $("#writeAddress").show();
        }
    });

    $('#typeofPayment').on('change', function () {
        if ($(this).val() === "cash") {
            $("#writeBankCardNumber").hide();
        }
        if ($(this).val() === "card") {
            $("#writeBankCardNumber").show();
        }
    });
</script>
</body>
</html>