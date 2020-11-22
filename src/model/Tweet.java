package model;

import java.io.Serializable;

public class Tweet implements Serializable {
	private String userName;
	private String text;

	public Tweet() {}
	public Tweet(String userName, String text) {
		this.userName = userName;
		this.text = text;
	}

	public String getUserName() { return userName; }
	public String getText() { return text; }

}
