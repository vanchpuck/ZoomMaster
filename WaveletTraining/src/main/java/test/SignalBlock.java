package test;

import com.jonnygold.wavelet.Signal;

public abstract class SignalBlock implements IsBlock {

	private Signal signal;
	
	public SignalBlock(Signal signal){
		if(signal.height != getHeight() || signal.width != getWidth() ){
			throw new IllegalArgumentException("Неподходящая размерность сигнала: "+signal.height+" x "+signal.width);
		}
		this.signal = signal;
	}

	@Override
	public int[] getData() {
		double[] source = signal.getData();		
		int[] buffer = new int[source.length];
		
		for(int i=0; i<buffer.length; i++){
			buffer[i] = (int) source[i];
		}
		
		return buffer;
	}

}
