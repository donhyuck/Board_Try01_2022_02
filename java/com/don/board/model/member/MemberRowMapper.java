package com.don.board.model.member;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.don.board.model.article.RowMapper;

public class MemberRowMapper implements RowMapper<Member> {

	@Override
	public Member getRow(ResultSet rs) throws SQLException {

		int idx = rs.getInt("idx");
		String regDate = rs.getString("regDate");
		String loginId = rs.getString("loginId");
		String loginPw = rs.getString("loginPw");
		String nickname = rs.getString("nickname");

		Member member = new Member(idx, regDate, loginId, loginPw, nickname);

		return member;
	}

}
