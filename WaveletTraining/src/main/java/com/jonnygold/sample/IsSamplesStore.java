package com.jonnygold.sample;

import com.jonnygold.wavelet.Signal;

public interface IsSamplesStore {

	public void connect() throws StoreException;
	
	public void disconnect() throws StoreException;
	
	public void saveSamples(Signal signal) throws StoreException;
	
	public Signal getIncreased(Signal signal) throws StoreException;
	
}
