package test.repository;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Test;

import test.Person;

public class Test1 {

	private EntityManager em;
	private TestRepository repository;

	@Before
	public void init() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("test");
		em = entityManagerFactory.createEntityManager();
	}

	@Test
	public void test() throws Exception {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		repository.save(new Person("person1"));
		assertEquals(1, repository.count().longValue());
		System.out.println(repository.findWithCustomQuery("person1"));
		transaction.commit();
	}
}
