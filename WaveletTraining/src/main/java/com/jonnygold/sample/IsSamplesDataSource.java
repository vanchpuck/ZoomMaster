package com.jonnygold.sample;

import com.jonnygold.wavelet.Signal;
import com.jonnygold.wavelet.WaveletData2D;
import com.jonnygold.wavelet.WaveletData2D.Segment;

public interface IsSamplesDataSource<S extends IsBlock, L extends IsBlock> {

	public static class Sample{
		
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
		
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder()
				.append("(")
				.append(getSegmentId(segment))
				.append(",\"{");
			for(double val : small.getData()){
				builder.append(String.valueOf((int)val)).append(",");
			}
			builder.deleteCharAt(builder.lastIndexOf(","))
				.append("}\",\"{");
			
			for(double val : big.getData()){
				builder.append(String.valueOf((int)val)).append(",");
			}
			builder.deleteCharAt(builder.lastIndexOf(","))
				.append("}\")");
			return builder.toString();
		}
		
		private int getSegmentId(WaveletData2D.Segment segment){
			switch(segment){
			case LL : 
				return 1;
			case LH : 
				return 2;
			case HL : 
				return 3;
			case HH : 
				return 4;
			default : 
				throw new IllegalArgumentException("Неизвестный сегмент");
			}
		}
		
	}
	
	public void connect() throws DataSourceException;
	
	public void disconnect() throws DataSourceException;
	
	public void saveSample(Sample sample) throws DataSourceException;
	
	public Sample findSample(Signal sample, Segment segment) throws DataSourceException;
	
	public S getSmallBlock();
	
	public L getLargeBlock();
	
	public void flush();
	
}
