package com.yaris.open_trivia_api.data;

import lombok.Data;

import java.util.List;

/**
 * Class that represents the data that the server will send when a GET request is made to /questions
 */
@Data
public class Question {

  private final String question;
  private final List<String> possibleAnswers;
}
