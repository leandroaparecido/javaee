package test;

import org.cdisource.springintegration.CdiBeanFactoryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
public class TestMvcConfig {

	@Bean
	public static CdiBeanFactoryPostProcessor cdiBeanFactoryPostProcessor() {
		return new CdiBeanFactoryPostProcessor();
	}

	/*
	@Bean
	public PlatformTransactionManager transactionManager() {
		JtaTransactionManager manager = new JtaTransactionManager();
		return manager;
	}
	*/

	@Bean
	public InternalResourceViewResolver configureInternalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
}
