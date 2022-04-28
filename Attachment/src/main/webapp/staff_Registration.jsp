<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Kisii University</title>

<!-- Font Icon -->
<link rel="stylesheet"
	href="fonts/material-icon/css/material-design-iconic-font.min.css">

<!-- Main css -->
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript">
function validateRegister(){
	var staffno = document.getElementById('staffno').value.trim();
	var contact = document.getElementById('contact').value.trim();
	var pass = document.getElementById('pass').value.trim();
	var re_pass = document.getElementById('repeat_pass').value.trim();
	
	if(staffno == ""){
		alert("Staff Number cannot be blank!");
		return false;
	}else if(contact == ""){
		alert("Staff Contact cannot be blank!");
	return false;
	}else if(pass == "" || re_pass == ""){
		alert("Staff Password cannot be blank!");
		return false;
	}else if(pass.length < 5){
		alert("Password too short and week!");
		return false;
	}else if(pass != re_pass){
		alert("Passwords do not match!");
		return false;
	}else{
		return true;
	}
	
}
</script>
</head>
<body>
<input type="hidden" id="status" value="${status}">
	<div class="main">

		<!-- Sign up form -->
		<section class="signup">
			<div class="container">
				<div class="signup-content">
					<div class="signup-form">
						<h2 class="form-title">Sign up</h2>
					
						<form onsubmit="return validateRegister()" method="post" action="registration" class="register-form"
							id="register-form">
							<div class="form-group">
								<label for="name"><i
									class="zmdi zmdi-account material-icons-name"></i></label> <input
									type="text" name="staffno" id="staffno" placeholder="Staff No" />
							</div>
							<div class="form-group">
								<label for="contact"><i class="zmdi zmdi-lock-outline"></i></label>
								<input type="text" name="contact" id="contact"
									placeholder="Contact no" />
							</div>
							<div class="form-group">
								<label for="pass"><i class="zmdi zmdi-lock"></i></label> <input
									type="password" name="pass" id="pass" placeholder="Password" />
							</div>
							<div class="form-group">
								<label for="re-pass"><i class="zmdi zmdi-lock-outline"></i></label>
								<input type="password" name="re_pass" id="repeat_pass"
									placeholder="Repeat your password" />
							</div>
							
							
							<div class="form-group form-button">
								<input type="submit" name="signup" id="signup"
									class="form-submit" value="Register" />
							</div>
						</form>
					</div>
					<div class="signup-image">
						<figure>
							<img src="images/signup-image.jpg" alt="sing up image">
						</figure>
						<a href="staff_login.jsp" class="signup-image-link">I am already
							member</a>
							
					</div>
				</div>
			</div>
		</section>


	</div>
	
    
    
    
</body>
<!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>