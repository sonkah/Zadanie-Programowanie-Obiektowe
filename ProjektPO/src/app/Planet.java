package app;

import static app.Board.TILE_SIZE;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Planet extends StackPane {

	Circle rec = new Circle(Board.TILE_SIZE / 2);
	private PlanetType type;
	private int x, y;
	private double mouseX, mouseY;
	private double prevX, prevY;

	private static int oldColumn;
	private static int oldRow;

	private static Planet selected = null;

	// KONSTRUKTOR***********************************************************
	public Planet(PlanetType type, int x, int y, Board b) {
		this.type = type;
		setCoords(x, y);

		// Circle rec = new Circle(Board.TILE_SIZE / 2);
		rec.setCenterX(Board.TILE_SIZE / 2);
		rec.setCenterY(Board.TILE_SIZE / 2);

		rec.setFill(Color.BLACK);

		// rec.setTranslateX(Board.TILE_SIZE );
		// rec.setTranslateY(Board.TILE_SIZE );
		move(x, y);

		switch (this.type) {
		case Mercury:
			rec.setFill(Color.BLUE);
			break;
		case Venus:
			rec.setFill(Color.YELLOW);
			break;
		case Earth:
			rec.setFill(Color.GREEN);
			break;
		// case Earth : img = new Image("/Planets/earth.png"); break;
		case Mars:
			rec.setFill(Color.RED);
			break;
		case Jupiter:
			rec.setFill(Color.GREY);
			break;
		case Saturn:
			rec.setFill(Color.ORANGE);
			break;
		case Uranus:
			rec.setFill(Color.PINK);
			break;
		case Neptune:
			rec.setFill(Color.LIGHTBLUE);
			break;
		/*
		 * case Pluto: rec.setFill(Color.BLACK); break; case Sun:
		 * rec.setFill(Color.WHITE); break; case Moon: rec.setFill(Color.BROWN);
		 * break;
		 */
		default: {
			rec.setFill(Color.BLUE);
			this.type = PlanetType.Mercury;
		}
			;

		}

		getChildren().addAll(rec);
		// this.rec.setFill(new ImagePattern(img));

		setOnMousePressed(event -> {
			mouseX = event.getSceneX();
			mouseY = event.getSceneY();
			
			this.getParent().getScene();
			
			int column = (int) mouseX / TILE_SIZE; 			// COLUMN - X!!!
			int row = (int) mouseY / TILE_SIZE; 			// ROW - Y! PAMIÊTAJ

			if (selected == null) {
				oldRow = row; 								// pamietam te coords do drugiego klikniecia
				oldColumn = column;

				selected = this; 							// zaznaczam pierwsza planete

			} else {

				if ((oldColumn == column && Math.abs(oldRow - row) == 1)
						|| (oldRow == row && Math.abs(oldColumn - column) == 1)) {

					swap(selected, this); 					// zamieniam kolory
					if (b.checkMatch(column, row) && b.checkMatch(oldColumn, oldRow)) {
						b.makeMove(column, row);
						b.makeMove(oldColumn, oldRow);
					} else if (b.checkMatch(oldColumn, oldRow)) {
						b.makeMove(oldColumn, oldRow);
					} else if (b.checkMatch(column, row)) {
						b.makeMove(column, row);
					} else
						swap(selected, this);
					
					selected = null;
				} else
					selected = null;
			}

		});

		/*
		 * setOnMouseDragged(e -> { relocate(e.getSceneX() - mouseX + prevX,
		 * e.getSceneY() - mouseY + prevY); });
		 */
	}

	// GETTERY,
	// SETTERY************************************************************
	public PlanetType getType() {
		return this.type;
	}

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setCoords(int x, int y) {
		setY(y);
		setX(x);
	}

	public void setColor(Paint c) {
		rec.setFill(c);
	}

	public Paint getColor() {
		return rec.getFill();
	}

	public int getColumn() { 
		return (int) getTranslateX() / TILE_SIZE;
	}

	public int getRow() {
		return (int) getTranslateY() / TILE_SIZE;
	}

	// ************************************************************************
	/*
	 * public void swap(Planet a, Planet b) { Planet c = a; a.relocate(b.getX()
	 * Board.TILE_SIZE, b.getY() * Board.TILE_SIZE); b.relocate(a.getX() *
	 * Board.TILE_SIZE, a.getY() * Board.TILE_SIZE); b.setX(c.getX());
	 * b.setY(c.getY()); a.setX(b.getX()); a.setY(b.getY());
	 * 
	 * }
	 */
	public void swap(Planet a, Planet b) {
		Paint c = a.getColor();
		a.setColor(b.getColor());
		b.setColor(c);
	}

	public void move(int x, int y) {
		prevX = x * TILE_SIZE;
		prevY = y * TILE_SIZE;

		relocate(prevX, prevY);
	}

}
