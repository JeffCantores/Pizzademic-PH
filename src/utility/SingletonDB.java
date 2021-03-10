package utility;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class SingletonDB implements DBOps{
	
	private static Connection connection;
	
	private SingletonDB() {
	}
	
	private static Connection getDBConnection() {
		
		Connection connection = null;

		try {
			
			connection = ((DataSource)InitialContext.doLookup("java:/comp/env/jdbc/SEG31_DS")).getConnection();
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return connection;
	}
	
	public static Connection getConnection() {
		return (( connection !=null )
			? connection
			: getDBConnection());		
	}
	
	public static void instantiate() {
			
				try { //This block of code instantiates all the values inside the database :>
					
				   connection =  getDBConnection();
				   boolean  isInstantiated = false;
				   Statement stmt;
				   
				   String query = INITIALIZE_PIZZA_TABLE;
				   String query2 = INITIALIZE_UPGRADES_TABLE;
				   String query3 = INSERT_PIZZA_VALUES;
				   String query4 = INSERT_UPGRADE_VALUES;
				   String query5 = INITIALIZE_TRANSACTION_TABLE;
					
				   stmt = connection.createStatement();
				   isInstantiated = stmt.execute(query);
				   isInstantiated = stmt.execute(query2);
				   isInstantiated = stmt.execute(query3);
				   isInstantiated = stmt.execute(query4);
				   isInstantiated = stmt.execute(query5);
				   
				   if(isInstantiated) {
					   System.out.println("DB Instantiated Successfully!");
				   }
				  
				}catch (SQLException sqle) {
					System.err.println(sqle.getMessage());
				}
				
			
			
		}

}
