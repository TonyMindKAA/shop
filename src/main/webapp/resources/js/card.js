        $(".cart_quantity_delete").on( "click", function(e){
            e.preventDefault();
            var resultTotalPrice = parseInt($(this).parent().prev().children().html().split(" ")[0]);
            var productNumbers = parseInt($(this).parent().prev().prev().children().children().next().val());
            var generalResultTotalPrice = parseInt($("#cart_total_price_result").html().split(" ")[0]);
            var generalProductNumbers = parseInt($("#cart_total_price_result").parent().prev().children().children().children().val());

            console.log(productNumbers + " :: productNumbers");
            console.log(resultTotalPrice+" :: resultTotalPrice");


            generalProductNumbers -= productNumbers;
            generalResultTotalPrice -= resultTotalPrice;

            console.log(generalProductNumbers + " :: generalProductNumbers ");
            console.log(generalResultTotalPrice +" :: generalResultTotalPrice ");



            $("#cart_total_price_result").parent().prev().children().children().children().val(generalProductNumbers);
            $("#cart_total_price_result").html(generalResultTotalPrice+" uah");

             var productId = $(this).parent().prev().prev().prev().prev().children().next().html().split(" ")[2];
             console.log(productId);
             $.ajax({
                method : 'post',
                url : 'card/info',
                dataType : 'json',
                data:{ 'id': productId, 'command': 'delete'},
                success : function() {
                    console.log("deleted");
                }
            });
            $(this).parent().parent().remove();
        });

        function updateCardProduct( productId, productsNumber){
             $.ajax({
                method : 'post',
                url : 'card/info',
                dataType : 'json',
                data:{ 'id': productId, 'productsNumber': productsNumber,'command': 'update' },
                success : function() {
                    console.log("product updated");
                }
            });
        }

        $(".cart_quantity_up").on( "click", function(e){
            e.preventDefault();
            var numberProducts = $(this).next().val();
            if(numberProducts < 100){
                $(this).next().val(++numberProducts);
                updateTotalPrice($(this), numberProducts);
                 var totalPrice = parseInt($(this).parent().parent().prev().text().split(" ")[0]);
                addToTotalResult(totalPrice);
                incrementProductTotalNumber();
                var idProduct = $(this).parent().parent().prev().prev().children().next().html().split(" ")[2];
                var productsNumber = $(this).next().val();
                updateCardProduct(idProduct, productsNumber);
            }
        });

        $(".cart_quantity_down").on( "click", function(e){
            e.preventDefault();
            var numberProducts = $(this).prev().val();
            if(numberProducts > 1){
                $(this).prev().val(--numberProducts);
                updateTotalPrice($(this), numberProducts);
                var totalPrice = parseInt($(this).parent().parent().prev().text().split(" ")[0]);
                minusFromTotalResult(totalPrice);
                decrementProductTotalNumber();
                var idProduct = $(this).parent().parent().prev().prev().children().next().html().split(" ")[2];
                var productsNumber = $(".cart_quantity_down").prev().val();
                updateCardProduct(idProduct, productsNumber);
            }
        });

        function updateTotalPrice(referenceOnSelf, numberProducts){
                var resultPriceText = referenceOnSelf.parent().parent().prev().text();
                var defaultPriceA = referenceOnSelf.parent().parent().prev().prev();
                var totalPrice = parseInt(resultPriceText.split(" ")[0]);
                var newResultPrice = parseInt(numberProducts) * totalPrice;
                var resultNumber = newResultPrice + " uah";
                referenceOnSelf.parent().parent().next().children().first().text(resultNumber);
        }

        function addToTotalResult(money){
                var totalPriceResult = parseInt($("#cart_total_price_result").text().split(" ")[0]);
                totalPriceResult = totalPriceResult +  money;
                $("#cart_total_price_result").text(totalPriceResult+" uah");
        }

        function minusFromTotalResult(money){
                var totalPriceResult = parseInt($("#cart_total_price_result").text().split(" ")[0]);
                totalPriceResult = totalPriceResult -  money;
                $("#cart_total_price_result").text(totalPriceResult+" uah");
        }

        function incrementProductTotalNumber(){
            cart_quantity_input_result
            var totalPriceResult = parseInt($("#cart_quantity_input_result").val());
            $("#cart_quantity_input_result").val(++totalPriceResult);
        }

        function decrementProductTotalNumber(){
            cart_quantity_input_result
            var totalPriceResult = parseInt($("#cart_quantity_input_result").val());
            $("#cart_quantity_input_result").val(--totalPriceResult);
        }

