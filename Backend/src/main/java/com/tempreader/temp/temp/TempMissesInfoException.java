package com.tempreader.temp.temp;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Date, Temp or Humidity is missing")
public class TempMissesInfoException extends Exception {
}
