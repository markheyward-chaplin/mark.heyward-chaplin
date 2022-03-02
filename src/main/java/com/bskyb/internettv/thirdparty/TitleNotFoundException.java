package com.bskyb.internettv.thirdparty;

public class TitleNotFoundException extends Exception {

	private static final long serialVersionUID = -4245074485224397137L;

	public TitleNotFoundException() {
		super();
	}

	public TitleNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TitleNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public TitleNotFoundException(String message) {
		super(message);
	}

	public TitleNotFoundException(Throwable cause) {
		super(cause);
	}
}
