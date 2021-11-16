package com.irctc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.util.UrlPathHelper;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/*
	 * @Bean public HttpFirewall allowSemicolonHttpFirewall() { StrictHttpFirewall
	 * firewall = new StrictHttpFirewall(); firewall.setAllowUrlEncodedSlash(true);
	 * firewall.setAllowSemicolon(true); return firewall; }
	 */

	@Bean
	public HttpFirewall defaultHttpFirewall() {
		return new DefaultHttpFirewall();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
		web.httpFirewall(defaultHttpFirewall());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authentication) throws Exception {
		authentication.inMemoryAuthentication().withUser("user").password(passwordEncoder().encode("user"))
				.authorities("ROLE_USER")
				// .roles("USER")
				.and().withUser("admin").password(passwordEncoder().encode("admin"))
				.authorities("ROLE_USER", "ROLE_ADMIN")
		// .roles("USER", "ADMIN")
		;
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.httpBasic().and().authorizeRequests().antMatchers("/irctc/welcome").permitAll()
				.antMatchers(HttpMethod.POST, "/irctc/routes").hasAnyRole("ADMIN")
				.antMatchers(HttpMethod.GET, "/irctc/routes/*").hasAnyRole("ADMIN")
				.antMatchers(HttpMethod.GET, "/irctc/routes/trains").hasAnyRole("USER", "ADMIN")
				.antMatchers(HttpMethod.PUT, "/irctc/routes/*").hasAnyRole("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/irctc/routes/*/*").hasAnyRole("ADMIN")
				.antMatchers(HttpMethod.POST, "/irctc/trains").hasAnyRole("ADMIN")
				.antMatchers(HttpMethod.PUT, "/irctc/trains/*").hasAnyRole("ADMIN")
				.antMatchers(HttpMethod.GET, "/routes/*/trains").hasAnyRole("USER", "ADMIN")
				.antMatchers("/swagger-ui.html", "/swagger-resources/**").permitAll().anyRequest().authenticated().and()
				.csrf().disable().formLogin().disable();
	}
}