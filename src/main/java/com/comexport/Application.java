package com.comexport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableAutoConfiguration
@EnableWebMvc
public class Application {

    protected SpringApplicationBuilder configure( SpringApplicationBuilder applicationBuilder ) {
        return applicationBuilder.sources( Application.class );
    }

    public static void main( String[] args ) {
        SpringApplication.run( Application.class, args );
    }
}
