package com.don.address;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

	// 기능을 담당하는 sql
	// sql을 운반하는 statement
	// 이를 통신하기 위한 conn

	// insert는 데이터를 보내는 반면에 select는 조회결과가 있다. 결과 반환은 ResultSet
	public ArrayList<Addr> selectDatas() {

		String sql = "SELECT * FROM addr";

		Connection conn = getConnection();

		ArrayList<Addr> addrList = new ArrayList<>();

		try {
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int idx = rs.getInt("idx"); // 현재 가리키는 행의 idx 컬럼값을 int 반환
				String name = rs.getString("name"); // 현재 가리키는 행의 name 컬럼값을 String 반환
				String address = rs.getString("address");
				String phone = rs.getString("phone");

				Addr addr = new Addr(idx, name, address, phone);
				addrList.add(addr);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return addrList;
	}

}
