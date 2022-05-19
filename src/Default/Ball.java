/*
 * Name: Adam Kaplan
 * 
 * Project: #4
 * 
 * Lab Section: MW 09:00am - 10:15am
 * 
 * TA: Wolf Honore
 * 
 * I affirm that I have not given or received any unauthorized help on this assignment, and that this work is my own.
 */

package Default;

import java.awt.Color;

public class Ball{
	/**
	 * Constant variable for gravity
	 */
	private final double G = 9.81;
	private double animationCoef = 0.15;
	
	
	/**
	 * Constant variable by how much the vertical speed is reduced
	 * each time the ball hits a wall
	 */
	private final double BOUNCE_REDUCE = 0.98;
	
	
	/**
	 * Variables for the speed and the angle of the trajectory
	 * the ball will follow
	 */
	private double v, theta;
	
	/*
	 * Setters and Getters
	 */
	
	public void setV(double v){
		this.v = v;
	}
	
	public void setTheta(double theta){
		this.theta = theta;
	}
	
	public double getV(){
		return this.v;
	}
	
	public double getTheta(){
		return this.theta;
	}
	
	
	/**
	 * Variables for the speed in horizontal and vertical direction
	 * Dependent on v, theta
	 */
	private double speedX, speedY;
	
	
	/**
	 * The x and y coordinates of the ball, its radius, and its color
	 */
	private double x, y, r;
	private Color color;
	
	/*
	 * Setters and Getters
	 */
	
	public void setX(double x){
		this.x = x;
	}
	
	public void setY(double y){
		this.y = y;
	}
	
	public void setR(double r){
		this.r = r;
	}
	
	public void setColor(Color color){
		this.color = color;
	}
	
	public double getX(){
		return this.x;
	}
	
	public double getY(){
		return this.y;
	}
	
	public double getR(){
		return this.r;
	}
	
	public Color getColor(){
		return this.color;
	}
	
	
	/**
	 * Instance variable of the platform off of which the ball will
	 * bounce off
	 */
	private Platform platform;
	
	
	/**
	 * Local variables to save the width and height of the window the ball is in
	 */
	private double width, height;
	
	
	/**
	 * Constructor
	 * Creates the Ball at the given coordinates with the given radius
	 * @param x
	 * @param y
	 * @param r
	 */
	public Ball(double x, double y, double r, Color color){
		this.x = x;
		this.y = y;
		this.r = r;
		this.color = color;
	}
	
	
	/**
	 * Calculates the speed in each direction based on the angle and the speed
	 */
	private void calcSpeed(){
		this.speedX = this.v * Math.cos(Math.toRadians(this.theta));
		this.speedY = this.v * Math.sin(Math.toRadians(this.theta));
	}
	
	
	/**
	 * Read in the bounds of the window the ball is in
	 * @param width
	 * @param height
	 */
	public void getWindowBounds(double width, double height){
		this.width = width;
		this.height = height;
	}
	
	
	/**
	 * Sets all the variables required for the simulation of the trajectory
	 * @param v
	 * @param theta
	 */
	public void initialize(double v, double theta){
		this.v = v;
		this.theta = theta;
		calcSpeed();
	}
	
	
	/**
	 * Adds platform measurements to the ball class
	 * @param platform
	 */
	public void addPlatform(Platform platform){
		this.platform = platform;
	}
	
	
	/**
	 * Checks if the ball hit the platform
	 * @return
	 */
	private boolean hitPlatform(){
		double pX = this.platform.getpX();
		double pY = this.platform.getpY();
		double pSize = this.platform.getpSize();
		
		// Check if the ball is on the same level as the platform
		if((this.y + this.r + 5) >= pY){
			if((this.x) >= (pX) && (this.x) <= (pX + pSize)){
				return true;
			}
		}
		
		return false;
	}
	
	
	/**
	 * Checks if the ball hit the floor
	 * @return
	 */
	private boolean hitFloor(){
		if((this.y + this.r) >= height){
			return true;
		}
		
		return false;
	}
	
	
	/**
	 * Resets the ball to its start values, except that the
	 * speed and the angle can be changed
	 * @param v
	 * @param theta
	 */
	public void reset(double v, double theta){
		this.x = 50;
		this.y = 50;
		this.r = 20;
		this.v = v;
		this.theta = theta;
		this.color = Color.RED;
		calcSpeed();
	}
	
	
	/**
	 * Class that is crucial to the animation
	 * @return
	 */
	public boolean prepPaint(){
		if((this.x - this.r) <= 0 || (this.x + this.r) >= width){
			speedX *= -1.0;
		}
		if((this.y - this.r) <= 0 || hitPlatform()){
			speedY *= -1.0 * BOUNCE_REDUCE;
		}
		
		if(hitFloor()){
			reset(this.v, this.theta);
			
			return false;
		}
		
		x += speedX * animationCoef;
		y += speedY * animationCoef;
		
		speedY += G * animationCoef;
		
		return true;
	}
}
