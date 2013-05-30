package com.dear.exception;

public class IndexBuildException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5516866037061329925L;

	public IndexBuildException(Exception e) {
		super(e);
	}
}
