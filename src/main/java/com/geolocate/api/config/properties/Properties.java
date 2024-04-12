package com.geolocate.api.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ComponentScan
@ConfigurationProperties(prefix = "application")
public class Properties {

	private String googleUrl;
	private String googleApiKey;
	private String environment;

	private Path path;
	private Securiry security;


	@Data
	public class Path {
		public String ping;
		public String geolocatesvc;
	}

	@Data
	public class Securiry {
		public String username;
		public String password;
	}
}
