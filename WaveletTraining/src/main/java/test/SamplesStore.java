package test;

import com.jonnygold.wavelet.Signal;
import com.jonnygold.wavelet.SimpleTransform;
import com.jonnygold.wavelet.Transformer;
import com.jonnygold.wavelet.WaveletTransformer;
import com.jonnygold.wavelet.filter.WaveletFilter;

public abstract class SamplesStore implements IsSamplesStore {

	private final Transformer transformer;
	
	public SamplesStore(){
		transformer = new WaveletTransformer(getWaveletFilter(), SimpleTransform.getInstance());
	}
		
	public void connect(){
		
	}
	
	public void disconnect(){
		
	}
	
	@Override
	public void saveSamples(Signal signal){
		Transformer transformer = new WaveletTransformer(getWaveletFilter(), SimpleTransform.getInstance());
		
		
	}
	
	@Override
	public Signal getIncreased(Signal signal){
		return null;
	}
	
	protected abstract Transformer getTransformer();
	
}
