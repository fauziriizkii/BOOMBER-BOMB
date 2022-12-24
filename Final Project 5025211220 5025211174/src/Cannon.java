import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.ArrayList;

public class Cannon {

	private int fireButtonX = 105;
	private int fireButtonY = 350;
	private int fireButtonWidth = 100;
	private int fireButtonHeight = 50;

	private Color colorSelected = new Color(85, 85, 85);

	private int diameter = 100;
	private int width = 300;

	private int x = 120;
	private int y = GamePanel.HEIGHT - diameter - 50;

	private int ballX;
	private int ballY;

	private int angle;
	private int size;
	private int power;

	public ArrayList<Ball> balls = new ArrayList<Ball>();

	// ----------------------------------------------------------//

	public Cannon() {
	}

	public Graphics2D draw(Graphics2D g, int angle, int size, int power) {

		this.angle = angle;
		this.size = size;
		this.power = power;

		g = drawBalls(g);
		g = drawCannon(g);
		g = drawButtons(g);

		return g;

	}

	private Graphics2D drawCannon(Graphics2D g) {

		diameter = size + 50;

		// -------- Turn Cannon ----------//
		int xPoly[] = { x, x + width, x + width, x };
		int yPoly[] = { y, y, y + diameter, y + diameter };
		int i;
		for (i = 0; i < xPoly.length; i++) {
			int newXY[] = rotateXY(xPoly[i], yPoly[i], angle, x, y + diameter);
			xPoly[i] = newXY[0];
			yPoly[i] = newXY[1];
		}
		for (i = 0; i < xPoly.length; i++) {
			yPoly[i] = yPoly[i] + y + 100 - yPoly[3]; // keeps it fixed to the corner
		}

		// Where the ball will be spawning
		ballX = xPoly[1];
		ballY = yPoly[1];
		ballX += xPoly[2] - xPoly[1] - diameter - 2;

		// Cannon
		g.setColor(Color.BLACK);
		Polygon poly = new Polygon(xPoly, yPoly, xPoly.length);
		g.fillPolygon(poly);
		// Wheel
		g.setColor(new Color(139, 69, 19));
		g.fillOval(x - 25, GamePanel.HEIGHT - 100, 100, 100);

		return g;
	}

	private Graphics2D drawBalls(Graphics2D g) {

		// If fire button is clicked
		if (GamePanel.click == true && GamePanel.cursorX > fireButtonX
				&& GamePanel.cursorX < fireButtonX + fireButtonWidth && GamePanel.cursorY > fireButtonY
				&& GamePanel.cursorY < fireButtonY + fireButtonHeight) {
			balls.clear();
			balls.add(new Ball(ballX, ballY, diameter,
					(int) (((double) power * -1) - ((double) power * -1) / ((double) 157) * (angle * -1)),
					(int) (((double) power * -1) / ((double) 157) * (angle * -1)), colorSelected));
			GamePanel.click = false;
		}

		// Draw balls
		for (int i = 0; i < balls.size(); i++) {
			g.setColor(balls.get(i).getColor());
			g.fillOval(balls.get(i).getX(), balls.get(i).getY(), balls.get(i).getDiameter(),
					balls.get(i).getDiameter());
			balls.get(i).update();

			// If ball collide with enemy
			for(int j = 0; j < GamePanel.enemies.size(); j++){
				if(isColliding(balls.get(i).getX(), balls.get(i).getY(), 
					GamePanel.enemies.get(j).getX(), GamePanel.enemies.get(j).getY(), 
					balls.get(i).getDiameter(), GamePanel.enemies.get(j).getRadius())){
						if (GamePanel.enemies.get(j).getSpawntime() > 0)
							continue;
						// System.out.println("HIT");
						
						GamePanel.scoreManager.setScore(GamePanel.scoreManager.getScore()+10);
						// balls.clear();
						GamePanel.enemies.get(j).setHit(true);
						GamePanel.enemies.get(j).setSpawntime(GamePanel.spawntime);		
				}
			}

			// If ball hit bottom
			if (balls.get(0).getY() + balls.get(0).getDiameter() >= GamePanel.HEIGHT) {
				balls.clear();
			}
			

		}

		return g;
	}

	private Graphics2D drawButtons(Graphics2D g) {

		// fire button
		g.setColor(Color.RED);
		g.fillRect(fireButtonX, fireButtonY, fireButtonWidth, fireButtonHeight);
		g.setColor(Color.BLACK);
		Font f = new Font("Calibri", Font.BOLD, 48);
		g.setFont(f);
		g.drawString("FIRE", fireButtonX + 7, fireButtonY + fireButtonHeight - 10);

		return g;
	}

	private int[] rotateXY(int x, int y, int angle, int cx, int cy) {

		double tempX = x - cx;
		double tempY = y - cy;

		double rotatedX = tempX * Math.cos((double) angle / 100) - tempY * Math.sin((double) angle / 100);
		double rotatedY = tempX * Math.sin((double) angle / 100) + tempY * Math.cos((double) angle / 100);

		x = (int) (rotatedX + cx);
		y = (int) (rotatedY + cy);

		return new int[] { x, y };

	}

	private boolean isColliding(double x1, double y1, double x2, double y2, int r1, int r2)
    {
        double dx = x1 - x2;
        double dy = y1 - y2;
        double distance = Math.sqrt(dx * dx + dy * dy);
        
        if(distance < r1 + r2)
        {
			for(int i = 0; i < balls.size(); i++){
				
			}
            return true;
        }
        return false;
    }

}