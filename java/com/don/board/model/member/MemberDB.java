package com.don.board.model.member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.don.board.model.article.CommonDB;

public class MemberDB {

	CommonDB cdb = new CommonDB();

	// 회원번호로 회원정보 가져오기
	public Member getMemberByIdx(int idx) {

		Member foundMember = null;

		String sql = String.format("SELECT * FROM `member` WHERE idx = %d", idx);

		ArrayList<Member> members = cdb.selectList(sql, new MemberRowMapper());

		if (members.size() > 0) {
			foundMember = members.get(0);
		}

		return foundMember;
	}

	// 로그인 정보로 회원번호 가져오기
	public int getMemberIdxByLoginInfo(String loginId, String loginPw) {

		String sql = String.format("SELECT idx FROM `member` WHERE loginId = '%s' AND loginPw = '%s'", loginId,
				loginPw);

		Connection conn = cdb.getConnection();
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

	// 회원가입
	public void insertMember(String loginId, String loginPw, String nickname) {

		String sql = String.format(
				"INSERT INTO `member` SET regDate=NOW(), loginId = '%s', loginPw = '%s', nickname = '%s'", loginId,
				loginPw, nickname);

		cdb.updateQuery(sql);
	}

}
