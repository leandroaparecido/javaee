package test.repository;

import static org.junit.Assert.assertEquals;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import test.Person;

import com.ctp.cdi.query.QueryExtension;
import com.ctp.cdi.query.meta.DaoComponents;

//@RunWith(Arquillian.class)
public class Test1 {

	@Inject
	private TestRepository repository;

	@Produces
	@PersistenceContext
	private EntityManager em;

	@Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
            .addClass(TestRepository.class)
            .addPackages(true, "com.ctp.cdi")
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
            .addAsManifestResource("META-INF/persistence.xml");
    }

//	@Test
	public void test() throws Exception {
//		EntityTransaction transaction = em.getTransaction();
//		transaction.begin();
		repository.save(new Person("person1"));
		assertEquals(1, repository.count().longValue());
		System.out.println(repository.findWithCustomQuery("person1"));
//		transaction.commit();
	}
}
