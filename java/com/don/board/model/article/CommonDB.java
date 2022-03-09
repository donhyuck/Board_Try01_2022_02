package com.don.board.model.article;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CommonDB {

	// DB 접속정보
	// 연결하기
	String url = "jdbc:mysql://localhost:3306/jsptry?serverTimezone=UTC";
	String user = "root";
	String pass = "";

	String driver = "com.mysql.cj.jdbc.Driver";

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

	// 여러개 가져오기
	// 제너릭 프로그램 사용
	public <T> ArrayList<T> selectList(String sql, RowMapper<T> mapper) {

		Connection conn = getConnection();

		ArrayList<T> resultList = new ArrayList<>();

		try {
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				// ==== 게시물 구조 ====
				T row = mapper.getRow(rs);
				resultList.add(row);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultList;
	}

	// 한개 가져오기
	public <T> T getOne(String sql, RowMapper<T> mapper) {

		T result = null;
		ArrayList<T> resultList = selectList(sql, mapper);

		if (resultList.size() > 0) {
			result = resultList.get(0);
		}

		return result;
	}

	// 반영하기
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
