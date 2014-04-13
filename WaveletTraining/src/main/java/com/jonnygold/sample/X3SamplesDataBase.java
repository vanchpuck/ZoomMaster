package com.jonnygold.sample;

public class X3SamplesDataBase extends PostgreSamplesDataBase<X3Block, X9Block> {

	public X3SamplesDataBase(String URL, String userName, String password) {
		super(URL, userName, password);
	}

	@Override
	public X3Block getSmallBlock() {
		return X3Block.getInstance();
	}

	@Override
	public X9Block getLargeBlock() {
		return X9Block.getInstance();
	}

}
