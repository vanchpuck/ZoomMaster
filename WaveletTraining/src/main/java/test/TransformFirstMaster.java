package test;

import com.jonnygold.wavelet.Signal;
import com.jonnygold.wavelet.Transformer;

import test.IsSamplesDataSource.Sample;

public class TransformFirstMaster<S extends IsBlock, L extends IsBlock> extends SamplesMaster<S, L> {

	public static <S extends IsBlock, L extends IsBlock> TransformFirstMaster<S, L> newInstance(Signal signal, Transformer transformer){
		return new TransformFirstMaster<S, L>(signal ,transformer);
	}
	
	protected TransformFirstMaster(Signal signal, Transformer transformer) {
		super(signal, transformer);
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Sample<S, L> getNext() {
		// TODO Auto-generated method stub
		return null;
	}

}
