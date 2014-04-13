package com.jonnygold.sample;

public abstract class AbstractSamplesDataSource<S extends IsBlock, L extends IsBlock> implements IsSamplesDataSource<S, L> {

	private S smallBlock;
	
	private L largeBlock;
	
	public AbstractSamplesDataSource(S smallBlock, L largeBlock){
		this.smallBlock = smallBlock;
		this.largeBlock = largeBlock;
	}
	
	@Override
	public S getSmallBlock() {
		return smallBlock;
	}

	@Override
	public L getLargeBlock() {
		return largeBlock;
	}

}
