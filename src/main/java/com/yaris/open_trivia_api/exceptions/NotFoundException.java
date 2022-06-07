package com.yaris.open_trivia_api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Bad request exception, used when the body of a POST request to /checkanswer does not
 * contain the correct parameters.
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Not found")
public class NotFoundException extends Exception {}
