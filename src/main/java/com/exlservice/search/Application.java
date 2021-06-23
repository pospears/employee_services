package com.exlservice.search;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.exlservice.search.service.GeneratorService;

@SpringBootApplication
@ComponentScan(basePackages = "com.exlservice.search")
public class Application  
{

	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		logger.info(String.format("Starting up application and seeding data"));

		 ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		 context.getBean(GeneratorService.class).seedEmployeeData();
	}

    @Configuration
    public class DateTimeFormatConfiguration extends WebMvcConfigurerAdapter {
        @Override
        public void addFormatters(FormatterRegistry registry) {
            DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
            registrar.setUseIsoFormat(true);
            registrar.registerFormatters(registry);
        }
    }
}
