package com.jonnygold.z_sample;

public interface IsDataSourceFactory {

	public SamplesSaver newSamplesSaver();
	
	public SamplesLoader newSamplesLoader();
	
}
