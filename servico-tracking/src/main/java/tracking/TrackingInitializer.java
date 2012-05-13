package tracking;

import javax.servlet.*;

import org.cdisource.springintegration.servletsupport.*;
import org.springframework.web.*;
import org.springframework.web.context.*;
import org.springframework.web.context.support.*;
import org.springframework.web.servlet.*;

public class TrackingInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(TrackingMvcConfig.class);

		ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
		servlet.setLoadOnStartup(1);
		servlet.addMapping("/mvc/*");

		servletContext.addListener(new ContextLoaderListener(ctx));
		servletContext.addListener(ApplicationContextFinderServletContextListener.class);
	}
}
