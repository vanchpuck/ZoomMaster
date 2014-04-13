/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonnygold.wavelet;

/**
 *
 * @author Vanchpuck
 */
public interface Transformer {
    
    <T extends Signal> WaveletData1D<T> getDirectTransform(T input, TransformDirection direction);
    
    <T extends Signal> WaveletData2D<T> getDirectTransform2D(T input);
    
    <T extends Signal> T getInverseTransform(WaveletData1D<T> input);
    
    <T extends Signal> T getInverseTransform2D(WaveletData2D<T> input);
    
}
