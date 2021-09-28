package com.moviebooking.udaan.exception.handler;

import com.moviebooking.udaan.exception.handler.ApiExceptionResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class ApiExceptionResponseDto {
	 	private Integer errorCode;
	    private String status;
	    private String message;
	    private String displayMessage;
}
