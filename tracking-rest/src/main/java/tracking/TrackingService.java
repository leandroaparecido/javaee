package tracking;

import javax.ejb.*;
import javax.inject.*;
import javax.ws.rs.*;

import voucher.VoucherServiceRemote;

@Named
@Path("/test")
public class TrackingService {

	@EJB(lookup = "java:global/servico-voucher-0.0.1-SNAPSHOT/VoucherService!voucher.VoucherServiceRemote")
	private VoucherServiceRemote voucherService;

	@GET
	@Path("{name}")
	public String track(@PathParam("name") String s) {
		return voucherService.sayHello(s);
	}
}
