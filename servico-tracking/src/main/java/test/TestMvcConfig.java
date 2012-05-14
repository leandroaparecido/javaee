package test;

import org.cdisource.springintegration.*;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.*;

@Configuration
@EnableWebMvc
public class TestMvcConfig {

	@Bean
	public static CdiBeanFactoryPostProcessor cdiBeanFactoryPostProcessor() {
		return new CdiBeanFactoryPostProcessor();
	}

	@Bean
	public InternalResourceViewResolver configureInternalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
}
