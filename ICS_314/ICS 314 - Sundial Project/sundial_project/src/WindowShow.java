/**
 * Class to build the jFrames for displaying the sundial and gnomon
 * @author John Rasay, Christopher Rickett, Lawton Takaesu
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class WindowShow {

  public static void setNativeLookAndFeel() {
    try{
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    catch(Exception e) {
      System.out.println("Error setting native LAF: " + e);
    }
  }
 
  /**
   * Create a new jFrame element with the following parameters
   * @param content - handle to the content to be displayed
   * @param width - width of the new jFrame
   * @param height - height of the new jFrame
   * @param title - title of the new jFrame
   * @param bgColor - background color of the new jFrame
   * @return new jFrame with the above attributes
   */
  public  JFrame openInJFrame
     (Container content, int width, int height, String title, Color bgColor) {
    class ExitListener extends WindowAdapter {
      public void windowClosing(WindowEvent event) {
        
      }
    }
    //constructor with titlde
    JFrame frame = new JFrame(title);
    //set background color of the frame and content
    frame.setBackground(bgColor);
    content.setBackground(bgColor);
    //set dimensions
    frame.setSize(width, height);
    frame.setContentPane(content);
    //set the frame to be visible
    frame.setVisible(true);
    return(frame);
  }
 
  /**
   * Create a new jFrame but with the default background color automatically set to white
   *@param content - handle to the content to be displayed
   * @param width - width of the new jFrame
   * @param height - height of the new jFrame
   * @param title - title of the new jFrame
   * @return new jFrame with above attributes and a white background
   */
  public  JFrame openInJFrame
                     (Container content, int width, int height, String title) {
    return(openInJFrame(content, width, height, title, Color.white));
  }
 
  /**
   * Create a new jFrame with the default title and background color set to white
   * @param content - handle to the content to be displayed
   * @param width - width of the new jFrame
   * @param height - height of the new jFrame
   * @return new jFrame with above attrivutes, default title, and white background
   */
  public  JFrame openInJFrame(Container content, int width, int height) {
    return(openInJFrame(content, width, height,content.getClass().getName(), Color.white));
  }
}