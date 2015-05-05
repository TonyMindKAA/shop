// model
var Email = function(email, confirmedEmail) {
	this.getEmail = email;
	this.getConfirmedEmail = confirmedEmail;
}

var Password = function(password, confirmedPassword) {
	this.getPassword = password;
	this.getConfirmedPassword = confirmedPassword;
}

var RegistrationForm = function(name, lastName, password, email, phone) {
	this.name = name;
	this.lastName = lastName;
	this.password = password;
	this.email = email;
	this.phone = phone;
}

var RegistrationFormValidationReport = function() {
	this.isNameValid = true;
	this.isLastNameValid = true;
	this.isPasswordValid = true;
	this.isEmailValid = true;
	this.isConfirmedEmailValid = true;
	this.isPhoneValid = true;
	this.nameValue = "";
	this.lastNameValue = "";
	this.emailValue = "";
	this.confirmedEmailValue = "";
	this.phoneValue = "";
}

//view
var GetterFormData = function() {
	var name = document.getElementById("name").value;
	var lastName =document.getElementById("lastName").value;
	var emailAddress = document.getElementById("emailAddress").value;
	var confirmEmailAddress = document.getElementById("confirmEmailAddress").value;
	var password = document.getElementById("password").value;
	var confirmPassword =document.getElementById("confirmPassword").value;
	var phone =document.getElementById("phone").value;
	this.registrationForm = new RegistrationForm(name, lastName,new Password(password, confirmPassword), new Email(emailAddress,confirmEmailAddress),phone);
}

var RenderRegistrationFormFails = function (RegistrationFormValidationReport) {
	var report = RegistrationFormValidationReport;
	if(!report.isNameValid){
		setToBorderRedColor("name");
	}else{
		setToBorderGreenColor("name");
	}
	
	if(!report.isLastNameValid){
		setToBorderRedColor("lastName");
	}else{
		setToBorderGreenColor("lastName");
	}
	
	if(!report.isPasswordValid){
		setToBorderRedColor("password");
		setToBorderRedColor("confirmPassword");
	}else{
		setToBorderGreenColor("password");
		setToBorderGreenColor("confirmPassword");
	}
	
	if(!report.isEmailValid){
		setToBorderRedColor("emailAddress");
	}else{
		setToBorderGreenColor("emailAddress");
	}
	
	if(!report.isConfirmedEmailValid){
		setToBorderRedColor("confirmEmailAddress");
	}else{
		setToBorderGreenColor("confirmEmailAddress");
	}
	
	if(!report.isPhoneValid){
		setToBorderRedColor("phone");
	}else{
		setToBorderGreenColor("phone");
	}
}

var RegistrationFormStyleToDefault = function(){
	setToBorderDefaultColor("name");
	setToBorderDefaultColor("lastName");
	setToBorderDefaultColor("password");
	setToBorderDefaultColor("confirmPassword");
	setToBorderDefaultColor("emailAddress");
	setToBorderDefaultColor("confirmEmailAddress");
	setToBorderDefaultColor("phone");
}

var setToBorderRedColor= function(ElementId) {
	document.getElementById(ElementId).style.border= "1px solid #8B0000";
}

var setToBorderGreenColor= function(ElementId) {
	document.getElementById(ElementId).style.border= "1px solid  #ADFF2F";
}

var setToBorderDefaultColor= function(ElementId) {
	document.getElementById(ElementId).style.border= "";
}

//controller
var RegistrationFormController = function(e) {
	var formData = new GetterFormData().registrationForm;
	var validator = new RegistrationFormValidator();
	if(!validator.validate(formData)){
		e.preventDefault();
		var report = new RegistrationFormValidationReportGenerator();
		RenderRegistrationFormFails(report.generate(formData));
		console.log(report.generate(formData));
		console.log("no ok");
		return;
	}
	RegistrationFormStyleToDefault();
	console.log("ok");
}

var RegistrationFormValidationReportGenerator = function() {
	var registrationForm = RegistrationForm;
	var validator = new RegistrationFormValidator();

	this.generate = function(RegistrationForm) {
		report = new RegistrationFormValidationReport();

		report.nameValue = RegistrationForm.name;
		if (!validator.isValidName(RegistrationForm.name)) {
			report.isNameValid = false;
		}

		report.lastNameValue = RegistrationForm.lastName;
		if (!validator.isValidName(RegistrationForm.lastName)) {
			report.isLastNameValid = false;
		}

		if (!validator.isValidPassword(RegistrationForm.password)) {
			report.isPasswordValid = false;
		}

		report.emailValue = RegistrationForm.email.getEmail;
		if (!validator.isValidEmail(RegistrationForm.email)) {
			report.isEmailValid = false;
		}

		report.confirmedEmailValue = RegistrationForm.email.getConfirmedEmail;
		if (!validator.isValidConfirmedEmail(RegistrationForm.email)) {
			report.isConfirmedEmailValid = false;
		}

		
		report.phoneValue = RegistrationForm.phone
		if (!validator.isValidPhone(RegistrationForm.phone)) {
			report.isPhoneValid = false;
		}
		return report;
	}
}

var RegistrationFormValidator = function() {
	this.isValidName = function(Name) {
		return (Name.length > 2 && Name.length < 25);
	}

	this.isValidPassword = function(Password) {
		var password = Password.getPassword;
		var confirmedPassword = Password.getConfirmedPassword;
		return confirmedPassword === password && password.length > 4;
	}

	this.isValidEmail = function(Email) {
		var email = Email.getEmail;
		var reg = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
		return reg.test(email);
	}

	this.isValidConfirmedEmail = function(Email) {
		var email = Email.getEmail;
		var confirmedEmail = Email.getConfirmedEmail;
		return email === confirmedEmail;
	}

	this.isValidPhone = function(Phone) {
		var reg = /^[(]{0,1}[0-9]{3}[)]{0,1}[-\s\.]{0,1}[0-9]{3}[-\s\.]{0,1}[0-9]{4}$/;
		return reg.test(Phone);
	}

	this.validate = function(RegistrationForm) {
		if (!this.isValidName(RegistrationForm.name)) {
			return false;
		}
		if (!this.isValidName(RegistrationForm.lastName)) {
			return false;
		}
		if (!this.isValidPassword(RegistrationForm.password)) {
			return false;
		}
		if (!this.isValidEmail(RegistrationForm.email)) {
			return false;
		}
		if (!this.isValidPhone(RegistrationForm.phone)) {
			return false;
		}
		return true;
	}
}