package com.jonnygold.sample;

public class DataSourceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7410979950275859107L;

	public DataSourceException() {
		super();
	}
	
	public DataSourceException(String message) {
		super(message);
	}
	
	public DataSourceException(Throwable cause){
		super(cause);
	}
	
	public DataSourceException(String message, Throwable cause){
		super(message, cause);
	}
	
}
