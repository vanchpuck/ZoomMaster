package com.jonnygold.image;

import java.awt.image.BufferedImage;

import com.jonnygold.wavelet.Signal;

public class BufferedImageWrapper<T extends BufferedImage> extends SignalWrapper<T> {

	protected static interface BufferedImageConverter extends IsSourceConverter<BufferedImage> {}
	
	public BufferedImageWrapper(T source, IsSourceConverter<T> converter) {
		super(source, converter);
	}
	
	public BufferedImageWrapper(IsSourceConverter<T> converter, Signal ... signals){
		super(converter, signals);
	}

}
