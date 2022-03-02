package com.bskyb.internettv.thirdparty;

public class TechnicalFailureException extends Exception {

	private static final long serialVersionUID = 3134750950743793831L;

	public TechnicalFailureException() {
		super();
	}

	public TechnicalFailureException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TechnicalFailureException(String message, Throwable cause) {
		super(message, cause);
	}

	public TechnicalFailureException(String message) {
		super(message);
	}

	public TechnicalFailureException(Throwable cause) {
		super(cause);
	}
}
