package com.jonnygold.sample;

public class X4Block implements IsBlock {

	public static final int WIDTH = 8;
	public static final int HEIGHT = 8;
	
	private static X4Block instance;
	
	private X4Block(){}
	
	public static X4Block getInstance(){
		if(instance == null){
			instance = new X4Block();
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
