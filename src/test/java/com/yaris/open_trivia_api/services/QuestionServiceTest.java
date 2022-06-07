package com.yaris.open_trivia_api.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yaris.open_trivia_api.data.CheckAnswerRequest;
import com.yaris.open_trivia_api.data.Question;
import com.yaris.open_trivia_api.data.open_trivia_api.OpenTriviaApiJSONData;
import com.yaris.open_trivia_api.data.open_trivia_api.OpenTriviaApiJSONDataResults;
import com.yaris.open_trivia_api.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class QuestionServiceTest {

  private static QuestionService questionService;

  /**
   * Set up Question service.
   *
   * @throws JsonProcessingException Thrown by RestService
   */
  @BeforeAll
  static void setUp() throws JsonProcessingException {
    RestService restService = Mockito.mock(RestService.class);
    OpenTriviaApiJSONData openTriviaApiJSONData = new OpenTriviaApiJSONData();

    OpenTriviaApiJSONDataResults[] openTriviaApiJSONDataResultsArr =
        new OpenTriviaApiJSONDataResults[1];
    OpenTriviaApiJSONDataResults openTriviaApiJSONDataResults =
        new OpenTriviaApiJSONDataResults(
            "Geography",
            "multiple",
            "medium",
            "What is the only country in the world with a flag that doesn&#039;t have four right angles?",
            "Nepal",
            new ArrayList<>(Arrays.asList("Panama", "Angola", "Egypt")));

    openTriviaApiJSONDataResultsArr[0] = openTriviaApiJSONDataResults;
    openTriviaApiJSONData.setResults(openTriviaApiJSONDataResultsArr);
    Mockito.when(restService.getQuestionsAsObject()).thenReturn(openTriviaApiJSONData);

    questionService = new QuestionService(restService);
    questionService.setQuestionsAmount(1);
  }

  @Test
  void testGetQuestions() throws IOException {
    Question[] questions = questionService.getQuestions();
    assertEquals(1, questions.length);
    assertEquals(4, questions[0].getPossibleAnswers().size());
  }

  @Test
  void testIsValidAnswerWithoutRequestBody() {
    CheckAnswerRequest checkAnswerRequest = new CheckAnswerRequest("", "");
    assertThrows(NotFoundException.class, () -> questionService.isValidAnswer(checkAnswerRequest));
  }

  @Test
  void testIsValidAnswerWithRequestBodyCorrectAnswer() throws NotFoundException, IOException {
    questionService.getQuestions();
    CheckAnswerRequest checkAnswerRequest =
        new CheckAnswerRequest(
            "What is the only country in the world with a flag that doesn&#039;t have four right angles?",
            "Nepal");
    assertTrue(questionService.isValidAnswer(checkAnswerRequest));
  }

  @Test
  void testIsValidAnswerWithRequestBodyInCorrectAnswer() throws NotFoundException, IOException {
    questionService.getQuestions();
    CheckAnswerRequest checkAnswerRequest =
        new CheckAnswerRequest(
            "What is the only country in the world with a flag that doesn&#039;t have four right angles?",
            "Panama");
    assertFalse(questionService.isValidAnswer(checkAnswerRequest));
  }
}
