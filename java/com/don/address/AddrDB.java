package com.don.address;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AddrDB {

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

	// SQL을 운반하는 객체 Statement
	// 데이터를 DB에 저장하는 메서드
	public void insertData() {

		String sql = "INSERT INTO addr SET `name` = '홍길동', address = '서울', phone = '010-1111-1111'";

		Connection conn = getConnection();

		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
