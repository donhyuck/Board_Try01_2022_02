package com.don.board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ReplyDB {

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

	public void insertReply(int articleIdx, String body, String nickname) {

		String sql = String.format(
				"INSERT INTO articleReply SET regDate=NOW(), articleIdx = '%d', `body` = '%s', nickname = '%s'",
				articleIdx, body, nickname);

		updateQuery(sql);
	}

	public ArrayList<Reply> getReplyList(String sql) {

		Connection conn = getConnection();

		ArrayList<Reply> replyList = new ArrayList<>();

		try {
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int idx = rs.getInt("idx");
				String regDate = rs.getString("regDate");
				int articleIdx = rs.getInt("articleIdx");
				String body = rs.getString("body");
				String nickname = rs.getString("nickname");

				Reply reply = new Reply(idx, regDate, articleIdx, body, nickname);
				replyList.add(reply);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return replyList;
	}

	public ArrayList<Reply> getRepliesByArticleIdx(int articleIdx) {

		String sql = String.format("SELECT * FROM articleReply WHERE articleIdx = %d", articleIdx);

		return getReplyList(sql);
	}

}
