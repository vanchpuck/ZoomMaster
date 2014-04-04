package test;

import com.jonnygold.wavelet.Signal;

public interface IsSamplesStore {

//	public void connect();
//	
//	public void disconnect();
	
	public void saveSamples(Signal signal);
	
	public Signal getIncreased(Signal signal);
	
}
