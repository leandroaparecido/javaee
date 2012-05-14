package test;

import javax.inject.*;
import javax.ws.rs.*;

@Named
@Path("/test")
public class TestService {

	@Inject
	private HelloService voucherService;

	@GET
	@Path("{name}")
	public String track(@PathParam("name") String s) {
		return voucherService.sayHello(s);
	}
}
