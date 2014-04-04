package com.jonnygold.image;

public final class SplitOptions {

	// Шаг разделителя по оси ОХ
	private final int xStep;
	
	// Шаг разделителя по оси ОY
	private final int yStep;
	
	// Высота окна
	private final int height;
	
	// Шмрина окна
	private final int width;
	
	public SplitOptions (int xStep, int yStep, int height, int width){
		this.xStep = xStep;
		this.yStep = yStep;
		this.height = height;
		this.width = width;
	}
	
	public final int getHeight() {
		return height;
	}
	
	public final int getWidth() {
		return width;
	}
	
	public final int getXStep() {
		return xStep;
	}
	
	public final int getYStep() {
		return yStep;
	}
	
}
