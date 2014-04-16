package com.jonnygold.sample;

import com.jonnygold.wavelet.Signal;
import com.jonnygold.wavelet.Transformer;
import com.jonnygold.wavelet.WaveletData2D;

public class SplitFirstSamplesStore<S extends IsBlock, L extends IsBlock> implements IsSamplesStore {

	protected static class SamplesCheck {
		
		public boolean pass(Signal signal){
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
	
	private SamplesCheck sampleCheck;
	
	public SplitFirstSamplesStore(IsSamplesDataSource<S, L> dataSource, Transformer transformer){
		this.transformer = transformer;
		this.dataSource = dataSource;
		this.sampleCheck = new SamplesCheck();
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
		dataSource.flush();
	}
	
	private void saveSample(Signal signal) throws StoreException{
		WaveletData2D<Signal> wData1Step = transformer.getDirectTransform2D(signal);
		WaveletData2D<Signal> wData2Step = transformer.getDirectTransform2D(WaveletData2D.Segment.LL.getSignal(wData1Step));
	
		try {
			Signal small = null;
			for(WaveletData2D.Segment segment : WaveletData2D.Segment.values()){				
				if(segment != WaveletData2D.Segment.LL){
					small = segment.getSignal(wData2Step);
					if(sampleCheck.pass(small)){
						dataSource.saveSample(
								new IsSamplesDataSource.Sample(small, segment.getSignal(wData1Step), segment));
					}
				}
			}		
		} catch (DataSourceException e) {
			e.printStackTrace();
			throw new StoreException(e);
		}
	}

	@Override
	public Signal getIncreased(Signal signal) throws StoreException {
		Signal increased = new Signal(signal.height*2, signal.width*2);
		
		SignalSplitter<S> smallSplitter = new SignalSplitter<S>(signal, dataSource.getSmallBlock(), 4);
		SignalSplitter<L> largeSplitter = new SignalSplitter<L>(increased, dataSource.getLargeBlock(), 8);
		
		try {
			for(int i=0; i<smallSplitter.getBlocksCount(); i++){
				Signal inc = getIncreasedSignal(smallSplitter.getBlock(i));			
				try{
					largeSplitter.setBlock(inc, i);
				} catch(Exception e){
					e.printStackTrace();
				}
				
			}
		} catch (DataSourceException e) {
			e.printStackTrace();
			throw new StoreException(e);
		}
		
		
		
		return null;
	}
	
	private Signal getIncreasedSignal(Signal signal) throws DataSourceException{
//		Signal increased = new Signal(signal.height*2, signal.width*2);
		WaveletData2D<Signal> increased = new ClearWaveletData2D(signal.height*2, signal.width*2);
				
		WaveletData2D<Signal> wData = transformer.getDirectTransform2D(signal);
		
		if(sampleCheck.pass(WaveletData2D.Segment.LH.getSignal(wData))){
			Signal s = dataSource.findSample(WaveletData2D.Segment.LH.getSignal(wData), WaveletData2D.Segment.LH).getBig();
			increased.getScaled().getWavelet().setSignal(s, 0, 0); 
		}
		
		if(sampleCheck.pass(WaveletData2D.Segment.HL.getSignal(wData))){
			Signal s = dataSource.findSample(WaveletData2D.Segment.HL.getSignal(wData), WaveletData2D.Segment.HL).getBig();
			increased.getWavelet().getScaled().setSignal(s, 0, 0);
		}
		
		if(sampleCheck.pass(WaveletData2D.Segment.HH.getSignal(wData))){
			Signal s = dataSource.findSample(WaveletData2D.Segment.HH.getSignal(wData), WaveletData2D.Segment.HH).getBig();
			increased.getWavelet().getWavelet().setSignal(s, 0, 0);
		}
		
		return transformer.getInverseTransform2D(wData);
		
	}
	
	

}
