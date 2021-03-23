package myapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FxmlApp extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("viewPackage/board.fxml")); // Label, Button // parent 는 무슨형식의 파일이든 다 담을 수 있음
								// getClass는 현재 클래스를 리턴하고 getResource는 리턴된 클래스위치에서 상대경로로 리소스의 URL을 리턴한다.

		// 컨테이너를 Scene의 매개값으로.
		Scene scene = new Scene(root);

		// Stage의 매개값으로 Scene 달아줌.
		primaryStage.setScene(scene); // 장면(scene)을 생성한 후 윈도우에 올리는 과정
		primaryStage.show(); // 윈도우를 보여주는 메소드
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
