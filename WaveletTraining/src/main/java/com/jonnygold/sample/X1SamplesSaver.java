package com.jonnygold.sample;

import com.jonnygold.image.SignalSplitter;
import com.jonnygold.image.SplitOptions;
import com.jonnygold.wavelet.Signal;
import com.jonnygold.wavelet.Transformer;
import com.jonnygold.wavelet.WaveletData2D;

public class X1SamplesSaver extends SamplesSaver {

	public X1SamplesSaver(IsSamplesDataSource dataSource) {
		super(dataSource);
	}

	@Override
	public void saveSamples(Signal signal) {
		Transformer transformer = getDataSource().getDataAttributes().getTransformer();
		
		WaveletData2D<Signal> wData1Step = transformer.getDirectTransform2D(signal);
		WaveletData2D<Signal> wData2Step = transformer.getDirectTransform2D(wData1Step.getScaled().getScaled());
		
		SplitOptions optForSmall = new SplitOptions(1, 1, 3, 3);
		SplitOptions optForLarge = new SplitOptions(2, 2, 6, 6);
		
		
		SignalSplitter smallSplitter = new SignalSplitter(wData1Step.getWavelet().getScaled(), optForSmall);
		SignalSplitter largeSplitter = new SignalSplitter(wData2Step.getWavelet().getScaled(), optForLarge);
		saveSamples(smallSplitter, largeSplitter, Sample.Category.HL);
		
		
		smallSplitter = new SignalSplitter(wData1Step.getScaled().getWavelet(), optForSmall);
		largeSplitter = new SignalSplitter(wData2Step.getScaled().getWavelet(), optForLarge);		
		saveSamples(smallSplitter, largeSplitter, Sample.Category.LH);
		
		
		smallSplitter = new SignalSplitter(wData1Step.getWavelet().getWavelet(), optForSmall);
		largeSplitter = new SignalSplitter(wData2Step.getWavelet().getWavelet(), optForLarge);		
		saveSamples(smallSplitter, largeSplitter, Sample.Category.HH);
	}
	
	private void saveSamples(SignalSplitter smallSplitter, SignalSplitter largeSplitter, Sample.Category category){
		// TODO оставим проверку на время тестирования
		if(smallSplitter.getBlocksCount() != largeSplitter.getBlocksCount()){
			throw new IllegalArgumentException("Некорректно выделены блоки при формировании семплов сигнала.");
		}
		
		Sample sample = null;
		for(int i=0; i<smallSplitter.getBlocksCount(); i++){
			sample = new Sample(smallSplitter.getBlock(i), largeSplitter.getBlock(i), category);
			
			getDataSource().saveSample(sample);
		}
	}

}
