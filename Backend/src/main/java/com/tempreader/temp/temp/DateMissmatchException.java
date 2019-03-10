package com.tempreader.temp.temp;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = "Date does not match pattern")
public class DateMissmatchException extends Exception {
}
