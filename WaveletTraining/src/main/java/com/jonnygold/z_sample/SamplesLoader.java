package com.jonnygold.z_sample;

import com.jonnygold.wavelet.Signal;

abstract class SamplesLoader {

	private IsSamplesDataSource dataSource;
	
	public SamplesLoader(IsSamplesDataSource dataSource){
		this.dataSource = dataSource;
	}
	
	public BlockDimension getBlockDimension(){
		return dataSource.getDataAttributes().getBlockDimension();
	}
	
	public Sample loadSample(Signal signal){
		return null;
	}
	
}
