package com.jonnygold.sample;

import com.jonnygold.wavelet.Signal;
import com.jonnygold.wavelet.Transformer;
import com.jonnygold.wavelet.WaveletData2D;

public class SamplesStore<S extends IsBlock, L extends IsBlock> implements IsSamplesStore {

	private final Transformer transformer;
	
	private IsSamplesDataSource<S, L> dataSource;
	
	public SamplesStore(IsSamplesDataSource<S, L> dataSource, Transformer transformer){
		this.transformer = transformer;
		this.dataSource = dataSource;
	}
		
	public void connect() throws StoreException {
		try {
			dataSource.connect();
		} catch (DataSourceException e) {
			e.printStackTrace();
			throw new StoreException(e);
		}
	}
	
	public void disconnect() throws StoreException {
		try {
			dataSource.disconnect();
		} catch (DataSourceException e) {
			e.printStackTrace();
			throw new StoreException(e);
		}
	}
	
	@Override
	public void saveSamples(Signal signal) throws StoreException {		
		
		WaveletData2D<Signal> wData1Step = transformer.getDirectTransform2D(signal);
		WaveletData2D<Signal> wData2Step = transformer.getDirectTransform2D(WaveletData2D.Segment.LL.getSignal(wData1Step));
				
		saveSamples(wData2Step, wData1Step, WaveletData2D.Segment.LH);
		saveSamples(wData2Step, wData1Step, WaveletData2D.Segment.HL);
		saveSamples(wData2Step, wData1Step, WaveletData2D.Segment.HH);
		
	}
	
	private void saveSamples(WaveletData2D<Signal> small, WaveletData2D<Signal> large, WaveletData2D.Segment segment) throws StoreException {
		SignalSplitter<S> smallSplitter = new SignalSplitter<S>(segment.getSignal(small), dataSource.getSmallBlock());
		SignalSplitter<L> largeSplitter = new SignalSplitter<L>(segment.getSignal(large), dataSource.getLargeBlock());
		
		// TODO оставим проверку на время тестирования
		if(smallSplitter.getBlocksCount() != largeSplitter.getBlocksCount()){
			throw new IllegalArgumentException("Некорректно выделены блоки при формировании семплов сигнала.");
		}
		
		try{
			IsSamplesDataSource.Sample<S, L> sample = null;
			for(int i=0; i<smallSplitter.getBlocksCount(); i++){
				sample = new IsSamplesDataSource.Sample<S, L>(smallSplitter.getBlock(i), largeSplitter.getBlock(i), segment);
				
				dataSource.saveSample(sample);
			}
		} catch (DataSourceException e) {
			e.printStackTrace();
			throw new StoreException(e);
		}
	}
	
	@Override
	public Signal getIncreased(Signal signal){
		return null;
	}
	
}
