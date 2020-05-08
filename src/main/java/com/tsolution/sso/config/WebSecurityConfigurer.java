package com.tsolution.sso.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
//@Order(1)
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

	// @Value("${security.Logistics.signing-key}")
	private String signingKey = "MaYzkSjmkzPC57L";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Bean
	@Autowired
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/languages**", "/swagger**/**", "/webjars/**", "/v2/api-docs", "/api/**");
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http// .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()//
//                .exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)).and()//
				.authorizeRequests()
				.antMatchers("/oauth/**", "/languages**", "/swagger**/**", "/webjars/**", "/v2/api-docs", "/api/**")
				.permitAll().anyRequest().authenticated().and()//
				.httpBasic().disable().cors().and().csrf().disable()//
				.headers().frameOptions().disable().and()//
				.formLogin().permitAll();
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		// converter.setVerifierKey(this.signingKey);
		converter.setSigningKey(this.signingKey);
		return converter;
	}

	@Bean
	public TokenStore tokenStore() {
		return new JdbcTokenStore(this.jdbcTemplate.getDataSource());
	}

	@Bean
	@Primary
	public DefaultTokenServices tokenServices() {
		DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setTokenStore(this.tokenStore());
		defaultTokenServices.setSupportRefreshToken(true);
		// Token Timeout. With this is 1 day ^^
		defaultTokenServices.setAccessTokenValiditySeconds(24 * 60 * 60);
		defaultTokenServices.setRefreshTokenValiditySeconds(30 * 24 * 60 * 60);
		return defaultTokenServices;
	}

}