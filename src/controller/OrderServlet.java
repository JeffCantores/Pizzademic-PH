package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.builder.PizzaBuilder;

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
			Double upgradePrice = Double.parseDouble(request.getParameter("upgradePrice"));
			int pizzaUpgrade = Integer.parseInt(request.getParameter("upgrade"));
			String packaging = request.getParameter("packing");
			
			
			PizzaBuilder mealBuilder = new PizzaBuilder(pizzaFlavor, pizzaQty, pizzaUpgrade, pizzaPrice, upgradePrice, packaging);	

			request.setAttribute("order", mealBuilder); 
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("checkout.jsp");
			dispatcher.forward(request, response);
			
		}catch(NullPointerException npe){
			//If the user put something on their forehead, they will be redirected to the error page
			npe.printStackTrace();
			RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
			dispatcher.forward(request, response);
		}
		
	}
	
}
