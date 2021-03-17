package model.transaction;

import java.io.IOException;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.ServletContext;


public class EmailSender{   
	
	 private static String fromSender = "bscsmail.se31@gmail.com";
	 private static String senderPassword = "BSCS-SE31";
	 private static String toRecipient = null;
	 private static String mailSubject = "SEG31-DESPATT-CANTORES_GALANG-MP4";
	 private static String mailBody = "Thank you for ordering!";
	
	 public static void main(String[] args, String userEmail, ServletContext context) { 
		 
		 toRecipient = userEmail;
	     Mailer.send(fromSender,senderPassword ,toRecipient,mailSubject ,mailBody, context);   
	 }  
	 
}    

class Mailer{  
	
    public static void send(final String from,final String password,String to,String sub,String msg, ServletContext context){  
    	
    	//Get properties object    
          Properties properties = new Properties();    
          properties.put("mail.smtp.host", "smtp.gmail.com");    
          properties.put("mail.smtp.socketFactory.port", "465");    
          properties.put("mail.smtp.socketFactory.class",    
                    "javax.net.ssl.SSLSocketFactory");    
          properties.put("mail.smtp.auth", "true");    
          properties.put("mail.smtp.port", "465");    
          
          //get Session   
          Session session = Session.getDefaultInstance(properties,    
           new javax.mail.Authenticator() {    
        	  
        	  protected PasswordAuthentication getPasswordAuthentication() {    
        		  return new PasswordAuthentication(from,password);  
        	  }    
        	  
          });    
          
          //compose message    
          try {    
        	  
        	  Path path = Paths.get("");
        	  String root = path.toRealPath(LinkOption.NOFOLLOW_LINKS).toString();
 	         
	          MimeMessage message = new MimeMessage(session);    
	          message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
	          message.setSubject(sub);    
	             
	           
	          //3) create MimeBodyPart object and set your message text     
			  BodyPart messageBodyPart1 = new MimeBodyPart();  
			  messageBodyPart1.setText(msg);  
			      
			  //4) create new MimeBodyPart object and set DataHandler object to this object      
			  MimeBodyPart messageBodyPart2 = new MimeBodyPart();  
			  
			  String filename = root + "\\pizzademic-ph-receipt.pdf";
			  
			  DataSource source = new FileDataSource(filename);  
			  messageBodyPart2.setDataHandler(new DataHandler(source));  
			  messageBodyPart2.setFileName(filename);  
			     
			     
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
        	  
          }catch (IOException e) {
    	      System.err.println(e);
    	  } finally {
    		  
		}    
             
    }  
   
}  




