package disney.service.imp;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

import disney.utils.Configs;

@Service
public class MailService {
	private static final Logger logger = LoggerFactory.getLogger(MailService.class);
	
	public String sendTextEmail(String emailTo,String nombre) throws IOException {
		// the sender email should be the same as we used to Create a Single Sender Verification
		    Email from = new Email(Configs.EMAIL_SENDER);
		    String subject = "Registro Exitoso";
		    Email to = new Email(emailTo);
		    Content content = new Content("text/plain", "Felicidades por registrarte en Disney-Api, "+nombre);
		    Mail mail = new Mail(from, subject, to, content);
		
		    SendGrid sg = new SendGrid(Configs.SEND_GRID_KEY);
		    Request request = new Request();
		    try {
		      request.setMethod(Method.POST);
		      request.setEndpoint("mail/send");
		      request.setBody(mail.build());
		      Response response = sg.api(request);
		      logger.info(response.getBody());
		      return response.getBody();	     
		    } catch (IOException ex) {
		      throw ex;
		    }	   
	}
}