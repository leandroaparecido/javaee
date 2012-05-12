package tracking;

import javax.ejb.*;
import javax.enterprise.inject.*;

import voucher.*;

public class EnvironmentResources {

	@Produces @EJB(lookup = "java:global/servico-voucher-0.0.1-SNAPSHOT/VoucherService!voucher.VoucherServiceRemote")
	VoucherServiceRemote voucherService;

}
