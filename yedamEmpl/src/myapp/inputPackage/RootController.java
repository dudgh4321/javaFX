package myapp.inputPackage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import common.InputBoardVO;
import common.InputDAO;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class RootController implements Initializable {
	@FXML
	private TextField txtTitle;
	@FXML
	private PasswordField txtPassword;
	@FXML
	private ComboBox<String> comboPublic;
	@FXML
	private DatePicker dateExit;
	@FXML
	private TextArea txtContent;

	@Override
	public void initialize(URL loaction, ResourceBundle resources) {

	}

	public void handleBtnRegAction(ActionEvent e) {
		String title = txtTitle.getText();
		String passwd = txtPassword.getText();
		String publicity = comboPublic.getValue();
		String exitDate = dateExit.getValue().toString();
		String contents = txtContent.getText();

		InputBoardVO bo = new InputBoardVO();
		bo.setTitle(title);
		bo.setPasswd(passwd);
		bo.setPublicity(publicity);
		bo.setExitDate(exitDate);
		bo.setContents(contents);

		InputDAO.insertBoard(bo);

		// 초기화
		txtTitle.setText("");
		txtPassword.setText("");
		comboPublic.setValue("공개");
		dateExit.setValue(LocalDate.now());
		txtContent.setText("");
	}

//		String title = txtTitle.getText();
//		System.out.println("title: " + title);
//		
//		String password = txtPassword.getText();
//		System.out.println("password: " + password);
//
//		String strPublic = comboPublic.getValue();
//		System.out.println("public: " + strPublic);
//		
//		LocalDate localDate = dateExit.getValue();
//		if(localDate != null) {
//			System.out.println("dateExit :" + localDate.toString());
//		}
//		
//		String content = txtContent.getText();
//		System.out.println("content :" + content);

	public void handleBtnCancelAction(ActionEvent e) {
		Platform.exit();
	}

}
