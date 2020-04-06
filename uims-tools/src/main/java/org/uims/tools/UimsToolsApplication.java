package org.uims.tools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.uims.tools.threadpool.ThreadPoolConfig;

@SpringBootApplication
@EnableConfigurationProperties(value = {ThreadPoolConfig.class})
public class UimsToolsApplication {

    public static void main(String[] args) {
        SpringApplication.run(UimsToolsApplication.class, args);
    }

}
