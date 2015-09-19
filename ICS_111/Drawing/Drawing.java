/*
**
** John Rasay
** ICS 111
** Drawing.java
** OCT 5 2009
*/

import java.awt.*;
import java.awt.event.*;

public class Drawing extends Frame
{
   public static void main(String[] args)
   {
      Drawing drawing = new Drawing();
      drawing.setSize(600, 500);
      drawing.setLocation(100, 50);
      drawing.setBackground(new Color(50, 200, 255));
      drawing.setTitle("Battle Tank");
      drawing.setVisible(true);
      WindowListener listen = new WindowAdapter()
      {
         public void windowClosing(WindowEvent e)
	 {
            System.exit(0);
	 }
      };
      drawing.addWindowListener(listen);
   }
   
   public void paint(Graphics g)
   {
      //The Background & sun!   
      g.setColor(new Color(153, 51, 0));
      g.fillRect(0, 380, 600, 120);   
      g.setColor(Color.yellow);
      g.fillArc(500, 50, 50, 50, 0, 360);
      g.setColor(Color.black);
      g.drawArc(500, 50, 50, 50, 0, 360);
      g.drawRect(0, 380, 600, 120);
      // The building!
      g.setColor(Color.red);
      g.fillRect(0, 100, 150, 280);
      g.setColor(Color.white);
      g.fillRect(20, 150, 30, 50);
      g.fillRect(100, 150, 30, 50);
      g.fillRect(10, 300, 100, 80);
      g.setColor(Color.black);
      g.drawRect(0, 100, 150, 280);
      g.drawRect(20, 150, 30, 50);
      g.drawRect(100, 150, 30, 50);
      g.drawRect(10, 300, 100, 80);
      g.drawLine(60, 300, 60, 380);
      //The Flag!
      g.fillRect(60, 50, 10, 50);
      g.setColor(Color.white);
      g.fillRect(70, 50, 70, 30);
      g.setColor(Color.black);
      g.drawRect(70, 50, 70, 30);
      //The Tank!
      g.setColor(Color.green);
      g.fillRect(300, 250, 150, 100);
      g.fillRect(220, 280, 80, 40);
      g.fillRect(260, 360, 230, 20);
      g.setColor(Color.black);
      g.fillArc(250, 350, 50, 40, 90, 180);
      g.fillArc(450, 350, 50, 40, 90, -180);
      g.fillArc(350, 210, 50, 40, 0, 180);
      g.fillRect(270, 350, 210, 10);
      g.fillRect(270, 380, 210, 10);
      g.fillRect(200, 280, 20, 40);
      g.fillRect(350, 230, 50, 20);
      g.drawRect(300, 250, 150, 100);
      g.drawRect(220, 280, 80, 40);
      g.drawRect(200, 280, 20, 40);
      //The tittle!
      g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
      g.setColor(Color.black);
      g.drawString("Battle Tank", 250, 450);
   } 
}





