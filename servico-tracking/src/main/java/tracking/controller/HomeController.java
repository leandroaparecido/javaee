package tracking.controller;

import javax.enterprise.context.*;
import javax.inject.*;

import org.slf4j.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import voucher.*;

@Controller
@RequestScoped
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Inject
	private VoucherServiceRemote voucherService;

	public HomeController() {
		logger.info("HomeController criado");
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		logger.info("Welcome home! VoucherService result is: " + voucherService.sayHello("home"));
		return "home";
	}
}
