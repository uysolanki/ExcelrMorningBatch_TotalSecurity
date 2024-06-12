package com.excelr.cms.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity 
public class MyWebSecurityConfig //extends WebSecurityConfigurerAdapter
{
	
//	@Override //Authentication
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception 
//	{
////		auth.inMemoryAuthentication()
////		.withUser("Aditya")
////		.password("aditya123")		// cleartext ADMIN C R U D
////  		.authorities("ADMIN")
////  		.and()
////  		.withUser("Krushna")
////  		.password("krushna123")		// cleartext ADMIN C R U D
////  		.authorities("ADMIN")
////  		.and()
////  		.withUser("Kedar")
////  		.password("kedar123")		// cleartext ADMIN C R
////  		.authorities("USER")
////  		.and()
////  		.withUser("Ritesh")
////  		.password("rites123")		// cleartext ADMIN C R
////  		.authorities("USER");
//		
//		auth.authenticationProvider(myAuthProvider());
//	}
	
	
	private DaoAuthenticationProvider myAuthProvider() {
		DaoAuthenticationProvider daoAuth=new DaoAuthenticationProvider();
		daoAuth.setUserDetailsService(myUserDetailService());
		daoAuth.setPasswordEncoder(myPasswordEncoder());
		return daoAuth;
	}

	@Bean
	public PasswordEncoder myPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService myUserDetailService() {
		return new MyUserDetailsServiceImpl();
	}

//	@Override  //Authorisation
//	protected void configure(HttpSecurity http) throws Exception {
//		 http.authorizeRequests()
//	        .antMatchers("/","/listOfCustomers","/showFormToAddCustomer","/403").hasAnyAuthority("USER","ADMIN")
//	        .antMatchers("/updatecustomerform/**","/showFormToAddUser","/deletecustomer/**").hasAuthority("ADMIN")
//	        .anyRequest().authenticated()
//	        .and()
//	        .formLogin().loginProcessingUrl("/login").successForwardUrl("/listOfCustomers").permitAll()
//	        .and()
//	        .logout().logoutSuccessUrl("/login").permitAll()
//	        .and()
//	        .exceptionHandling().accessDeniedPage("/403")  //if there is 403 status code
//	        .and()
//	        .cors().and().csrf().disable();
//	}
	
//	@Bean
// 	public PasswordEncoder getPasswordEncoder()
// 	{
// 		return NoOpPasswordEncoder.getInstance();
// 	}

	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception 
	{
		 http.authorizeRequests()
        .antMatchers("/","/listOfCustomers","/showFormToAddCustomer","/403").hasAnyAuthority("USER","ADMIN")
        .antMatchers("/updatecustomerform/**","/showFormToAddUser","/deletecustomer/**").hasAuthority("ADMIN")
        .anyRequest().authenticated()
        .and()
        .formLogin().loginProcessingUrl("/login").successForwardUrl("/listOfCustomers").permitAll()
        .and()
        .logout().logoutSuccessUrl("/login").permitAll()
        .and()
        .exceptionHandling().accessDeniedPage("/403")  //if there is 403 status code
        .and()
        .cors().and().csrf().disable();
		
		 http.authenticationProvider(myAuthProvider());
         return http.build();
    }
}
