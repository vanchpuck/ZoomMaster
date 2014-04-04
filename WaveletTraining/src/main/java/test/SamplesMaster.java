package test;

import com.jonnygold.wavelet.Signal;
import com.jonnygold.wavelet.Transformer;

public abstract class SamplesMaster<S extends IsBlock, L extends IsBlock> {

	public SamplesMaster(Signal signal, Transformer transformer){
		
	}
	
	public abstract boolean hasNext();
	
	public abstract IsSamplesDataSource.Sample<S, L> getNext();
	
}
