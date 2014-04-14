package com.jonnygold.sample;

public class X8Block implements IsBlock {
	
	public static final int WIDTH = 8;
	public static final int HEIGHT = 8;
	
	private static X8Block instance;
	
	private X8Block(){}
	
	public static X8Block getInstance(){
		if(instance == null){
			instance = new X8Block();
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
