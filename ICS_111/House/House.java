/*
** House.java
**
** This program draws a very simple house. It is used to show the basic drawing 
** methods from the Java AWT, the Abstract Windowing Toolkit.
** It is also designed to demonstrate inheritance and the use of predefined Java
** classes and methods.
**
** John Rasay
** Honolulu community college
** ICS 111
*/

import java.awt.*;
import java.awt.event.*;

public class House extends Frame
{
   public static void main(String[] args)
   {
      House house = new House();
      house.setSize(600, 500);
      house.setLocation(100, 50);
      house.setBackground(new Color(50, 200, 255));
      house.setTitle("My beautiful house");
      house.setVisible(true);
      WindowListener listen = new WindowAdapter() // to allow the window to close
      {
          public void windowClosing(WindowEvent e)
	  {
	     System.exit(0);
	  }
      };    // notice that this semi-colon is required!
      house.addWindowListener(listen);
   }
   
   public void paint(Graphics g)
   {
      g.setColor(Color.green);
      g.fillRect(0, 350, 600, 150);
      g.setColor(Color.yellow);
      g.fillArc(500, 50, 50, 50, 0, 360);
      g.setColor(Color.black);
      g.drawArc(500, 50, 50, 50, 0, 360);
      
      g.setColor(Color.red);
      g.fillRect(200, 200, 200, 200);
      g.setColor(Color.black);
      g.drawRect(200, 200, 200, 200);
      
      g.setColor(new Color(150, 100, 50));
      g.fillRect(320, 310, 50, 90);
      g.setColor(Color.black);
      g.drawRect(320, 310, 50, 90);
      g.setColor(Color.lightGray);
      g.fillArc(240, 230, 50, 40, 90, 180);
      g.fillRect(265, 230, 70, 40);
      g.fillArc(310, 230, 50, 40, 270, 180);
      g.setColor(Color.black);
      g.drawArc(240, 230, 50, 40, 90, 180);
      g.drawLine(265, 230, 335, 230);
      g.drawArc(310, 230, 50, 40, 270, 180);
      g.drawLine(265, 270, 335, 270);
      
      g.setColor(Color.white);
      g.fillRoundRect(220, 300, 60, 60, 20, 20);
      g.setColor(Color.black);
      g.drawRoundRect(220, 300, 60, 60, 20, 20);
      
      Polygon roof = new Polygon();
      roof.addPoint(300, 150);
      roof.addPoint(400, 200);
      roof.addPoint(200, 200);
      g.setColor(Color.black);
      g.fillPolygon(roof);
      g.drawPolygon(roof);
      g.drawLine(300, 150, 180, 210);
      g.drawLine(300, 150, 420, 210);
      
      Polygon chimney = new Polygon();
      chimney.addPoint(340, 140);
      chimney.addPoint(360, 140);
      chimney.addPoint(360, 180);
      chimney.addPoint(340, 170);
      g.setColor(Color.red);
      g.fillPolygon(chimney);
      g.setColor(Color.black);
      g.drawPolygon(chimney);
      
      g.drawArc(355, 345, 10, 10, 0, 360);
      
      
      g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
      g.setColor(Color.black);
      g.drawString("My House", 250, 450);
   }
}
