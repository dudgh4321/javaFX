package common;

public class InputBoardVO {
	int boardNo; 
	String title;
	String passwd;
	String publicity;
	String exitDate;
	String contents;


	public int getBoardNo() {
		return boardNo;
	}

	public String getTitle() {
		return title;
	}

	public String getPasswd() {
		return passwd;
	}

	public String getPublicity() {
		return publicity;
	}

	public String getExitDate() {
		return exitDate;
	}

	public String getContents() {
		return contents;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public void setPublicity(String publicity) {
		this.publicity = publicity;
	}

	public void setExitDate(String exitDate) {
		this.exitDate = exitDate;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

}
