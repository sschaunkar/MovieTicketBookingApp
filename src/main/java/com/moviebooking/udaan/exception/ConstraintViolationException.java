package com.moviebooking.udaan.exception;

import java.util.List;

import com.moviebooking.udaan.enums.ErrorCode;

@SuppressWarnings("serial")
public class ConstraintViolationException {
	private List<String> violations;

	public ConstraintViolationException() {
		super();
	}

	public ConstraintViolationException(final Exception ex) {
		super();
	}

	public ConstraintViolationException(final List<String> violations) {
		super();
		this.violations = violations;
	}

//	public ConstraintViolationException(List<String> errors, ErrorCode missingRequiredFields) {
//		super(missingRequiredFields, errors);
//		this.violations = errors;
//	}

	public List<String> getViolations() {
		return violations;
	}

	public void setViolations(final List<String> violations) {
		this.violations = violations;
	}
}
