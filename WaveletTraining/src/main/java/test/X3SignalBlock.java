package test;

import com.jonnygold.wavelet.Signal;

public class X3SignalBlock extends SignalBlock {

	public X3SignalBlock(Signal signal) {
		super(signal);
	}

	public final int getWidth(){
		return 3;
	}
	
	public final int getHeight(){
		return 3;
	}
	
}
