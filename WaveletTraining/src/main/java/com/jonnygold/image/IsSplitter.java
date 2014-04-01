package com.jonnygold.image;

import com.jonnygold.wavelet.Signal;

public interface IsSplitter {
	
	public int getBlockCount (Signal signal);
	
	public Signal getBlock (int idx);
	
	public void setBlock (Signal signal, int idx);
	
}
