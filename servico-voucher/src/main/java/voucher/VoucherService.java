package voucher;

import javax.ejb.*;
import javax.enterprise.event.*;
import javax.inject.*;

import org.slf4j.*;

@Stateless
@Remote(VoucherServiceRemote.class)
public class VoucherService implements VoucherServiceRemote {

	private static final Logger logger = LoggerFactory.getLogger(VoucherService.class);

	@Inject
	private Event<Integer> invalidVouchersEvent;

	public String sayHello(String to) {
		logger.debug("Saying hello to [{}]", to);
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
