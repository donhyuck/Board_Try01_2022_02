package com.don.board;

public class Reply {

	private int idx;
	private String regDate;
	private int articleIdx;
	private String body;
	private String nickname;

	public Reply(int idx, String regDate, int articleIdx, String body, String nickname) {
		super();
		this.idx = idx;
		this.regDate = regDate;
		this.articleIdx = articleIdx;
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

	public int getArticleIdx() {
		return articleIdx;
	}

	public void setArticleIdx(int articleIdx) {
		this.articleIdx = articleIdx;
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
