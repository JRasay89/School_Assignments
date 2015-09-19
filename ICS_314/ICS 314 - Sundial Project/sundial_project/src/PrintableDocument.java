/**
 * Class to print the jFrames containing the sundial and gnomon
 * @author John Rasay, Christopher Rickett, Lawton Takaesu
 */

import java.awt.*;

import javax.swing.*;
import java.awt.print.*;


public class PrintableDocument implements Printable {
  private Component compent;
 
  public static void printComponent(Component c) {
    new PrintableDocument(c).print();
  }
 
  public PrintableDocument(Component compent) {
    this.compent = compent;
  }
 
  public void print() {
    PrinterJob printJob = PrinterJob.getPrinterJob();
    printJob.setPrintable(this);
    if(printJob.printDialog())
      try {
      printJob.print();
    }
    catch(PrinterException pe) {
      System.out.println("Error printing: " + pe);
    }
  }
 
  /**
   * Implemented print method to format the graphic to fit on standard 8.5x11" paper
   */
  public int print(Graphics g, PageFormat pageFormat, int pageIndex) {
    if (pageIndex > 0) {
      return(NO_SUCH_PAGE);
    }
    else {
      Graphics2D graph = (Graphics2D)g;
      graph.setBackground(Color.white);
      graph.scale(.5, .5);
      graph.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
      disableBuffering(compent);
      compent.paint(graph);
      enableBuffering(compent);
      return(PAGE_EXISTS);
    }
  }
 
  public static void disableBuffering(Component c) {
    RepaintManager currentManager = RepaintManager.currentManager(c);
    currentManager.setDoubleBufferingEnabled(false);
  }
 
  public static void enableBuffering(Component c) {
    RepaintManager currentManager = RepaintManager.currentManager(c);
    currentManager.setDoubleBufferingEnabled(true);
  }
}

