package com.pandora.infrastructure.config;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class HttpConfig {

    @Configuration
    public class HttpsConfig {


        @Bean
        public ServletWebServerFactory serverFactory() {
            TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
            tomcat.addAdditionalTomcatConnectors(createStandardConnector());
            return tomcat;
        }

        private Connector createStandardConnector() {
            Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
            connector.setPort(8080);
            return connector;
        }
    }
}
