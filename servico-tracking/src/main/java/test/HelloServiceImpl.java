package test;

import java.text.*;
import java.util.*;

import javax.annotation.*;
import javax.ejb.*;
import javax.enterprise.event.*;
import javax.inject.*;
import javax.interceptor.*;
import javax.mail.*;
import javax.mail.internet.*;

import org.javasimon.cdi.*;
import org.slf4j.*;

import test.repository.*;

@Stateless
@Interceptors(StopwatchInterceptor.class)
public class HelloServiceImpl implements HelloService {

	private static final Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);

	@Inject
	Event<Integer> updateEvent;

	@Inject
	TestRepository repository;

	@Resource(name = "java:jboss/mail/Default")
	Session session;

	@Chrono
	public String sayHello(String to) {
		logger.debug("Saying hello to [{}]!", to);
		repository.saveAndFlush(new Person(to));
		sendMail();
		return "Hello " + to;
	}

	@Schedule(hour = "*", minute = "*", second = "*/30", info = "Testing scheduling", persistent = false)
	public void scheduledUpdate() {
		logger.info("Updating...");
		String name = "voucher.max";
		logger.info("Configuration [{}={}]", name, System.getProperty(name));
		updateEvent.fire(1);
	}

	@Chrono("findByNameLike")
	public List<Person> findByName(String name) {
		return repository.findByNameLikeNamed("%" + name + "%");
	}

	public void sendMail() {
		try {
	        Message message = new MimeMessage(session);
	        message.setFrom();
	        message.setRecipients(Message.RecipientType.TO,
	                InternetAddress.parse("lehphyro@gmail.com", false));
	        message.setSubject("Test message from async example");
	        message.setHeader("X-Mailer", "JavaMail");
	        DateFormat dateFormatter = DateFormat
	                .getDateTimeInstance(DateFormat.LONG, DateFormat.SHORT);
	        Date timeStamp = new Date();
	        String messageBody = "This is a test message from the async example "
	                + "of the Java EE Tutorial. It was sent on "
	                + dateFormatter.format(timeStamp)
	                + ".";
	        message.setText(messageBody);
	        message.setSentDate(timeStamp);
	        //Transport.send(message);
	        logger.info("Mail sent to {}", "lehphyro@gmail.com");
	    } catch (MessagingException ex) {
	        logger.error("Error in sending message", ex);
	    }
	}
}
