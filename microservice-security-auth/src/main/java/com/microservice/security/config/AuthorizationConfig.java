package com.microservice.security.config;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableAuthorizationServer
@Slf4j
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter{
	
	@Autowired
    private RedisConnectionFactory connectionFactory;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()")
				.checkTokenAccess("isAuthenticated()")
				.allowFormAuthenticationForClients();
		//super.configure(security);
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.jdbc(dataSource);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenServices(defaultTokenServices())
				//.tokenEnhancer(jwtAccessTokenConverter())
				.accessTokenConverter(jwtAccessTokenConverter())
				.authenticationManager(authenticationManager)
				.userDetailsService(userDetailsService);
	}
	
	@Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(connectionFactory);
    }
	
	@Bean
	public DefaultTokenServices defaultTokenServices() throws NoSuchAlgorithmException {
		DefaultTokenServices tokenServices = new DefaultTokenServices();
		tokenServices.setTokenStore(tokenStore());
		tokenServices.setSupportRefreshToken(true);
		tokenServices.setTokenEnhancer(jwtAccessTokenConverter());
		return tokenServices;
	}
	
	@Autowired
	private UserAuthenticationConverter MyUserAuthenticationConverter;
	
	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() throws NoSuchAlgorithmException {
		JwtAccessTokenConverter jwtaccessTokenConverter = new JwtAccessTokenConverter();
		DefaultAccessTokenConverter defaultAccessTokenConverter = new DefaultAccessTokenConverter();
		defaultAccessTokenConverter.setUserTokenConverter(MyUserAuthenticationConverter);
		jwtaccessTokenConverter.setAccessTokenConverter(defaultAccessTokenConverter);
		jwtaccessTokenConverter.setKeyPair(keyPair());
		return jwtaccessTokenConverter;
	}
	
	@SuppressWarnings("deprecation")
	public KeyPair keyPair() throws NoSuchAlgorithmException {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(1024);
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		log.info("公钥：{}",new String(Base64.encode(keyPair.getPublic().getEncoded())));
		log.info("私钥：{}",new String(Base64.encode(keyPair.getPrivate().getEncoded())));
		return keyPair;
	}
	
}
