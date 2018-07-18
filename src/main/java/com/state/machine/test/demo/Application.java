package com.state.machine.test.demo;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.RequestContextFilter;

@Configuration
@ComponentScan
@EnableAutoConfiguration
class Application extends SpringBootServletInitializer  {

 
    @Override 
    public void onStartup(final ServletContext servletContext ) throws ServletException {
        super.onStartup(servletContext);
        System.out.println("*************************************************************************************************");
        servletContext.addFilter("requestContextFilter", new RequestContextFilter() ).addMappingForUrlPatterns(null, false, "/*");
    }
}
