package com.jonnygold.image;

import java.util.List;

import com.jonnygold.wavelet.Signal;

interface IsSourceConverter<T>{
	
	public List<Signal> getSignals(T source);
	
	public T getSource(List<Signal> signals);
	
}
