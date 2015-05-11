function turnOnOrderPaginationHandling (){
    $("#result-shop-select").on("click", function () {
        $("#resultHidden").val($(this).val());
    });
    $("#order-shop-main-select-tag").on("click", function () {
        $("#orderHidden").val($(this).val());
    });
    $(".paginationLi").on("click", function (e) {
        changePaginationMarkerOnClick();
        $(this).parent().addClass("active");
        $("#currentPageHidden").val($(this).text());
        /*$("#filterForm").submit();*/
    });
    function changePaginationMarkerOnClick(){
        var length = $( ".pagination" ).children().length;
        var firstChildren = $( ".pagination li" );
        for (var i = 0; i < length; i++) {
            firstChildren.removeClass("active");
            firstChildren = firstChildren.next();
        }
    }
}