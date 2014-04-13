/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonnygold.wavelet;

/**
 *
 * @author Vanchpuck
 */
public abstract class WaveletData <T extends Signal> extends Signal{
        
    public final TransformDirection direction;
    
    protected WaveletData(int h, int w, TransformDirection direction){
        super(h, w);
        this.direction = direction;
    }
    
    abstract T getScaled();
    
    abstract T getWavelet();
    
    abstract void setScaled(T scaled) throws Exception;
    
    abstract void setWavelet(T wavelet) throws Exception;
}
