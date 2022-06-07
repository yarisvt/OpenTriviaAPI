package com.yaris.open_trivia_api.data.open_trivia_api;

import com.yaris.open_trivia_api.data.open_trivia_api.OpenTriviaApiJSONDataResults;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * Class to test the OpenTriviaApiJSONDataResults by checking whether it correctly returns all
 * possible answers.
 */
class OpenTriviaApiJSONDataResultsTest {

  /** Test get all possible answers. */
  @Test
  void testGetAllPossibleAnswers() {

    OpenTriviaApiJSONDataResults openTriviaApiJSONDataResults =
        new OpenTriviaApiJSONDataResults(
            "Geography",
            "multiple",
            "medium",
            "What is the only country in the world with a flag that doesn&#039;t have four right angles?",
            "Nepal",
            new ArrayList<>(Arrays.asList("Panama", "Angola", "Egypt")));
    assertArrayEquals(
        new String[] {"Panama", "Angola", "Egypt", "Nepal"},
        openTriviaApiJSONDataResults.getAllPossibleAnswers().toArray());
  }
}
