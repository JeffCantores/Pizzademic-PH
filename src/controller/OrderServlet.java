package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.transaction.Transaction;

public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			int pizzaQty = Integer.parseInt(request.getParameter("quantity"));
			String pizzaFlavor = request.getParameter("flavor");
			Double pizzaPrice = Double.parseDouble(request.getParameter("price"));
			int pizzaUpgrade = Integer.parseInt(request.getParameter("upgrade"));
			
			Transaction transaction = new Transaction(pizzaFlavor, pizzaQty, pizzaUpgrade, pizzaPrice);	

			request.setAttribute("order", transaction); 
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("checkout.jsp");
			dispatcher.forward(request, response);
			
		}catch(NullPointerException npe){
			//If the user put something on their forehead, they will be redirected to the error page
			RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
			dispatcher.forward(request, response);
		}
		
	}
	
}
