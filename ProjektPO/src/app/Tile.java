package app;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle{
	
	Planet planet;
	
	public Planet getPlanet(){
		return this.planet;
	}

	public void setPlanet(Planet planet){
		this.planet = planet;
	}
	
	public Tile(boolean color, int x, int y){
		setWidth(Board.TILE_SIZE);
		setHeight(Board.TILE_SIZE);
		
		relocate(x* Board.TILE_SIZE, y * Board.TILE_SIZE);
		
		setFill(color ? Color.valueOf("003399") : Color.valueOf("000066"));
		
	}
	

}
