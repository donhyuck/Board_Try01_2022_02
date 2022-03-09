package com.don.board.model.article;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ArticleDB {

	// JDBC
	// DBMS 접속정보 세팅

	// DBMS 주소
	String url = "jdbc:mysql://localhost:3306/jsptry?serverTimezone=UTC";
	// 인증정보
	String user = "root";
	String pass = "";

	// 드라이버 정보(자바 클래스)
	String driver = "com.mysql.cj.jdbc.Driver"; // 다운로드 받아야 함

	private Connection getConnection() {

		Connection conn = null;

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			System.out.println("Connection 가져오는 중 문제 발생");
		}

		return conn;
	}

	//
	public void insertArticle(String title, String body, String nickname) {

		String sql = String.format(
				"INSERT INTO article SET regDate=NOW(), `title` = '%s', `body` = '%s', nickname = '%s'", title, body,
				nickname);

		updateQuery(sql);
	}

	//
	public ArrayList<Article> getArticleList(String sql) {

		Connection conn = getConnection();

		ArrayList<Article> articleList = new ArrayList<>();

		try {
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int idx = rs.getInt("idx");
				String regDate = rs.getString("regDate");
				String title = rs.getString("title");
				String body = rs.getString("body");
				String nickname = rs.getString("nickname");

				Article article = new Article(idx, regDate, title, body, nickname);
				articleList.add(article);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return articleList;
	}

	public ArrayList<Article> getArticles() {

		String sql = "SELECT * FROM article";

		ArrayList<Article> articles = getArticleList(sql);

		return articles;
	}

	public Article getArticleByIdx(int idx) {

		Article foundArticle = null;

		String sql = String.format("SELECT * FROM article WHERE idx = %d", idx);

		ArrayList<Article> articles = getArticleList(sql);

		if (articles.size() > 0) {
			foundArticle = articles.get(0);
		}

		return foundArticle;
	}

	// insert와 update에서의 공통부분을 묶는다.
	public void updateQuery(String sql) {

		Connection conn = getConnection();
		Statement stmt = null;// 체크

		try {
			stmt = conn.createStatement(); // 체크
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void updateArticle(int idx, String title, String body) {

		String sql = String.format("UPDATE article SET `title` = '%s', `body` = '%s' WHERE idx=%d", title, body, idx);

		updateQuery(sql);
	}

	public void deleteArticle(int idx) {

		String sql = String.format("DELETE FROM article WHERE idx=%d", idx);

		updateQuery(sql);
	}
}
