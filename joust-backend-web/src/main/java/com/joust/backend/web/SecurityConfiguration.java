package com.joust.backend.web;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@Configuration
@ImportResource({ "classpath:beans-security.xml" })
@EnableWebSecurity
public abstract class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Bean
  public abstract DataSource dataSource();

  @Bean
  public JdbcClientDetailsService clientDetails() {
    return new JdbcClientDetailsService(dataSource());
  }

  @Bean
  public JdbcTokenStore tokenStore() {
    return new JdbcTokenStore(dataSource());
  }

  @Bean
  public DefaultTokenServices tokenServices() {
    DefaultTokenServices tokenServices = new DefaultTokenServices();
    tokenServices.setTokenStore(tokenStore());
    tokenServices.setSupportRefreshToken(true);
    tokenServices.setClientDetailsService(clientDetails());
    return tokenServices;
  }

}