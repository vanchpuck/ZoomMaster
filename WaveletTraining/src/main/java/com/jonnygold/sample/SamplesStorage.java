package com.jonnygold.sample;

import com.jonnygold.wavelet.Signal;

class SamplesStorage {

	private SamplesSaver saver;
	
	/*
	 * Можно попробовать реализовть с помощью билдера.
	 * SamplesSaver можно параметризовать стратегиями разбиения на блоки для большей гибкости.
	 */
	SamplesStorage(IsSamplesDataSource dataSource){
		this.saver = new X1SamplesSaver(dataSource);
	}
	
	public void addSignal(Signal signal){
		saver.saveSamples(signal);
	}
	
}
