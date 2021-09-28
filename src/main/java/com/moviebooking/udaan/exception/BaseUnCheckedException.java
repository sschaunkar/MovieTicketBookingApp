package com.moviebooking.udaan.exception;

import java.util.List;

import com.moviebooking.udaan.enums.ErrorCode;

public class BaseUnCheckedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private ErrorCode errorCode;
	private Object[] messagePositionalArgs;
	private Throwable exception;
	public BaseUnCheckedException() {
		super();
	}

	public BaseUnCheckedException(final ErrorCode errorCode, final Object... messagePositionalArgs) {
		super();
		this.errorCode = errorCode;
		this.messagePositionalArgs = messagePositionalArgs;
	}

	public BaseUnCheckedException(final ErrorCode errorCode, final List<String> errors) {
		super();
		this.errorCode = errorCode;
		final StringBuilder sb = new StringBuilder();
		if (null != errors && !errors.isEmpty()) {
			for (String val : errors) {
				sb.append(" ").append(val);
			}
			this.errorCode.setErrorMessage(sb.toString());
		}
	}

	public BaseUnCheckedException(final ErrorCode errorCode, final Throwable underlyingException,
			final Object... messagePositionalArgs) {
		super(underlyingException);
		this.errorCode = errorCode;
		this.messagePositionalArgs = messagePositionalArgs;
		this.exception = underlyingException;
	}

	public BaseUnCheckedException(final Throwable underlyingException) {
		super(underlyingException);
		this.exception = underlyingException;
	}

	public BaseUnCheckedException(final ErrorCode errorCode) {
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
