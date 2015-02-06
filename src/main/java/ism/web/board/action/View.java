package ism.web.board.action;

public class View {
	private String path ;
	private boolean redirect; // true : 리다이렉션, false : 포워딩을하라는 뜻
	
	public View(String path, boolean redirect) {
		super();
		this.path = path;
		this.redirect = redirect;
	}

	public boolean isForward() {
		return !redirect;
	}

	public String getPath() {
		return path;
	}
	
	
	
	
}
