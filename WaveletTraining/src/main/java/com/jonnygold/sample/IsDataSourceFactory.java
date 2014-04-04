package com.jonnygold.sample;

public interface IsDataSourceFactory {

	public SamplesSaver newSamplesSaver();
	
	public SamplesLoader newSamplesLoader();
	
}
