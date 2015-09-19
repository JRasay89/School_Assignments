import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.Line2D;
import java.math.BigDecimal;
import javax.swing.JPanel;

public class DrawDial extends JPanel {
	//******************************************************************************Instance Variables*************************************************************************************
	private int width;//store screen width here
	private int height;//store screen height here
	//----------------------------------------------Coordinates for the border----------------------------------------------
	//Left side
	private double left_point_x;
	private double left_point_y1;
	private double left_point_y2;
	//right side
	private double right_point_x;
	private double right_point_y1;
	private double right_point_y2;
	//top side
	private double top_point_x1;
	private double top_point_x2;
	private double top_point_y;
	//bottom side
	private double bottom_point_x1;
	private double bottom_point_x2;
	private double bottom_point_y;
	
	private double origin; //mid of the border
	private double h; //Height of the border
	private double b; //Length of half of the border
	//--------------------------------------------------Hour Lines----------------------------------------------------------
	//Hours for pm
	private double one_pm;
	private double two_pm;
	private double three_pm;
	private double four_pm;
	private double five_pm;
	private double six_pm;
	//Hours for am
	private double six_am;
	private double seven_am;
	private double eight_am;
	private double nine_am;
	private double ten_am;
	private double eleven_am;
	private double twelve;
		
	//-------------------------------------------------Hour_Angles-----------------------------------------------------------
	//Hours on the east
	private double one_pm_angle;
	private double two_pm_angle;
	private double three_pm_angle;
	private double four_pm_angle;
	private double five_pm_angle;
	private double six_pm_angle;
	//Hours on the west
	private double six_am_angle;
	private double seven_am_angle;
	private double eight_am_angle;
	private double nine_am_angle;
	private double ten_am_angle;
	private double eleven_am_angle;
	//The 12'o clock 
	private double twelve_angle;
	
	//boolean variables for checking if hour line is on the left or right of the dial
	private boolean one_pm_eastwest = false;
	private boolean two_pm_eastwest = false;
	private boolean three_pm_eastwest = false;
	private boolean four_pm_eastwest = false;
	private boolean five_pm_eastwest = false;
	private boolean six_pm_eastwest = false;
	private boolean six_am_eastwest = false;
	private boolean seven_am_eastwest = false;
	private boolean eight_am_eastwest = false;
	private boolean nine_am_eastwest = false;
	private boolean ten_am_eastwest = false;
	private boolean eleven_am_eastwest = false;
	private boolean twelve_eastwest = false;
   
	/**
	 * The constructor of DrawDial
	 * @param one_pm_angle is the angle for the 1'o clock 
	 * @param two_pm_angle is the angle for the 2'o clock 
	 * @param three_pm_angle is the angle for the 3'o clock 
	 * @param four_pm_angle
	 * @param five_pm_angle
	 * @param six_pm_angle
	 * @param six_am_angle
	 * @param seven_am_angle
	 * @param eight_am_angle
	 * @param nine_am_angle
	 * @param ten_am_angle
	 * @param eleven_am_angle
	 * @param twelve_angle
	 */
	public DrawDial(double one_pm_angle,double two_pm_angle,double three_pm_angle,double four_pm_angle,double five_pm_angle,double six_pm_angle,
					double six_am_angle,double seven_am_angle,double eight_am_angle,double nine_am_angle,double ten_am_angle,double eleven_am_angle,double twelve_angle) {
		
		setBackground(Color.white);
		
		//************************************************************Screen Resolution**************************************************************************************************
		this.width = Toolkit.getDefaultToolkit().getScreenSize().width;
		this.height = Toolkit.getDefaultToolkit().getScreenSize().height;
		//**************************************************Initializing the Coordinates of the border***********************************************************************************
		//left side
		this.left_point_x = this.width*.05;
		this.left_point_y1 = this.height*.05;
		this.left_point_y2 = this.height*.85;
		//right side
		this.right_point_x = this.width*.90;
		this.right_point_y1 = this.height*.05;
		this.right_point_y2 = this.height*.85;
		//top side
		this.top_point_x1 = this.width*.05;
		this.top_point_x2 = this.width*.90;
		this.top_point_y = this.height*.05;
		//bottom side
		this.bottom_point_x1 = this.width*.05;
		this.bottom_point_x2 = this.width*.90;
		this.bottom_point_y = this.height*.85;
		
		//*************************************************************Initializing the length and width of the border****************************************************************
		this.origin = (((this.width*.90 - this.width*.05)/2)+ this.width*.05);
		this.h = this.height * .85 - this.height * .05;
		this.b = this.origin - this.width*.05;
		
		//*******************************************************************Initializing 12'o clock line*****************************************************************************
		//Check if on west side or east side
		if (twelve_angle <= 0) {
			this.twelve_angle = 90 + twelve_angle;
			if (this.twelve_angle >= 0) {
				if (this.twelve_angle <= 45) {
					this.twelve = Math.tan(Math.toRadians(this.twelve_angle))*(this.b);
					this.twelve = (this.h - this.round(this.twelve, 2, BigDecimal.ROUND_HALF_UP)) + this.height*.05;
				}
				else if (this.twelve_angle > 45 && this.twelve_angle < 90) {
					this.twelve = Math.tan(Math.toRadians(90 - this.twelve_angle))*(this.h);
					this.twelve = (this.origin - (this.round(this.twelve, 2, BigDecimal.ROUND_HALF_UP)));
				}
			}
		}
		else if (twelve_angle > 0) {
			twelve_eastwest = true;
			this.twelve_angle = 90 - twelve_angle;
			if (this.twelve_angle >= 0) {
				if (this.twelve_angle <= 45) {
					this.twelve = Math.tan(Math.toRadians(this.twelve_angle))*(this.b);
					this.twelve = (this.h - this.round(this.twelve, 2, BigDecimal.ROUND_HALF_UP)) + this.height*.05;
				}
				else if (this.twelve_angle > 45 && this.twelve_angle < 90) {
					this.twelve = Math.tan(Math.toRadians(90 - this.twelve_angle))*(this.h);
					this.twelve = (this.origin + (this.round(this.twelve, 2, BigDecimal.ROUND_HALF_UP)));
				}
			}
		}
		
		//*******************************************************************Initializing 1'o clock line******************************************************************************
		//Check if on west side or east side
		if (one_pm_angle <= 0) {
			this.one_pm_angle = 90 + one_pm_angle;
			if (this.one_pm_angle >= 0) {
				if (this.one_pm_angle <= 45) {
					this.one_pm = Math.tan(Math.toRadians(this.one_pm_angle))*(this.b);
					this.one_pm = (this.h - this.round(this.one_pm, 2, BigDecimal.ROUND_HALF_UP)) + this.height*.05;
				}
				else if (this.one_pm_angle > 45 && this.one_pm_angle < 90) {
					this.one_pm = Math.tan(Math.toRadians(90 - this.one_pm_angle))*(this.h);
					this.one_pm = (this.origin - (this.round(this.one_pm, 2, BigDecimal.ROUND_HALF_UP)));
				}
			}
		}
		else if (one_pm_angle > 0) {
			one_pm_eastwest = true;
			this.one_pm_angle = 90 - one_pm_angle;
			if (this.one_pm_angle >= 0) {
				if (this.one_pm_angle <= 45) {
					this.one_pm = Math.tan(Math.toRadians(this.one_pm_angle))*(this.b);
					this.one_pm = (this.h - this.round(this.one_pm, 2, BigDecimal.ROUND_HALF_UP)) + this.height*.05;
				}
				else if (this.one_pm_angle > 45 && this.one_pm_angle < 90) {
					this.one_pm = Math.tan(Math.toRadians(90 - this.one_pm_angle))*(this.h);
					this.one_pm = (this.origin + (this.round(this.one_pm, 2, BigDecimal.ROUND_HALF_UP)));
				}
			}
		}
		
		//*******************************************************************Initializing 2'o clock line******************************************************************************
		//Check if on west side or east side
		if (two_pm_angle <= 0) {
			this.two_pm_angle = 90 + two_pm_angle;
			if (this.two_pm_angle >= 0) {
				if (this.two_pm_angle <= 45) {
					this.two_pm = Math.tan(Math.toRadians(this.two_pm_angle))*(this.b);
					this.two_pm = (this.h - this.round(this.two_pm, 2, BigDecimal.ROUND_HALF_UP)) + this.height*.05;
				}
				else if (this.two_pm_angle > 45 && this.two_pm_angle < 90) {
					this.two_pm = Math.tan(Math.toRadians(90 - this.two_pm_angle))*(this.h);
					this.two_pm = (this.origin - (this.round(this.two_pm, 2, BigDecimal.ROUND_HALF_UP)));
				}
			}
		}
		else if (two_pm_angle > 0) {
			two_pm_eastwest = true;
			this.two_pm_angle = 90 - two_pm_angle;
			if (this.two_pm_angle >= 0) {
				if (this.two_pm_angle <= 45) {
					this.two_pm = Math.tan(Math.toRadians(this.two_pm_angle))*(this.b);
					this.two_pm = (this.h - this.round(this.two_pm, 2, BigDecimal.ROUND_HALF_UP)) + this.height*.05;
				}
				else if (this.two_pm_angle > 45 && this.two_pm_angle < 90) {
					this.two_pm = Math.tan(Math.toRadians(90 - this.two_pm_angle))*(this.h);
					this.two_pm = (this.origin + (this.round(this.two_pm, 2, BigDecimal.ROUND_HALF_UP)));
				}
			}
		}
		
		//*******************************************************************Initializing 3'o clock line******************************************************************************
		//Check if on west side or east side
		if (three_pm_angle <= 0) {
			this.three_pm_angle = 90 + three_pm_angle;
			if (this.three_pm_angle >= 0) {
				if (this.three_pm_angle <= 45) {
					this.three_pm = Math.tan(Math.toRadians(this.three_pm_angle))*(this.b);
					this.three_pm = (this.h - this.round(this.three_pm, 2, BigDecimal.ROUND_HALF_UP)) + this.height*.05;
				}
				else if (this.three_pm_angle > 45 && this.three_pm_angle < 90) {
					this.three_pm = Math.tan(Math.toRadians(90 - this.three_pm_angle))*(this.h);
					this.three_pm = (this.origin - (this.round(this.three_pm, 2, BigDecimal.ROUND_HALF_UP)));
				}
			}
		}
		else if (three_pm_angle > 0) {
			three_pm_eastwest = true;
			this.three_pm_angle = 90 - three_pm_angle;
			if (this.three_pm_angle >= 0) {
				if (this.three_pm_angle <= 45) {
					this.three_pm = Math.tan(Math.toRadians(this.three_pm_angle))*(this.b);
					this.three_pm = (this.h - this.round(this.three_pm, 2, BigDecimal.ROUND_HALF_UP)) + this.height*.05;
				}
				else if (this.three_pm_angle > 45 && this.three_pm_angle < 90) {
					this.three_pm = Math.tan(Math.toRadians(90 - this.three_pm_angle))*(this.h);
					this.three_pm = (this.origin + (this.round(this.three_pm, 2, BigDecimal.ROUND_HALF_UP)));
				}
			}
		}
		//*******************************************************************Initializing 4'o clock line******************************************************************************
				//Check if on west side or east side
		if (four_pm_angle <= 0) {
			this.four_pm_angle = 90 + four_pm_angle;
			if (this.four_pm_angle >= 0) {
				if (this.four_pm_angle <= 45) {
					this.four_pm = Math.tan(Math.toRadians(this.four_pm_angle))*(this.b);
					this.four_pm = (this.h - this.round(this.four_pm, 2, BigDecimal.ROUND_HALF_UP)) + this.height*.05;
				}
				else if (this.four_pm_angle > 45 && this.four_pm_angle < 90) {
					this.four_pm = Math.tan(Math.toRadians(90 - this.four_pm_angle))*(this.h);
					this.four_pm = (this.origin - (this.round(this.four_pm, 2, BigDecimal.ROUND_HALF_UP)));
				}
			}
		}
		else if (four_pm_angle > 0) {
			four_pm_eastwest = true;
			this.four_pm_angle = 90 - four_pm_angle;
			if (this.four_pm_angle >= 0) {
				if (this.four_pm_angle <= 45) {
					this.four_pm = Math.tan(Math.toRadians(this.four_pm_angle))*(this.b);
					this.four_pm = (this.h - this.round(this.four_pm, 2, BigDecimal.ROUND_HALF_UP)) + this.height*.05;
				}
				else if (this.four_pm_angle > 45 && this.four_pm_angle < 90) {
					this.four_pm = Math.tan(Math.toRadians(90 - this.four_pm_angle))*(this.h);
					this.four_pm = (this.origin + (this.round(this.four_pm, 2, BigDecimal.ROUND_HALF_UP)));
				}
			}
		}
		
		//*******************************************************************Initializing 5'o clock line******************************************************************************
			//Check if on west side or east side
		if (five_pm_angle <= 0) {
			this.five_pm_angle = 90 + five_pm_angle;
			if (this.five_pm_angle >= 0) {
				if (this.five_pm_angle <= 45) {
					this.five_pm = Math.tan(Math.toRadians(this.five_pm_angle))*(this.b);
					this.five_pm = (this.h - this.round(this.five_pm, 2, BigDecimal.ROUND_HALF_UP)) + this.height*.05;
						}
				else if (this.five_pm_angle > 45 && this.five_pm_angle < 90) {
					this.five_pm = Math.tan(Math.toRadians(90 - this.five_pm_angle))*(this.h);
					this.five_pm = (this.origin - (this.round(this.five_pm, 2, BigDecimal.ROUND_HALF_UP)));
				}
			}
		}
		else if (five_pm_angle > 0) {
			five_pm_eastwest = true;
			this.five_pm_angle = 90 - five_pm_angle;
			if (this.five_pm_angle >= 0) {
				if (this.five_pm_angle <= 45) {
					this.five_pm = Math.tan(Math.toRadians(this.five_pm_angle))*(this.b);
					this.five_pm = (this.h - this.round(this.five_pm, 2, BigDecimal.ROUND_HALF_UP)) + this.height*.05;
				}
				else if (this.five_pm_angle > 45 && this.five_pm_angle < 90) {
					this.five_pm = Math.tan(Math.toRadians(90 - this.five_pm_angle))*(this.h);
					this.five_pm = (this.origin + (this.round(this.five_pm, 2, BigDecimal.ROUND_HALF_UP)));
				}
			}
		}
		//*******************************************************************Initializing 6'o clock line******************************************************************************
		//Check if on west side or east side
		if (six_pm_angle <= 0) {
			this.six_pm_angle = 90 + six_pm_angle;
			if (this.six_pm_angle >= 0) {
				if (this.six_pm_angle <= 45) {
					this.six_pm = Math.tan(Math.toRadians(this.six_pm_angle))*(this.b);
					this.six_pm = (this.h - this.round(this.six_pm, 2, BigDecimal.ROUND_HALF_UP)) + this.height*.05;
				}
				else if (this.six_pm_angle > 45 && this.six_pm_angle < 90) {
					this.six_pm = Math.tan(Math.toRadians(90 - this.six_pm_angle))*(this.h);
					this.six_pm = (this.origin - (this.round(this.six_pm, 2, BigDecimal.ROUND_HALF_UP)));
				}
			}
		}
		else if (six_pm_angle > 0) {
			six_pm_eastwest = true;
			this.six_pm_angle = 90 - six_pm_angle;
			if (this.six_pm_angle >= 0) {
				if (this.six_pm_angle <= 45) {
					this.six_pm = Math.tan(Math.toRadians(this.six_pm_angle))*(this.b);
					this.six_pm = (this.h - this.round(this.six_pm, 2, BigDecimal.ROUND_HALF_UP)) + this.height*.05;
				}
				else if (this.six_pm_angle > 45 && this.six_pm_angle < 90) {
					this.six_pm = Math.tan(Math.toRadians(90 - this.six_pm_angle))*(this.h);
					this.six_pm = (this.origin + (this.round(this.six_pm, 2, BigDecimal.ROUND_HALF_UP)));
				}
			}
		}
		
		//*******************************************************************Initializing 6'o clock am line******************************************************************************
				//Check if on west side or east side
		if (six_am_angle <= 0) {
			this.six_am_angle = 90 + six_am_angle;
			if (this.six_am_angle >= 0) {
				if (this.six_am_angle <= 45) {
					this.six_am = Math.tan(Math.toRadians(this.six_am_angle))*(this.b);
					this.six_am = (this.h - this.round(this.six_am, 2, BigDecimal.ROUND_HALF_UP)) + this.height*.05;
				}
				else if (this.six_am_angle > 45 && this.six_am_angle < 90) {
					this.six_am = Math.tan(Math.toRadians(90 - this.six_am_angle))*(this.h);
					this.six_am = (this.origin - (this.round(this.six_am, 2, BigDecimal.ROUND_HALF_UP)));
				}
			}
		}
		else if (six_am_angle > 0) {
			six_am_eastwest = true;
			this.six_am_angle = 90 - six_am_angle;
			if (this.six_am_angle >= 0) {
				if (this.six_am_angle <= 45) {
					this.six_am = Math.tan(Math.toRadians(this.six_am_angle))*(this.b);
					this.six_am = (this.h - this.round(this.six_am, 2, BigDecimal.ROUND_HALF_UP)) + this.height*.05;
				}
				else if (this.six_am_angle > 45 && this.six_am_angle < 90) {
					this.six_am = Math.tan(Math.toRadians(90 - this.six_am_angle))*(this.h);
					this.six_am = (this.origin + (this.round(this.six_am, 2, BigDecimal.ROUND_HALF_UP)));
				}
			}
		}
		//*******************************************************************Initializing 7'o clock am line******************************************************************************
		//Check if on west side or east side
		if (seven_am_angle <= 0) {
			this.seven_am_angle = 90 + seven_am_angle;
			if (this.seven_am_angle >= 0) {
				if (this.seven_am_angle <= 45) {
					this.seven_am = Math.tan(Math.toRadians(this.seven_am_angle))*(this.b);
					this.seven_am = (this.h - this.round(this.seven_am, 2, BigDecimal.ROUND_HALF_UP)) + this.height*.05;
				}
				else if (this.seven_am_angle > 45 && this.seven_am_angle < 90) {
					this.seven_am = Math.tan(Math.toRadians(90 - this.seven_am_angle))*(this.h);
					this.seven_am = (this.origin - (this.round(this.seven_am, 2, BigDecimal.ROUND_HALF_UP)));
				}
			}
		}
		else if (seven_am_angle > 0) {
			seven_am_eastwest = true;
			this.seven_am_angle = 90 - seven_am_angle;
			if (this.seven_am_angle >= 0) {
				if (this.seven_am_angle <= 45) {
					this.seven_am = Math.tan(Math.toRadians(this.seven_am_angle))*(this.b);
					this.seven_am = (this.h - this.round(this.seven_am, 2, BigDecimal.ROUND_HALF_UP)) + this.height*.05;
				}
				else if (this.seven_am_angle > 45 && this.seven_am_angle < 90) {
					this.seven_am = Math.tan(Math.toRadians(90 - this.seven_am_angle))*(this.h);
					this.seven_am = (this.origin + (this.round(this.seven_am, 2, BigDecimal.ROUND_HALF_UP)));
				}
			}
		}
		//*******************************************************************Initializing 8'o clock am line******************************************************************************
		//Check if on west side or east side
		if (eight_am_angle <= 0) {
			this.eight_am_angle = 90 + eight_am_angle;
			if (this.eight_am_angle >= 0) {
				if (this.eight_am_angle <= 45) {
					this.eight_am = Math.tan(Math.toRadians(this.eight_am_angle))*(this.b);
					this.eight_am = (this.h - this.round(this.eight_am, 2, BigDecimal.ROUND_HALF_UP)) + this.height*.05;
				}
				else if (this.eight_am_angle > 45 && this.eight_am_angle < 90) {
					this.eight_am = Math.tan(Math.toRadians(90 - this.eight_am_angle))*(this.h);
					this.eight_am = (this.origin - (this.round(this.eight_am, 2, BigDecimal.ROUND_HALF_UP)));
				}
			}
		}
		else if (eight_am_angle > 0) {
			eight_am_eastwest = true;
			this.eight_am_angle = 90 - eight_am_angle;
			if (this.eight_am_angle >= 0) {
				if (this.eight_am_angle <= 45) {
					this.eight_am = Math.tan(Math.toRadians(this.eight_am_angle))*(this.b);
					this.eight_am = (this.h - this.round(this.eight_am, 2, BigDecimal.ROUND_HALF_UP)) + this.height*.05;
				}
				else if (this.eight_am_angle > 45 && this.eight_am_angle < 90) {
					this.eight_am = Math.tan(Math.toRadians(90 - this.eight_am_angle))*(this.h);
					this.eight_am = (this.origin + (this.round(this.eight_am, 2, BigDecimal.ROUND_HALF_UP)));
				}
			}
		}
		//*******************************************************************Initializing 9'o clock am line******************************************************************************
		//Check if on west side or east side
		if (nine_am_angle <= 0) {
			this.nine_am_angle = 90 + nine_am_angle;
			if (this.nine_am_angle >= 0) {
				if (this.nine_am_angle <= 45) {
					this.nine_am = Math.tan(Math.toRadians(this.nine_am_angle))*(this.b);
					this.nine_am = (this.h - this.round(this.nine_am, 2, BigDecimal.ROUND_HALF_UP)) + this.height*.05;
				}
				else if (this.nine_am_angle > 45 && this.nine_am_angle < 90) {
					this.nine_am = Math.tan(Math.toRadians(90 - this.nine_am_angle))*(this.h);
					this.nine_am = (this.origin - (this.round(this.nine_am, 2, BigDecimal.ROUND_HALF_UP)));
				}
			}
		}
		else if (nine_am_angle > 0) {
			nine_am_eastwest = true;
			this.nine_am_angle = 90 - nine_am_angle;
			if (this.nine_am_angle >= 0) {
				if (this.nine_am_angle <= 45) {
					this.nine_am = Math.tan(Math.toRadians(this.nine_am_angle))*(this.b);
					this.nine_am = (this.h - this.round(this.nine_am, 2, BigDecimal.ROUND_HALF_UP)) + this.height*.05;
				}
				else if (this.nine_am_angle > 45 && this.nine_am_angle < 90) {
					this.nine_am = Math.tan(Math.toRadians(90 - this.nine_am_angle))*(this.h);
					this.nine_am = (this.origin + (this.round(this.nine_am, 2, BigDecimal.ROUND_HALF_UP)));
				}
			}
		}
		//*******************************************************************Initializing 10'o clock am line******************************************************************************
		//Check if on west side or east side
		if (ten_am_angle <= 0) {
			this.ten_am_angle = 90 + ten_am_angle;
			if (this.ten_am_angle >= 0) {
				if (this.ten_am_angle <= 45) {
					this.ten_am = Math.tan(Math.toRadians(this.ten_am_angle))*(this.b);
					this.ten_am = (this.h - this.round(this.ten_am, 2, BigDecimal.ROUND_HALF_UP)) + this.height*.05;
				}
				else if (this.ten_am_angle > 45 && this.ten_am_angle < 90) {
					this.ten_am = Math.tan(Math.toRadians(90 - this.ten_am_angle))*(this.h);
					this.ten_am = (this.origin - (this.round(this.ten_am, 2, BigDecimal.ROUND_HALF_UP)));
				}
			}
		}
		else if (ten_am_angle > 0) {
			ten_am_eastwest = true;
			this.ten_am_angle = 90 - ten_am_angle;
			if (this.ten_am_angle >= 0) {
				if (this.ten_am_angle <= 45) {
					this.ten_am = Math.tan(Math.toRadians(this.ten_am_angle))*(this.b);
					this.ten_am = (this.h - this.round(this.ten_am, 2, BigDecimal.ROUND_HALF_UP)) + this.height*.05;
				}
				else if (this.ten_am_angle > 45 && this.ten_am_angle < 90) {
					this.ten_am = Math.tan(Math.toRadians(90 - this.ten_am_angle))*(this.h);
					this.ten_am = (this.origin + (this.round(this.ten_am, 2, BigDecimal.ROUND_HALF_UP)));
				}
			}
		}
		
		//*******************************************************************Initializing 11'o clock am line******************************************************************************
		//Check if on west side or east side
		if (eleven_am_angle <= 0) {
			this.eleven_am_angle = 90 + eleven_am_angle;
			if (this.eleven_am_angle >= 0) {
				if (this.eleven_am_angle <= 45) {
					this.eleven_am = Math.tan(Math.toRadians(this.eleven_am_angle))*(this.b);
					this.eleven_am = (this.h - this.round(this.eleven_am, 2, BigDecimal.ROUND_HALF_UP)) + this.height*.05;
				}
				else if (this.eleven_am_angle > 45 && this.eleven_am_angle < 90) {
					this.eleven_am = Math.tan(Math.toRadians(90 - this.eleven_am_angle))*(this.h);
					this.eleven_am = (this.origin - (this.round(this.eleven_am, 2, BigDecimal.ROUND_HALF_UP)));
				}
			}
		}
		else if (eleven_am_angle > 0) {
			eleven_am_eastwest = true;
			this.eleven_am_angle = 90 - eleven_am_angle;
			if (this.eleven_am_angle >= 0) {
				if (this.eleven_am_angle <= 45) {
					this.eleven_am = Math.tan(Math.toRadians(this.eleven_am_angle))*(this.b);
					this.eleven_am = (this.h - this.round(this.eleven_am, 2, BigDecimal.ROUND_HALF_UP)) + this.height*.05;
				}
				else if (this.eleven_am_angle > 45 && this.eleven_am_angle < 90) {
					this.eleven_am = Math.tan(Math.toRadians(90 - this.eleven_am_angle))*(this.h);
					this.eleven_am = (this.origin + (this.round(this.eleven_am, 2, BigDecimal.ROUND_HALF_UP)));
				}
			}
		}
	}
	
	/**
	 * Drawing the Lines
	 */
	public void paint(Graphics g) {
		Font f = new Font ("default", Font.BOLD, 30);
		g.setFont(f);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(Color.BLACK);
		g2d.scale(.6,.6);
		g.drawString("AM", (int) (this.width*.05 + 15) , (int) (this.height*.85 + 50));
		g.drawString("PM", (int) (this.width*.90 - 55) , (int) (this.height*.85 + 50));
		  
		//*************************************************************************Drawing the Border************************************************************************************
		g2d.draw(new Line2D.Double(left_point_x,left_point_y1,left_point_x,left_point_y2)); //Left_Side  
		g2d.draw(new Line2D.Double(right_point_x,right_point_y1,right_point_x,right_point_y2 )); //Right_Side 
		g2d.draw(new Line2D.Double(top_point_x1,top_point_y,top_point_x2,top_point_y));//Top_Side   
		g2d.draw(new Line2D.Double(bottom_point_x1,bottom_point_y,bottom_point_x2,bottom_point_y ));//Bottom_Side 
		
		
		//**************************************************************************The 12'o clock line and label************************************************************************
		if (!this.twelve_eastwest && this.twelve_angle != 90 && this.twelve_angle >= 0) { //Hour line is on left
			if (this.twelve_angle <= 45) {
				g2d.draw(new Line2D.Double(this.width*.05,this.twelve,this.origin,this.height*.85)); 
				g.drawString("12", (int) (this.width*.05 - 20) , (int) (this.twelve));
			}
			else {
				g2d.draw(new Line2D.Double(this.twelve,this.height*.05,this.origin,this.height*.85)); 
				g.drawString("12",(int) this.twelve, (int) (this.height*.05));
			}
		}
		else if (this.twelve_eastwest && this.twelve_angle != 90 && this.twelve_angle >= 0) {//Hour Line is on right
			if (this.twelve_angle <= 45) {
				g2d.draw(new Line2D.Double(this.width*.90,this.twelve,this.origin,this.height*.85)); 
				g.drawString("12", (int) (this.width*.90), (int) (this.twelve));
			}
			else {
				g2d.draw(new Line2D.Double(this.twelve,this.height*.05,this.origin,this.height*.85)); 
				g.drawString("12",(int) this.twelve, (int) (this.height*.05));
			}
		}
		else if (this.twelve_angle == 90 && this.twelve_angle >= 0) {
			g2d.draw(new Line2D.Double(this.origin,this.height*.05,this.origin,this.height*.85)); 
			g.drawString("12", (int) (this.origin), (int) (this.height*.05));
		}
		
		//**************************************************************************The 1'o clock line and label************************************************************************
		if (!this.one_pm_eastwest && this.one_pm_angle != 90 && this.one_pm_angle >= 0) { //Hour line is on left
			if (this.one_pm_angle <= 45) {
				g2d.draw(new Line2D.Double(this.width*.05,this.one_pm,this.origin,this.height*.85)); 
				g.drawString("1", (int) (this.width*.05 - 20), (int) (this.one_pm));
			}
			else {
				g2d.draw(new Line2D.Double(this.one_pm,this.height*.05,this.origin,this.height*.85)); 
				g.drawString("1",(int) this.one_pm, (int) (this.height*.05));
			}
		}
		else if (this.one_pm_eastwest && this.one_pm_angle != 90 && this.one_pm_angle >= 0) {//Hour Line is on right
			if (this.one_pm_angle <= 45) {
				g2d.draw(new Line2D.Double(this.width*.90,this.one_pm,this.origin,this.height*.85)); 
				g.drawString("1", (int) (this.width*.90), (int) (this.one_pm));
			}
			else {
				g2d.draw(new Line2D.Double(this.one_pm,this.height*.05,this.origin,this.height*.85)); 
				g.drawString("1",(int) this.one_pm, (int) (this.height*.05));
			}
		}
		else if (this.one_pm_angle == 90 && this.one_pm_angle >= 0) {
			g2d.draw(new Line2D.Double(this.origin,this.height*.05,this.origin,this.height*.85)); 
			g.drawString("1", (int) (this.origin), (int) (this.height*.05));
		}
		//**************************************************************************The 2'o clock line and label************************************************************************
		if (!this.two_pm_eastwest && this.two_pm_angle != 90 && this.two_pm_angle >= 0) { //Hour line is on left
			if (this.two_pm_angle <= 45) {
				g2d.draw(new Line2D.Double(this.width*.05,this.two_pm,this.origin,this.height*.85)); 
				g.drawString("2", (int) (this.width*.05 - 20), (int) (this.two_pm));
			}
			else {
				g2d.draw(new Line2D.Double(this.two_pm,this.height*.05,this.origin,this.height*.85)); 
				g.drawString("2",(int) this.two_pm, (int) (this.height*.05));
			}
		}
		else if (this.two_pm_eastwest && this.two_pm_angle != 90 && this.two_pm_angle >= 0) {//Hour Line is on right
			if (this.two_pm_angle <= 45) {
				g2d.draw(new Line2D.Double(this.width*.90,this.two_pm,this.origin,this.height*.85)); 
				g.drawString("2", (int) (this.width*.90), (int) (this.two_pm));
			}
			else {
				g2d.draw(new Line2D.Double(this.two_pm,this.height*.05,this.origin,this.height*.85)); 
				g.drawString("2",(int) this.two_pm, (int) (this.height*.05));
			}
		}
		else if (this.two_pm_angle == 90 && this.two_pm_angle >= 0) { // Hour line is 90 degrees
			g2d.draw(new Line2D.Double(this.origin,this.height*.05,this.origin,this.height*.85)); 
			g.drawString("2", (int) (this.origin), (int) (this.height*.05));
		}
		//**************************************************************************The 3'o clock line and label************************************************************************
		if (!this.three_pm_eastwest && this.three_pm_angle != 90 && this.three_pm_angle >= 0) { //Hour line is on left
			if (this.three_pm_angle <= 45) {
				g2d.draw(new Line2D.Double(this.width*.05,this.three_pm,this.origin,this.height*.85)); 
				g.drawString("3", (int) (this.width*.05 -  20), (int) (this.three_pm));
			}
			else {
				g2d.draw(new Line2D.Double(this.three_pm,this.height*.05,this.origin,this.height*.85)); 
				g.drawString("3",(int) this.three_pm, (int) (this.height*.05));
			}
		}
		else if (this.three_pm_eastwest && this.three_pm_angle != 90 && this.three_pm_angle >= 0) {//Hour Line is on right
			if (this.three_pm_angle <= 45) {
				g2d.draw(new Line2D.Double(this.width*.90,this.three_pm,this.origin,this.height*.85)); 
				g.drawString("3", (int) (this.width*.90), (int) (this.three_pm));
			}
			else {
				g2d.draw(new Line2D.Double(this.three_pm,this.height*.05,this.origin,this.height*.85)); 
				g.drawString("3",(int) this.three_pm, (int) (this.height*.05));
			}
		}
		else if (this.three_pm_angle == 90 && this.three_pm_angle >= 0) { // Hour line is 90 degrees
			g2d.draw(new Line2D.Double(this.origin,this.height*.05,this.origin,this.height*.85)); 
			g.drawString("3", (int) (this.origin), (int) (this.height*.05));
		}
		//**************************************************************************The 4'o clock line and label************************************************************************
		if (!this.four_pm_eastwest && this.four_pm_angle != 90 && this.four_pm_angle >= 0) { //Hour line is on left
			if (this.four_pm_angle <= 45) {
				g2d.draw(new Line2D.Double(this.width*.05,this.four_pm,this.origin,this.height*.85)); 
				g.drawString("4", (int) (this.width*.05 - 20), (int) (this.four_pm));
			}
			else {
				g2d.draw(new Line2D.Double(this.four_pm,this.height*.05,this.origin,this.height*.85)); 
				g.drawString("4",(int) this.four_pm, (int) (this.height*.05));
			}
		}
		else if (this.four_pm_eastwest && this.four_pm_angle != 90 && this.four_pm_angle >= 0) {//Hour Line is on right
			if (this.four_pm_angle <= 45) {
				g2d.draw(new Line2D.Double(this.width*.90,this.four_pm,this.origin,this.height*.85)); 
				g.drawString("4", (int) (this.width*.90), (int) (this.four_pm));
			}
			else {
				g2d.draw(new Line2D.Double(this.four_pm,this.height*.05,this.origin,this.height*.85)); 
				g.drawString("4",(int) this.four_pm, (int) (this.height*.05));
			}
		}
		else if (this.four_pm_angle == 90 && this.four_pm_angle >= 0) { // Hour line is 90 degrees
			g2d.draw(new Line2D.Double(this.origin,this.height*.05,this.origin,this.height*.85)); 
			g.drawString("4", (int) (this.origin), (int) (this.height*.05));
		}
		//**************************************************************************The 5'o clock line and label************************************************************************
		if (!this.five_pm_eastwest && this.five_pm_angle != 90 && this.five_pm_angle >= 0) { //Hour line is on left
			if (this.five_pm_angle <= 45) {
				g2d.draw(new Line2D.Double(this.width*.05,this.five_pm,this.origin,this.height*.85)); 
				g.drawString("5", (int) (this.width*.05 - 20), (int) (this.five_pm));
			}
			else {
				g2d.draw(new Line2D.Double(this.five_pm,this.height*.05,this.origin,this.height*.85)); 
				g.drawString("5",(int) this.five_pm, (int) (this.height*.05));
			}
		}
		else if (this.five_pm_eastwest && this.five_pm_angle != 90 && this.five_pm_angle >= 0) {//Hour Line is on right
			if (this.five_pm_angle <= 45) {
				g2d.draw(new Line2D.Double(this.width*.90,this.five_pm,this.origin,this.height*.85)); 
				g.drawString("5", (int) (this.width*.90), (int) (this.five_pm));
			}
			else {
				g2d.draw(new Line2D.Double(this.five_pm,this.height*.05,this.origin,this.height*.85)); 
				g.drawString("5",(int) this.five_pm, (int) (this.height*.05));
			}
		}
		else if (this.five_pm_angle == 90 && this.five_pm_angle >= 0) { // Hour line is 90 degrees
			g2d.draw(new Line2D.Double(this.origin,this.height*.05,this.origin,this.height*.85)); 
			g.drawString("5", (int) (this.origin), (int) (this.height*.05));
		}
		
		//**************************************************************************The 6'o clock line and label************************************************************************
		if (!this.six_pm_eastwest && this.six_pm_angle != 90 && this.six_pm_angle >= 0) { //Hour line is on left
			if (this.six_pm_angle <= 45) {
				g2d.draw(new Line2D.Double(this.width*.05,this.six_pm,this.origin,this.height*.85)); 
				g.drawString("6", (int) (this.width*.05 - 20), (int) (this.six_pm));
			}
			else {
				g2d.draw(new Line2D.Double(this.six_pm,this.height*.05,this.origin,this.height*.85)); 
				g.drawString("6",(int) this.six_pm, (int) (this.height*.05));
			}
		}
		else if (this.six_pm_eastwest && this.six_pm_angle != 90 && this.six_pm_angle >= 0) {//Hour Line is on right
			if (this.six_pm_angle <= 45) {
				g2d.draw(new Line2D.Double(this.width*.90,this.six_pm,this.origin,this.height*.85)); 
				g.drawString("6", (int) (this.width*.90), (int) (this.six_pm));
			}
			else {
				g2d.draw(new Line2D.Double(this.six_pm,this.height*.05,this.origin,this.height*.85)); 
				g.drawString("6",(int) this.six_pm, (int) (this.height*.05));
			}
		}
		else if (this.six_pm_angle == 90 && this.six_pm_angle >= 0) { // Hour line is 90 degrees
			g2d.draw(new Line2D.Double(this.origin,this.height*.05,this.origin,this.height*.85)); 
			g.drawString("6", (int) (this.origin), (int) (this.height*.05));
		}
		//**************************************************************************The 6'o clock am line and label************************************************************************
		if (!this.six_am_eastwest && this.six_am_angle != 90 && this.six_am_angle >= 0) { //Hour line is on left
			if (this.six_am_angle <= 45) {
				g2d.draw(new Line2D.Double(this.width*.05,this.six_am,this.origin,this.height*.85)); 
				g.drawString("6", (int) (this.width*.05 - 20), (int) (this.six_am));
			}
			else {
				g2d.draw(new Line2D.Double(this.six_am,this.height*.05,this.origin,this.height*.85)); 
				g.drawString("6",(int) this.six_am, (int) (this.height*.05));
			}
		}
		else if (this.six_am_eastwest && this.six_am_angle != 90 && this.six_am_angle >= 0) {//Hour Line is on right
			if (this.six_am_angle <= 45) {
				g2d.draw(new Line2D.Double(this.width*.90,this.six_am,this.origin,this.height*.85)); 
				g.drawString("6", (int) (this.width*.90), (int) (this.six_am));
			}
			else {
				g2d.draw(new Line2D.Double(this.six_am,this.height*.05,this.origin,this.height*.85)); 
				g.drawString("6",(int) this.six_am, (int) (this.height*.05));
			}
		}
		else if (this.six_am_angle == 90 && this.six_am_angle >= 0) { // Hour line is 90 degrees
			g2d.draw(new Line2D.Double(this.origin,this.height*.05,this.origin,this.height*.85)); 
			g.drawString("6", (int) (this.origin), (int) (this.height*.05));
		}
		//**************************************************************************The 7'o clock am line and label************************************************************************
		if (!this.seven_am_eastwest && this.seven_am_angle != 90 && this.seven_am_angle >= 0) { //Hour line is on left
			if (this.seven_am_angle <= 45) {
				g2d.draw(new Line2D.Double(this.width*.05,this.seven_am,this.origin,this.height*.85)); 
				g.drawString("7", (int) (this.width*.05 - 20), (int) (this.seven_am));
			}
			else {
				g2d.draw(new Line2D.Double(this.seven_am,this.height*.05,this.origin,this.height*.85)); 
				g.drawString("7",(int) this.seven_am, (int) (this.height*.05));
			}
		}
		else if (this.seven_am_eastwest && this.seven_am_angle != 90 && this.seven_am_angle >= 0) {//Hour Line is on right
			if (this.seven_am_angle <= 45) {
				g2d.draw(new Line2D.Double(this.width*.90,this.seven_am,this.origin,this.height*.85)); 
				g.drawString("7", (int) (this.width*.90), (int) (this.seven_am));
			}
			else {
				g2d.draw(new Line2D.Double(this.seven_am,this.height*.05,this.origin,this.height*.85)); 
				g.drawString("7",(int) this.seven_am, (int) (this.height*.05));
			}
		}
		else if (this.seven_am_angle == 90 && this.seven_am_angle >= 0) { // Hour line is 90 degrees
			g2d.draw(new Line2D.Double(this.origin,this.height*.05,this.origin,this.height*.85)); 
			g.drawString("7", (int) (this.origin), (int) (this.height*.05));
		}
		//**************************************************************************The 8'o clock am line and label************************************************************************
		if (!this.eight_am_eastwest && this.eight_am_angle != 90 && this.eight_am_angle >= 0) { //Hour line is on left
			if (this.eight_am_angle <= 45) {
				g2d.draw(new Line2D.Double(this.width*.05,this.eight_am,this.origin,this.height*.85)); 
				g.drawString("8", (int) (this.width*.05 - 20), (int) (this.eight_am));
			}
			else {
				g2d.draw(new Line2D.Double(this.eight_am,this.height*.05,this.origin,this.height*.85)); 
				g.drawString("8",(int) this.eight_am, (int) (this.height*.05));
			}
		}
		else if (this.eight_am_eastwest && this.eight_am_angle != 90 && this.eight_am_angle >= 0) {//Hour Line is on right
			if (this.eight_am_angle <= 45) {
				g2d.draw(new Line2D.Double(this.width*.90,this.eight_am,this.origin,this.height*.85)); 
				g.drawString("8", (int) (this.width*.90), (int) (this.eight_am));
			}
			else {
				g2d.draw(new Line2D.Double(this.eight_am,this.height*.05,this.origin,this.height*.85)); 
				g.drawString("8",(int) this.eight_am, (int) (this.height*.05));
			}
		}
		else if (this.eight_am_angle == 90 && this.eight_am_angle >= 0) { // Hour line is 90 degrees
			g2d.draw(new Line2D.Double(this.origin,this.height*.05,this.origin,this.height*.85)); 
			g.drawString("8", (int) (this.origin), (int) (this.height*.05));
		}
		//**************************************************************************The 9'o clock am line and label************************************************************************
		if (!this.nine_am_eastwest && this.nine_am_angle != 90 && this.nine_am_angle >= 0) { //Hour line is on left
			if (this.nine_am_angle <= 45) {
				g2d.draw(new Line2D.Double(this.width*.05,this.nine_am,this.origin,this.height*.85)); 
				g.drawString("9", (int) (this.width*.05 - 20), (int) (this.nine_am));
			}
			else {
				g2d.draw(new Line2D.Double(this.nine_am,this.height*.05,this.origin,this.height*.85)); 
				g.drawString("9",(int) this.nine_am, (int) (this.height*.05));
			}
		}
		else if (this.nine_am_eastwest && this.nine_am_angle != 90 && this.nine_am_angle >= 0) {//Hour Line is on right
			if (this.nine_am_angle <= 45) {
				g2d.draw(new Line2D.Double(this.width*.90,this.nine_am,this.origin,this.height*.85)); 
				g.drawString("9", (int) (this.width*.90), (int) (this.nine_am));
			}
			else {
				g2d.draw(new Line2D.Double(this.nine_am,this.height*.05,this.origin,this.height*.85)); 
				g.drawString("9",(int) this.nine_am, (int) (this.height*.05));
			}
		}
		else if (this.nine_am_angle == 90 && this.nine_am_angle >= 0) { // Hour line is 90 degrees
			g2d.draw(new Line2D.Double(this.origin,this.height*.05,this.origin,this.height*.85)); 
			g.drawString("9", (int) (this.origin), (int) (this.height*.05));
		}
		//**************************************************************************The 10'o clock am line and label************************************************************************
		if (!this.ten_am_eastwest && this.ten_am_angle != 90 && this.ten_am_angle >= 0) { //Hour line is on left
			if (this.ten_am_angle <= 45) {
				g2d.draw(new Line2D.Double(this.width*.05,this.ten_am,this.origin,this.height*.85)); 
				g.drawString("10", (int) (this.width*.05 - 20), (int) (this.ten_am));
			}
			else {
				g2d.draw(new Line2D.Double(this.ten_am,this.height*.05,this.origin,this.height*.85)); 
				g.drawString("10",(int) this.ten_am, (int) (this.height*.05));
			}
		}
		else if (this.ten_am_eastwest && this.ten_am_angle != 90 && this.ten_am_angle >= 0) {//Hour Line is on right
			if (this.ten_am_angle <= 45) {
				g2d.draw(new Line2D.Double(this.width*.90,this.ten_am,this.origin,this.height*.85)); 
				g.drawString("10", (int) (this.width*.90), (int) (this.ten_am));
			}
			else {
				g2d.draw(new Line2D.Double(this.ten_am,this.height*.05,this.origin,this.height*.85)); 
				g.drawString("10",(int) this.ten_am, (int) (this.height*.05));
			}
		}
		else if (this.ten_am_angle == 90 && this.ten_am_angle >= 0) { // Hour line is 90 degrees
			g2d.draw(new Line2D.Double(this.origin,this.height*.05,this.origin,this.height*.85)); 
			g.drawString("10", (int) (this.origin), (int) (this.height*.05));
		}
		//**************************************************************************The 11'o clock am line and label************************************************************************
		if (!this.eleven_am_eastwest && this.eleven_am_angle != 90 && this.eleven_am_angle >= 0) { //Hour line is on left
			if (this.eleven_am_angle <= 45) {
				g2d.draw(new Line2D.Double(this.width*.05,this.ten_am,this.origin,this.height*.85)); 
				g.drawString("11", (int) (this.width*.05 - 20), (int) (this.eleven_am));
			}
			else {
				g2d.draw(new Line2D.Double(this.eleven_am,this.height*.05,this.origin,this.height*.85)); 
				g.drawString("11",(int) this.eleven_am, (int) (this.height*.05));
			}
		}
		else if (this.eleven_am_eastwest && this.eleven_am_angle != 90 && this.eleven_am_angle >= 0) {//Hour Line is on right
			if (this.eleven_am_angle <= 45) {
				g2d.draw(new Line2D.Double(this.width*.90,this.eleven_am,this.origin,this.height*.85)); 
				g.drawString("11", (int) (this.width*.90), (int) (this.eleven_am));
			}
			else {
				g2d.draw(new Line2D.Double(this.eleven_am,this.height*.05,this.origin,this.height*.85)); 
				g.drawString("11",(int) this.eleven_am, (int) (this.height*.05));
			}
		}
		else if (this.eleven_am_angle == 90 && this.eleven_am_angle >= 0) { // Hour line is 90 degrees
			g2d.draw(new Line2D.Double(this.origin,this.height*.05,this.origin,this.height*.85)); 
			g.drawString("11", (int) (this.origin), (int) (this.height*.05));
		}
	}	
	
	 /**
	  * This rounds a number
	  * @param unrounded is the number to be rounded.
	  * @param precision the number of decimals to round to.
	  * @param roundingMode mode of rounding
	  * @return the rounded value
	  */
	 public double round(double unrounded, int precision, int roundingMode)
	 {
		 BigDecimal bd = new BigDecimal(unrounded);
	  	 BigDecimal rounded = bd.setScale(precision, roundingMode);
		 return rounded.doubleValue();
	 }
}
