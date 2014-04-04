//package com.jonnygold.image;
//
//import com.jonnygold.wavelet.Signal;
//
//public class DiscreteSignal extends Signal {
//	
//	private SplitOptions options;
//	
//	private int blocksPerRow;
//	
//	private int blocksPerColumn;
//	
//	private int blocksCount;	
//	
//	public DiscreteSignal(double[] input, int h, int w) {
//		super(input, h, w);
//		
//		setSplitOptions(new SplitOptions(1, 1, 1, 1));
//	}
//	
//	public DiscreteSignal(double[] input, int h, int w, SplitOptions options) {
//		super(input, h, w);
//		
//		setSplitOptions(options);
//	}
//	
//	public Signal getBlock(int idx){
//		int y = getY(idx);
//		int x = getX(idx);
//		if(y+options.getHeight() > super.height || x+options.getWidth() > super.width){
//			throw new IllegalArgumentException("Втекущей реализации выделить невозможно выделить блок: "+idx);
//		}
//		return super.getSignal(y, x, options.getHeight(), options.getWidth());
//	}
//	
//	public int getBlocksCount(){
//		return blocksCount;
//	}
//	
//	public void setBlock(Signal block, int idx){
//		if(block.width != options.getWidth() || block.height != options.getHeight()){
//			throw new IllegalArgumentException("Размер блока не соответствует заданным параметрам разделения.");
//		} 
//		else if(block.width != options.getWidth() || block.height != options.getHeight()){
//			throw new IllegalArgumentException("Втекущей реализации выделить невозможно установить блок: "+idx);
//		}
//		super.setSignal(block, getY(idx), getX(idx));
//	}
//	
//	public void setSplitOptions(SplitOptions options){
//		this.options = options;
//		
//		blocksPerRow = (super.width - options.getWidth())/options.getXStep() + 1;
//		
//		blocksPerColumn = (super.height - options.getHeight())/options.getYStep() + 1;
//		
//		blocksCount = blocksPerRow * blocksPerColumn;
//	}
//	
//	private int getY(int idx){
//		return idx / blocksPerRow * options.getXStep();
//	}
//	
//	private int getX(int idx){
//		return idx % blocksPerRow * options.getXStep();
//	}
//	
//}
