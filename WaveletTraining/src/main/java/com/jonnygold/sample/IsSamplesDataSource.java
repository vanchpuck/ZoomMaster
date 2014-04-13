package com.jonnygold.sample;

import com.jonnygold.wavelet.Signal;
import com.jonnygold.wavelet.WaveletData2D;

public interface IsSamplesDataSource<S extends IsBlock, L extends IsBlock> {

	public static class Sample<S, L>{
		
		private final WaveletData2D.Segment segment;
		private final Signal small;
		private final Signal big;
		
		public Sample(Signal small, Signal big, WaveletData2D.Segment segment){
			this.small = small;
			this.big = big;
			this.segment = segment;
		}
		
		public final Signal getSmall(){
			return small;
		}
		
		public final Signal getBig(){
			return big;
		}
		
		public WaveletData2D.Segment getSegment() {
			return segment;
		}
		
	}
	
	public void connect() throws DataSourceException;
	
	public void disconnect() throws DataSourceException;
	
	public void saveSample(Sample<S, L> sample) throws DataSourceException;
	
	public Sample<S, L> findSample(S small) throws DataSourceException;
	
	public S getSmallBlock();
	
	public L getLargeBlock();
	
}
