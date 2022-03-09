package com.don.board.model.member;

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

	// 회원가입
	public void insertMember(String loginId, String loginPw, String nickname) {

		String sql = String.format(
				"INSERT INTO `member` SET regDate=NOW(), loginId = '%s', loginPw = '%s', nickname = '%s'", loginId,
				loginPw, nickname);

		updateQuery(sql);
	}

	public ArrayList<Member> getMemberList(String sql) {

		Connection conn = getConnection();

		ArrayList<Member> memberList = new ArrayList<>();

		try {
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int idx = rs.getInt("idx");
				String regDate = rs.getString("regDate");
				String loginId = rs.getString("loginId");
				String loginPw = rs.getString("loginPw");
				String nickname = rs.getString("nickname");

				Member member = new Member(idx, regDate, loginId, loginPw, nickname);
				memberList.add(member);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return memberList;
	}

	// 회원번호로 회원정보 가져오기
	public Member getMemberByIdx(int idx) {

		Member foundMember = null;

		String sql = String.format("SELECT * FROM `member` WHERE idx = %d", idx);

		ArrayList<Member> members = getMemberList(sql);

		if (members.size() > 0) {
			foundMember = members.get(0);
		}

		return foundMember;
	}

	// 로그인 정보로 회원번호 가져오기
	public int getMemberIdxByLoginInfo(String loginId, String loginPw) {

		String sql = String.format("SELECT idx FROM `member` WHERE loginId = '%s' AND loginPw = '%s'", loginId, loginPw);

		Connection conn = getConnection();
		int foundMemberIdx = 0;

		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				foundMemberIdx = rs.getInt("idx");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return foundMemberIdx;
	}

}
