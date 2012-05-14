package test;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import test.repository.TestRepository;

@Stateless
public class HelloServiceImpl implements HelloService {

	private static final Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);

	@Inject
	Event<Integer> updateEvent;

	@PersistenceContext
	EntityManager entityManager;

	TestRepository repository;

	@PostConstruct
	public void init() {
		RepositoryFactorySupport support = new JpaRepositoryFactory(entityManager);
		repository = support.getRepository(TestRepository.class);
	}

	public String sayHello(String to) {
		logger.debug("Saying hello to [{}]!", to);
		repository.save(new Person(to));
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
