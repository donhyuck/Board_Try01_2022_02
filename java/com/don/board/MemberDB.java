package com.don.board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MemberDB {

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
}
