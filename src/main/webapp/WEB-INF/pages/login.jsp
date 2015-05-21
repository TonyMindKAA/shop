<%@include file="/WEB-INF/jspf/generaJstl.jspf" %>
<%@ taglib prefix="reg" uri="http://koraytugay.com/loginTags" %>
<%@ taglib prefix="cardInf" uri="http://koraytugay.com/cardInfoTag"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<%@include file="/WEB-INF/jspf/head.jspf" %>
	<link href="resources/css/custom.css" rel="stylesheet">
</head>
<body>
	<%@include file="/WEB-INF/jspf/header.jspf" %>
	<section id="form">
		<div class="container">
			<div class="row">
				<div class="col-sm-4 col-sm-offset-1">
					<div class="login-form">
						<reg:loginTags />
					</div>
				</div>
			</div>
		</div>
	</section>
	<%@include file="/WEB-INF/jspf/fotter.jspf" %>
	<%@include file="/WEB-INF/jspf/jsLinks.jspf" %>
	<script src=" http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.js"></script>
	<script src="resources/js/jquery-validator/validator.js"></script>
	<script type="text/javascript">
	    $( document ).ready(function() {
	    	$( "#registratinSubmit" ).click( function(e){
	    			RegistrationFormController(e)
	    	});
	    });
    </script>
</body>
</html>