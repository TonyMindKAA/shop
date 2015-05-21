<%@include file="/WEB-INF/jspf/generaJstl.jspf" %>
<%@include file="/WEB-INF/jspf/registrationTagLib.jspf" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<%@include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>
	<%@include file="/WEB-INF/jspf/header.jspf" %>
	<section id="form">
		<div class="container">
			<div class="row">
				<div class="col-sm-4 col-sm-offset-1">
					<div class="login-form">
					</div>
				</div>
			    <div class="col-sm-4">
					<div class="signup-form">
						<h2>New User Signup!</h2>
						<form action="registration" method="post" enctype="multipart/form-data" id="registrationForm">
							<div class="${registrationFormReport.nameValid? ' ': 'errorClass'}">
								<input type="text" id="name" name="name"  value="${registrationFormReport.registrationFormBean.name}" placeholder="<fmt:message key="form.registration.name" bundle="${bundle}" />"/>
							</div>
							<div class="${registrationFormReport.lastNameValid? ' ': 'errorClass'}">
								<input type="text" id="lastName" value="${registrationFormReport.registrationFormBean.lastName}"   name="lastName" placeholder="<fmt:message key="form.registration.last.name" bundle="${bundle}" />"/>
							</div>
							<div class="${registrationFormReport.emailAddressValid? ' ': 'errorClass'}">
								<input type="email" id="emailAddress" value="${registrationFormReport.registrationFormBean.emailAddress}"  name="emailAddress" placeholder="<fmt:message key="form.registration.email" bundle="${bundle}" />"/>
							</div>
							<div class="${registrationFormReport.confirmEmailAddressValid? ' ': 'errorClass'}">
								<input type="email" id="confirmEmailAddress" value="${registrationFormReport.registrationFormBean.confirmEmailAddress}"  name="confirmEmailAddress" placeholder="<fmt:message key="form.registration.email.confirm" bundle="${bundle}" />"/>
							</div>
							<div class="${registrationFormReport.passwordValid? ' ': 'errorClass'}">
								<input type="password" id ="password"  name="password" placeholder="<fmt:message key="form.registration.password" bundle="${bundle}" />"/>
							</div>
							<div class="${registrationFormReport.confirmPasswordValid? ' ': 'errorClass'}">
								<input type="password" id="confirmPassword"  name ="confirmPassword" placeholder="<fmt:message key="form.registration.password.confirm" bundle="${bundle}" />"/>
							</div >
							<div class="${registrationFormReport.phoneValid? ' ': 'errorClass'}">
								<input type="text" id="phone" name="phone" value="${registrationFormReport.registrationFormBean.phone}"  placeholder="<fmt:message key="form.registration.password.phone" bundle="${bundle}" />"/>
							</div>						
							<reg:capthcaTags />
							<input type="file" name="avatar" name="uploadFile" />
							<button type="submit" class="btn btn-default" id="registratinSubmit">Signup</button>
						</form>
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