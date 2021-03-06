package org.angel.devWiki.api;

import java.io.UnsupportedEncodingException;

import javax.sql.DataSource;

import org.angel.devWiki.api.config.CustomizedRestMvcConfiguration;
import org.angel.devWiki.api.config.ProdDataBaseConfig;
import org.angel.devWiki.api.config.SecurityConfig;
import org.angel.devWiki.api.config.WebConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
@Import({ProdDataBaseConfig.class, SecurityConfig.class, WebConfig.class, CustomizedRestMvcConfiguration.class})
@Qualifier("dev-wiki.api.config")
public class App extends RepositoryRestMvcConfiguration {
	
	@Autowired
	private Environment env;
	
	@Autowired
	@Qualifier("users")
	private DataSource userDataSource; 
	

	 @Bean
	 @Qualifier("users")
	 public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(userDataSource);
	 }
	 	 
	 public static void main(String[] args) throws UnsupportedEncodingException {
		 SpringApplication.run(App.class);
	 }
	 
	
//	@Bean
//	public OncePerRequestFilter validationFilter(){
//		return new JWTAuthFilter();
//	}

}
