package com.moviebooking.udaan.exception;

import com.moviebooking.udaan.enums.ErrorCode;

public class BaseCheckedException extends Exception {
	private static final long serialVersionUID = 1L;

	private ErrorCode errorCode;
	private Object[] messagePositionalArgs;
	private Throwable exception;

	public BaseCheckedException(final ErrorCode errorCode, final Object... messagePositionalArgs) {
		super();
		this.errorCode = errorCode;
		this.messagePositionalArgs = messagePositionalArgs;
	}

	public BaseCheckedException(final ErrorCode errorCode, final Throwable underlyingException,
			final Object... messagePositionalArgs) {
		super(underlyingException);
		this.errorCode = errorCode;
		this.messagePositionalArgs = messagePositionalArgs;
		this.exception = underlyingException;
	}

	public BaseCheckedException(final Throwable underlyingException) {
		super(underlyingException);
		this.exception = underlyingException;
	}

	public BaseCheckedException(final ErrorCode errorCode) {
		super(errorCode.getErrorMessage());
		this.errorCode = errorCode;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	public Object[] getMessagePositionalArgs() {
		return messagePositionalArgs;
	}

	public void setMessagePositionalArgs(Object[] messagePositionalArgs) {
		this.messagePositionalArgs = messagePositionalArgs;
	}

	public Throwable getException() {
		return exception;
	}

	public void setException(Throwable exception) {
		this.exception = exception;
	}
}
