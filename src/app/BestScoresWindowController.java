package app;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class BestScoresWindowController implements Initializable{
	
	
	@FXML
	ListView<String> bestScoresListView;

	ObservableList<String> items = FXCollections.observableArrayList();
	
	ReaderWriter rw = new ReaderWriter();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try{
			bestScoresListView.setEditable(true);
			rw.parse();
			
			Collections.sort(rw.records);
			Collections.reverse(rw.records);
			int i = 1;
			for(Record r : rw.records){
				items.add(i + ".\t" + r.toString());
				i++;
				}
			
			bestScoresListView.setItems(items);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

}
