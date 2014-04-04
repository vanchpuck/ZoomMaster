package test;

import com.jonnygold.wavelet.Signal;
import com.jonnygold.wavelet.SimpleTransform;
import com.jonnygold.wavelet.Transformer;
import com.jonnygold.wavelet.WaveletTransformer;
import com.jonnygold.wavelet.filter.HaarFilter;


public class X3HaarSampleStore extends SamplesStore {

	private final X3SamplesDataSource<X3SignalBlock, X9SignalBlock> dataSource;
	
	/*
	 * (non-Javadoc)
	 * @see test.SamplesStore#connect()
	 * В конструкторе передавать трансформер и семпл-мастерs
	 */
	
	@Override
	public void connect() {
		dataSource.connect();
	}

	@Override
	public void disconnect() {
		dataSource.disconnect();
	}

	@Override
	public void saveSamples(Signal signal) {
		
		for(SamplesMaster<X3SignalBlock, X9SignalBlock> samplesMaster = 
				TransformFirstMaster.newInstance(signal, transformer); samplesMaster.hasNext(); ){
			dataSource.saveSample(samplesMaster.getNext());
		}
	
	}

	@Override
	public Signal getIncreased(Signal signal) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected HaarFilter getWaveletFilter() {
		return HaarFilter.getInstance();
	}

}
