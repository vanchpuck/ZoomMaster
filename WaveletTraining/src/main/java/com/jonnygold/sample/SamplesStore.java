//package com.jonnygold.sample;
//
//import com.jonnygold.wavelet.Signal;
//import com.jonnygold.wavelet.Transformer;
//import com.jonnygold.wavelet.WaveletData2D;
//
//public class SamplesStore<S extends IsBlock, L extends IsBlock> implements IsSamplesStore {
//
//	private static class SamplesCheck {
//		
//		public static boolean pass(Signal signal){
//			for(double coef : signal.getData()){
//				if(Double.compare(coef, 0) != 0){
//					return true;
//				}
//			}
//			return false;
//		}
//	}
//	
//	private static final int SMALL_SPLIT_STEP = 1;
//	private static final int LARGE_SPLIT_STEP = 2;
//	
//	private final Transformer transformer;
//	
//	private IsSamplesDataSource<S, L> dataSource;
//	
//	public SamplesStore(IsSamplesDataSource<S, L> dataSource, Transformer transformer){
//		this.transformer = transformer;
//		this.dataSource = dataSource;
//	}
//		
//	public void connect() throws StoreException {
//		try {
//			dataSource.connect();
//		} catch (DataSourceException e) {
//			e.printStackTrace();
//			throw new StoreException(e);
//		}
//	}
//	
//	public void disconnect() throws StoreException {
//		try {
//			dataSource.disconnect();
//		} catch (DataSourceException e) {
//			e.printStackTrace();
//			throw new StoreException(e);
//		}
//	}
//	
//	@Override
//	public void saveSamples(Signal signal) throws StoreException {		
//		
//		WaveletData2D<Signal> wData1Step = transformer.getDirectTransform2D(signal);
//		WaveletData2D<Signal> wData2Step = transformer.getDirectTransform2D(WaveletData2D.Segment.LL.getSignal(wData1Step));
//				
//		saveSamples(wData2Step, wData1Step, WaveletData2D.Segment.LH);
//		saveSamples(wData2Step, wData1Step, WaveletData2D.Segment.HL);
//		saveSamples(wData2Step, wData1Step, WaveletData2D.Segment.HH);
//		
//	}
//	
//	private void saveSamples(WaveletData2D<Signal> small, WaveletData2D<Signal> large, WaveletData2D.Segment segment) throws StoreException {
//		SignalSplitter<S> smallSplitter = new SignalSplitter<S>(segment.getSignal(small), dataSource.getSmallBlock(), SMALL_SPLIT_STEP);
//		SignalSplitter<L> largeSplitter = new SignalSplitter<L>(segment.getSignal(large), dataSource.getLargeBlock(), LARGE_SPLIT_STEP);
//		
//		// TODO оставим проверку на время тестирования
//		if(smallSplitter.getBlocksCount() != largeSplitter.getBlocksCount()){
//			throw new IllegalArgumentException("Некорректно выделены блоки при формировании семплов сигнала.");
//		}
//		
//		try{
//			IsSamplesDataSource.Sample<S, L> sample = null;
//			for(int i=0; i<smallSplitter.getBlocksCount(); i++){
//				sample = new IsSamplesDataSource.Sample<S, L>(smallSplitter.getBlock(i), largeSplitter.getBlock(i), segment);
//				
//				if(SamplesCheck.pass(sample.getSmall())){
//					dataSource.saveSample(sample);
//				}				
//			}
//		} catch (DataSourceException e) {
//			e.printStackTrace();
//			throw new StoreException(e);
//		}
//	}
//	
//	@Override
//	public Signal getIncreased(Signal signal){
//		return null;
//	}
//	
//}
