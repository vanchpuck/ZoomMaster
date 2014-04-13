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
/*************
 * 
 * ВОЗМОЖНО, ЛУЧШЕ СДЕЛАТЬ ЕГО ИНТЕРФЕЙСОМ
 */
public abstract class TransformLogic {
    
    abstract <T extends Signal> WaveletData1D<T> getDirectTransform(T input, WaveletFilter filter, TransformDirection direction);
    
    abstract <T extends Signal> T getInverseTransform(WaveletData<T> input, WaveletFilter filter);
    
}
