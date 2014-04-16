package com.jonnygold.sample;

import com.jonnygold.wavelet.Signal;

public class SignalSplitter <S extends IsBlock> {

	private final Signal signal;
	
	private S block;
	
	private int blocksPerRow;
	
	private int blocksCount;
	
	private int step;
	
	public SignalSplitter(Signal signal, S block, int step){
		this.signal = signal;
		this.block = block;
		this.step = step;
		
		setBlock(block);
	}
	
	public Signal getBlock(int idx){
		int y = getY(idx);
		int x = getX(idx);
		if(y+block.getHeight() > signal.height || x+block.getWidth() > signal.width){
			throw new IllegalArgumentException("Втекущей реализации выделить невозможно выделить блок: "+idx);
		}
		return signal.getSignal(y, x, block.getHeight(), block.getWidth());
	}
	
	public int getBlocksCount(){
		return blocksCount;
	}
	
	public void setBlock(Signal blocksignal, int idx){
		if(blocksignal.width != block.getWidth() || blocksignal.height != block.getHeight()){
			throw new IllegalArgumentException("Размер блока не соответствует заданным параметрам разделения.");
		} 
		else if(blocksignal.width != block.getWidth() || blocksignal.height != block.getHeight()){
			throw new IllegalArgumentException("Втекущей реализации невозможно установить блок: "+idx);
		}
		int y = getY(idx);
		int x = getX(idx);
		signal.setSignal(blocksignal, getY(idx), getX(idx));
	}
	
	public void setBlock(S block){
		this.block = block;
		
		int blocksPerColumn = (signal.height - block.getHeight())/step + 1;
		
		blocksPerRow = (signal.width - block.getWidth())/step + 1;
		
		blocksCount = blocksPerRow * blocksPerColumn;
	}
	
	public Signal getSignal(){
		return this.signal;
	}
	
	private int getY(int idx){
		return idx / blocksPerRow * step;
	}
	
	private int getX(int idx){
		return idx % blocksPerRow * step;
	}
	
}
