package com.jonnygold.image;

import java.util.List;

import com.jonnygold.wavelet.Signal;

public abstract class SignalWrapper<T> implements IsSignalWrapper<T> {
	
	private final List<Signal> signals;
	
	private IsSourceConverter<T> converter;
	
	public SignalWrapper(T source, IsSourceConverter<T> converter){
		this.converter = converter;
		this.signals = converter.getSignals(source);
	}
	
	@Override
	public T getSource() {
		return converter.getSource(getSignals());
	}

	@Override
	public List<Signal> getSignals() {
		return signals;
	}
	
	protected IsSourceConverter<T> getConverter(){
		return converter;
	}

}
