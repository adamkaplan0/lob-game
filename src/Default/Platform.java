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

public class Platform{
	
	
	/**
	 * Instance variables for the x and y coordinate of the platform
	 */
	private double x, y;
	
	
	/**
	 * Instance variable for the size of the platform
	 */
	private double size;
	
	
	/**
	 * Instance variable for the color of the platform
	 */
	private Color color;
	
	
	/**
	 * Constructor
	 * @param x
	 * @param y
	 * @param size
	 * @param color
	 */
	public Platform(double x, double y, double size, Color color){
		this.x = x;
		this.y = y;
		this.size = size;
		this.color = color;
	}
	
	
	/*
	 * Setters and Getters
	 */
	
	public double getpX(){
		return this.x;
	}
	
	public double getpY(){
		return this.y;
	}
	
	public double getpSize(){
		return this.size;
	}
	
	public Color getColor(){
		return this.color;
	}
	
	public void setpX(double x){
		this.x = x;
	}
	
	public void setpY(double y){
		this.y = y;
	}
	
	public void setpSize(double size){
		this.size = size;
	}
	
	public void setColor(Color color){
		this.color = color;
	}
}
