package com.proyecto.Gpsi.Security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/views/dashboard/**","/logout").authenticated()
			.and()
			.formLogin()
    		.failureUrl("/login_error")
			.loginPage("/login").permitAll()
				.usernameParameter("username")
				.defaultSuccessUrl("/views/dashboard/inicio")
				.passwordParameter("password")
				.and()
				.rememberMe().key("uniqueAndSecret").rememberMeParameter("checkRememberMe").tokenValiditySeconds(3600000)
			.and()
			.logout().deleteCookies("JSESSIONID").logoutSuccessUrl("/login?logout").permitAll();
            
	}

	//antMatchers("/views/gestion_envios/**","/views/seguimiento/**").hasAuthority("Seguimiento")
	//		.antMatchers("/views/gestion_envios/**").hasAuthority("Empresa")
//.antMatchers("/views/gestion_envios/listar","/views/enrutador/**","/views/bitacora_operaciones/**","/views/marcas/**","/views/roles/**","/views/ruta/**").hasAuthority("Admin")
	
	
}
