package com.jonnygold.sample;

import com.jonnygold.wavelet.Signal;
import com.jonnygold.wavelet.TransformDirection;
import com.jonnygold.wavelet.WaveletData1D;

class ClearRowWaveletData1D extends WaveletData1D<Signal> {

	protected ClearRowWaveletData1D(int h, int w) {
		super(new Signal(h, w/2), new Signal(h, w/2), h, w, TransformDirection.ROW_TRANSFORM);
	}

}
