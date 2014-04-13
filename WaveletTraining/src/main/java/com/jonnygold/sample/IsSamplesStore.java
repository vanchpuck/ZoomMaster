package com.jonnygold.sample;

import com.jonnygold.wavelet.Signal;

public interface IsSamplesStore {

//	public void connect();
//	
//	public void disconnect();
	
	public void saveSamples(Signal signal) throws StoreException;
	
	public Signal getIncreased(Signal signal) throws StoreException;
	
}
