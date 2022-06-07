package com.yaris.open_trivia_api.data.open_trivia_api;

import lombok.Data;

import java.io.Serializable;

/** Class that represents the data from the Open Trivia API. */
@Data
public class OpenTriviaApiJSONData implements Serializable {
  private int response_code;
  private OpenTriviaApiJSONDataResults[] results;
}
