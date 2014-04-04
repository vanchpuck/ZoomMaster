package com.jonnygold.image;

import com.jonnygold.wavelet.Signal;

public class SignalSplitter {

	private final Signal signal;
	
	private SplitOptions options;
	
	private int blocksPerRow;
	
	private int blocksCount;
	
	public SignalSplitter(Signal signal, SplitOptions options){
		this.signal = signal;
		this.options = options;
		
		setSplitOptions(options);
	}
	
	public Signal getBlock(int idx){
		int y = getY(idx);
		int x = getX(idx);
		if(y+options.getHeight() > signal.height || x+options.getWidth() > signal.width){
			throw new IllegalArgumentException("Втекущей реализации выделить невозможно выделить блок: "+idx);
		}
		return signal.getSignal(y, x, options.getHeight(), options.getWidth());
	}
	
	public int getBlocksCount(){
		return blocksCount;
	}
	
	public void setBlock(Signal block, int idx){
		if(block.width != options.getWidth() || block.height != options.getHeight()){
			throw new IllegalArgumentException("Размер блока не соответствует заданным параметрам разделения.");
		} 
		else if(block.width != options.getWidth() || block.height != options.getHeight()){
			throw new IllegalArgumentException("Втекущей реализации невозможно установить блок: "+idx);
		}
		signal.setSignal(block, getY(idx), getX(idx));
	}
	
	public void setSplitOptions(SplitOptions options){
		this.options = options;
		
		int blocksPerColumn = (signal.height - options.getHeight())/options.getYStep() + 1;
		
		blocksPerRow = (signal.width - options.getWidth())/options.getXStep() + 1;
		
		blocksCount = blocksPerRow * blocksPerColumn;
	}
	
	private int getY(int idx){
		return idx / blocksPerRow;
	}
	
	private int getX(int idx){
		return idx % blocksPerRow;
	}
	
}
