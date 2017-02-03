package app; 

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
 
public class GameWindowController implements Initializable{
    
	@FXML
	private Pane primaryPane;
	@FXML
	public Label pointsLabel, timeLabel, timeIDLab, pointsIDLab;
	@FXML
	private Label nameLabel;
	@FXML
	private Button startButton, bestScoresButton, endButton;
	@FXML
	private Pane boardPane;
	
	Board b = new Board();
	ReaderWriter rw = new ReaderWriter();
	
	
	@FXML
	private void handleBestScoresButtonAction(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("BestScoresWindow.fxml"));
        Scene scene = new Scene(root);
        
        scene.getStylesheets().add(getClass().getResource("welcomeWindow.css").toExternalForm());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Najlepsze wyniki");
        stage.setScene(scene);
        stage.show();
	}
	
	@FXML
	private void handlestartButtonAction(ActionEvent e) throws IOException {
		if(startButton.getText()== "Start"){
			boardPane.getChildren().add(b.getRoot());
			startButton.setText("Stop");
			
		}
		else if(startButton.getText()== "Stop")
		{
			endButton.setVisible(true);
			startButton.setText("Jeszcze raz");
			Record r = new Record(Integer.valueOf(pointsLabel.getText()), nameLabel.getText());
			rw.checkBest(r);
			
		}
		else{
			endButton.setVisible(false);
			boardPane.getChildren().add(b.getRoot());
			startButton.setText("Stop");
			pointsLabel.setText("0");
		}
		
	}
	@FXML
	private void handleendButtonAction(ActionEvent e){
		Stage stage = (Stage)primaryPane.getScene().getWindow();
		stage.close();
	}
	
	public void setName(String name){
		nameLabel.setText(name);
	}
	
	public void setPoints(int p){
		pointsLabel.setText(""+ p);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try{
			rw.parse();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
}
