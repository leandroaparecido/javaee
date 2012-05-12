package voucher.repository;

import java.util.*;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;

import voucher.*;

public interface VoucherRepository extends JpaRepository<Person, Long> {

	List<Person> findByNameLike(String name);

	@Query("SELECT p FROM Person p WHERE p.name = :name")
	List<Person> findWithCustomQuery(@Param("name") String name, Sort sort);

}
