package com.moviebooking.udaan.enums;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong"),
	BAD_REQUEST(HttpStatus.BAD_REQUEST, "Please check your request"),
	UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "unauthorized to perform this action"),
	ALREADY_BOOKED(HttpStatus.UNPROCESSABLE_ENTITY, "Requested Seats already Booked"),
	DUPLICATE_SEATS(HttpStatus.UNPROCESSABLE_ENTITY, "Duplicate Seats not Allowed"),
	NO_RECORDS_FOUND(HttpStatus.NO_CONTENT, "No Records Found"), 
	MISSING_REQUIRED_FIELDS(HttpStatus.BAD_REQUEST, "Please check your request"), 
	HOUSE_FULL(HttpStatus.UNPROCESSABLE_ENTITY, "Seats are already full"),
	NOT_AVAILABLE(HttpStatus.UNPROCESSABLE_ENTITY, "Requested Seats not Available");
	
	private HttpStatus subErroCode;
	private String errorMessage;

	private ErrorCode(HttpStatus subErroCode, String errorMessage) {
		this.subErroCode = subErroCode;
		this.errorMessage = errorMessage;
	}

	public HttpStatus getSubErroCode() {
		return subErroCode;
	}

	public void setSubErroCode(HttpStatus subErroCode) {
		this.subErroCode = subErroCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
