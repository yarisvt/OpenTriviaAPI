package com.yaris.open_trivia_api;

import com.yaris.open_trivia_api.data.CheckAnswerRequest;
import com.yaris.open_trivia_api.data.Question;
import com.yaris.open_trivia_api.exceptions.NotFoundException;
import com.yaris.open_trivia_api.services.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;

/**
 * Custom API for the Open Trivia API that only sends the question and all possible answers. Then,
 * another request can be made to check whether the selected answer is the correct answer.
 */
@RestController
@RequiredArgsConstructor
public class OpenTriviaApiController {

  public final QuestionService questionService;

  /**
   * Server response when a GET request is send to /questions. A JSON object will be returned with 5
   * questions and the corresponding possible answers.
   *
   * @return the questions response
   */
  @GetMapping("/questions")
  public Question[] questions() throws IOException {
    return questionService.getQuestions();
  }

  /**
   * Server response when a POST request is send to /checkanswer. The body should contain a JSON
   * object with the question and answer. E.g.:
   * {
   *    "question": "What is the capital of the Netherlands?",
   *    "answer": "Amsterdam"
   * }
   *
   * The server sends a response whether the selected answer is the correct answer.
   *
   * @param checkAnswerRequest body of POST request containing the JSON data
   * @return boolean indicating whether the selected answer is the correct answer
   */
  @PostMapping(
      value = "/checkanswer",
      consumes = {MediaType.APPLICATION_JSON_VALUE})
  public boolean checkAnswer(@Valid @RequestBody CheckAnswerRequest checkAnswerRequest)
      throws NotFoundException {
    return questionService.isValidAnswer(checkAnswerRequest);
  }
}
