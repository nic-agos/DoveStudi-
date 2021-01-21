<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Account</title>
<link href="css/myReviews.css" rel="stylesheet"/>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
</head>


<body>	
	<div class="curved">
		<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1440 319"><path fill="#FF5500" fill-opacity="1" d="M0,64L48,96C96,128,192,192,288,224C384,256,480,256,576,245.3C672,235,768,213,864,181.3C960,149,1056,107,1152,106.7C1248,107,1344,149,1392,170.7L1440,192L1440,320L1392,320C1344,320,1248,320,1152,320C1056,320,960,320,864,320C768,320,672,320,576,320C480,320,384,320,288,320C192,320,96,320,48,320L0,320Z"></path></svg>
	</div>
	<div id="sidebar">
		<div id="rectangle" >
			<div class="toggle-btn" onclick="toggleSideBar(); togglePage();toggleTopBar();">
				<span></span>
				<span></span>
				<span></span>
			</div>
		</div>
		 <ul>
		 	<li style="background:#FF5500; color:#ffffff"><a style="font-size:20px; font-weight:bold; background-color:#FF5500;">#DoveStudi</a></li>
		 	<li><a href="SearchRoom.jsp">Search for Rooms</a></li>
		 	<li><a href="AccountPublicInfo.jsp">My Account</a></li>
		 	<li><a href="MyGroups.jsp">My Groups</a></li>
		 	<li><a href="AccountMyFutReservations.jsp">My Reservations</a></li>
		 	<li><a href="AccountMyReviews.jsp">My Reviews</a></li>
		 	<li><a href="AccountMyRooms.jsp">My Rooms</a></li>
		 	<li><a href="PostRoom.jsp">Post a Room</a></li>
		 	<li><a href="index.jsp">Log out</a></li>
		 </ul>
	</div>
	<div id="sidebar">
		<div id="rectangle" >
			<div class="toggle-btn" onclick="toggleSideBar(); togglePage();toggleTopBar();">
				<span></span>
				<span></span>
				<span></span>
			</div>
		</div>
		 <ul>
		 	<li style="background:#FF5500; color:#ffffff"><a style="font-size:20px; font-weight:bold; background-color:#FF5500;">#DoveStudi</a></li>
		 	<li><a href="SearchRoom.jsp">Search for Rooms</a></li>
		 	<li><a href="AccountPublicInfo.jsp">My Account</a></li>
		 	<li><a href="MyGroups.jsp">My Groups</a></li>
		 	<li><a href="AccountMyFutReservations.jsp">My Reservations</a></li>
		 	<li><a href="AccountMyReviews.jsp">My Reviews</a></li>
		 	<li><a href="AccountMyRooms.jsp">My Rooms</a></li>
		 	<li><a href="PostRoom.jsp">Post a Room</a></li>
		 	<li><a href="index.jsp">Log out</a></li>
		 </ul>
	</div>
	<div class="container" style="text-align:center; margin-top:20px; font-weight:600;">
  		<div class="vertical-center">
    		<h1 style="font-weight:600;">My Reservations</h1>
  		</div>
	</div>
		
	<div class= "container head-profile">
		
		
		<div class="col-md-18" style="margin-top:80px;" >
		<div class="row">
		
			<div class="card">
				<div class="card-header">Done reviews</div>
					<div class="card">
  					<div class="card-body">
						<div class="row">
                        	<div class="col-md-4">
                              	<label>Reviewed user:</label>
                            </div>
                            <div class="col-md-3">
                        		<p>Luca456</p>
                            </div>
                            <div class="col-md-2">
                              	<label>Date:</label>
                            </div>
                            <div class="col-md-3">
                        		<p>30-06-2020</p>
                            </div>
                        </div>
                        <div class="row">

                            <div class="col-md-12">
                        		<p>4/5 Fantasyivdkbn�ekfnbjearnb�erjknrb�enbk�
                        		erkelnlknklbtrb�kltr�
                        		kbn�ktrnb�lktrq
                        		bkjntrbltkrnblktr
                        		fbnlkltrblkrtnwbtrh</p>
                            </div>
                        </div>
                 	</div></div>
                 </div>
                 
                 <div class="card">
				<div class="card-header">Received reviews</div>
				<div class="card">
  				<div class="card-body">
						<div class="row">
                        	<div class="col-md-4">
                              	<label>Reviewing user:</label>
                            </div>
                            <div class="col-md-3">
                        		<p>Mario123</p>
                            </div>
                            <div class="col-md-2">
                              	<label>Date:</label>
                            </div>
                            <div class="col-md-3">
                        		<p>23-06-2020</p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                        		<p>4/5 Fantasyivdkbn�ekfnbjearnb�erjknrb�enbk�</p>
                            </div>
                        </div>
                 </div></div>
                 </div>
                 
			</div>
			</div></div>	
	
	<script>function toggleSideBar(){
				document.getElementById("sidebar").classList.toggle("active");
			}	
	</script>
</body>
</html>