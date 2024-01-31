package com;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

/**
 * @author 309556
 * date: May 14, 2012
 */

@SuppressWarnings("serial")
public class CropCircle extends Canvas{
	
	private BufferedImage bimg;
	
	double pX, pY;
	double cX, cY;
	double theta;
	double R,r;
	
	public CropCircle(){
		cX = 200; cY = 200;
	}
	
	public Graphics2D createGraphics2D(int w, int h) {
        Graphics2D g2 = null;
        if (bimg == null || bimg.getWidth() != w || bimg.getHeight() != h) {
            bimg = (BufferedImage) createImage(w, h);
        }
        g2 = bimg.createGraphics();
        g2.setBackground(getBackground());
        //g2.clearRect(0, 0, w, h);
        return g2;
    }

   public void update(Graphics g)
      {
          paint(g);
      }

   public void paint(Graphics graphics)
      {
         Dimension d = getSize();
         Graphics2D g2 = createGraphics2D(d.width, d.height);  //---------------CREATED-------------
         g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
         
         double X = 300; //center of the trianle
         double Y = 300;
         double Rad = 100;

         double ang = 90;
         int c = 0;
         
         g2.setPaint(Color.GREEN);
         while(ang<360){
        	 cX = X + Rad * Math.cos(ang * 3.14159/180);
             cY = Y - Rad * Math.sin(ang * 3.14159/180);
             
             g2.drawOval((int)(cX-15), (int)(cY-15), 30, 30);
             drawInnerCirc(g2, cX, cY, 15);
             
             drawCirc(g2, ang+180);
             
        	 ang+=120;
        	 c++;
         }

         
         g2.dispose();  //-------------DISPOSED---------------
         graphics.drawImage(bimg, 0, 0, this); 
      }
   
   public void drawCirc(Graphics2D g2, double initAng){
	   double theta = (initAng*3.14159/180);
       R = 100;
       r = 23;
       while(r>=5){ //theta <= 6.28318  2*3.14159 = 2pi = 360 degree
      	 
      	 pX = cX + R * Math.cos(theta);
      	 pY = cY - R * Math.sin(theta);
      	 
      	 g2.drawOval((int)(pX-r), (int)(pY-r), (int)r*2, (int)r*2);
      	 drawInnerCirc(g2, pX, pY, r);
      	 
      	 theta += r/R + (r-1)/R;
      	 r-=1;
       }
   }
   
   public void drawInnerCirc(Graphics2D g2, double centX, double centY, double inR){
	   while(inR>=1){
		   g2.setPaint(new GradientPaint((int)(centX-inR), (int)(centY-inR),Color.GREEN,(int)(centX+inR), (int)(centY+inR),Color.YELLOW));
		   g2.drawOval((int)(centX-inR), (int)(centY-inR), (int)inR*2, (int)inR*2);
		   inR-=2;
	   }
   }

  public static void main(String args[]){
	  final CropCircle iCanvas = new CropCircle();
      JFrame frame = new JFrame();
      frame.setSize(600, 630);
      iCanvas.setBackground(new Color(34,139,34));
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setTitle("Crop Circle - by Prasenjit Das [ 309556 ]");
      frame.getContentPane().add(iCanvas);
      frame.setVisible(true);
  }
}
