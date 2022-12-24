import java.awt.Graphics2D;
import java.awt.*;


public class Enemy {

	//x and y coordinates of enemies
	private double x;
	private double y;
	
	private double dx;
	private double dy;
	
	//radius of the enemies
	private int radius;
	
	//speed of enemies
	private double speed;
	
	//Enemies Type based on level
	private int type;
	private int level;
	
	//number lives of enemies
	private int lives;
	
	//Timer enemies
	private long spawntime;
	
	//color of the enemies
	private Color color1;
	
	private boolean ready;
    private boolean hit;
    private long hitTimer;
    
    private boolean slow;
	
    public Enemy(int type,int level) {
    	
    	this.type = type;
    	this.level = level;
    	
    	if(type == 1) {
    		color1 = Color.PINK;
    		if(level == 1) {
    			speed = 6;
    			radius = 50;
    			lives = 1;
    	
    		}
    		else if(level == 2) {
    			speed = 8;
    			radius = 45;
    			lives = 1;
    			
    		}
    		else if(level == 3) {
    			speed = 10;
    			radius = 40;
    			lives = 1;
    			
    		}
    	}
    	else if (type == 2) {
    		color1 = Color.BLUE;
    		if(level == 1 ) {
    			speed = 8;
    			radius = 45;
    			lives = 1;
    			
    		}
    		else if(level == 2 ) {
    			speed = 10;
    			radius = 40;
    			lives = 1;
    			
    		}
    		
    		else if(level == 3 ) {
    			speed = 12;
    			radius = 35;
    			lives = 1;
    			
    		}
    	}
    	
    	else if (type == 3) {
    		color1 = Color.BLACK;
    		if(level == 1 ) {
    			speed = 8;
    			radius = 35;
    			lives = 1;
    			
    		}
    		else if(level == 2 ) {
    			speed = 10;
    			radius = 30;
    			lives = 1;
    			
    		}
    		
    		else if(level == 3 ) {
    			speed = 12;
    			radius = 25;
    			lives = 1;
    			
    		}
    	}
    	
    	x = Math.random()*GamePanel.WIDTH/2 + GamePanel.WIDTH/4;
    	y = (radius + 100);
    	
    	double angle = Math.random() * 140 + 20;
    	double radians = Math.toRadians(angle);
    	
    	dx = Math.cos(radians) * speed;
		dy = Math.sin(radians) * speed;
    	
    	ready = false;
    	hit = false;
    	hitTimer = 0;
    	slow = false;
    	
    }
    
    public double getX() {return x;}
    public double getY() {return y;}
    public double getDx() {return dx;}
 	public double getDy() {return dy;}
    public int getRadius() {return radius;}
    public double getSpeed() {return speed;}
    public int getType() {return type;}
    public int getLevel() {return level;}
    public int getLives() {return lives;}
    public boolean gotHit() {return hit;}
	public long getSpawntime(){return spawntime;}
    public boolean isSlow() {return slow;}
    
    public void setX(double x) {this.x = x;}
    public void setY(double y) {this.y = y;}
    public void setDx(double dx) {this.dx = dx;}
	public void setDy(double dy) {this.dy = dy;}
    public void setRadius(int radius) {this.radius = radius;}
    public void setSpeed(double speed) {this.speed = speed;}
    public void setLives(int lives) {this.lives = lives;}
    public void setHit(boolean b) {this.hit = b;}
    public void setSlow(boolean b) {slow = b;}
	public void setSpawntime(long t){this.spawntime = t;}
	
    public void update()
    {
        
        if(slow == true)
        {
            x += dx * 0.5;
            y += dy * 0.5;
        }
        else
        {
            x += dx;
            y += dy;
        }
        
        
        if(ready == false)
        {
            if(x > radius && x < GamePanel.WIDTH - radius &&
               y > (radius + 100) && y < GamePanel.HEIGHT - radius)
            {
                ready = true;
            }
        }
        
        checkBoundaries();
        
        if(hit == true)
        {
            long elapsedTime = (System.nanoTime() - hitTimer) / 1000000;
            if(elapsedTime > 50)
            {
                hit = false;
                hitTimer = 0;
            }
        }
    }
    
    public Graphics2D draw(Graphics2D g) {
    	if(hit == false && spawntime <= 0) {
    		g.setColor(color1);
    		g.fillOval((int) (x-radius),(int)(y-radius),(2*radius),(2*radius));
    		g.setStroke(new BasicStroke(3));
    		g.setColor(color1.darker());
    		g.drawOval((int) (x-radius),(int)(y-radius),(2*radius),(2*radius));
    		g.setStroke(new BasicStroke(1));
    	}
    	else {
			spawntime -= 13;
			System.out.println(spawntime);
    	}
		update();
		return g;
    }
    
    private void checkBoundaries()
    {
        if(x < GamePanel.WIDTH/2-GamePanel.WIDTH/6 && dx < 0)
        {
            dx = -dx;
        }
        
        if(x > GamePanel.WIDTH - radius && dx > 0)
        {
            dx = -dx;
        }
        
        if(y < (radius) && dy < 0)
        {
            dy = -dy;
        }
        
        if(y > 700 - radius && dy > 0)
        {
            dy = -dy;
        }
    }
	
}



































