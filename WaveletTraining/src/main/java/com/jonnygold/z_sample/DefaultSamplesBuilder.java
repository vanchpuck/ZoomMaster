package com.jonnygold.z_sample;

public class DefaultSamplesBuilder extends SamplesStore.Builder {

	@Override
	final void initDataSource() {
		getStore().setDataSource(null);
	}

	@Override
	final void initSamplesMaker() {
		getStore().setSamplesMaker(null);
	}

}
