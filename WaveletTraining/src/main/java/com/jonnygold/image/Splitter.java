package com.jonnygold.image;

import com.jonnygold.wavelet.Signal;

public class Splitter implements IsSplitter {

	// Шаг разделителя по оси ОХ
	private int xStep;
	
	// Шаг разделителя по оси ОY
	private int yStep;
	
	// Высота окна
	private int height;
	
	// Шмрина окна
	private int width;
	
	public Splitter (int xStep, int yStep, int height, int width){
		this.xStep = xStep;
		this.yStep = yStep;
		this.height = height;
		this.width = width;
	}
	
	@Override
	public int getBlockCount(Signal signal) {
		return ((signal.width - width)/xStep + 1) * ((signal.height - height)/yStep + 1) ;
	}

	@Override
	public Signal getBlock(int idx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setBlock(Signal signal, int idx) {
		// TODO Auto-generated method stub
		
	}

}
