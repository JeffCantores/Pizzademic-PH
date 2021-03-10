package model.upgrade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utility.DBOps;
import utility.SingletonDB;

public class FourCheeseUpgrade extends Upgrade implements DBOps{
		
	private String upgradeType;
	private String upgradeDetails;
	
	@Override
	public String viewUpgradeType() {
		return upgradeType;
	}
		
	@Override
	public String viewUpgradeDetails() {
		return upgradeDetails;
	}
	
	public FourCheeseUpgrade() {
		
		ResultSet upgradeRecord = null;//this will hold the data retrieved from the db
		Connection connection = SingletonDB.getConnection(); 
		
		if(connection != null) {
			
			try {
				String pizzaUpgrade = "Four Cheese Supreme";
				
				PreparedStatement pstmnt = connection.prepareStatement(SELECT_UPGRADE_PRODUCT);//query for searching the pizza upgrade
				
				pstmnt.setString(1,pizzaUpgrade);
				
				upgradeRecord = pstmnt.executeQuery();//query execution
				
	            while(upgradeRecord.next()) {
	            	upgradeType = upgradeRecord.getString("upgrade_type");
		            upgradeDetails = upgradeRecord.getString("upgrade_description");
		 
	            }
	          
			} catch (SQLException sqle) {
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
	public Upgrade clone() {
		return new FourCheeseUpgrade();
	}
		
		
}
