package model.builder;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletContext;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import utility.SingletonDB;

public class PizzaBuilder implements Facade{
	
	private String name;
	private String userEmail;
	private String pizzaFlavor;
	private String creditCardNum; //either 13 digits or 16 tayo
	private int quantity;
	private int upgradeQuantity; //pag greater than pizzaQty neglect natin yung excess
	private Double productPrice;
	private Double upgradePrice;
	private Double totalUpgradePrice;
	private Double totalPrice; 
	private String packing;
	
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
	
	public String getPacking() {
		return this.packing;
	}

	public void setPacking(String packing) {
		this.packing = packing;
	}

	public PizzaBuilder() {
	}
	
	//this constructor creates an object that will serve as the temporary storage of user order
	public PizzaBuilder(String pizza, int quantity, int upgrade, Double productPrice, Double upgradePrice, String packing) {
		this.pizzaFlavor = pizza;
		this.quantity = quantity;
		this.upgradeQuantity = upgrade;
		this.productPrice = productPrice;
		this.upgradePrice = upgradePrice;
		this.packing = packing;
		if(this.upgradeQuantity > this.quantity) {
			this.upgradeQuantity = quantity;
		}
		
	   getCost();
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
	
	public Double getCost() {
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
		String selectPizza = "INSERT IGNORE INTO transactions (customer_name, street, brgy, city, zip_code, pizza_flavor, total_price, upgrade_quantity, quantity, packaging)" + 
				"VALUES (?, ?, ?, ?, ?, ?, ? ,? ,?, ?);";
		
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
			pstmnt.setString(10, packing);
			
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

	@Override
	public boolean process(ServletContext context) {
		
		if(luhnTest()) {
			System.out.println("Credit Card is Valid");
			addToTransactions();
			updateProductTable();
			generate();
			sendMail(userEmail, context);
			
			return true;
		}else {
			System.out.println("Credit card is invalid!");
			return false;
		}
	}
	
	
	public void generate() {
		
		String filename = "pizzademic-ph-receipt.pdf";
        
		PDDocument document = new PDDocument();
		PDPage page = new PDPage();
		
		//PDF Generator
	     try {
	    	 Path path = Paths.get("");
	         String root = path.toAbsolutePath().toString();
	         String documentLocation = root +"\\" + filename;
	         
	   	      document.addPage(page);
	   	      
	   	      String messageTitle1 = "THANK YOU FOR ORDERING!";
	   	      String messageTitle2 = "ORDER CONFIRMATION RECEIPT";
	   	      String orderMessage1 = "Your orders are: ";
	   	      String orderMessage2 = String.valueOf(this.quantity) + "pc/s " + this.pizzaFlavor;
	   	      String orderMessage3 = "Upgraded pizza: " + String.valueOf(this.upgradeQuantity);
	   	      String orderMessage4 = "Total Upgrade Price: " + String.valueOf(this.totalUpgradePrice) + "0";
	   	      String orderMessage5 = this.packing;
	   	      String totalPriceMessage= "TOTAL: Php " + String.valueOf(this.totalPrice) + "0";
	   	      String addressMessage1 = "Customer Name: " + this.name;
	   	      String addressMessage2 = "Address: " + this.houseSt + ", " + this.brgy + ", " + this.city + ", " + this.zipCode;  
	   	  
		   	   PDPageContentStream contents = new PDPageContentStream(document, page);
		   	   
		   	   PDFont font = PDType1Font.COURIER_BOLD;
		   	   
	           contents.beginText();
	           
	           contents.setFont(font, 30);
	           
	           //Setting the leading
		   	   contents.setLeading(25f);
		   	   
	           contents.newLineAtOffset(50, 700);
	           
	           contents.showText(messageTitle1);
	           contents.newLine();
	           contents.showText(messageTitle2);
	           
	           contents.setLeading(50f);
	           
	           contents.setFont(font, 12); 
	           
	           contents.newLine();
	           contents.showText(addressMessage1);
	           
	           contents.setLeading(20f);
	           
	           contents.newLine();
	           contents.showText(addressMessage2);
	           
	           contents.setLeading(40f);
	           contents.setFont(font, 20);
	           
	           contents.newLine();
	           contents.showText(orderMessage1);
	           contents.setFont(font, 15);
	           
	           contents.setLeading(20f);
	           
	           contents.newLine();
	           contents.showText(orderMessage2);
	           contents.newLine();
	           contents.showText(orderMessage3);
	           contents.newLine();
	           contents.showText(orderMessage4);
	           contents.newLine();
	           contents.showText(orderMessage5);
	           contents.setLeading(50f);
	           
	           contents.newLine();
	           contents.setFont(font, 27);
	           contents.showText(totalPriceMessage);
	           
	           contents.endText();
	           contents.close();
	   	      
	   	      document.save(documentLocation);
	   	      
	   	      System.out.println("receipt created successfully! ");
	   	      System.out.println("Specified location of the receipt: " + documentLocation);
	   	      document.close();
	   	      
	       }catch(IOException ioe) {
	    	  System.out.println("FAILED");
	      	 ioe.printStackTrace();
	       }

	}
	
	
	public void sendMail(String to, ServletContext context){  
    	
    	//Get properties object    
          Properties properties = new Properties();    
          
          properties.put("mail.smtp.host", context.getInitParameter("mail.smtp.host"));
          properties.put("mail.smtp.socketFactory.port", context.getInitParameter("mail.smtp.host"));
          properties.put("mail.smtp.socketFactory.class", context.getInitParameter("mail.smtp.socketFactory.class"));
          properties.put("mail.smtp.auth", context.getInitParameter("mail.smtp.auth"));
          properties.put("mail.smtp.port", context.getInitParameter("mail.smtp.port"));
          
          final String sender = context.getInitParameter("sender");
          final String password = context.getInitParameter("password");
          
          //get Session   
          Session session = Session.getDefaultInstance(properties,    
           new javax.mail.Authenticator() {    
        	  
        	  protected PasswordAuthentication getPasswordAuthentication() {    
        		  return new PasswordAuthentication(sender, password);  
        	  }    
        	  
          });    
          
          
          //compose message    
          try {    
        	  
        	  Path path = Paths.get("");
        	  String root = path.toAbsolutePath().toString();
        	  
        	  String fileName = "pizzademic-ph-receipt.pdf";
        	  String docLocation = root + "\\" + fileName;
        	  
        	  String subject = context.getInitParameter("subject");
        	  String body = context.getInitParameter("body");
 	         
	          MimeMessage message = new MimeMessage(session);    
	          message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
	          message.setSubject(subject);    
	             
	           
	          //3) create MimeBodyPart object and set your message text     
			  BodyPart messageBodyPart1 = new MimeBodyPart();  
			  messageBodyPart1.setText(body);  
			      
			  //4) create new MimeBodyPart object and set DataHandler object to this object      
			  MimeBodyPart messageBodyPart2 = new MimeBodyPart();  
			  
			  DataSource source = new FileDataSource(docLocation);  
			  messageBodyPart2.setDataHandler(new DataHandler(source));  
			  messageBodyPart2.setFileName(docLocation);  
			     
			     
			  //5) create Multipart object and add MimeBodyPart objects to this object      
			  Multipart multipart = new MimeMultipart();  
			  multipart.addBodyPart(messageBodyPart1);  
			  multipart.addBodyPart(messageBodyPart2);  
			  
			  //6) set the multiplart object to the message object  
			  message.setContent(multipart);  
	           
	         //send message  
	         Transport.send(message);    
	         System.out.println("message sent successfully");    
          } catch (MessagingException e) {
        	  e.printStackTrace();
        	  throw new RuntimeException(e);
        	  
          } finally {
    		  
		}    
             
    }  


}

