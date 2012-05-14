package voucher;

import javax.annotation.*;
import javax.ejb.*;
import javax.enterprise.event.*;
import javax.inject.*;
import javax.persistence.*;

import org.slf4j.*;
import org.springframework.data.jpa.repository.support.*;
import org.springframework.data.repository.core.support.*;

import voucher.repository.*;

@Stateless
public class VoucherService implements VoucherServiceRemote {

	private static final Logger logger = LoggerFactory.getLogger(VoucherService.class);

	@Inject
	Event<Integer> invalidVouchersEvent;

//	@PersistenceContext
//	EntityManager entityManager;

	@Inject
	private VoucherRepository repository;

/*	@PostConstruct
	public void init() {
		RepositoryFactorySupport support = new JpaRepositoryFactory(entityManager);
		repository = support.getRepository(VoucherRepository.class);
	}
*/
	public String sayHello(String to) {
		logger.debug("Saying hello to [{}]!", to);
		repository.save(new Person(to));
		return "Hello " + to;
	}

	@Schedule(hour = "*", minute = "*", second = "*/30", info = "Update invalid vouchers", persistent = false)
	public void updateInvalidVouchers() {
		logger.info("Updating invalid vouchers");
		String nome = "voucher.max";
		logger.info("Configuracao [{}={}]", nome, System.getProperty(nome));
		invalidVouchersEvent.fire(1);
	}
}
