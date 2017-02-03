package app;

import javafx.scene.Group;
import javafx.scene.layout.Pane;

public class Board {
	public static final int TILE_SIZE = 80;
	public static final int H = 7;
	public static final int W = 7;
	// **************************************************
	public Tile[][] board = new Tile[W][H];

	private Group tileGroup = new Group();
	private Group planetGroup = new Group();
	
	Pane root;

	int points;

	// **************************************************

	public Board() {
		this.root = createContent();						//to jest niepotrzebne
	}
	
	public Pane getRoot() {
		this.root = createContent();
		return this.root;
	}
	
	private Pane createContent() {
		Pane root = new Pane();
		root.setPrefSize(W * TILE_SIZE, H * TILE_SIZE);
		
		points = 0;
		 
		root.getChildren().addAll(tileGroup, planetGroup);
		 
		for (int j = 0; j < W; j++) {
			for (int i = 0; i < H; i++) {

				Tile tile = new Tile((i + j) % 2 == 0, i, j);
				
				board[i][j] = tile;

				tileGroup.getChildren().add(tile);

				Planet planet = makePlanet(PlanetType.randomPlanetType(), i, j);

				tile.setPlanet(planet);
				// System.out.println(board[i][j].planet.getColor());

				planetGroup.getChildren().add(planet);
			}
			
		}
		return root;

	}

	/*
	 * public void swap(Planet a, Planet b){ Planet c = a;
	 * board[b.getX()][b.getY()].setPlanet(a); a.setCoords(b.getX(), b.getY());
	 * 
	 * board[c.getX()][c.getY()].setPlanet(b); b.setCoords(b.getX(), b.getY());
	 * }
	 */

	private Planet makePlanet(PlanetType type, int x, int y) {
		
		Planet planet = new Planet(type, x, y, this);
		
		return planet;
	}

	public boolean checkMatch(int columns, int rows) {

		int rowsCounter = countRows(rows);
		int columnsCounter = countColumns(columns);

		if (rowsCounter < 5 || columnsCounter < 5)
			return true;
		else
			return false;
	}

	
	public int countRows(int row) {

		int rowsCounter = 0;

		for (int i = 0; i < Board.H - 1; i++) {
			if (board[i][row].getPlanet().getColor() != board[i + 1][row].getPlanet().getColor()) {
				rowsCounter++;
			}
		}
		return rowsCounter;
	}

	public int countColumns(int column) {

		int columnsCounter = 0;

		for (int i = 0; i < Board.H - 1; i++) {
			if (board[column][i].getPlanet().getColor() != board[column][i + 1].getPlanet().getColor()) { 	//jezeli maj¹ ró¿ne kolory
				columnsCounter++; 													
			}
		}
		return columnsCounter;
	}

	public void makeMove(int columns, int rows) {

		if (countRows(rows) < 5) {
			
			points++;
			for (int i = 0; i < Board.H - 1; i++) {
				Planet p = new Planet(PlanetType.randomPlanetType(), i, rows, this);
				board[i][rows].setPlanet(p);
				planetGroup.getChildren().add(p);
			}
	//		System.out.println("masz " + points + " punktow");
		}

		if (countColumns(columns) < 5) {
			
			points++;
			for (int i = 0; i < Board.H - 1; i++) {
				Planet p = new Planet(PlanetType.randomPlanetType(), columns, i, this);
				board[columns][i].setPlanet(p);
				planetGroup.getChildren().add(p);
			}
	//		System.out.println("masz " + points + " punktow");
		}
		WelcomeWindowController.controller.setPoints(points);
		
	}
	
	public int getPoints(){
		return this.points;
	}
}
