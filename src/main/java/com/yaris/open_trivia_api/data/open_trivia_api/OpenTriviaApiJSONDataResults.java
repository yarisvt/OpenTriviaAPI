package com.yaris.open_trivia_api.data.open_trivia_api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/** Class that represents the 'results' key from the data in the Open Trivia API. */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpenTriviaApiJSONDataResults implements Serializable {

  private String category;
  private String type;
  private String difficulty;
  private String question;
  private String correct_answer;
  private List<String> incorrect_answers;

  /**
   * Gets all possible answers by concatenating the incorrect answers with the correct answer.
   *
   * @return the all possible answers
   */
  public List<String> getAllPossibleAnswers() {
    List<String> allPossibleAnswers = new ArrayList<>(incorrect_answers);
    allPossibleAnswers.add(getCorrect_answer());
    return allPossibleAnswers;
  }
}
