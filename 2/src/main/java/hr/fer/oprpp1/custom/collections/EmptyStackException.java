package hr.fer.oprpp1.custom.collections;

public class EmptyStackException extends RuntimeException {

	private static final long serialVersionUID = 8971006009421026750L;

	public EmptyStackException() {
	}

	public EmptyStackException(String message) {
		super(message);
	}

	public EmptyStackException(Throwable cause) {
		super(cause);
	}

	public EmptyStackException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmptyStackException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
