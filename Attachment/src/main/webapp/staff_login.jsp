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
<link href="pop.css" rel="stylesheet">
<script type="text/javascript">
function validateLogin(){
	var reg = document.getElementById('staffno');
	var pass = document.getElementById('pass');
	if(reg.value.trim() == "" || pass.value.trim() == ""){
		alert("Blank fields not allowed");
		return false;
	}else{
		return true;
	}
	
}

function forgotValidation(){
	var regno = document.getElementById("staff_id");
	var phone = document.getElementById('phone');
	var newPass = document.getElementById('newPass');
	
	if(regno.value.trim() == "" || phone.value.trim() == ""){
		
		alert("Empty field not allowed!");
		return false;
	}
	else if(newPass.value.trim().length < 4){
		alert("Weak Passwords are not accepted!");	
		return false;
	}
		else{
	
		return true;
	}
}
</script>
</head>
<body>
<input type="hidden" id="status" value="${status}">

	<div class="main">

		<!-- Sing in  Form -->
		<section class="sign-in">
			<div class="container">
				<div class="signin-content">
					<div class="signin-image">
						<figure>
							<img src="images/signin-image.jpg" alt="sing up image">
						</figure>
						<a href="staff_registration.jsp" class="signup-image-link">Create an
							account</a>
					</div>

					<div class="signin-form">
						<h2 class="form-title">Sign in</h2>
						<form onsubmit="return validateLogin()" method="post" action="staffLog" class="register-form"
							id="login-form">
							<div class="form-group">
								<label for="username"><i
									class="zmdi zmdi-account material-icons-name"></i></label> <input
									type="text" name="staffno" id="staffno"
									placeholder="Staff NO" />
							</div>
							<div class="form-group">
								<label for="password"><i class="zmdi zmdi-lock"></i></label> <input
									type="password" name="pass" id="pass"
									placeholder="Password" />
							</div>
							
							<div class="form-group form-button">
								<input type="submit" name="signin" id="signin"
									class="form-submit" value="Log in" />
							</div>
						</form>
						<div class="social-login">
							<span class="social-label"><a href="#" id="button">Forgot Password?</a></span>
							
						</div>
					</div>
				</div>
			</div>
		</section>
<div class="popup">
        <div class="popup-content">
            <img  src="close.png" alt="user" class ="close">
            <h4>Update Password</h4>
            <form onsubmit="return forgotValidation()" action="updatePassStaff" method="post">
            <input type="text" placeholder="Staff No" name="staffno" id="staff_id"/>
            <input type="phone" placeholder="Phone No" name="phone" id="phone">
            <input type="password" placeholder="New password" name="pass" id="newPass"/>
            
            <button type ="submit">Update</button>
            </form>
        </div>
    </div>
    <script>
       document.getElementById("button").addEventListener("click", function(){
           document.querySelector(".popup").style.display = "flex";
       })
       document.querySelector(".close").addEventListener("click",function(){
           document.querySelector(".popup").style.display="none";
       })
    </script>
	</div>

	<!-- JS -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="js/main.js"></script>
	<script src="https://unpkg.com/sweetalert.min.js"></script>
    <link rel="stylesheet" href="alert/dist/sweetalert.css">
	<script type="text/javascript">
	
</body>
<!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>