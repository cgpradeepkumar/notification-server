package com.infosat.notification.server.config;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.TemplateLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
//import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import static freemarker.template.Configuration.VERSION_2_3_27;

/**
 * @author pradeepcg
 * @created 07/11/2020 - 14:07
 */

@Configuration
public class FreeMarkerConfig {

    @Bean
    @Primary
    public FreeMarkerConfigurationFactoryBean getFreeMarkerConfiguration() {
        FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
        bean.setTemplateLoaderPath("/templates/mail/");
        return bean;
    }

//    @Bean
//    public FreeMarkerConfigurer freemarkerClassLoaderConfig() {
//        freemarker.template.Configuration configuration = new freemarker.template.Configuration(VERSION_2_3_27);
//        TemplateLoader templateLoader = new ClassTemplateLoader(this.getClass(), "/templates/mail");
//        configuration.setTemplateLoader(templateLoader);
//        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
//        freeMarkerConfigurer.setConfiguration(configuration);
//        return freeMarkerConfigurer;
//    }
}
