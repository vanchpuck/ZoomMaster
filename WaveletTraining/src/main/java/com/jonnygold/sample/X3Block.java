package com.jonnygold.sample;

public final class X3Block implements IsBlock {

	public static final int WIDTH = 3;
	public static final int HEIGHT = 3;
	
	private static X3Block instance;
	
	private X3Block(){}
	
	public static X3Block getInstance(){
		if(instance == null){
			instance = new X3Block();
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
