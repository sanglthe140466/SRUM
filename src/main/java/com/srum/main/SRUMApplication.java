package com.srum.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = "com.srum")
@EntityScan(basePackages = "com.srum.entity")
@EnableJpaRepositories(basePackages = "com.srum.repository")
@EnableTransactionManagement(proxyTargetClass = true)
public class SRUMApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SRUMApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SRUMApplication.class);
    }


}
