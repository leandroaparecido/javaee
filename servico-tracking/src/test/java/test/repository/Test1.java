package test.repository;

import static org.junit.Assert.*;

import javax.persistence.*;

import org.junit.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.support.*;
import org.springframework.data.repository.core.support.*;

import test.Person;
import test.repository.TestRepository;

public class Test1 {

	private EntityManager em;
	private TestRepository repository;

	@Before
	public void init() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("test");
		em = entityManagerFactory.createEntityManager();
		RepositoryFactorySupport support = new JpaRepositoryFactory(em);
		repository = support.getRepository(TestRepository.class);
	}

	@Test
	public void test() throws Exception {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		repository.save(new Person("person1"));
		assertEquals(1, repository.count());
		System.out.println(repository.findWithCustomQuery("person1", new Sort(Sort.Direction.ASC, "name")));
		transaction.commit();
	}
}
