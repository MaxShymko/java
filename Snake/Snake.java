import java.util.*;

public class Snake {

	public class Coord {
		public int x, y;
		Coord(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals(Object o) {
			Coord coord = (Coord)o;
			return (x == coord.x && y == coord.y);
		}
	}

	private ArrayList<Coord> coords;

	public enum Vector {
		LEFT, UP, RIGHT, DOWN
	}
	private Vector vector;

	Snake() {
		coords = new ArrayList<Coord>();
		coords.add(new Coord(SnakeEngine.CELLS_COUNT/2-1, SnakeEngine.CELLS_COUNT/2));
		coords.add(new Coord(SnakeEngine.CELLS_COUNT/2, SnakeEngine.CELLS_COUNT/2));
		coords.add(new Coord(SnakeEngine.CELLS_COUNT/2+1, SnakeEngine.CELLS_COUNT/2));
		vector = Vector.LEFT;
	}

	public int getX(int item) {
		return coords.get(item).x;
	}

	public int getY(int item) {
		return coords.get(item).y;
	}

	public int getLength() {
		return coords.size();
	}

	public boolean snakeContains(int x, int y) {
		return coords.contains(new Coord(x, y));
	}

	public void setVector(Vector vect) {
		switch(vect) {
			case LEFT:
				if(vector != Vector.RIGHT)
					vector = vect;
			break;
			case UP:
				if(vector != Vector.DOWN)
					vector = vect;
			break;
			case RIGHT:
				if(vector != Vector.LEFT)
					vector = vect;
			break;
			case DOWN:
				if(vector != Vector.UP)
					vector = vect;
			break;
		}
	}

	public void generateNextStep(Eat eat) {
		int 
			x = coords.get(0).x,
			y = coords.get(0).y;
		switch(vector) {
			case LEFT:
				x = x == 0 ? SnakeEngine.CELLS_COUNT-1 : x - 1;
			break;
			case UP:
				y = y == 0 ? SnakeEngine.CELLS_COUNT-1 : y - 1;
			break;
			case RIGHT:
				x = x == SnakeEngine.CELLS_COUNT-1 ? 0 : x + 1;
			break;
			case DOWN:
				y = y == SnakeEngine.CELLS_COUNT-1 ? 0 : y + 1;
			break;
		}
		Coord head = new Coord(x, y);

		if(coords.contains(head)) {
			SnakeEngine.snakeEngine.gameRestart();
			return;
		}

		Coord last = coords.get(coords.size()-1);
		
		for(int i = coords.size()-1; i > 0; i--) {
			coords.set(i, coords.get(i-1));
		}
		coords.set(0, head);

		if(x == eat.getX() && y == eat.getY()) {
			coords.add(last);
			eat.changePlace(this);
		}
	}
}