package com.jonnygold.z_sample;

import com.jonnygold.wavelet.Signal;

final class Sample {
	
	private final WaveletSegment2D segment;
	private final Signal small;
	private final Signal big;
	
	public Sample(Signal small, Signal big, WaveletSegment2D segment){
		this.small = small;
		this.big = big;
		this.segment = segment;
	}
	
	public final Signal getSmall(){
		return new Signal(small.getData(), small.height, small.width);
	}
	
	public final Signal getBig(){
		return new Signal(big.getData(), big.height, big.width);
	}
	
	public WaveletSegment2D getCategory() {
		return segment;
	}
	
}
