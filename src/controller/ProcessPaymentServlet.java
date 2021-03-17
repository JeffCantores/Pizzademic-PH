package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.transaction.Transaction;

import javax.servlet.*;

public class ProcessPaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			ServletContext context = getServletContext();
			
			Transaction confirmTransaction = new Transaction();
			
			confirmTransaction.setName(request.getParameter("nameOnCard"));
			confirmTransaction.setUserEmail(request.getParameter("userEmail"));
			confirmTransaction.setCreditCardNum(request.getParameter("creditCardNo"));
			confirmTransaction.setPizzaFlavor(request.getParameter("pizzaFlavor"));
			confirmTransaction.setQuantity(Integer.parseInt(request.getParameter("quantity")));
			confirmTransaction.setUpgradeQuantity(Integer.parseInt(request.getParameter("upgradeQuantity")));
			confirmTransaction.setTotalUpgradePrice(Double.parseDouble(request.getParameter("totalUpgradePrice")));
			confirmTransaction.setTotalPrice(Double.parseDouble(request.getParameter("totalPrice")));
			confirmTransaction.setHouseSt(request.getParameter("houseStreet"));
			confirmTransaction.setBrgy(request.getParameter("brgy"));
			confirmTransaction.setCity(request.getParameter("city"));
			confirmTransaction.setZipCode(request.getParameter("zipCode"));
			
			//implementation of the Facade Design Pattern
			boolean isValid = confirmTransaction.process(context);
			
			if(isValid) {
				request.setAttribute("checkout", confirmTransaction); 
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("order-confirmation.jsp");
				dispatcher.forward(request, response);
			}else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
				dispatcher.forward(request, response);
			}
			
		}catch(NullPointerException npe){
			//If the user put the wrong keyword or spelling, they will be redirected to the error page
			npe.printStackTrace();
			RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
			dispatcher.forward(request, response);
		}
	}

}
