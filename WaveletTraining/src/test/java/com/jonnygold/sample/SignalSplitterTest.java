package com.jonnygold.sample;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.jonnygold.wavelet.Signal;

public class SignalSplitterTest {

	private static final double DELTA = 1e-15;
	
	private static class X2Block implements IsBlock{

		@Override
		public int getWidth() {
			return 2;
		}

		@Override
		public int getHeight() {
			return 2;
		}
	}
	
	private Signal signal;
	
	private SignalSplitter<X2Block> splitter;
	
	@Before
	public void init(){
		double[] data = new double[]{1, 1, 2, 2, 1, 1, 2, 2, 3, 3, 4, 4, 3, 3, 4, 4};
		signal = new Signal(data, 4, 4);
		splitter = new SignalSplitter<SignalSplitterTest.X2Block>(signal, new X2Block());
	}
	
	@Test
	public void getCountTest(){
		assertEquals("Неверно расчитано число блоков", 9, splitter.getBlocksCount());
	}
	
	@Test
	public void getBlockNullTest(){
		assertNotNull("Блок не может быть равен NULL", splitter.getBlock(4));
	}
	
	@Test
	public void getBlockTest(){
		assertArrayEquals("Неверно выделен блок", new double[]{1, 2, 3, 4}, splitter.getBlock(4).getData(), DELTA);
	}

}
