package myapp.viewPackage;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;

public class RootController implements Initializable {
	@FXML
	private ListView<String> listView;
	@FXML
	private TableView<Phone> tableView;
	@FXML
	private ImageView imageView;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		listView.setItems(FXCollections.observableArrayList("갤럭시S1", "갤럭시S2", "갤럭시S3", "갤럭시S4", "갤럭시S5", "갤럭시S6", "갤럭시S7" )); 
		//해당하는 리스트를 담아주는 메소드(setItems) , observableList 타입의 list  observableList<String> list = FXCollections.observableArrayList()
		
		//SelectedIndex 속성강시 
		listView.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			  		@Override
		public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				tableView.getSelectionModel().select(newValue.intValue());
				tableView.scrollTo(newValue.intValue());
			}
		}});
		obsevableList phoneList = FXComllection.observableArraylist( 	new Phone("갤럭시S1", "phone01.png"),
																		new Phone("갤럭시S1", "phone01.png"),
																		new Phone("갤럭시S1", "phone01.png"),
																		new Phone("갤럭시S1", "phone01.png"),
																		new Phone("갤럭시S1", "phone01.png"),
																		new Phone("갤럭시S1", "phone01.png"),
																		new Phone("갤럭시S1", "phone01.png")
				);
			TableColumn tcSmartPhone = tableView.getColumns().get(0);	
		
		
	}

}
