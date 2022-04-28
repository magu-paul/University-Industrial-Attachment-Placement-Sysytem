<%
if(session.getAttribute("staffno") == null){
	response.sendRedirect("index.html");
}
%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/sql" prefix="sql" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Kisii University</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
<!-- Font Awesome icons (free version)-->
<script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js"
	crossorigin="anonymous"></script>
<!-- Google fonts-->
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" type="text/css" />
<link
	href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic"
	rel="stylesheet" type="text/css" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="css/index-styles.css" rel="stylesheet" />
<link href="pop2.css" rel="stylesheet">
<style>
*{
    font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
}
body{
    background-color: blueviolet;
}
input{
    outline: none;
    border: none;
}
.multi-selector{
    width: max-content;
}
.select-field{
    border: 1px solid rgb(187, 187, 187);
}
.select-field,.task,.list{
    width: 100%;

    padding: 0.3rem;
}
.list{
    box-shadow: 0 30px 60px rgb(0,0,0,0.2);
    display: none;
}
.down-arrow{
    font-size: 1.2rem;
    display: inline-block;
    cursor: pointer;
    transition: 0.2s linear;
}
.task{
    display: block;
    padding-left: 0;
}
.task span{
    float: right;
    font-size: 0.6rem;
    padding-top: 6px;
}
.task:hover{
    background-color: aliceblue;
}
.show{
    display: block;
}
.rotate180{
    transform: rotate(-60deg);
}

</style>
<script type="text/javascript">
function sessionUpdate(){
	var name = document.getElementById('session_name').value;
	var dip = document.getElementById('dip').value;
	var deg = document.getElementById('deg').value;
	
	if(name == ""){
		alert("Empty Session Period");
		return false;
	}else if(dip == ""){
		alert("Empty Diploma Year");
		return false;
	}else if(deg == ""){
		alert("Empty Diploma Year");
		return false;
	}else if(deg.length > 2 || dip.length > 2){
		alert("Only Last Two digits of the year");
		return false;
	}else if(deg.length < 2 || dip.length < 2){
		alert("Two last digits of the year are required!");
		return false;
	}else{
		return true;
	}
}
</script>
</head>
<body id="page-top">
	<!-- Navigation-->
	<nav
		class="navbar navbar-expand-lg bg-secondary text-uppercase fixed-top"
		id="mainNav">
		<div class="container">
			<a class="navbar-brand" href="#page-top">Site Administrator</a>
			<button
				class="navbar-toggler text-uppercase font-weight-bold bg-primary text-white rounded"
				type="button" data-bs-toggle="collapse"
				data-bs-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				Menu <i class="fas fa-bars"></i>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ms-auto">
					
					<li class="nav-item mx-0 mx-lg-1"><a
						class="nav-link py-3 px-0 px-lg-3 rounded" href="Admin_home.jsp">Home</a></li>
					<li class="nav-item mx-0 mx-lg-1"><a
						class="nav-link py-3 px-0 px-lg-3 rounded" href="students.jsp"></a></li>
					<li class="nav-item mx-0 mx-lg-1"><a
						class="nav-link py-3 px-0 px-lg-3 rounded" href="lecturers.jsp">Lecturers</a></li>
					
					<li class="nav-item mx-0 mx-lg-1"><a
						class="nav-link py-3 px-0 px-lg-3 rounded" href="session.jsp">Control Panel</a></li>
					<li class="nav-item mx-0 mx-lg-1"><a
						class="nav-link py-3 px-0 px-lg-3 rounded" href="logout">Logout</a></li>
					
				</ul>
			</div>
		</div>
	</nav>
	
	<div class="masthead bg-primary text-white " style="padding:100px;">
	
<h3>Set A New Session</p>

<form onsubmit="return sessionUpdate()" action="schedule" method="post">
<label>Session Period</label>
<input name="date" type="text" placeholder="Session" id="session_name">
<br>




<div class="multi-selector">

     <div class="select-field">
<label>School : </label><input type="text" name="" placeholder="Select Schools" id="" class="input-selector">
     <span class="down-arrow">&blacktriangledown;</span>
     </div>
<!---------List of checkboxes and options----------->
     <div class="list">
    <sql:setDataSource var="db" driver="com.mysql.cj.jdbc.Driver" url="jdbc:mysql://localhost:3306/attachment" user="root" password="bmpg1998" />

<sql:query var="rs" dataSource="${db}">select * from school</sql:query>
<c:forEach items="${rs.rows}" var="row">
<label for="task1" class="task">
            <input type="checkbox" name="schools" value="<c:out value="${row.id}"></c:out>"> >
            <c:out value="${row.name}"></c:out> 
            <span><c:out value="${row.id}"></c:out> </span>
     </label>

 </c:forEach>
       
      
      
     </div>


    </div>


    <script>
    

     document.querySelector('.select-field').addEventListener('click',()=>{
         document.querySelector('.list').classList.toggle('show');
         document.querySelector('.down-arrow').classList.toggle('rotate180');

     });

    </script>


<br><br>
<label>Diploma : </label><input type="text" name="dip" placeholder="year" style="width:60px" id="dip">
<label>Degree : </label><input type="text" name="deg" placeholder="year" style="width:60px" id="deg">
<br><br>
<button type="submit">Schedule</button>
</form>

<br>
<hr>

<button id="assign" >Assign Lecturers</button>
<br>
<form action="genLetter" method="post">
<button type="submit">Generate Letter</button>
</form>
<br>
<div class="popup">
        <div class="popup-content">
            <img  src="close.png" alt="user" class ="close">
            
            <form action="assignLecturers" method="post">
            <input type="text" placeholder="School ID" name="school"/>
            <input type="text" placeholder="Department ID" name="dep"/>
            
            <button type ="submit">Assign</button>
            </form>
        </div>
    </div>
    <script>
       document.getElementById("assign").addEventListener("click", function(){
           document.querySelector(".popup").style.display = "flex";
       })
       document.querySelector(".close").addEventListener("click",function(){
           document.querySelector(".popup").style.display="none";
       })
    </script>
<br>
<form action="deleteGroup" method="post">
<button type="submit" > Delete Current Group</button>
</form>
	</div>
	
	
	
	<!-- Footer-->
	<footer class="footer text-center">
		<div class="container">
			<div class="row">
				<!-- Footer Location-->
				<div class="col-lg-4 mb-5 mb-lg-0">
					<h4 class="text-uppercase mb-4">Location</h4>
					<p class="lead mb-0">
						Kisii, Kenya 
					</p>
				</div>
				<!-- Footer Social Icons-->
				<div class="col-lg-4 mb-5 mb-lg-0">
					<h4 class="text-uppercase mb-4">Around the Web</h4>
					<a class="btn btn-outline-light btn-social mx-1" href="#!"><i
						class="fab fa-fw fa-facebook-f"></i></a> <a
						class="btn btn-outline-light btn-social mx-1" href="#!"><i
						class="fab fa-fw fa-twitter"></i></a> <a
						class="btn btn-outline-light btn-social mx-1" href="#!"><i
						class="fab fa-fw fa-linkedin-in"></i></a> <a
						class="btn btn-outline-light btn-social mx-1" href="#!"><i
						class="fab fa-fw fa-dribbble"></i></a>
				</div>
				<!-- Footer About Text-->
				<div class="col-lg-4">
					<h4 class="text-uppercase mb-4">About Kisii University</h4>
					<p class="lead mb-0">
						University of the 21<sup>st</sup> Century .
					</p>
				</div>
			</div>
		</div>
	</footer>
	<!-- Copyright Section-->
	<div class="copyright py-4 text-center text-white">
		<div class="container">
			<small>Copyright &copy; Paul Magu 2022</small>
		</div>
	</div>
	
</body>
</html>

</body>
</html>