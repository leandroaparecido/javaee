package test.repository;

import java.util.List;

import test.Person;

import com.ctp.cdi.query.Dao;
import com.ctp.cdi.query.EntityDao;
import com.ctp.cdi.query.Query;
import com.ctp.cdi.query.QueryParam;

@Dao
public interface TestRepository extends EntityDao<Person, Long> {

	List<Person> findByNameLike(String name);

	@Query("SELECT p FROM Person p WHERE p.name = :name")
	List<Person> findWithCustomQuery(@QueryParam("name") String name);

}
