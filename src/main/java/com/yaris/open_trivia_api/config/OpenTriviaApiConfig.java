package com.yaris.open_trivia_api.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/** Config file */
@Configuration
@ComponentScan({"com.yaris.open_trivia_api"})
public class OpenTriviaApiConfig {

  /**
   * Gets rest template.
   *
   * @return the rest template
   */
  @Bean
  public RestTemplate getRestTemplate() {
    return new RestTemplateBuilder().build();
  }
}
