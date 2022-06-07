package com.yaris.open_trivia_api.services;

import com.yaris.open_trivia_api.data.open_trivia_api.OpenTriviaApiJSONData;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** Class to test the RestService which makes a GET request to the original Open Trivia API. */
@RequiredArgsConstructor
class RestServiceTest {

  private static RestService restService;

  /** Set up RestService with OpenTrivia API URL */
  @BeforeAll
  static void setUp() {
    restService = new RestService(new RestTemplateBuilder().build());
    restService.setOpenTriviaApiUrl("https://opentdb.com/api.php?amount=5&type=multiple");
  }

  /**
   * Test request to original Open Trivia API. Should return a JSON object of length 5, namely 5
   * questions with possible answers.
   */
  @Test
  void testGetQuestionsAsObject() throws IOException {
    OpenTriviaApiJSONData openTriviaApiJSONData = restService.getQuestionsAsObject();
    assertEquals(5, openTriviaApiJSONData.getResults().length);
  }
}
