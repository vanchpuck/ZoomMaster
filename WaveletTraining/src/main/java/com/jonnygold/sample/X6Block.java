package com.jonnygold.sample;

public final class X6Block implements IsBlock {

	public static final int WIDTH = 6;
	public static final int HEIGHT = 6;
	
	private static X6Block instance;
	
	private X6Block(){}
	
	public static X6Block getInstance(){
		if(instance == null){
			instance = new X6Block();
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

