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
public class WaveletData1D<T extends Signal> extends WaveletData<T>{

    private T scaled;
    private T wavelet;
    
    protected WaveletData1D(T scaled, T wavelet, int h, int w, TransformDirection direction){
        super(h, w, direction);
        this.scaled = scaled;
        this.wavelet = wavelet;
    }
        
    @Override
    public T getScaled() {
        return this.scaled;
    }

    @Override
    public T getWavelet() {
        return this.wavelet;
    }

    @Override
    protected void setScaled(T scaled) throws Exception {
        if(scaled.height != this.scaled.height || scaled.width != this.scaled.width){
            throw new Exception("Не совпадают размеры области");
        }
        
        this.scaled = scaled;
    }

    @Override
    protected void setWavelet(T wavelet) throws Exception {
        if(wavelet.height != this.wavelet.height || wavelet.width != this.wavelet.width){
            throw new Exception("Не совпадают размеры области");
        }
        
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
