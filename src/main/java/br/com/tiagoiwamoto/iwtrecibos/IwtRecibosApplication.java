package br.com.tiagoiwamoto.iwtrecibos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties
public class IwtRecibosApplication {

    public static void main(String[] args) {
        SpringApplication.run(IwtRecibosApplication.class, args);
    }

}
