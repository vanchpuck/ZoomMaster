package com.jonnygold.sample;

import com.jonnygold.wavelet.Signal;

class SamplesStore {

	abstract static class Builder{
				
		private SamplesStore store = new SamplesStore();
		
		SamplesStore getStore(){
			return store;
		}		
		
		abstract void initDataSource();
		
		abstract void initSamplesMaker();
		
	}
	
	private IsSamplesDataSource dataSource;
	
	private SamplesStore() {}
	
	
	public void connect(){
		
	}
	
	public void disconnect(){
		
	}
	
	public void saveSamples(Signal signal){
		
	}
	
	public Signal getIncreased(Signal signal){
		return null;
	}
	
	final void setDataSource(IsSamplesDataSource dataSource){
		this.dataSource = dataSource;
	}
	
	final void setSamplesMaker(IsSamplesMaker samplesMaker){
		
	}
		
}
