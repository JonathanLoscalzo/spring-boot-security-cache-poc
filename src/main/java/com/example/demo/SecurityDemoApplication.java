package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class SecurityDemoApplication {
    /**
     * https://dev.to/keysh/spring-security-with-jwt-3j76
     * 
     * @param args
     */
    public static void main(String[] args) {
	SpringApplication.run(SecurityDemoApplication.class, args);
    }
    
    /*
     * @Bean public LettuceConnectionFactory redisConnectionFactory() {
     * 
     * return new LettuceConnectionFactory(new
     * RedisStandaloneConfiguration("server", 6379)); }
     */

}
