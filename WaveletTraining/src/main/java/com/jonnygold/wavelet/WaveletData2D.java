/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonnygold.wavelet;

import java.util.Arrays;

/**
 *
 * @author Vanchpuck
 */
public class WaveletData2D<T extends Signal> extends WaveletData<WaveletData1D<T>> {

    private WaveletData1D<T> scaled;
    private WaveletData1D<T> wavelet;
    
    protected WaveletData2D(WaveletData1D<T> scaled, WaveletData1D<T> wavelet, int h, int w){
        super(h, w, TransformDirection.ROW_TRANSFORM);
        this.scaled = scaled;
        this.wavelet = wavelet;
    }
    
    @Override
    public WaveletData1D<T> getScaled() {
        return this.scaled;
    }

    @Override
    public WaveletData1D<T> getWavelet() {
        return this.wavelet;
    }

    @Override
    void setScaled(WaveletData1D<T> scaled) throws Exception {
        this.scaled = scaled;
    }

    @Override
    void setWavelet(WaveletData1D<T> wavelet) throws Exception {
        this.wavelet = wavelet;
    }
    
    @Override
    public double[] getData(){        
        double[] data = new double[super.height*super.width];
        double[] tmp = null;
        
        switch(this.direction){
        case COL_TRANSFORM :
        	tmp = scaled.getData();
            System.arraycopy(tmp, 0, data, 0, tmp.length);
            
            tmp = wavelet.getData();
            System.arraycopy(tmp, 0, data, data.length>>1, tmp.length);
            break;
        case ROW_TRANSFORM :
        	int h = this.scaled.height;
            int w = this.scaled.width;
            
            double[] scaledData = this.scaled.getData();
            double[] waveletData = this.wavelet.getData();
            
            for(int y=0; y<h; y++){
                
                tmp = Arrays.copyOfRange(scaledData, y*w, y*w+w);
                System.arraycopy(tmp, 0, data, y*this.width, w);
                
                tmp = Arrays.copyOfRange(waveletData, y*w, y*w+w);
                System.arraycopy(tmp, 0, data, y*this.width+w, w);
            }
            break;
        default :
        	throw new IllegalArgumentException("Неизвестный индикатор направления преобразования");
        }
        return data;
    }
    
}
