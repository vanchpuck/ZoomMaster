package com.jonnygold.image;

import java.awt.Transparency;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.SampleModel;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.List;

import com.jonnygold.wavelet.Signal;

class GrayscaleConverter implements BufferedImageWrapper.BufferedImageConverter{

	
	ColorSpace colorSpace = ColorSpace.getInstance(ColorSpace.CS_GRAY);
	
	@Override
	public List<Signal> getSignals(BufferedImage source) {
		ColorConvertOp convert = new ColorConvertOp(colorSpace, null);
	
		source = convert.filter(source, null);
		
		WritableRaster raster = source.getRaster();
		
		List<Signal> result = new ArrayList<>(2);
		
		int h = raster.getHeight();
		int w = raster.getWidth();
		
		double[] data = raster.getSamples(0, 0, w, h, 0, new double[w * h]);
		result.add (new Signal(data, h, w ));
		
		
		
		return result;
	}

	@Override
	public BufferedImage getSource(List<Signal> signals) {
		Signal signal = signals.get(0);
		
        ColorModel cm = new ComponentColorModel(colorSpace, false, true,
                Transparency.OPAQUE, DataBuffer.TYPE_BYTE);
        SampleModel sm = cm.createCompatibleSampleModel(signal.width, signal.height);
        
        DataBuffer db = new DataBufferByte(signal.width * signal.height  );
        
        WritableRaster raster = Raster.createWritableRaster(sm, db, null);
        
        raster.setSamples(0, 0, signal.width, signal.height, 0, signal.getData());
        	
		return new BufferedImage(cm, raster, false, null);
	}
	
}
