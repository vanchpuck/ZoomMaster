package com.jonnygold.image;

import static org.junit.Assert.*;

import org.junit.Test;

import com.jonnygold.wavelet.Signal;

public class SplitterTest {

	@Test
	public void SizeTest1() {
		Splitter splitter = new Splitter(1, 1, 3, 3);
		Signal signal = new DiscreteSignal(new double[36], 6, 6);
		
		assertEquals("Число блоков расчитано неверно", 16, splitter.getBlockCount(signal)); 
	}
	
	@Test
	public void SizeTest2() {
		Splitter splitter = new Splitter(1, 1, 3, 3);
		Signal signal = new DiscreteSignal(new double[144], 12, 12);
		
		assertEquals("Число блоков расчитано неверно", 100, splitter.getBlockCount(signal)); 
	}
	
	@Test
	public void SizeTest3() {
		Splitter splitter = new Splitter(1, 1, 4, 4);
		Signal signal = new DiscreteSignal(new double[144], 12, 12);
		
		assertEquals("Число блоков расчитано неверно", 81, splitter.getBlockCount(signal)); 
	}

}
