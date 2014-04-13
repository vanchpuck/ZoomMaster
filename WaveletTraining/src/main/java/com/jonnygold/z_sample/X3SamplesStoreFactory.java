package com.jonnygold.z_sample;

public class X3SamplesStoreFactory implements IsDataSourceFactory {

	private static X3SamplesStoreFactory instance;
		
	private final IsSamplesDataSource dataSource;
	
	private X3SamplesStoreFactory(IsSamplesDataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public static X3SamplesStoreFactory getInstance(){
		if(instance == null){
			instance = new X3SamplesStoreFactory(null);
		}
		return instance;
	}
	
	@Override
	public SamplesSaver newSamplesSaver() {
		return new X1SamplesSaver(dataSource);
	}

	@Override
	public SamplesLoader newSamplesLoader() {
		// TODO Auto-generated method stub
		return null;
	}

}
