package es.ua.biblioteca.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
 
@Configuration
public class SecurityConfig {
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		   http
			.authorizeRequests()
				//.antMatchers("/", "/home").permitAll()
				.antMatchers(HttpMethod.GET, "/api/book/**").permitAll()
				.antMatchers(HttpMethod.GET, "/searchBook").permitAll()
				.antMatchers(HttpMethod.POST, "/createBook").hasRole("ADMIN")
				.antMatchers(HttpMethod.GET, "/createBook").hasRole("ADMIN")
				.antMatchers(HttpMethod.POST, "/api/book/").hasRole("ADMIN")
		        .antMatchers(HttpMethod.PUT, "/api/book/**").hasRole("ADMIN")
		        .antMatchers(HttpMethod.DELETE, "/api/book/**").hasRole("ADMIN")
				//.anyRequest().authenticated()
				.and()
			.formLogin()
				//.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.permitAll();
        return http.build();
    }
	
	 @Bean
	 public InMemoryUserDetailsManager userDetailsService() {
	     UserDetails user = User.withDefaultPasswordEncoder()
	         .username("user")
	         .password("password")
	          .roles("ADMIN")
	          .build();
	     return new InMemoryUserDetailsManager(user);
	 }
}
