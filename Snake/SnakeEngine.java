import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SnakeEngine {
	public static final int
		CELL = 25, 
		CELLS_COUNT = 20,
		GAME_SPEED = 200,
		FRAME_WIDTH = CELL*CELLS_COUNT+17,
		FRAME_HEIGHT = CELL*CELLS_COUNT+39;

	static final Color
		BACKGROUND_COLOR = new Color(39, 40, 34),
		SNAKE_HEAD_COLOR = new Color(230, 230, 230),
		SNAKE_BODY_COLOR = new Color(102, 217, 208),
		EAT_COLOR = new Color(249, 38, 114);
	
	static SnakeEngine snakeEngine;
	Snake snake;
	Eat eat;
	JFrame frame;
	java.util.Timer timer;
	MyDrawPanel drawPanel;

	public static void main(String[] args) {

		snakeEngine = new SnakeEngine();
		snakeEngine.gameInitialize();
	}

	public void gameInitialize() {
		gameRestart();

		frame = new JFrame("Snake");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		drawPanel = new MyDrawPanel();
		frame.getContentPane().add(drawPanel);
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setResizable(false);
		frame.setVisible(true);

		timer = new java.util.Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
            	snake.generateNextStep(eat);
				drawPanel.repaint();
            }
        }, 0, GAME_SPEED);

		frame.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch(e.getKeyCode()) {
					case 37:
						snake.setVector(Snake.Vector.LEFT);
					break;
					case 38:
						snake.setVector(Snake.Vector.UP);
					break;
					case 39:
						snake.setVector(Snake.Vector.RIGHT);
					break;
					case 40:
						snake.setVector(Snake.Vector.DOWN);
					break;
				}
				snake.generateNextStep(eat);
				drawPanel.repaint();
			}
		});
	}

	public void gameRestart() {
		snake = new Snake();
		eat = new Eat(snake);
	}

	class MyDrawPanel extends JPanel {
		public void paintComponent(Graphics g) {
			g.setColor(BACKGROUND_COLOR);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());

			g.setColor(SNAKE_BODY_COLOR);
			for(int i = 1; i < snake.getLength(); i++) {
				g.drawRoundRect(snake.getX(i)*CELL, snake.getY(i)*CELL, CELL, CELL, 17, 17);
			}

			g.setColor(SNAKE_HEAD_COLOR);
			g.drawRoundRect(snake.getX(0)*CELL, snake.getY(0)*CELL, CELL, CELL, 17, 17);

			g.setColor(EAT_COLOR);
			g.fillOval(eat.getX()*CELL+CELL/4, eat.getY()*CELL+CELL/4, CELL/2, CELL/2);			
		}
	}
}