package app;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class WelcomeWindowController extends Application implements Initializable {

	@FXML
	private Label urNameLabel, welcomeLabel;

	@FXML
	private Button enterNameButton;

	@FXML
	public TextField textFieldName;
	
	@FXML
	private Pane pane;
	
	@FXML
	private HBox hBoxPane = new HBox(10);
	public FXMLLoader loader;
	public static GameWindowController controller;
	
	@FXML
	private void handleEnterNameButtonAction(ActionEvent event) throws IOException {
		if (!textFieldName.getText().isEmpty()) {
			
			loader = new FXMLLoader(
					  getClass().getResource(
					    "GameWindow.fxml"
					  )
					);

					Parent p = loader.load();

					controller = loader.<GameWindowController>getController();
					controller.setName(textFieldName.getText());
			
					Scene gameWindowScene = new Scene(p);
					Stage gameStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
					gameStage.setScene(gameWindowScene);
					gameStage.setTitle("Gra");
					gameStage.show();
					
//			String name = textFieldName.getText();
//			FXMLLoader loader = new FXMLLoader();
//			GameWindowController gwc = FXMLLoader.load(getClass().getResource("GameWindow.fxml"));
//			
//			Parent gameWindowParent = FXMLLoader.load(getClass().getResource("GameWindow.fxml"));	     // przypisujê do TEJ SAMEJ STAGE co w app
//			Scene gameWindowScene = new Scene(gameWindowParent);
//			Stage gameStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
//			;
//			gameStage.setScene(gameWindowScene);
//			gameStage.show();
		}
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

	@Override
	public void start(Stage arg0) throws Exception {

	}

}
