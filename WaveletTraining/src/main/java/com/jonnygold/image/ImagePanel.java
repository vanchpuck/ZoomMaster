package com.jonnygold.image;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class ImagePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1074614158077103276L;
	private BufferedImage image;
	
	public ImagePanel(){
		super();
		setBackground(Color.WHITE);
	}
	
	public ImagePanel(LayoutManager layout){
		super(layout);
		setBackground(Color.WHITE);
	}
	
	@Override
	public void paint(Graphics g) {
		if(image != null){
			g.drawImage(image, 0, 0, this);
		}
	}
	
	public void setImage(BufferedImage image) {
		if(image != null){
			setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
			
			this.image = image;
			
			repaint();
		}
	}
	
}
