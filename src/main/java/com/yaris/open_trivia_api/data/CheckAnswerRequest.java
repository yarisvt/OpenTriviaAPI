package com.yaris.open_trivia_api.data;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Class that represents the JSON data that the user should send when making a POST request to
 * /checkanswer.
 */
@Data
@AllArgsConstructor
public class CheckAnswerRequest {

  @NotNull private String question;

  @NotNull private String answer;
}
