package model.iterator.pizzas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import model.iterator.iterator.*;
import utility.SingletonDB;
import utility.DBOps;
import model.iterator.bean.*;

public class PizzademicPHPizzas implements PizzaIterator, DBOps{

	ArrayList<PizzaBean> pizzas;
	
	public PizzademicPHPizzas() {
		pizzas = new ArrayList<PizzaBean>();
		
		ResultSet pizzaRecords = null;
		Connection connection = SingletonDB.getConnection(); 
		
		if(connection != null) {
			try {
				
				PreparedStatement pstmnt = connection.prepareStatement(GET_ALL_RECORDS);//Pizza search query preparations
				
				pizzaRecords = pstmnt.executeQuery();//execution of query
				
				while(pizzaRecords.next()) {
		            String pizzaFlavor = pizzaRecords.getString("pizza_flavor");
		            String pizzaPhoto = pizzaRecords.getString("pizza_photo");
		            String pizzaDescription = pizzaRecords.getString("pizza_description");
		            
		            addPizza(pizzaFlavor, pizzaPhoto, pizzaDescription);
				}  
	                
	        }catch (SQLException sqle) {
				try {
					connection.rollback();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				sqle.printStackTrace();
			}
		}
	}
	
	
	public void addPizza(String pizzaName, String pizzaPhoto, String pizzaDesc) {
		PizzaBean pizzademicPizzas = new PizzaBean(pizzaName, pizzaPhoto, pizzaDesc);
		pizzas.add(pizzademicPizzas);
	}
	
	
	@Override
	public Iterator createPizzaIterator() {
		return pizzas.iterator();
	}

	

}
