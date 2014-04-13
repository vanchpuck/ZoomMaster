package com.jonnygold.sample;

public final class X9Block implements IsBlock {

	public static final int WIDTH = 9;
	public static final int HEIGHT = 9;
	
	private static X9Block instance;
	
	private X9Block(){}
	
	public static X9Block getInstance(){
		if(instance == null){
			instance = new X9Block();
		}
		return instance;
	}
	
	@Override
	public int getWidth() {
		return WIDTH;
	}

	@Override
	public int getHeight() {
		return HEIGHT;
	}

}

