package com.yaris.open_trivia_api.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yaris.open_trivia_api.data.CheckAnswerRequest;
import com.yaris.open_trivia_api.data.Question;
import com.yaris.open_trivia_api.data.open_trivia_api.OpenTriviaApiJSONDataResults;
import com.yaris.open_trivia_api.exceptions.NotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/** Service with logic for the GET routes. */
@Service
@RequiredArgsConstructor
public class QuestionService {

  private static final HashMap<String, String> questionsWithCorrectAnswer = new HashMap<>();

  private final RestService restService;

  @Setter(AccessLevel.PACKAGE)
  @Value("${QUESTIONS_AMOUNT}")
  private int questionsAmount;

  /**
   * Get the question with possible answers.
   *
   * @return Array of questions with corresponding possible answers
   * @throws JsonProcessingException Thrown by RestService
   */
  public Question[] getQuestions() throws JsonProcessingException {
    // clear the Map that contains the correct answers for each question when it becomes to large
    if (questionsWithCorrectAnswer.size() > 10000) {
      questionsWithCorrectAnswer.clear();
    }

    Question[] questionsData = new Question[questionsAmount];
    int counter = 0;
    for (OpenTriviaApiJSONDataResults result : restService.getQuestionsAsObject().getResults()) {
      List<String> possibleAnswers = result.getAllPossibleAnswers();
      Collections.shuffle(possibleAnswers);
      questionsData[counter] = new Question(result.getQuestion(), possibleAnswers);

      // store question with correct answer, needed when user sends POST request to check answer
      questionsWithCorrectAnswer.put(result.getQuestion(), result.getCorrect_answer());

      counter++;
    }
    // send custom JSON response with question and possible answers
    return questionsData;
  }

  /**
   * Return whether the selected answer is correct.
   *
   * @param checkAnswerRequest Body of POST request containing the question and selected answer
   * @return boolean whether the selected answer is the correct answer
   * @throws NotFoundException the NotFound exception
   */
  public boolean isValidAnswer(CheckAnswerRequest checkAnswerRequest) throws NotFoundException {
    String correctAnswer = questionsWithCorrectAnswer.get(checkAnswerRequest.getQuestion());

    // a POST request is made with a question that is not present in the Map
    if (correctAnswer == null) {
      throw new NotFoundException();
    }
    return correctAnswer.equals(checkAnswerRequest.getAnswer());
  }
}
