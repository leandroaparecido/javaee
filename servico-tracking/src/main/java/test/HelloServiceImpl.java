package test;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import test.repository.TestRepository;

@Stateless
public class HelloServiceImpl implements HelloService {

	private static final Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);

	@Inject
	Event<Integer> updateEvent;

	@Inject
	TestRepository repository;

	public String sayHello(String to) {
		logger.debug("Saying hello to [{}]!", to);
		repository.saveAndFlush(new Person(to));
		return "Hello " + to;
	}

	@Schedule(hour = "*", minute = "*", second = "*/30", info = "Testing scheduling", persistent = false)
	public void scheduledUpdate() {
		logger.info("Updating...");
		String name = "voucher.max";
		logger.info("Configuration [{}={}]", name, System.getProperty(name));
		updateEvent.fire(1);
	}
}
