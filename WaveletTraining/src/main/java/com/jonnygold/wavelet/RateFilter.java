/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonnygold.wavelet;

/**
 *
 * @author Vanchpuck
 */
public class RateFilter implements Transformer{
    
    private int border;
    
    private Transformer transformer;
    
    public RateFilter(WaveletTransformer transformer, int border){
        this.border = border;
        this.transformer = transformer;
    }

    @Override
    public <T extends Signal> WaveletData1D<T> getDirectTransform(T input, TransformDirection direction) {
        WaveletData1D<T> transformed = transformer.getDirectTransform(input, direction);
        
        Signal wavelet = transformed.getWavelet();
        
        double[] data = wavelet.getData();
        
        filter(data, this.border);
        
        return transformed;
    }

    @Override
    public <T extends Signal>  WaveletData2D<T> getDirectTransform2D(T input) {
        WaveletData2D<T> transformed = transformer.getDirectTransform2D(input);
        
        double[] data = transformed.getScaled().getWavelet().getData();
        filter(data, this.border);
        
        data = transformed.getWavelet().getScaled().getData();
        filter(data, this.border);
        
        data = transformed.getWavelet().getWavelet().getData();
        filter(data, this.border);
        
        return transformed;
    }
    
    private void filter(double[] data, int border){
        for(int i=0; i<data.length; i++){
            if(Math.abs(data[i]) < this.border){
                data[i] = 0;
            }
        }
    }

    @Override
    public <T extends Signal> T getInverseTransform(WaveletData1D<T> input) {
        return transformer.getInverseTransform(input);
    }

    @Override
    public <T extends Signal> T getInverseTransform2D(WaveletData2D<T> input) {
        return transformer.getInverseTransform2D(input);
    }
    
}
