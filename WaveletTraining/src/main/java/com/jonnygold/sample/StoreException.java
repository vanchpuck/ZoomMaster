package com.jonnygold.sample;

public class StoreException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8336588399921908994L;

	public StoreException() {
		super();
	}
	
	public StoreException(String message) {
		super(message);
	}
	
	public StoreException(Throwable cause){
		super(cause);
	}
	
	public StoreException(String message, Throwable cause){
		super(message, cause);
	}
}
