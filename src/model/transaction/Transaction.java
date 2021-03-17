package model.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utility.SingletonDB;

public class Transaction implements Facade{
	
	private String name;
	private String userEmail;
	private String pizzaFlavor;
	private String creditCardNum; //either 13 digits or 16 tayo
	private int quantity;
	private int upgradeQuantity; //pag greater than pizzaQty neglect natin yung excess
	private Double productPrice;
	private Double upgradePrice;
	private Double totalUpgradePrice;
	private Double totalPrice; // included na yung price ng upgrade dito pre
	
	private String houseSt;
	private String brgy;
	private String city;
	private String zipCode;
	
	
	public String getHouseSt() {
		return houseSt;
	}

	public void setHouseSt(String houseSt) {
		this.houseSt = houseSt;
	}

	public String getBrgy() {
		return brgy;
	}

	public void setBrgy(String brgy) {
		this.brgy = brgy;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	public Double getTotalUpgradePrice() {
		return totalUpgradePrice;
	}

	public void setTotalUpgradePrice(Double totalUpgradePrice) {
		this.totalUpgradePrice = totalUpgradePrice;
	}

	public Transaction() {
	}
	
	//this constructor creates an object that will serve as the temporary storage of user order
	public Transaction(String pizza, int quantity, int upgrade, Double productPrice, Double upgradePrice) {
		this.pizzaFlavor = pizza;
		this.quantity = quantity;
		this.upgradeQuantity = upgrade;
		this.productPrice = productPrice;
		this.upgradePrice = upgradePrice;
		if(this.upgradeQuantity > this.quantity) {
			this.upgradeQuantity = quantity;
		}
		
	   calculator();
	}

	public String getPizzaFlavor() {
		return pizzaFlavor;
	}

	public void setPizzaFlavor(String pizzaFlavor) {
		this.pizzaFlavor = pizzaFlavor;
	}

	public int getUpgradeQuantity() {
		return upgradeQuantity;
	}

	public void setUpgradeQuantity(int upgrade) {
		this.upgradeQuantity = upgrade;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreditCardNum() {
		return creditCardNum;
	}

	public void setCreditCardNum(String creditCardNum) {
		this.creditCardNum = creditCardNum;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}
	
	public Double getUpgradePrice() {
		return upgradePrice;
	}

	public void setUpgradePrice(Double upgradePrice) {
		this.upgradePrice = upgradePrice;
	}


	public boolean luhnTest(){
		
        int s1 = 0, s2 = 0;
        String reverse = new StringBuffer(creditCardNum).reverse().toString();
        
        for(int i = 0 ;i < reverse.length();i++){
            int digit = Character.digit(reverse.charAt(i), 10);
            if(i % 2 == 0){//this is for odd digits, they are 1-indexed in the algorithm
                s1 += digit;
            }else{//add 2 * digit for 0-4, add 2 * digit - 9 for 5-9
                s2 += 2 * digit;
                if(digit >= 5){
                    s2 -= 9;
                }
            }
        }
        return (s1 + s2) % 10 == 0;
    }
	
	public void addToTransactions(){

		Connection connection = SingletonDB.getConnection(); 
		String selectPizza = "INSERT IGNORE INTO transactions (customer_name, street, brgy, city, zip_code, pizza_flavor, total_price, upgrade_quantity, quantity)" + 
				"VALUES (?, ?, ?, ?, ?, ?, ? ,? ,?);";
		
		try {
			
			PreparedStatement pstmnt = connection.prepareStatement(selectPizza);//Pizza search query preparations
			
			pstmnt.setString(1,name);
			pstmnt.setString(2,houseSt);
			pstmnt.setString(3,brgy);
			pstmnt.setString(4,city);
			pstmnt.setString(5,zipCode);
			pstmnt.setString(6,pizzaFlavor);
			pstmnt.setDouble(7,totalPrice);
			pstmnt.setInt(8,upgradeQuantity);
			pstmnt.setInt(9,quantity);
			
			pstmnt.executeUpdate();//execution of query
			
		}catch (SQLException sqle) {
			try {
				connection.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			sqle.printStackTrace();
		}
		
		System.out.println("Successfully Added to Table!");
    }
	
	public void updateProductTable(){

		Connection connection = SingletonDB.getConnection(); 
		ResultSet pizzaRecord = null;
		
		int currentStock = 0;
		int updatedStock = 0;
		
		String selectPizza = "select * from pizza where pizza_flavor like ?";
		String updatePizzaQuantity = "UPDATE `pizza` SET `quantity` = ? WHERE `pizza_flavor` = ?;";
		
		try {
			
			PreparedStatement pstmnt = connection.prepareStatement(selectPizza);//Pizza search query preparations
			
			pstmnt.setString(1,pizzaFlavor);
			pizzaRecord = pstmnt.executeQuery();
			
			while(pizzaRecord.next()) {
				currentStock = pizzaRecord.getInt("quantity");
			}
			
			updatedStock = currentStock - this.quantity; // subtracting the selected stock from the current stock
			
			pstmnt = connection.prepareStatement(updatePizzaQuantity);
			pstmnt.setInt(1, updatedStock);
			pstmnt.setString(2, pizzaFlavor);
			
			pstmnt.executeUpdate();
			
		}catch (SQLException sqle) {
			try {
				connection.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			sqle.printStackTrace();
		}
		
		System.out.println("Successfully Updated Stock!");
    }
	
	public Double calculator() {
		Double total = (double) 0;
		
		total = this.productPrice * this.quantity;
		
		if(this.upgradeQuantity != 0) {
			this.totalUpgradePrice = this.upgradeQuantity*this.upgradePrice;
			this.totalPrice = total + this.totalUpgradePrice;
		}else {
			this.totalPrice = total;
		}
		return totalPrice;
	}


	@Override
	public boolean process() {
		
		if(luhnTest()) {
			System.out.println("Credit Card is Valid");
			addToTransactions();
			updateProductTable();
			GeneratePDF.main(null, pizzaFlavor, quantity, upgradeQuantity, totalPrice, name, houseSt, brgy, city, zipCode, totalUpgradePrice);
			EmailSender.main(null, userEmail);
			
			return true;
		}else {
			System.out.println("Credit card is invalid!");
			return false;
		}
		
		
		
	}


}

