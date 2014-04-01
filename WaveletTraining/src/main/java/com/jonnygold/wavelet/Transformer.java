/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonnygold.wavelet;

/**
 *
 * @author Vanchpuck
 */
public abstract class Transformer {
    
    abstract <T extends Signal> WaveletData1D<T> getDirectTransform(T input, TransformDirection direction);
    
    abstract <T extends Signal> WaveletData2D<T> getDirectTransform2D(T input);
    
    abstract <T extends Signal> T getInverseTransform(WaveletData1D<T> input);
    
    abstract <T extends Signal> T getInverseTransform2D(WaveletData2D<T> input);
    
}
