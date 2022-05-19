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
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Canvas extends JPanel{
	
	
	/**
	 * Instance variables for the ball and the platform
	 */
	private Ball ball;
	private Platform platform;
	
	
	/**
	 * Instance variables for the life, time, level, score
	 */
	private int life, time, level, score;
	
	
	/*
	 * Setters and Getters
	 */
	
	public void setLife(int life){
		this.life = life;
	}
	
	public void setTime(int time){
		this.time = time;
	}
	
	public void setLevel(int level){
		this.level = level;
	}
	
	public void setScore(int score){
		this.score = score;
	}
	
	public int getLife(){
		return this.life;
	}
	
	public int getTime(){
		return this.time;
	}
	
	public int getLevel(){
		return this.level;
	}
	
	public int getScore(){
		return this.score;
	}
	
	
	/**
	 * Boolean to check if the game ended
	 */
	private boolean end;
	
	/*
	 * Setters and Getters
	 */
	public void setEnd(boolean end){
		this.end = end;
	}
	
	public boolean getEnd(){
		return this.end;
	}
	
	
	/**
	 * Constructor to create the canvas
	 * @param ball
	 * @param platform
	 */
	public Canvas(Ball ball, Platform platform){
		this.ball = ball;
		this.platform = platform;
		
		ball.addPlatform(platform);
	}
	
	
	/**
	 * Draws the ball
	 * @param g
	 */
	private void drawBall(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setColor(ball.getColor());
		g2.fill(new Ellipse2D.Double(ball.getX() - (ball.getR() / 2.0), ball.getY() - (ball.getR() / 2.0), ball.getR(), ball.getR()));
	}
	
	
	/**
	 * Draws the platform
	 * @param g
	 */
	private void drawPlatform(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setColor(platform.getColor());
		g2.fill(new Rectangle2D.Double(platform.getpX(), platform.getpY(), platform.getpSize(), 20));
	}
	
	
	/**
	 * Draws the String with the remaining lives
	 * @param g
	 */
	private void drawLife(Graphics g){
		g.setColor(Color.BLACK);
		
		g.drawString(String.format("Lives remaining: %d", life), getWidth() - 130, 40);
	}
	
	
	/**
	 * Draws the String with the remaining time
	 * @param g
	 */
	private void drawTime(Graphics g){
		g.setColor(Color.BLACK);
		
		g.drawString(String.format("Time remaining: %d", time), getWidth() - 130, 20);
	}
	
	
	/**
	 * Draws the String with the level number
	 * @param g
	 */
	private void drawLevel(Graphics g){
		g.setColor(Color.BLACK);
		
		g.drawString(String.format("Level %d", level), getWidth() / 2 - 20, 20);
	}
	
	
	/**
	 * Draws the String with the current score
	 * @param g
	 */
	private void drawScore(Graphics g){
		g.setColor(Color.BLACK);
		
		g.drawString(String.format("Score: %d", score), getWidth() - 65, 60);
	}
	
	
	/**
	 * Function that checks all the properties of the game before drawing
	 * @param g
	 * @return
	 */
	public boolean draw(Graphics g){
		if(time == 0){
			switch(level){
			case 1:
				ball.reset(30, 30);
				this.platform.setpSize(100);
				break;
				
			case 2:
				ball.reset(40, 20);
				this.platform.setpSize(60);
				break;
			}
		}
		
		if(ball.prepPaint()){
			repaint();
			return true;
		}
		else{
			repaint();
			return false;
		}
	}
	
	
	/**
	 * The actual drawing happens here
	 */
	@Override
	public void paintComponent(Graphics g){
		
		if(!end){
			drawLife(g);
			drawTime(g);
			drawLevel(g);
			drawScore(g);
			drawPlatform(g);
			drawBall(g);
		}
		else{
			g.drawString(String.format("GAME OVER"), getWidth() / 2 - 30, getHeight() / 2);
			g.drawString(String.format("Score: %d", score), getWidth() / 2 - 30, getHeight() / 2 + 20);
		}
	}
}
