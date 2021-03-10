package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.pizza.*;
import model.upgrade.*;
import utility.*;


public class ProcessPizzaServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String pizzaType = request.getParameter("flavor");
			
			PizzademicPizza pizza = new PizzaFactory().getPizza(pizzaType.toUpperCase()); 	
			Upgrade upgrade = new UpgradePizzaFactory().getUpgrade(pizzaType.toUpperCase());
			
			pizza.setUpgrade(upgrade);
			
			;
			System.out.println("Flavor picked: " + pizzaType);
			
			request.setAttribute("pizza", pizza);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("display.jsp");
			dispatcher.forward(request, response);
			
		}catch(NullPointerException npe){
			//If the user put the wrong keyword or spelling, they will be redirected to the error page
			RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
			dispatcher.forward(request, response);
		}
		
	}
	
}
