package com.jonnygold.sample;

import com.jonnygold.sample.PostgreSamplesDataBase;
import com.jonnygold.sample.X4Block;
import com.jonnygold.sample.X8Block;

public class X4SamplesDataBase extends PostgreSamplesDataBase<X4Block, X8Block> {

	public X4SamplesDataBase(String URL, String userName, String password) {
		super(URL, userName, password);
	}

	@Override
	public X4Block getSmallBlock() {
		return X4Block.getInstance();
	}

	@Override
	public X8Block getLargeBlock() {
		return X8Block.getInstance();
	}

}
