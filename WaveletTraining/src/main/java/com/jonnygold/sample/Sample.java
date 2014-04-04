package com.jonnygold.sample;

import com.jonnygold.wavelet.Signal;

final class Sample {

	public static enum Category{
		HL, LH, HH;
	}
	
	private final Category category;
	private final Signal small;
	private final Signal big;
	
	public Sample(Signal small, Signal big, Category category){
		this.small = small;
		this.big = big;
		this.category = category;
	}
	
	public final Signal getSmall(){
		return new Signal(small.getData(), small.height, small.width);
	}
	
	public final Signal getBig(){
		return new Signal(big.getData(), big.height, big.width);
	}
	
	public Category getCategory() {
		return category;
	}
	
}
