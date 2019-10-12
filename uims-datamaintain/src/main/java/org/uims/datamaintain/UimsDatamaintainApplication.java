package org.uims.datamaintain;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * <p>Datamaintain启动类。</p>
 *
 * <p> scanBasePackages 启动时扫描公用包 org.uims.common 中的公共配置 {@link org.uims.common.config.ResultResponseBodyAdvice}</p>
 * <p>MapperScan 指定扫描mybatis映射文件</p>
 * @author kyrie
 * @since 1.0
 */
@SpringBootApplication(scanBasePackages = {"org.uims.datamaintain","org.uims.common.config"})
@MapperScan(basePackages="org.uims.datamaintain.*.dao")
@EnableEurekaClient
public class UimsDatamaintainApplication {

    public static void main(String[] args) {
        SpringApplication.run(UimsDatamaintainApplication.class, args);
    }

    @Configuration
    public static class SecurityPermitAllConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests().anyRequest().permitAll()
                    .and().csrf().disable();
        }
    }
}
