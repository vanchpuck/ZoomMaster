package com.jonnygold.image;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DiscreteSignalTest {

	private static final double DELTA = 1e-15;
	
	private static DiscreteSignal signal;
	
	@BeforeClass
	public static void initSignal(){
		double[] data = new double[]{1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8};
		signal = new DiscreteSignal(data, 4, 4, new SplitOptions(1, 1, 2, 2));
	}
	
	@Test
	public void getBlockTest1() {
		double[] block = signal.getBlock(4).getData();
		assertArrayEquals("Неверно выделен блок", new double[]{6,7,2,3}, block, DELTA); 
	}
	
	@Test
	public void getBlockTest2() {
		signal.setSplitOptions(new SplitOptions(2, 2, 2, 2));
		double[] block = signal.getBlock(1).getData();
		assertArrayEquals("Неверно выделен блок", new double[]{3,4,7,8}, block, DELTA); 
	}
	
	@Test
	public void getBlockTest3() {
		signal.setSplitOptions(new SplitOptions(2, 2, 2, 2));
		double[] block = signal.getBlock(2).getData();
		assertArrayEquals("Неверно выделен блок", new double[]{1,2,5,6}, block, DELTA); 
	}
	
	@Test
	public void getBlockTest4() {
		signal.setSplitOptions(new SplitOptions(1, 1, 1, 1));
		double[] block = signal.getBlock(9).getData();
		assertArrayEquals("Неверно выделен блок", new double[]{2}, block, DELTA); 
	}
	
	@Test
	public void setBlockTest() {
		signal.setSplitOptions(new SplitOptions(2, 2, 2, 2));
				
		signal.setBlock(new DiscreteSignal(new double[]{9, 9, 9, 9}, 2, 2), 2);
		
		double[] block = signal.getBlock(2).getData();
		assertArrayEquals("Блок задан неверно", new double[]{9, 9, 9, 9}, block, DELTA); 
	}

}
