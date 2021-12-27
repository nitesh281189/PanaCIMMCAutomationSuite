package com.PanaAutomation.reports;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

import org.openqa.jetty.util.FileResource;

//import com.PanaAutomation.Driver.DriverScript;
import com.PanaAutomation.Keywords.GenericKeywords;

//import javax.activation.*;

public class EmailReport {
	public static Properties envProp;
	
	public void setEnvProp(Properties envProp) {
		EmailReport.envProp = envProp;
		System.out.println("Wait");
	}	
	
	public Properties getEnvProp() {
		return envProp;
	}
	

    public void SendEmail()
    {
    	GenericKeywords GK = new GenericKeywords();
    	GK.zipReportsFolder();
        //String ToEmails = "praveenkumar.billakanti@tcs.com,anjana.gowlikar-external@tcs.com";
    	String ToEmails = envProp.getProperty("MailSendTo");
        String From = envProp.getProperty("MailSendFrom");
        String Subject = envProp.getProperty("MailSubject");
        String Body = envProp.getProperty("MailBody");
        String host = envProp.getProperty("SMTPHost");
        //String filename = "D:\\Automation\\PanaMA\\Report\\Report\\Fri_Jan_25_06_25_31_CST_2019\\Report.html";
        
        //String filename = ExtentManager.EmailReportName;
        
        String zipFileName=ExtentManager.ReportsZipName;
        
        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);
        MimeMessage message = new MimeMessage(session);
            
            try {
                // Create a default MimeMessage object.


                // Set From: header field of the header.
                message.setFrom(new InternetAddress(From));

                // Set To: header field of the header.
                List<String> items = Arrays.asList(ToEmails.split(","));
                for (String To: items)
                {
                	message.addRecipient(Message.RecipientType.TO, new InternetAddress(To));
                }

                // Set Subject: header field
                message.setSubject(Subject);

                // Now set the actual message
                //message.setText(Body);
                
                
                BodyPart objMessageBodyPart = new MimeBodyPart();

                objMessageBodyPart.setText(Body);

                Multipart multipart = new MimeMultipart();

                multipart.addBodyPart(objMessageBodyPart);

                objMessageBodyPart = new MimeBodyPart();
          
                //DataSource source = new FileResource(zipFileName);
                //objMessageBodyPart.setDataHandler(new DataHandler(source));
                objMessageBodyPart.setFileName("report.zip");
                multipart.addBodyPart(objMessageBodyPart);
       
                message.setContent(multipart);
                
                Transport transport = session.getTransport("smtp");

                transport.connect();

                transport.sendMessage(message, message.getAllRecipients());

                transport.close();
                System.out.println("Sent message successfully....");
             } catch (MessagingException mex) {
                mex.printStackTrace();
             }            

    }
}
