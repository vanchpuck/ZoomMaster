package com.jonnygold.image;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.jonnygold.wavelet.Signal;

public abstract class SignalWrapper<T> implements IsSignalWrapper<T> {
	
	private final List<Signal> signals;
	
	private IsSourceConverter<T> converter;
	
	public SignalWrapper(T source, IsSourceConverter<T> converter){
		this.converter = converter;
		this.signals = converter.getSignals(source);
	}
	
	public SignalWrapper(IsSourceConverter<T> converter, Signal ... signals){
		this.signals = new ArrayList<Signal>();
		this.converter = converter;
		for(Signal s : signals){
			this.signals.add(s);
		}
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
