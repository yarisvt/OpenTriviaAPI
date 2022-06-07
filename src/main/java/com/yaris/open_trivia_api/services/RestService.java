package com.yaris.open_trivia_api.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yaris.open_trivia_api.data.open_trivia_api.OpenTriviaApiJSONData;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/** Make GET request to original Open Trivia API. */
@Component
@RequiredArgsConstructor
public class RestService {

  private final RestTemplate restTemplate;

  @Setter(AccessLevel.PACKAGE)
  @Value("${OPEN_TRIVIA_API_URL}")
  private String openTriviaApiUrl;

  /**
   * Makes a GET request to original Open Trivia API, and return the JSON response as an object.
   *
   * @return the questions as object
   * @throws JsonProcessingException Thrown when the ObjectMapper cannot parse the JSON data.
   */
  public OpenTriviaApiJSONData getQuestionsAsObject() throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();

    String response = this.restTemplate.getForObject(openTriviaApiUrl, String.class);

    return objectMapper.readValue(response, OpenTriviaApiJSONData.class);
  }
}
