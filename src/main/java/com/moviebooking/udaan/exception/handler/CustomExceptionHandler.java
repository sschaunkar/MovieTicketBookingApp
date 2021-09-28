package com.moviebooking.udaan.exception.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.moviebooking.udaan.exception.BaseCheckedException;
import com.moviebooking.udaan.exception.BaseUnCheckedException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomExceptionHandler {

	@ExceptionHandler({ BaseUnCheckedException.class })
	public ResponseEntity<?> handleRuntimeException(final BaseUnCheckedException ex) {
		log.error("Api failed with error {} and {} UnAuthorizedException with stack trace : {}",
				ex.getErrorCode().getSubErroCode(), ex.getErrorCode().getErrorMessage(), ex);
		ApiExceptionResponseDto response = ApiExceptionResponseDto.builder()
				.displayMessage(ex.getErrorCode().getErrorMessage())
				.errorCode(ex.getErrorCode().getSubErroCode().value()).message(ex.getErrorCode().name()).build();
		return new ResponseEntity<ApiExceptionResponseDto>(response, new HttpHeaders(),
				ex.getErrorCode().getSubErroCode());
	}

	@ExceptionHandler({ BaseCheckedException.class })
	public ResponseEntity<?> handleException(final BaseCheckedException ex) {
		log.error("Api failed with error {} and {} UnAuthorizedException with stack trace : {}",
				ex.getErrorCode().getSubErroCode(), ex.getErrorCode().getErrorMessage(), ex);
		ApiExceptionResponseDto response = ApiExceptionResponseDto.builder()
				.displayMessage(ex.getErrorCode().getErrorMessage())
				.errorCode(ex.getErrorCode().getSubErroCode().value()).message(ex.getErrorCode().name()).build();
		return new ResponseEntity<ApiExceptionResponseDto>(response, new HttpHeaders(),
				ex.getErrorCode().getSubErroCode());
	}
}
