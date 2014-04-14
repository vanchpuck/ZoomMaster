package com.jonnygold.sample;

import com.jonnygold.wavelet.Signal;
import com.jonnygold.wavelet.Transformer;
import com.jonnygold.wavelet.WaveletData2D;

public class SplitFirstSamplesStore<S extends IsBlock, L extends IsBlock> implements IsSamplesStore {

	protected static class SamplesCheck {
		
		public static boolean pass(Signal signal){
			for(double coef : signal.getData()){
				if(Double.compare(coef, 0) != 0){
					return true;
				}
			}
			return false;
		}
	}
	
	private static final int SMALL_SPLIT_STEP = 1;
	private static final int LARGE_SPLIT_STEP = 2;
	
	private final Transformer transformer;
	
	private IsSamplesDataSource<S, L> dataSource;
	
	public SplitFirstSamplesStore(IsSamplesDataSource<S, L> dataSource, Transformer transformer){
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
		SignalSplitter<L> splitter = new SignalSplitter<L>(signal, dataSource.getLargeBlock(), SMALL_SPLIT_STEP);
		
		for(int i=0; i<splitter.getBlocksCount(); i++){
			saveSample(splitter.getBlock(i));
		}		
	}
	
	private void saveSample(Signal signal) throws StoreException{
		WaveletData2D<Signal> wData1Step = transformer.getDirectTransform2D(signal);
		WaveletData2D<Signal> wData2Step = transformer.getDirectTransform2D(WaveletData2D.Segment.LL.getSignal(wData1Step));
	
		try {
			if(SamplesCheck.pass(WaveletData2D.Segment.LH.getSignal(wData2Step)))
				dataSource.saveSample(
						new IsSamplesDataSource.Sample<S, L>(WaveletData2D.Segment.LH.getSignal(wData2Step),
								WaveletData2D.Segment.LH.getSignal(wData1Step), WaveletData2D.Segment.LH));		
		
//			dataSource.saveSample(
//					new IsSamplesDataSource.Sample<S, L>(WaveletData2D.Segment.HL.getSignal(wData2Step),
//							WaveletData2D.Segment.HL.getSignal(wData1Step), WaveletData2D.Segment.HL));
//			
//			dataSource.saveSample(
//					new IsSamplesDataSource.Sample<S, L>(WaveletData2D.Segment.HH.getSignal(wData2Step),
//							WaveletData2D.Segment.HH.getSignal(wData1Step), WaveletData2D.Segment.HH));
		
		} catch (DataSourceException e) {
			e.printStackTrace();
			throw new StoreException(e);
		}
	}

	@Override
	public Signal getIncreased(Signal signal) throws StoreException {
		// TODO Auto-generated method stub
		return null;
	}

}
