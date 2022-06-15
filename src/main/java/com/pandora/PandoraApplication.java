package com.pandora;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pandora.infrastructure.common.isolator.Isolator;
import com.pandora.infrastructure.common.isolator.MockIsolator;
import com.pandora.infrastructure.common.isolator.NormalIsolator;
import com.pandora.infrastructure.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class PandoraApplication {

    public static void main(String[] args) {
        SpringApplication.run(PandoraApplication.class, args);
    }

    @Bean
    @ConditionalOnMissingBean(Isolator.class)
    public Isolator isolator() {
        return new NormalIsolator();
    }

    @Bean
    public Gson gson() {
        //实例类中日期的类型必须是这几种类型java.util.Date, java.sql.Timestamp, java.sql.Date, setDateFormat才能生效
        return new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    }

    @Configuration
    public class WebConfigurer implements WebMvcConfigurer {

        @Autowired
        private LoginInterceptor loginInterceptor;

        @Override
        public void addInterceptors(InterceptorRegistry registry){
            registry.addInterceptor(loginInterceptor)
                    .addPathPatterns("/**")
                    .excludePathPatterns("/error")
                    .excludePathPatterns("/pandora/user/login");
        }
    }
}
