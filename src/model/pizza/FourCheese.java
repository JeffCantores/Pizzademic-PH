package model.pizza;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.upgrade.Upgrade;
import utility.DBOps;
import utility.SingletonDB;

public class FourCheese extends PizzademicPizza implements DBOps {
	
	private String pizzaFlavor;
	private Double pizzaPrice;
	private String pizzaDescription;
	private int quantity;
	
	private Upgrade upgrade;
	
	@Override
	public Upgrade getUpgrade() {
		return upgrade;
	}
	
	@Override
	public void setUpgrade(Upgrade upgrade) {
		this.upgrade = upgrade;
	}
	
	@Override
	public String viewFlavor() {
		return pizzaFlavor;
	}
	
	@Override
	public Double viewPrice() {
		
		return pizzaPrice;
	}
	
	@Override
	public String viewDescription() {
		return pizzaDescription;
	}
	
	@Override
	public int viewQuantity() {
		return quantity;
	}

	//dito mapupunta yung connection and reassignment
	public FourCheese() {
		
		ResultSet pizzaRecord = null;//This will hold the data fetched from the database 
		Connection connection = SingletonDB.getConnection(); 
	
		
		if(connection != null) {
			try {
				
				String pizza = "Four Cheese";
				
				PreparedStatement pstmnt = connection.prepareStatement(SELECT_PIZZA_PRODUCT);//Pizza search query preparations
				
				pstmnt.setString(1,pizza);
				
				pizzaRecord = pstmnt.executeQuery();//execution of query
				
				while(pizzaRecord.next()) {
		            pizzaFlavor = pizzaRecord.getString("pizza_flavor");
		            pizzaPrice = pizzaRecord.getDouble("pizza_price");
		            pizzaDescription = pizzaRecord.getString("pizza_description");
		            quantity = pizzaRecord.getInt("quantity");
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

	@Override
	public PizzademicPizza clone() {
		return new FourCheese();
	}

	
	

	
	


}
