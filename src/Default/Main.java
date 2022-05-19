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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Main extends JFrame implements KeyListener{

	
	/**
	 * Constant instance variables, which are used often in the program
	 */
	protected final int START_TIME = 60;
	protected final int START_LIFE = 3;
	
	
	/**
	 * Instance variables
	 */
	protected Platform platform;
	protected Ball ball;
	protected Timer movement;
	protected int life;
	protected Graphics g;
	protected Canvas c;
	protected boolean stop = false;
	protected int time;
	protected int level;
	protected int score;
	
	
	/**
	 * Function that makes setting the Ball instance variable easier
	 * @param x
	 * @param y
	 * @param r
	 * @param v
	 * @param theta
	 * @param c
	 */
	public void setBall(double x, double y, double r, double v, double theta, Color c){
		ball = new Ball(x, y, r, c);
		ball.getWindowBounds(getWidth(), getHeight());
		ball.initialize(v, theta);
	}
	
	
	/**
	 * Function that makes setting the Platform instance variable easier
	 * @param x
	 * @param y
	 * @param s
	 * @param c
	 */
	public void setPlatform(double x, double y, double s, Color c){
		platform = new Platform(x, y, s, c);
	}
	
	
	/**
	 * The main part of this whole program
	 * Initializes the game and the whole animation happens here
	 */
	public Main(){
		setTitle("BallBounce Testing");
		setSize(600, 622);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		life = START_LIFE;
		time = START_TIME;
		level = 1;
		score = 0;
		
		setBall(50, 50, 20, 30, 45, Color.RED);
		setPlatform(getWidth() / 2, getHeight() - 60, 150, Color.BLACK);
		
		ball.addPlatform(platform);
		
		c = new Canvas(ball, platform);
		c.setLife(life);
		c.setTime(time);
		c.setLevel(level);
		c.setScore(score);
		add(c);
		addKeyListener(this);
		
		g = getGraphics();
		
		
		// Timer for the animation
		Timer t = new Timer(10, null);
		
		// Timer for the time
		Timer timeTimer = new Timer(1000, null);
		
		
		// Animation action
		ActionListener animation = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(!c.draw(g)){
					life--;
					score -= 5;
					c.setScore(score);
					c.setLife(life);
					
					if(life == 0){
						c.setEnd(true);
						c.draw(g);
						t.stop();
					}
				}
			}
		};
		
		// Time action
		ActionListener Time = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(time == 0 && level <= 3){
					level++;
					c.setLevel(level);
					time = START_TIME;
					c.setTime(time);
					life = START_LIFE;
					c.setLife(life);
				}
				else if(level <= 3){
					time--;
					c.setTime(time);
				}
				else{
					c.setEnd(true);
					c.draw(g);
					t.stop();
					timeTimer.stop();
				}
				
				score += 1;
				c.setScore(score);
			}
			
		};
		
		timeTimer.addActionListener(Time);
		timeTimer.start();
		t.addActionListener(animation);
		t.start();
		
		
	}
	
	
	/**
	 * Function that represents the movement of the platform to the left
	 *
	 */
	protected class Left implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(platform.getpX() - 6 >= 0)
				platform.setpX(platform.getpX() - 6);
		}
	}
	
	
	/**
	 * Function that represents the movement of the platform to the right
	 *
	 */
	protected class Right implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(platform.getpX() + platform.getpSize() + 6 <= getWidth())
				platform.setpX(platform.getpX() + 6);
		}
	}

	
	/**
	 * Main method
	 * Starts the program
	 * @param args
	 */
	public static void main(String[] args) {
		new Main().setVisible(true);
	}

	@Override
	public void keyTyped(KeyEvent e){}
	
	/**
	 * Method to move the platform
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_A){
			movement = new Timer(10, new Left());
		}
		else if(e.getKeyCode() == KeyEvent.VK_D){
			movement = new Timer(10, new Right());
		}
		else{
			movement = new Timer(1000, null);
		}
		
		movement.start();
	}

	
	/**
	 * Method to move the platform
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		movement.stop();
	}

}
