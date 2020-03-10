package com.ccruza.microservices.monedamicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.ccruza.microservices.monedamicroservice")
@EnableDiscoveryClient
public class MonedaMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonedaMicroserviceApplication.class, args);
	}

//	@EnableWebSecurity // Jwt
//	@Configuration
//	class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//		@Override
//		protected void configure(HttpSecurity http) throws Exception {
//			http.csrf().disable()
//					.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
////					.authorizeRequests().antMatchers(HttpMethod.POST, "/moneda/login").permitAll().anyRequest()
//					.authorizeRequests().antMatchers().permitAll().anyRequest().authenticated();
//			http.cors();
//		}
//	}

}
