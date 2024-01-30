package com.niupi.niuojbackendgateway.filter;

import cn.hutool.core.text.AntPathMatcher;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * @author jillion
 * @create com.niupi.niuojbackendgateway.filter - the name of the target package where the new class or interface will
 * be created. niuoj-backend-microservice - the name of the current project. null.java - the name of the PHP file that will be created. GlobalAuthFilter - the name of the new file which you specify in the New File dialog box during thefile creation. Tom - the login name of the current user. 2024/1/24 - the current system date. 11:39 - the current system time. 2024 - the current year. 01 - the current month. 24 - the current day of the month. 11 - the current hour. 39 - the current minute. IntelliJ IDEA - the name of the IDE in which the file will be created. 1月 - the first 3 letters of the month name. Example: Jan, Feb, etc. 一月 - full name of a month. Example: January, February, etc.
 */
@Component
public class GlobalAuthFilter implements GlobalFilter, Ordered {
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        String path = serverHttpRequest.getURI().getPath();
//       判断路径中是否存在内部调用服务前缀inner
        if (antPathMatcher.match("/**/inner/**", path)) {
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.FORBIDDEN);
            DataBufferFactory dataBufferFactory = response.bufferFactory();
            DataBuffer dataBuffer = dataBufferFactory.wrap("无权限".getBytes(StandardCharsets.UTF_8));
            return response.writeWith(Mono.just(dataBuffer));
        }
        return chain.filter(exchange);
    }

    /**
     * 优先级最高的过滤器
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
