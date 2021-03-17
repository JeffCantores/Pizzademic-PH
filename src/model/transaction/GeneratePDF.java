package model.transaction;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class GeneratePDF {

	public static void main(String[] args, String pizzaFlavor, int quantity, 
			int upgradeQuantity, Double totalPrice, String name, String houseSt, String brgy, 
			String city, String zipCode, Double totalUpgradePrice) {
		
		String filename = "pizzademic-ph-receipt.pdf";
        
		PDDocument document = new PDDocument();
		PDPage page = new PDPage();
		
		//PDF Generator
	     try {
	    	 Path path = Paths.get("");
	         String root = path.toAbsolutePath().toString();
	         
	   	      document.addPage(page);
	   	      
	   	      String messageTitle1 = "THANK YOU FOR ORDERING!";
	   	      String messageTitle2 = "ORDER CONFIRMATION RECEIPT";
	   	      String orderMessage1 = "Your orders are: ";
	   	      String orderMessage2 = String.valueOf(quantity) + "pc/s " + pizzaFlavor;
	   	      String orderMessage3 = "Upgraded pizza: " + String.valueOf(upgradeQuantity);
	   	      String orderMessage4 = "Total Upgrade Price: " + String.valueOf(totalUpgradePrice);
	   	      String totalPriceMessage= "TOTAL: Php " + String.valueOf(totalPrice) + "0";
	   	      String addressMessage1 = "Customer Name: " + name;
	   	      String addressMessage2 = "Address: " + houseSt + ", " + brgy + ", " + city + ", " + zipCode;  
	   	      
	   	      
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
	           contents.setLeading(50f);
	           
	           contents.newLine();
	           contents.setFont(font, 27);
	           contents.showText(totalPriceMessage);
	           
	           contents.endText();
	           contents.close();
	   	      
	   	      document.save( root +"\\" + filename);
	   	      
	   	      System.out.println("receipt created successfully! ");
	   	      System.out.println("Specified location of the receipt: " + root +"\\" + filename );
	   	      document.close();
	   	      
	       }catch(IOException ioe) {
	    	  System.out.println("FAILED");
	      	 ioe.printStackTrace();
	       }

	}

}
