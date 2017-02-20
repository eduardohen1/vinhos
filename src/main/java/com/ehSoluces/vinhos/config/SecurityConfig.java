package com.ehSoluces.vinhos.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("joao").password("joao").roles("PESQUISAR_VINHO").and()
			.withUser("maria").password("maria").roles("CADASTRAR_VINHO","PESQUISAR_VINHO");	
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
		   .antMatchers("/layout/**"); //tudo que puder acessar sem estar logado, é colocado aqui.
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//vinhos/** => qq coisa acima de /vinhos/pesquisar; vinhos/novo/ só quem tem permissão de cadastrar_vinho
		http
			.authorizeRequests()
				.antMatchers("/vinhos").hasRole("PESQUISAR_VINHO")
				.antMatchers("/vinhos/**").hasRole("CADASTRAR_VINHO")
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
	
}
