package com.jonnygold.image;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.jonnygold.sample.PostgreSamplesDataBase;
import com.jonnygold.sample.SamplesStore;
import com.jonnygold.sample.StoreException;
import com.jonnygold.sample.X3Block;
import com.jonnygold.sample.X3SamplesDataBase;
import com.jonnygold.sample.X9Block;
import com.jonnygold.wavelet.RateFilter;
import com.jonnygold.wavelet.Signal;
import com.jonnygold.wavelet.SimpleTransform;
import com.jonnygold.wavelet.Transformer;
import com.jonnygold.wavelet.WaveletData2D;
import com.jonnygold.wavelet.WaveletTransformer;
import com.jonnygold.wavelet.filter.DB4Filter;
import com.jonnygold.wavelet.filter.HaarFilter;

public class Main extends JFrame {

	private static final String URL = "jdbc:postgresql://waveletsamples.ccacq9imxn4n.eu-west-1.rds.amazonaws.com:5432/WaveletSamples";
	
	private static final String USER = "JonnyGold";
	
	private static final String PASSWORD = "GoldenAxe";
	
	public Main() throws IOException, StoreException{
		super("Test");
		setSize(new Dimension(400, 400));
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		
//		File f = new File("/home/izolotov/Downloads/Calendar/moravia.jpg");
		File f = new File("C:\\Users\\Vanchpuck\\Desktop\\lenin.jpg");
		
		
		BufferedImage src = ImageIO.read(f);
		
		SignalWrapper<BufferedImage> wrapper = 
				new BufferedImageWrapper<BufferedImage>(ImageIO.read(f), new GrayscaleConverter());
		
		BufferedImage img = wrapper.getSource();
		
		ImagePanel imgPanel = new ImagePanel(new FlowLayout(FlowLayout.CENTER));
		
		Transformer t = new WaveletTransformer(DB4Filter.getInstance(), SimpleTransform.getInstance());
		WaveletData2D<Signal> w2d = t.getDirectTransform2D(wrapper.getSignals().get(0));
		
		wrapper.getSignals().set(0, w2d);
		
//		imgPanel.setImage(wrapper.getSource());
//		imgPanel.setImage(img);
		
		add(imgPanel);
		
		setVisible(true);
		
		
		Transformer transformer = 
				new RateFilter(new WaveletTransformer(DB4Filter.getInstance(), SimpleTransform.getInstance()), 5);
		
		X3SamplesDataBase dataBase = 
				new X3SamplesDataBase(URL, USER, PASSWORD);
		
		SamplesStore<X3Block, X9Block> samplesStore = 
				new SamplesStore<>(dataBase, transformer);
				
		samplesStore.connect();
		samplesStore.saveSamples(wrapper.getSignals().get(0));
		samplesStore.disconnect();
		
		imgPanel.setImage(wrapper.getSource());
		
	}
	
	public static void main(String[] args) throws IOException, StoreException {
		
		new Main();
		
		
	}
	
}
