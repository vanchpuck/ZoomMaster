package com.jonnygold.sample;

import com.jonnygold.wavelet.Signal;
import com.jonnygold.wavelet.WaveletData2D;

class ClearWaveletData2D extends WaveletData2D<Signal> {

	protected ClearWaveletData2D(int h, int w) {
		super(new ClearRowWaveletData1D(h, w/2) , new ClearRowWaveletData1D(h, w/2), h, w);
		// TODO Auto-generated constructor stub
	}

}
