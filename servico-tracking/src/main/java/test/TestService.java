package test;

import java.util.*;

import javax.inject.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

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

	@GET
	@Path("list/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Person> list(@PathParam("name") String s) {
		return voucherService.findByName(s);
	}
}
