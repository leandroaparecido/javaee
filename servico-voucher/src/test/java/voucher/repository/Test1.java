package voucher.repository;

import static org.junit.Assert.*;

import javax.persistence.*;

import org.junit.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.support.*;
import org.springframework.data.repository.core.support.*;

import voucher.*;

public class Test1 {

	private EntityManager em;
	private VoucherRepository repository;

	@Before
	public void init() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("test");
		em = entityManagerFactory.createEntityManager();
		// Testar http://ctpconsulting.github.com/query/1.0.0.Alpha3/introduction.html
		RepositoryFactorySupport support = new JpaRepositoryFactory(em);
		repository = support.getRepository(VoucherRepository.class);
	}

	@Test
	public void test() throws Exception {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		repository.save(new Person("leandro"));
		assertEquals(1, repository.count());
		System.out.println(repository.findWithCustomQuery("leandro", new Sort(Sort.Direction.ASC, "name")));
		transaction.commit();
	}
}
