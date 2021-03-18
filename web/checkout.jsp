<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		<style>
		
			body  {
			  background-image: url("images/bg-pizz.jpg");
			  
			}
			
			input, select, textarea{
			    color: #39f077;
			}
			
		</style>
		
		<title>Pizzademic PH</title>
	</head>
	
<body >
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
	    	<li class="nav-item">
	       		<a class="nav-link" href="index.jsp">Home</a>
	     	</li>
	  
	    	<li class="nav-item">
	       <a class="nav-link" href="https://www.facebook.com/Pizzademic">
	       Contact Us
	       </a>
	     </li>
	    </ul>
	 </div>
	 </div>
	</nav>
	
	<form action='processpayment.action' method="post">
	<div class="container mt-5 px-5 text-white bg-dark rounded">
    <div class="mb-4 p-5">
        <h2>Confirm order and pay</h2> <span>please make the payment, 
        after that you can enjoy your  pizza.</span>
    </div>
    <div class="row">
        <div class="col-md-8 ">
            <div class="card p-3 bg-dark border border-light">
                <h6 class="text-uppercase">Payment details</h6>
                <div class="inputbox mt-3"> 
                	<input type="text" name="nameOnCard" placeholder="ex: Juan Dela Cruz" pattern="^[A-Za-z]+((\s)?((\'|\-|\.)?([A-Za-z])+))*$"
                		class="form-control" required="required"> <!-- oninvalid="alert('Name on Card empty!\nex: Juan Dela Cruz'); -->
                	<span>Name on card</span> 
                </div>
                <div class="inputbox mt-3"> 
                	<input type="email" name="userEmail" placeholder="ex: customername@example.com" class="form-control" required="required"> 
                	<span>Email</span> 
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="inputbox mt-3 mr-2"> 
                        	<input type="text" name="creditCardNo" pattern="(?<=(\W|^))(?:\d[ -]*){13}(?=(\W|$))|(?<=(\W|^))(?:\d[ -]*){16}(?=(\W|$))"
                        		class="form-control" placeholder="13 or 16 digits only" required="required">
                        	<span>Card Number</span>
                        </div>
                    </div>
                    
                    <div class="col-md-6">
                        <div class="d-flex flex-row">
                            <div class="inputbox mt-3 mr-2"> 
                            	<input type="text" name="expiryDate" pattern="^(0?[3-9]|1[012])[- /.](2?[1-9]|[3-9][0-9])$"
                            		class="form-control" placeholder="eg. 03/21 onwards" oninvalid="alert('Either your provided information or credit card is invalid');" required="required"> 
                            	<span>Expiry</span> 
                            </div>
                            <div class="inputbox mt-3 mr-2"> 
                            	<input type="text" name="cvv" placeholder="3 digits only" 
                            		class="form-control" pattern="^\d\d\d$" required="required">
                            	<span>CVV</span> 
                            </div>
                        </div>
                    </div>
                </div>
                
                <div class="mt-4 mb-4">
                    <h6 class="text-uppercase">Billing Address</h6>
                    <div class="row mt-3">
                        <div class="col-md-6">
                            <div class="inputbox mt-3 mr-2"> 
                            	<input type="text" name="houseStreet" placeholder="ex: 123-A Orense St."
                            		class="form-control" required="required"> 
                            	<span>House Number | Street</span> 
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="inputbox mt-3 mr-2"> 
                            	<input type="text" name="brgy" placeholder="ex: Guadalupe Nuevo"
                            		class="form-control" required="required"> 
                            	<span>Barangay</span> 
                            </div>
                        </div>
                    </div>
                    
                    <div class="row mt-2">
                        <div class="col-md-6">
                            <div class="inputbox mt-3 mr-2"> 
                            	<input type="text" name="city" placeholder="ex: Makati" 
                            	class="form-control" required="required"> 
                            	<span>City</span> </div>
                        </div>
                        <div class="col-md-6">
                            <div class="inputbox mt-3 mr-2"> 
                            	<input type="text" name="zipCode" placeholder="ex: 1212"
                            	class="form-control" required="required"> 
                            	<span>Zip code</span> </div>
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="mt-4 mb-4 d-flex justify-content-between"> 
            	<button class="btn btn-secondary btn-lg" onclick="goBack()" role="button">Return</button> 
            	<button class="btn btn-success px-3" type="submit">Pay Php ${order.getTotalPrice()}0</button> 
            </div>
        </div>
        <div class="col-md-4">
            <div class="card bg-dark p-3 mb-3 border border-light"> <span>You have to pay</span>
                <div class="d-flex flex-row align-items-end mb-3">								
                    <h1 class="mb-0"><input type="text" readonly class="form-control-plaintext" name="totalPrice" value="${order.getTotalPrice()}0"></h1>
                    
                </div> <span>Enjoy wait for the delivery after you complete the payment</span><br>
                <div class="hightlight"> <span>100% Guaranteed freshly cooked pizza, within 30 mins, delivered at your doorsteps.</span> </div>
            </div>
             <div class="card bg-dark p-3 mb-3 border border-light"> <span>You Ordered:</span>
                <div class="d-flex flex-row align-items-end mb-3">
                    <h5 class="mb-0" style="color: white;"><input type="text" readonly class="form-control-plaintext" name="pizzaFlavor" value="${order.getPizzaFlavor()}" ></h5>
         			<h5 class="mb-0" style="color: white;"><input type="text" readonly class="form-control-plaintext" name="quantity" value="${order.getQuantity()}" ></h5>
         		</div> 
                <div class="d-flex flex-row align-items-end mb-3">
         			<h6 class="mb-0" style="color: white;"><input type="text" readonly class="form-control-plaintext" name="upgradedPizza" value="Upgraded Pizzas" ></h6>
         			<h6 class="mb-0" style="color: white;"><input type="text" readonly class="form-control-plaintext" name="upgradeQuantity" value="${order.getUpgradeQuantity()}"></h6>
                </div> 
                 <div class="d-flex flex-row align-items-end mb-3">
         			<h6 class="mb-0" style="color: white;"><input type="text" readonly class="form-control-plaintext" name="upgradedPizza" value="Total Upgrade Price: " ></h6>
         			<h6 class="mb-0" style="color: white;"><input type="text" readonly class="form-control-plaintext" name="totalUpgradePrice" value="${order.getTotalUpgradePrice()}0"></h6>
                </div> 
                <div class="d-flex flex-row align-items-end mb-3">
         			<input type="text" readonly class="form-control-plaintext" name="packing" value="${order.getPacking()}">
                </div> 
            </div>
        </div>
    </div>
</div>
</form>

<script>
function goBack() {
  window.history.back();
}
</script>

 <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  </body>
</html>