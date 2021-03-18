<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html lang="en">
	<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	
	<style type="text/css">
		
		.jumbotron {
		  position:relative;
		  overflow:hidden;
		  height: 100%;
		}
		
		.jumbotron .container {
		  position:relative;
		  z-index:2;
		  
		  background:rgba(0,0,0,0.4);
		  padding:2rem;
		  border:1px solid rgba(0,0,0,0.1);
		  border-radius:3px;
		}
		
		.jumbotron-background {
		  object-fit:cover;
		  font-family: 'object-fit: cover;';
		  position:absolute;
		  top:0;
		  z-index:1;
		  width:100%;
		  height:100%;
		  opacity:0.5;
		}
		
		img.blur {
			-webkit-filter: blur(4px);
			filter: blur(4px);
		  filter:progid:DXImageTransform.Microsoft.Blur(PixelRadius='4');	
		}	
		
		
		.mellow{
		background-color: #4C3232;
		
		}
	</style>
  <title>Pizzademic PH</title>
  </head>
  <body class="bg-dark">
  	<form action='processpizza.action' method="post">
	<nav class="navbar sticky-top navbar-expand-lg navbar-dark bg-dark">
	<div class="container">
	  <a class="navbar-brand" href="index.jsp">
	  <img src="images/pizzademic-logo.png" width="30" height="30" class="d-inline-block align-top" alt="PZD">
	   Pizzademic PH </a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02" 
	  aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	
	  <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
	    <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
	      <li class="nav-item dropdown  active">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" 
        aria-haspopup="true" aria-expanded="false">
          Products<span class="sr-only">(current)</span>
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <input class="dropdown-item" type="submit" name="flavor" 
          value='Four Cheese'>
          <div class="dropdown-divider"></div>
          <input class="dropdown-item" type="submit" name="flavor" 
          value='Pepperoni'>
          <div class="dropdown-divider"></div>
          <input class="dropdown-item" type="submit" name="flavor" 
          value='Tropicale'>
        </div>
      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="https://www.facebook.com/Pizzademic">
	        Contact Us
	        </a>
	      </li>
	      <form class="form-inline my-2" action='processpizza.action' method='post'>
	      <li class="px-2">
	      	<input class="form-control mr-sm-2" type="text" name='flavor' size='20' 
	      	placeholder="Search Pizza" aria-label="Search">
	      </li>
	      <li>
	      	<button class="btn btn-light btn-outline-light my-2 my-sm-0" type="submit">Search</button>
	      </li>
	    </form>
      </ul>
	  </div>
	  </div>
	 </nav>
	 
	 <div class="jumbotron jumbotron-fluid bg-dark">
		<div class="jumbotron-background">
    		<img src="images/bg-pizza.jpg" class="blur ">
  		</div>
	  <div class="container text-white mb-5">
	    <h1 class="display-4">${pizza.viewFlavor()}</h1>
	    <p class="lead">${pizza.viewDescription()}</p>
	    <span class="h2">Php ${pizza.viewPrice()}0</span><span>&nbsp srp </span><br>
	    
	    <span class="h5">Stock: ${pizza.viewQuantity()}</span> 
	    
	    <hr class="my-4">
	    <form action='processorder.action' method='post'>
  		<p class="lead">Upgrade to <b>${pizza.getUpgrade().viewUpgradeType()}</b> for only Php
  		<input type="text" name="upgradePrice" value="${pizza.getUpgrade().viewUpgradePrice()}" hidden>0 srp</p>
  		<p>${pizza.getUpgrade().viewUpgradeDetails()}</p>
    			<input type="text" name="flavor" value="${pizza.viewFlavor()}" hidden>
    			<input type="text" name="price" value="${pizza.viewPrice()}" hidden><br>
    			<label class="h4" for="quantity">Quantity:</label>
 	 			<input type="number" id="quantity" name="quantity" value="1" min="1" max="10"> 
 	 			<label class="h4" for="upgrade">&nbsp&nbsp No. of Pizza to upgrade:</label>
 	 			<input type="number" id="upgrade" name="upgrade" value="0" min="0" max="10">
 	 			<span disabled><br>Note: The excess number for upgrade will automatically be neglected</span><br>
 	 			<h5><input type="text" readonly class="form-control-plaintext" name="packing" value="${pizza.packing().pack()}" style="color: green;"></h5>
 	 			<!-- dito kinukuha nya rin yung available qty ng pizza max="ognl(?)"  -->
 	 			
    		<div class="lead">
    			<button class="btn btn-primary btn-lg" type="submit">Order ${pizza.viewFlavor()}</button>
  			</div>
    	</form>
  		
	  </div>
	  
	  <div class="jumbotron jumbotron-fluid bg-dark">
    		
  		</div>
	  
	  </div>
	</form>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  
</body>
</html>