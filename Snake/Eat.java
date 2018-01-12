public class Eat {
	private int 
		x, 
		y;
	Eat(Snake snake) {
		changePlace(snake);
	}

	public void changePlace(Snake snake) {
		int maxNum = SnakeEngine.CELLS_COUNT*SnakeEngine.CELLS_COUNT - snake.getLength();
		int randNum = (int)(Math.random()*maxNum);

		for(int i = 0; i < SnakeEngine.CELLS_COUNT; i++) {
			for(int j = 0; j < SnakeEngine.CELLS_COUNT; j++) {
				if(snake.snakeContains(i, j)) {
					continue;
				}
				if(randNum == 0) {
					x = i;
					y = j;
					return;
				}
				randNum--;
			}
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}