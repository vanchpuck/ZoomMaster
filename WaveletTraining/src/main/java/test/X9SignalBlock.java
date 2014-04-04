package test;

import com.jonnygold.wavelet.Signal;

public class X9SignalBlock extends SignalBlock {

	public X9SignalBlock(Signal signal) {
		super(signal);
	}

	@Override
	public final int getWidth() {
		return 9;
	}

	@Override
	public final int getHeight() {
		return 9;
	}

}
