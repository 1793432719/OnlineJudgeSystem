package com.niupi.niuojbackendgateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

import java.util.Arrays;

/**
 * @author jillion
 * @create com.niupi.niuojbackendgateway.config - the name of the target package where the new class or interface will
 * be created. niuoj-backend-microservice - the name of the current project. null.java - the name of the PHP file that will be created. CorsConfig - the name of the new file which you specify in the New File dialog box during thefile creation. Tom - the login name of the current user. 2024/1/24 - the current system date. 11:34 - the current system time. 2024 - the current year. 01 - the current month. 24 - the current day of the month. 11 - the current hour. 34 - the current minute. IntelliJ IDEA - the name of the IDE in which the file will be created. 1月 - the first 3 letters of the month name. Example: Jan, Feb, etc. 一月 - full name of a month. Example: January, February, etc.
 */
@Configuration
public class CorsConfig {
    @Bean
    public CorsWebFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedMethod("*");
        config.setAllowCredentials(true);
        // todo 实际改为线上真实域名、本地域名
        config.setAllowedOriginPatterns(Arrays.asList("*"));
        config.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
        source.registerCorsConfiguration("/**", config);
        return new CorsWebFilter(source);
    }

}
