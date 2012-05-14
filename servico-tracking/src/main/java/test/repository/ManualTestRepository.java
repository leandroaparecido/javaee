package test.repository;

import java.util.*;

import javax.persistence.*;

import test.Person;

public class ManualTestRepository {

	@PersistenceContext
	EntityManager em;

	public void save(String to) {
		Person person = new Person();
		person.setName(to);
		em.persist(person);
	}

	@SuppressWarnings("unchecked")
	public List<Person> findAll() {
		return em.createQuery("from Person p").getResultList();
	}
}
