/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonnygold.wavelet;

import com.jonnygold.wavelet.filter.WaveletFilter;

/**
 *
 * @author Vanchpuck
 */
public class WaveletTransformer implements Transformer{
    
    private WaveletFilter filter;
    private TransformLogic alg;
    
    public WaveletTransformer(WaveletFilter filter, TransformLogic alg){
        this.alg = alg;
        this.filter = filter;
    }
    
    public WaveletFilter getFilter(){
        return this.filter;
    }
    
    public TransformLogic getTransformLogic(){
        return this.alg;
    }
    
    public void setFilter(WaveletFilter filter){
        this.filter = filter;
    }
    
    public void setFilter(TransformLogic alg){
        this.alg = alg;
    }
    
    @Override
    public <T extends Signal> WaveletData1D<T> getDirectTransform(T input, TransformDirection direction) {
        return alg.getDirectTransform(input, filter, direction);
    }

    @Override
    public <T extends Signal> WaveletData2D<T> getDirectTransform2D(T input) {
        WaveletData1D<T> rowTransform = alg.getDirectTransform(input, filter, TransformDirection.ROW_TRANSFORM);
        
        WaveletData1D<T> scaled = alg.getDirectTransform(rowTransform.getScaled(), filter, TransformDirection.COL_TRANSFORM);
        
        WaveletData1D<T> wavelet = alg.getDirectTransform(rowTransform.getWavelet(), filter, TransformDirection.COL_TRANSFORM);
        
        return new WaveletData2D<T>(scaled, wavelet, input.height, input.width);
    }

    @Override
    public <T extends Signal> T getInverseTransform(WaveletData1D<T> input) {
        return alg.getInverseTransform(input, filter);
    }

    @Override
    public <T extends Signal> T getInverseTransform2D(WaveletData2D<T> input) {
        
        T scaled = alg.getInverseTransform(input.getScaled(), filter);
        T wavelet = alg.getInverseTransform(input.getWavelet(), filter);
        
        WaveletData1D<T> rowTransform = new WaveletData1D<T>(scaled, wavelet, input.height, input.width, TransformDirection.ROW_TRANSFORM);
        
        T src = alg.getInverseTransform(rowTransform, filter);
        
        return src;
    }
    
}
