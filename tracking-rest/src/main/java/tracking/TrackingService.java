package tracking;

import javax.inject.*;
import javax.ws.rs.*;

import voucher.*;

@Named
@Path("/test")
public class TrackingService {

	@Inject
	private VoucherServiceRemote voucherService;

	@GET
	@Path("{name}")
	public String track(@PathParam("name") String s) {
		return voucherService.sayHello(s);
	}
}
