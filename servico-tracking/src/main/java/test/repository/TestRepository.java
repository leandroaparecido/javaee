package test.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import test.Person;

public interface TestRepository extends JpaRepository<Person, Long> {

	List<Person> findByNameLike(String name);

	@Query("SELECT p FROM Person p WHERE p.name = :name")
	List<Person> findWithCustomQuery(@Param("name") String name, Sort sort);

}
