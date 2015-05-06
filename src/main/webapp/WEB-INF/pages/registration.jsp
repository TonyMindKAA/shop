<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="reg" uri="http://koraytugay.com/capthcaTags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Login | E-Shopper</title>
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="resources/css/font-awesome.min.css" rel="stylesheet">
    <link href="resources/css/prettyPhoto.css" rel="stylesheet">
    <link href="resources/css/price-range.css" rel="stylesheet">
    <link href="resources/css/animate.css" rel="stylesheet">
	<link href="resources/css/main.css" rel="stylesheet">
	<link href="resources/css/responsive.css" rel="stylesheet">
	<link href="resources/css/error.css" rel="stylesheet">
    <link rel="shortcut icon" href="resources/images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="resources/images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="resources/images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="resources/images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="resources/images/ico/apple-touch-icon-57-precomposed.png">
     <script src=" http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.js"></script>
    <script src="resources/js/jquery-validator/validator.js"></script>
    <script type="text/javascript">
	    $( document ).ready(function() {
	    	$( "#registratinSubmit" ).click( function(e){ 
	    			RegistrationFormController(e)
	    	});
	    });
    </script> 
</head><!--/head-->
<body>
	<%@include file="/WEB-INF/jspf/header.jspf" %>
	<section id="form"><!--form-->
		<div class="container">
			<div class="row">
				<div class="col-sm-4 col-sm-offset-1">
					<div class="login-form"><!--login form-->
						<h2>Login to your account</h2>
						<form action="#">
							<input type="text" placeholder="Name" />
							<input type="email" placeholder="Email Address" />
							<span>
								<input type="checkbox" class="checkbox"> 
								Keep me signed in
							</span>
							<button type="submit" class="btn btn-default">Login</button>
						</form>
					</div><!--/login form-->
				</div>
				<div class="col-sm-1">
					<h2 class="or">OR</h2>
				</div>
			<div class="col-sm-4">
					<div class="signup-form"><!--sign up form-->
						<h2>New User Signup!</h2>
						<form action="registration" method="post" id="registrationForm">
							<div class="${registrationFormReport.nameValid? ' ': 'errorClass'}">
								<input type="text" id="name" name="name"  value="${registrationFormReport.registrationFormBean.name}" placeholder="Name"/>
							</div>
							<div class="${registrationFormReport.lastNameValid? ' ': 'errorClass'}">
								<input type="text" id="lastName" value="${registrationFormReport.registrationFormBean.lastName}"   name="lastName" placeholder="Last name"/>
							</div>
							<div class="${registrationFormReport.emailAddressValid? ' ': 'errorClass'}">
								<input type="email" id="emailAddress" value="${registrationFormReport.registrationFormBean.emailAddress}"  name="emailAddress" placeholder="Email Address"/>
							</div>
							<div class="${registrationFormReport.confirmEmailAddressValid? ' ': 'errorClass'}">
								<input type="email" id="confirmEmailAddress" value="${registrationFormReport.registrationFormBean.confirmEmailAddress}"  name="confirmEmailAddress" placeholder="Confirm email Address"/>
							</div>
							<div class="${registrationFormReport.passwordValid? ' ': 'errorClass'}">
								<input type="password" id ="password"  name="password" placeholder="Password"/>
							</div>
							<div class="${registrationFormReport.confirmPasswordValid? ' ': 'errorClass'}">
								<input type="password" id="confirmPassword"  name ="confirmPassword" placeholder="Confirm password"/>
							</div >
							<div class="${registrationFormReport.phoneValid? ' ': 'errorClass'}">
								<input type="text" id="phone" name="phone" value="${registrationFormReport.registrationFormBean.phone}"  placeholder="Phone"/>
							</div>						
							<reg:capthcaTags />
							<button type="submit" class="btn btn-default" id="registratinSubmit">Signup</button>
						</form>
					</div><!--/sign up form-->
				</div>
			</div>
		</div>
	</section><!--/form-->
	<%@include file="/WEB-INF/jspf/header.jspf" %>
    <script src="resources/js/jquery.js"></script>
	<script src="resources/js/price-range.js"></script>
    <script src="resources/js/jquery.scrollUp.min.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
    <script src="resources/js/jquery.prettyPhoto.js"></script>
    <script src="resources/js/main.js"></script>
</body>
</html>