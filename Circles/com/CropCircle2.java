package com;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.geom.Ellipse2D ;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class CropCircle2 extends Canvas{
	
	private BufferedImage bimg;
	
	double pX, pY;
	double cX, cY;
	double theta;
	double R,r;
	
	public CropCircle2(){
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
         
         g2.setPaint(new Color(218, 165, 32));
         
         /*cX = 300; cY = 400;
         R = 10;
         double pY1;
         pY1 = cY + R;
         
         //======================Straight cut====================
         int c=1;
         while(c<=5){
        	 g2.drawOval((int)(cX-R), (int)(cY-R), (int)R*2, (int)R*2);
        	 R+=7*c;
        	 cY = pY1-R;
        	 c++;
         }
         */
         
         cX = 170; cY = 195; R = 115;
         //-----------------
         pX = cX;
         pY = cY-R;
         System.out.println(cX + " : " + cY + " = " + R);
         r=30;
         
         g2.drawOval((int)(pX-r), (int)(pY-r), (int)r*2, (int)r*2); // top most circle
         drawInnerCirc(g2, pX, pY, r);
         
         drawCirc(g2, 90, R, 1);  //left half
         drawCirc(g2, 90, R, -1); //right half
         
         //------------------Reverse method for Inner Circles-------------------
         double pY1;
         pY1 = cY + R;
         
         int c=5;
         while(c>=1){
        	 R-=7*c;
        	 cY = pY1-R;
        	 if(c%2 == 1){
        		 g2.setPaint(new Color(218, 165, 32));
        	 } else {
        		 g2.setPaint(new Color(255,215,0));
        	 }
        	 g2.fill(new Ellipse2D.Double((int)(cX-R), (int)(cY-R),(int)R*2, (int)R*2));
        	 c--;
         }
         
         g2.dispose();  //-------------DISPOSED---------------
         graphics.drawImage(bimg, 0, 0, this); 
   }
   
   public void drawCirc(Graphics2D g2, double initAng, double R1, double sign){
	   double theta = (initAng*3.14159/180);
       R = R1;
       r = 30;
       do{
    	 theta = theta +  sign*(r/R + (r-3)/R);
         r-=3;
      	 
         pX = cX + R * Math.cos(theta);
      	 pY = cY - R * Math.sin(theta);
      	 
      	 g2.drawOval((int)(pX-r), (int)(pY-r), (int)r*2, (int)r*2);
      	 drawInnerCirc(g2, pX, pY, r);
       }while(r>=5);
   }
   
   public void drawInnerCirc(Graphics2D g2, double centX, double centY, double inR){
	   while(inR>=1){
		   g2.setPaint(new GradientPaint((int)(centX-inR), (int)(centY-inR),new Color(218, 145, 32),(int)(centX+inR), (int)(centY+inR),Color.YELLOW));
		   g2.drawOval((int)(centX-inR), (int)(centY-inR), (int)inR*2, (int)inR*2);
		   inR-=2;
	   }
   }

  public static void main(String args[]){
	  final CropCircle2 iCanvas = new CropCircle2();
      JFrame frame = new JFrame();
      frame.setSize(350, 360);
      iCanvas.setBackground(new Color(255,215,0));
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setTitle("Crop Circle - by Prasenjit Das [ 309556 ]");
      frame.getContentPane().add(iCanvas);
      frame.setVisible(true);
  }
}
