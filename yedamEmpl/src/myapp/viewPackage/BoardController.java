package myapp.viewPackage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import common.BoardVO;
import common.InputBoardVO;
import common.InputDAO;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

public class BoardController implements Initializable {
	@FXML
	TextField title;
	@FXML
	TableView<BoardVO> tableView;
	@FXML
	TextField boardNo;
	@FXML
	TextArea contents;
	@FXML
	ComboBox<String> publicity;
	@FXML
	DatePicker exitDate;
	@FXML
	Button updateBtn, deleteBtn, exitBtn, addBtn, preBtn, nextBtn;

	private Stage primaryStage;

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//FXML에 onAction 하지않았을경우 
		deleteBtn.setOnAction(e -> deleteBtnAction(e)); // 메소드로 기능 적용 (fxml파일에서도 가능)
		
		addBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				addBtnAction(e);
			}

		});
		// 현재 존재하는 데이터 목록에 띄우는 기능
		ObservableList<BoardVO> list = InputDAO.boardList();

		TableColumn<BoardVO, Integer> tcBoardNo = (TableColumn<BoardVO, Integer>) tableView.getColumns().get(0);
		tcBoardNo.setCellValueFactory(new PropertyValueFactory<BoardVO, Integer>("boardNo"));  // 게시판번호

		TableColumn<BoardVO, String> tcTitle = (TableColumn<BoardVO, String>) tableView.getColumns().get(1);
		tcTitle.setCellValueFactory(new Callback<CellDataFeatures<BoardVO, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<BoardVO, String> param) {
				return param.getValue().titleProperty();
			}
		}); //제목

		TableColumn<BoardVO, String> tcPub = new TableColumn<BoardVO, String>("공개");
		tcPub.setCellValueFactory(new PropertyValueFactory<BoardVO, String>("publicity"));
		tableView.getColumns().add(tcPub);
		tcPub.setPrefWidth(70); // 공개여부

		TableColumn<BoardVO, String> tcExDate = new TableColumn<BoardVO, String>("종료일자");
		tcExDate.setCellValueFactory(new PropertyValueFactory<BoardVO, String>("exitDate"));
		tableView.getColumns().add(tcExDate);
		tcExDate.setPrefWidth(150); // 종료일자

		TableColumn<BoardVO, String> tcContent = new TableColumn<BoardVO, String>("내용");
		tcContent.setCellValueFactory(new PropertyValueFactory<BoardVO, String>("contents"));
		tableView.getColumns().add(tcContent);
		tcContent.setPrefWidth(231); // 내용

		tableView.setItems(list);
		
	    // 목록값 선택하면 아래 텍스트창에 보이게하는 메소드 
		tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<BoardVO>() {

			@Override
			public void changed(ObservableValue<? extends BoardVO> arg0, BoardVO oldVal, BoardVO newVal) {
				if (newVal != null) {
					System.out.println(newVal.getBoardNo());
					boardNo.setText(String.valueOf(newVal.getBoardNo()));
					title.setText(newVal.getTitle());
					publicity.setValue(newVal.getPublicity());
					contents.setText(newVal.getContents());
					exitDate.setValue(LocalDate.parse(newVal.getExitDate()));
				}
			}
		});

	} // end of initialize
	
// 등록, 삭제, 취소 버튼 액션메소드
	public void updateBtnAction(ActionEvent e) {
		BoardVO vo = new BoardVO();
		vo.setBoardNo(Integer.parseInt(boardNo.getText()));
		vo.setContents(contents.getText());
		vo.setExitDate(exitDate.getValue().toString());
		vo.setPublicity(publicity.getValue().toString());
		InputDAO.updateboard(vo);

		tableView.setItems(InputDAO.boardList());
	}

	public void deleteBtnAction(ActionEvent e) {
		BoardVO vo = new BoardVO();
		int deleteBoardNo = Integer.parseInt(boardNo.getText());
		InputDAO.deleteboard(deleteBoardNo);

		tableView.setItems(InputDAO.boardList());
	}
	public void exitBtnAction(ActionEvent e) {
		Platform.exit();
	}
	public void preBtnAction(ActionEvent e) {
	}
	public void nextBtnAction(ActionEvent e) {
		
	}
	

// 2번째 창 추가 / 취소 액션메소드	
	public void addBtnAction(ActionEvent e) {
		Stage stage = new Stage(StageStyle.DECORATED);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(primaryStage); // 윈도우를 소유할 메인 윈도우

		try {
			AnchorPane ap = FXMLLoader.load(getClass().getResource("BoardAdd.fxml"));
			Scene scene = new Scene(ap);
			stage.setScene(scene);
			stage.show();

			Button btnReg = (Button) ap.lookup("#btnReg");
			Button btnCancel = (Button) ap.lookup("#btnCancel");

			btnCancel.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					stage.close();
				}
			});

			btnReg.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					TextField txtTitle = (TextField) ap.lookup("#txtTitle");
					PasswordField txtpasswd = (PasswordField) ap.lookup("#txtPassword");
					ComboBox<String> comboPublic = (ComboBox<String>) ap.lookup("#comboPublic");
					DatePicker dateExit = (DatePicker) ap.lookup("#dateExit");
					TextArea txtContent = (TextArea) ap.lookup("#txtContent");

					InputBoardVO vo = new InputBoardVO();

					vo.setTitle(txtTitle.getText());
					vo.setPasswd(txtpasswd.getText());
					vo.setPublicity(comboPublic.getValue().toString());
					vo.setExitDate(dateExit.getValue().toString());
					vo.setContents(txtContent.getText());

					InputDAO.insertBoard(vo);
					tableView.setItems(InputDAO.boardList());
					stage.close();
				}
			});
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
