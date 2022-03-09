package com.don.board.model.article;

public class Article {

	private int idx;
	private String regDate;
	private String title;
	private String body;
	private String nickname;

	public Article(int idx, String regDate, String title, String body, String nickname) {
		super();
		this.idx = idx;
		this.regDate = regDate;
		this.title = title;
		this.body = body;
		this.nickname = nickname;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}
