import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterJob;

import javax.swing.*;

/**
 * Using SWT components within a JFrame, this class creates the user interface of
 * the Sundial application. This class will also check for valid inputs as well
 * as handle output of data to the user. Generation of the user interface was
 * done using the NetBeans IDE GUI Builder.
 * @author Lawton Takaesu
 * @assignment Sundial Project
 * @date April 8, 2013
 * @class ICS 314
 * 
 */

//new comment
//new comment 2

public class SundialUI extends javax.swing.JFrame{
	
	// Variables declaration - do not modify                     
    private javax.swing.JButton clearButton;
    private javax.swing.JTextArea console;
    private javax.swing.JLabel coordLabel;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JComboBox dayInput;
    private javax.swing.JButton generateButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel latLabel;
    private javax.swing.JTextField latInput;
    private javax.swing.JTextField longtInput;
    private javax.swing.JLabel longtLabel;
    private javax.swing.JComboBox monthInput;
    private javax.swing.JComboBox yearInput;
    private JFrame jFrame1;
    private String defaultConsoleText;
    // End of variables declaration                   

    //Our created variables
    private double lat = 0;
    private double longt = 0;
	private JButton sundialPrintButton;
	private JButton gnomonPrintButton;
	private DrawDial sundial;
	private DrawGnomon gnomon;
	private JFrame sundialPopupWindow;
	private JFrame gnomonPopupWindow;
	private WindowShow windowBuilder;
	private JPanel sundialButtonPanel;
	private JPanel gnomonButtonPanel;
    /**
     * Creates new form Sundial
     */
    public SundialUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

    	//Instantiate all fields
        jFrame1 = new javax.swing.JFrame();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        console = new javax.swing.JTextArea();
        coordLabel = new javax.swing.JLabel();
        latLabel = new javax.swing.JLabel();
        longtLabel = new javax.swing.JLabel();
        dateLabel = new javax.swing.JLabel();
        latInput = new javax.swing.JTextField();
        longtInput = new javax.swing.JTextField();
        monthInput = new javax.swing.JComboBox();
        dayInput = new javax.swing.JComboBox();
        yearInput = new javax.swing.JComboBox();
        generateButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        windowBuilder = new WindowShow();

        //layout main display frmae
        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        //set size of main frame and listeners for when the window is closed
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setMaximumSize(new java.awt.Dimension(900, 450));
        setMinimumSize(new java.awt.Dimension(900, 450));
        setPreferredSize(new java.awt.Dimension(900, 450));
        setResizable(false);

        //configure the console display by setting font and size
        console.setColumns(20);
        console.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        console.setRows(5);
        console.setLineWrap(true);
        console.setWrapStyleWord(true);
        console.setPreferredSize(new java.awt.Dimension(185, 50));
        console.setEditable(false);
        console.setFocusable(false);
        jScrollPane2.setViewportView(console);
        defaultConsoleText = "Enter the Latitude and Longitude values in the appropriately labeled boxes above to begin generation of the sundial.\n" + 
           		"\nFor computation purposes, please do not include an degree symbols in the longitude and latitude values.\n" + 
           		"\nAlso, please use negative values for Southern latitudes and Western longitudes.\n";

        //Set labels for the applet
        coordLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        coordLabel.setText("Coordinates");

        latLabel.setText("Latitude");

        longtLabel.setText("Longitude");

        dateLabel.setText("Date");

        latInput.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        longtInput.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        //Create combo boxes for the date inputs
        monthInput.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Month", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        dayInput.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Day", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        yearInput.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Year", "2013", "2012", "2011", "2010" }));

        //set text for the buttons
        generateButton.setText("Generate");
        
        clearButton.setText("Clear");
        
        //Group layout that controls the positioning of all elements
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(longtLabel)
                            .addComponent(latLabel)
                            .addComponent(dateLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(monthInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(dayInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(yearInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(latInput)
                                .addComponent(longtInput))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(generateButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 524, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(coordLabel)))
                .addContainerGap(845, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(coordLabel)
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(latLabel)
                            .addComponent(latInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(longtLabel)
                            .addComponent(longtInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dateLabel)
                            .addComponent(monthInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dayInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(yearInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(generateButton)
                            .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(589, Short.MAX_VALUE))
        );

        pack();

        //Action Listener for the generate button
        this.generateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//Debugging messages
				//console.append("DEBUG: Generate button pressed with arguments:\n");
				//console.append("DEBUG: Latitude = " + lat + "\n");
				//console.append("DEBUG: Longitutde = " + longt + "\n");
				//console.append("DEBUG: Date = " + monthInput.getSelectedItem() + "/" + dayInput.getSelectedItem() + "/" + yearInput.getSelectedItem());
				
				//use functions to parse input from longitude and latitude input fields
				getLat();
				getLongt();
				
				//Output progress to user
				console.append("\nGenerating sundial using Latitude = " + lat + "and Longitude = " + longt + "...\n");
				
				//cast the selected inputs for the date into integers
				Integer selectedDay = Integer.parseInt((String) dayInput.getSelectedItem());
				Integer selectedMonth = Integer.parseInt((String) monthInput.getSelectedItem());
				Integer selectedYear = Integer.parseInt((String) yearInput.getSelectedItem());
				
				//create math module object to calculate angles to draw the dial
				Sundial_MathModule computeAngles = new Sundial_MathModule();
				double angles[] = computeAngles.computeAngles(lat, longt, selectedDay, selectedMonth, selectedYear);
				sundial = new DrawDial(angles[7], angles[8], angles[9], angles[10], angles[11], angles[0], angles[0], angles[1], angles[2], angles[3], angles[4], angles[5], angles[6]);
                                

				
				//Create pop up window for the Sundial so it can be printed
				sundialPopupWindow = new JFrame();
				sundialPopupWindow = windowBuilder.openInJFrame(sundialPopupWindow.getContentPane(), 1100, 700, "Sundial", Color.white);
				//add sundial to the new pop up window
				sundialPopupWindow.add(sundial, BorderLayout.CENTER);
				//create the print button and action listener to print the sundial
				sundialButtonPanel = new JPanel();
				//sundialButtonPanel.setPreferredSize(new java.awt.Dimension(20, 20));
				//sundialButtonPanel.setMaximumSize(new java.awt.Dimension(20, 20));
				//sundialButtonPanel.setMinimumSize(new java.awt.Dimension(20, 20));
				sundialPrintButton = new JButton("Print");
				sundialPrintButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent arg0){
						PrintableDocument.printComponent(sundialPopupWindow);
					}
				});
				//add components to the pop up window and make them visible
				sundialButtonPanel.setBackground(Color.white);
				sundialButtonPanel.add(sundialPrintButton);
				sundialPopupWindow.add(sundialButtonPanel, BorderLayout.SOUTH);
				sundialButtonPanel.setVisible(true);
				sundialPopupWindow.setVisible(true);
				
				//Draw the Gnomon
				gnomon = new DrawGnomon(lat);
				
				//create pop up window for the gnomon so it can be printed
				gnomonPopupWindow = new JFrame();
				gnomonPopupWindow = windowBuilder.openInJFrame(gnomonPopupWindow.getContentPane(), 800, 900, "Gnomon", Color.white);
				gnomonPopupWindow.setLayout( new BorderLayout());
				//add gnomon to the gnomon window
				gnomonPopupWindow.add(gnomon, BorderLayout.CENTER);
				gnomonButtonPanel = new JPanel();

				//gnomonButtonPanel.setPreferredSize(new java.awt.Dimension(20, 20));
				//gnomonButtonPanel.setMaximumSize(new java.awt.Dimension(20, 20));
				//gnomonButtonPanel.setMinimumSize(new java.awt.Dimension(20, 20));
				gnomonPrintButton = new JButton("Print");
				gnomonPrintButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent arg0){
						PrintableDocument.printComponent(gnomonPopupWindow);
					}
				});
				//add components to the gnomon pop up window and make them visible
				gnomonButtonPanel.setBackground(Color.white);
				gnomonButtonPanel.add(gnomonPrintButton);
				gnomonPopupWindow.add(gnomonButtonPanel, BorderLayout.NORTH);
				gnomonButtonPanel.setVisible(true);
				gnomonPopupWindow.setVisible(true);
				//success dialog
				console.append("\nSundial and Gnomon generated! Use the print buttons in the corresponding windows to print the images!\n");
			}
        });
        
        //Aciton listener for the Clear button
        this.clearButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//console.append("DEBUG: Clear button pressed!\n");
				//console.append("DEBUG: Entry fields cleared!\n");
				//reset all input fields and console
				latInput.setText("");
				longtInput.setText("");
				dayInput.setSelectedIndex(0);
				monthInput.setSelectedIndex(0);
				yearInput.setSelectedIndex(0);
				console.setText(defaultConsoleText);
			}
        });
        
        pack();
        
        this.console.setText(defaultConsoleText);
    }// </editor-fold>                        
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SundialUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SundialUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SundialUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SundialUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SundialUI().setVisible(true);
            }
        });
    }
    
    /**
     * Private method to parse values from the Latitude input field.
     * Does type check via a try-catch block for number format errors.
     * If the entered value is valid, this method will set the global variable to the inputed value.
     */
    private void getLat(){
    	try{
    		this.lat = Double.parseDouble(this.latInput.getText());
    	}
    	catch (NullPointerException E){
    		this.console.append("\nNo values entered for Latitude. Please enter a value.\n");
    	}
    	catch (NumberFormatException E){
    		this.console.append("\nInvalid Latitude value. Please enter either a positive or negative value with no degree symbol.\n");
    		this.console.append("Example: 89.1285 or -32.5632\n");
    	}
    }
    
    /**
     * Private method to parse values from the Longitude input field.
     * Does type check via a try-catch block for number format errors.
     * If the entered value is valid, this method will set the global variable to the inputed value.
     */
    private void getLongt(){
    	try{
    		this.longt = Double.parseDouble(this.longtInput.getText());
    	}
    	catch (NullPointerException E){
    		this.console.append("\nNo values entered for Latitude. Please enter a value.\n");
    	}
    	catch (NumberFormatException E){
    		this.console.append("\nInvalid Longitude value. Please enter either a positive or negative value with no degree symbol.\n");
    		this.console.append("Example: 89.1285 or -32.5632\n");
    	}
    }
    
   
}
