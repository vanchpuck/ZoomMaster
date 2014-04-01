package com.jonnygold.image;

import java.util.List;

import com.jonnygold.wavelet.Signal;

public interface IsSignalWrapper<T> {

	public T getSource();
		
	public List<Signal> getSignals();
	
}
