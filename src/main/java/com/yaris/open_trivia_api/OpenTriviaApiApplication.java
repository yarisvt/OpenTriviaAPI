package com.yaris.open_trivia_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** Entry point for custom API for the Open Trivia API. */
@SpringBootApplication(scanBasePackages = {"com.yaris.open_trivia_api"})
public class OpenTriviaApiApplication {

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    SpringApplication.run(OpenTriviaApiApplication.class, args);
  }
}
