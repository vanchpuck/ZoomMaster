package com.jonnygold.image;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Main extends JFrame {

	public Main() throws IOException{
		super("Test");
		setSize(new Dimension(400, 400));
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		
		File f = new File("/home/izolotov/Downloads/Calendar/moravia.jpg");
		
		BufferedImage src = ImageIO.read(f);
		
		SignalWrapper<BufferedImage> wrapper = 
				new BufferedImageWrapper<BufferedImage>(ImageIO.read(f), new GrayscaleConverter());
		
		BufferedImage img = wrapper.getSource();
		
		ImagePanel imgPanel = new ImagePanel(new FlowLayout(FlowLayout.CENTER));
		
		
		
		imgPanel.setImage(img);
		
		add(imgPanel);
		
		setVisible(true);
	}
	
	public static void main(String[] args) throws IOException {
		
		new Main();
		
	}
	
}
