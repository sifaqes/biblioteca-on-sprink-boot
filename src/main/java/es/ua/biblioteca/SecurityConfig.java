package es.ua.biblioteca;

import java.io.IOException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
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
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.util.UrlPathHelper;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = User.withUsername("0103")
            .password(passwordEncoder.encode("0103"))
            .roles("USER")
            .build();

        UserDetails admin = User.withUsername("admin")
            .password(passwordEncoder.encode("admin"))
            .roles("USER", "ADMIN", "SUPER")
            .build();
        

        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public <CustomerUserDetails> SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        	
            .anyRequest().authenticated()
            .and()
            .formLogin()
            	.loginPage("/login")
            	.usernameParameter("email")
            	.passwordParameter("password")
            	.permitAll()
            	
            .and()
            //falta button html
            .logout()
    	        .logoutSuccessHandler((LogoutSuccessHandler) new LogoutSuccessHandler() {
    	         	
				@Override
				public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
						org.springframework.security.core.Authentication authentication)
						throws IOException, ServletException {
					
					System.out.println("El usario " + authentication.getName() + " ha cerrado la session.");
					UrlPathHelper helper = new UrlPathHelper();
					String contex = helper.getContextPath(request);
					response.sendRedirect(contex + "/login");
					
				}
    	            })
            .and()
            .rememberMe().tokenRepository(null)
            .and()
            .httpBasic()
            .and()
            .logout().permitAll()
            ;
        	
        
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return encoder;
    }
}