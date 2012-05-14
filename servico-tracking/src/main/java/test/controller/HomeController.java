package test.controller;

import javax.enterprise.context.*;
import javax.inject.*;

import org.slf4j.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import test.HelloService;

@Controller
@RequestScoped
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Inject
	HelloService helloService;

	public HomeController() {
		logger.info("HomeController created");
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		logger.info("Welcome home! HelloService result is: " + helloService.sayHello("home"));
		return "home";
	}
}
