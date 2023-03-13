package es.ua.biblioteca;

import org.aspectj.weaver.ast.And;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = User.withUsername("0103")
            .password(passwordEncoder.encode("0103"))
            .roles("USER")
            .build();

        UserDetails admin = User.withUsername("0000")
            .password(passwordEncoder.encode("0000"))
            .roles("USER", "ADMIN", "SUPER")
            .build();
        

        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        	
            .anyRequest().authenticated()
            .and()
//            .formLogin()
//            	.loginPage("/login")
//            	.usernameParameter("email")
//            	.permitAll()
//            .and()
            .httpBasic()
//            .and()
//            .logout().permitAll()
            ;
        	
        
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return encoder;
    }
}