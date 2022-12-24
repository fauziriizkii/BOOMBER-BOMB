import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable, MouseListener, MouseMotionListener{
	
	public static final int WIDTH = 1200;
	public static final int HEIGHT = 700;
	
	private Thread thread;
	private boolean running;
	private BufferedImage image;
	private Graphics2D g;
	private int fps = 60;

	public static ArrayList<Enemy> enemies;

	public static long spawntime;
	public static long gameTime;
	private int timeDisplay;
	public static GameFrame.ScoreManager scoreManager;
	
	private SliderInput angleSlider = new SliderInput(50, 70, 157, 0, "Angle");
	private SliderInput sizeSlider = new SliderInput(50, 140, 0, 75, "Size");
	private SliderInput powerSlider = new SliderInput(50, 210, 150, 0, "Power");
	private Cannon cannon = new Cannon();

	private String playerName;
	
    public GamePanel(String playerName, int lv, GameFrame.ScoreManager sManager){
    	super();
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		setFocusable(true);
		requestFocus();
		addMouseMotionListener(this);
		addMouseListener(this);

		this.playerName = playerName;
		scoreManager = sManager;
		gameTime = 30000;
		enemies = new ArrayList<Enemy>();
		if (lv == 2){
			spawntime = 3200;
			createEnemies(2);
		}
			
		else if (lv == 3){
			spawntime = 6400;
			createEnemies(3);
		}
			
		else{ //untuk level 1 ada bug maka dari itu saya taruh di else
			spawntime = 1200;
			createEnemies(1);
		}
			
		
    }

	public void addNotify() {
		super.addNotify();
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}

	public void run() {
		running = true;
		
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		//fps
		long startTime;
		long URDTimeMillis;
		long waitTime;
		long targetTime = 1000 / fps;
		
		//-------------------- GAME LOOP -----------------//
		while (running) {
			startTime = System.nanoTime();
			
			gameRender();
			gameDraw();
			gameTime -= 13;
			timeDisplay = (int)gameTime/1000;
			scoreManager.setTime(timeDisplay);
			scoreManager.updateDisplay(playerName);

			//fps
			URDTimeMillis = (System.nanoTime() - startTime) / 1000000;
			waitTime = targetTime - URDTimeMillis;
			try {
				Thread.sleep(waitTime);
			}
			catch (Exception e) {}
			
		}
	}

	private void gameRender() {
		
		//Background and grass
		g.setColor(new Color(197, 234, 243));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(new Color(28, 232, 119));
		g.fillRect(0, HEIGHT - 50, WIDTH, 50);
		
		//Cannon
		g = cannon.draw(g, angleSlider.getValue(), sizeSlider.getValue(), powerSlider.getValue());

		//Sliders
		g = angleSlider.draw(g);
		g = sizeSlider.draw(g);
		g = powerSlider.draw(g);
		
		//Draw Array of Enemies
		for(int i  = 0; i < enemies.size(); i++){
			g=enemies.get(i).draw(g);
		}
		
	}

	public void createEnemies(int level){
		enemies.clear();
		if(level == 1){
			for(int i = 0; i < 3; i++){
				enemies.add(new Enemy(1,1));
			}
			for(int i = 0; i < 2; i++){
				enemies.add(new Enemy(2,1));
			}
			for(int i = 0; i < 1; i++){
				enemies.add(new Enemy(3,1));
			}
		}
		else if(level == 2){
			for(int i = 0; i < 3; i++){
				enemies.add(new Enemy(1,2));
			}
			for(int i = 0; i < 2; i++){
				enemies.add(new Enemy(2,2));
			}
			for(int i = 0; i < 1; i++){
				enemies.add(new Enemy(3,2));
			}
		}
		else if(level == 3){
			for(int i = 0; i < 3; i++){
				enemies.add(new Enemy(1,3));
			}
			for(int i = 0; i < 2; i++){
				enemies.add(new Enemy(2,3));
			}
			for(int i = 0; i < 1; i++){
				enemies.add(new Enemy(3,3));
			}
		}
	}

	private void gameDraw() {
		Graphics g2 = this.getGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();
	}
	
	//-------------------- MOUSE LISTENING -----------------//
	static boolean dragging = false;
	static boolean click = false;
	static int cursorX = 0;
	static int cursorY = 0;
	
	@Override
	public void mouseClicked(MouseEvent e) {
		click = true;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		dragging = true;
		cursorX = e.getX();
		cursorY = e.getY();
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		dragging = false;
		click = false;
		cursorX = e.getX();
		cursorY = e.getY();
	}
	
	//EMPTY FUNCTIONS
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}

}