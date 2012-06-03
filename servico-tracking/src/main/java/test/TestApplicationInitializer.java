package test;

import javax.servlet.*;

import org.cdisource.springintegration.servletsupport.*;
import org.javasimon.console.*;
import org.springframework.web.*;
import org.springframework.web.context.*;
import org.springframework.web.context.support.*;
import org.springframework.web.servlet.*;

public class TestApplicationInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(TestConfig.class);

		ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
		servlet.setLoadOnStartup(1);
		servlet.addMapping("/mvc/*");

		ServletRegistration.Dynamic servlet2 = servletContext.addServlet("simon", SimonConsoleServlet.class);
		servlet2.addMapping("/javasimon-console/*");
		servlet2.setInitParameter("url-prefix", "/javasimon-console");

		servletContext.addListener(new ContextLoaderListener(ctx));
		servletContext.addListener(ApplicationContextFinderServletContextListener.class);
	}
}
