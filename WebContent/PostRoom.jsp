<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="logic.bean.*" %>
<%@ page import="logic.model.dao.*" %>
<%@ page import="java.sql.SQLException"%>
<%@ page import="logic.exception.DatabaseException"%>
<%@ page import="logic.controller.RoomController" %>
<%@ page import="logic.exception.AccountException" %>
<%@ page import="logic.exception.PersonException" %>

<jsp:useBean id="roomBean" scope="request" class="logic.bean.RoomBean"/>
<jsp:useBean id="roomSpecBean" scope="request" class="logic.bean.RoomSpecBean"/>
<jsp:setProperty name="roomBean" property="*"/>
<jsp:setProperty name="roomSpecBean" property="*"/>

<%
	boolean res;
	if (request.getParameter("confirmBtn")!= null) {
		
		System.out.println("entrato");
		System.out.println(roomBean.getName());
		System.out.println(roomBean.getNumPartecipants());
		System.out.println(roomBean.getSpecification());
		System.out.println(roomBean.getAddress());
		RoomController rContr = RoomController.getInstance();
		
		try {
			res = rContr.postRoom(roomBean, roomSpecBean);
			if(res) {
				
				String redirectURL = "http://localhost:8080/DoveStudi.git/AccountMyRooms.jsp";
				response.sendRedirect(redirectURL);	
				
			}else{
				System.out.println("errore");
			}
		
		}catch(DatabaseException de){
			de.printStackTrace();
		}
		
	}
	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Post a Room</title>
<link href="css/btn1.css" rel="stylesheet"/>
<link href="css/postRoom.css" rel="stylesheet"/>
<link href="css/sidebar.css" rel="stylesheet" />
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>
<body>
	
	<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1440 319"><path fill="#FF5500" fill-opacity="1" d="M0,64L48,96C96,128,192,192,288,224C384,256,480,256,576,245.3C672,235,768,213,864,181.3C960,149,1056,107,1152,106.7C1248,107,1344,149,1392,170.7L1440,192L1440,320L1392,320C1344,320,1248,320,1152,320C1056,320,960,320,864,320C768,320,672,320,576,320C480,320,384,320,288,320C192,320,96,320,48,320L0,320Z"></path></svg>
	
	<div id="sidebar">
		<div id="rectangle" >
			<div class="toggle-btn" onclick="toggleSideBar();">
				<span></span>
				<span></span>
				<span></span>
			</div>
		</div>
		 <ul>
		 	<li style="background:#000000; color:#000000"><a style="font-size:22px;  background-color:#000000;">#DoveStudi</a></li>
		 	<li><a href="SearchRoomsHost.jsp">Search for Rooms</a></li>
		 	<li><a href="AccPubInfo.jsp">My Account</a></li>
		 	<li><a href="MyGroups.jsp">My Groups</a></li>
		 	<li><a href="AccountMyFutReservations.jsp">My Reservations</a></li>
		 	<li><a href="AccountMyReviews.jsp">My Reviews</a></li>
		 	<li><a href="AccountMyRooms.jsp">My Rooms</a></li>
		 	<li><a href="PostRoom.jsp">Post a Room</a></li>
		 	<li><a href="index.jsp">Log out</a></li>
		 </ul>
	</div>

		<h1 style="font-family: sans-serif; font-weight:600;">Post a Room</h1>
		<div class="card" id="card">
			<form>
				<div class="row">
					<div class="col -md-8">
		  				<div class="form-group">
		    				<label for="adName">Name</label>
		    				<input type="text" class="form-control" id="name" name="name">
		  				</div>
		  			</div>
		  			<div class="col -md-8">
		  				<div class="form-group">
		    				<label for="roomAddress">Address</label>
		    				<input type="text" class="form-control" id="address" name="address">
		  				</div>
	  				</div>
	  			</div>
	  			<div class="row">
	  				<div class="col -md-8">
		  				<div class="form-group">
		    				<label for="maxSeats">Date</label>
		    				<input type="date" id="date" name="date" placeholder="Birth date" value="" required><!-- style="width:100%; font-size:16px; padding: 8px 0; margin: 8px 0; border-bottom: 1px solid #000000;" -->
						</div>
					</div>
					<div class="col -md-12">	
		  				<div class="form-group">
		    				<label for="maxSeats">CAP</label>
		    				<input type="text" class="form-control" id="cap" name="cap">
		  				</div>
		  			</div>	
		  			<div class="col -md-4">	
		  				<div class="form-group">
		    				<label for="maxSeats">Max seats</label>
		    				<input type="text" class="form-control" id="numPartecipants" name="numPartecipants">
		  				</div>
		  			</div>
	  			</div>
		  				<div class="form-group">
		    				<label for="roomDescription">Description</label>
		    				<textarea class="form-control" id="description" name="description" rows="3" style="max-height:100px;"></textarea>
		  				</div>
		  		<div class="row">
		  			<div class="col -md-8">	
		  				<div class="form-group">
		    				<label for="maxSeats">Start time</label>
		    				<input type="text" class="form-control" id="startTime" name="startTime">
		  				</div>
		  			</div>
		  			<div class="col -md-8">	
		  				<div class="form-group">
		    				<label for="maxSeats">End time</label>
		    				<input type="text" class="form-control" id="endTime" name="endTime">
		  				</div>
		  			</div>
		  		</div>
			
			<div class="container" style="text-align:center; margin-top:480px; margin-bottom:60px;">
  				<div class="vertical-center">
    				<button type="button" id="postBtn" name = "postBtn" class="btn btn-outline-warning"data-toggle="modal" data-target="#postRoomModal"><a>Post Room</a></button>
  				</div>
			</div>
		</div>
		
		<!-- Modal -->
	<div class="modal fade" id="postRoomModal" tabindex="-1" role="dialog" aria-labelledby="postRoomModal" aria-hidden="true">
	  <div class="modal-dialog modal-dialog-centered" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLongTitle">Post Room</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        Are you sure you want to post this Room?
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
	        <button type="submit" id="confirmBtn" name="confirmBtn" class="btn btn-outline-warning">Yes</a></button>
	      </div>
	    </div>
	  </div>
	</div>
	</form>

		
	
	
	<script>function toggleSideBar(){
				document.getElementById("sidebar").classList.toggle("active");
			}	
	</script>


</body>
</html>