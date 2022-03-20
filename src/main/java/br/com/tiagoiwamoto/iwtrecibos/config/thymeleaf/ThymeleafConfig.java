package br.com.tiagoiwamoto.iwtrecibos.config.thymeleaf;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 07/03/2022 | 06:55
 */

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Configuration
@RequiredArgsConstructor
public class ThymeleafConfig {


    @Bean
    public ClassLoaderTemplateResolver yourTemplateResolver() {
        ClassLoaderTemplateResolver yourTemplateResolver = new ClassLoaderTemplateResolver();
        yourTemplateResolver.setPrefix("templates/");
        yourTemplateResolver.setSuffix(".html");
        yourTemplateResolver.setTemplateMode(TemplateMode.HTML);
        yourTemplateResolver.setCharacterEncoding("UTF-8");
        yourTemplateResolver.setOrder(0);  // this is iportant. This way spring
        //boot will listen to both places 0
        //and 1

        return yourTemplateResolver;
    }

}
