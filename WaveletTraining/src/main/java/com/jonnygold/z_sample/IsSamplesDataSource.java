package com.jonnygold.z_sample;

import com.jonnygold.wavelet.Signal;
import com.jonnygold.wavelet.Transformer;

interface IsSamplesDataSource {
		
	public void connect();
	
	public void disconnect();
	
	public void saveSample (Sample sample);
	
	public Sample loadSample (Signal signal);
	
//	public BlockDimension getBlockDimension();
//	
//	public WaveletTransformer getTransformer();
	
}
