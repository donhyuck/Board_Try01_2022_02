package com.don.board.model.article;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ReplyDB {

	CommonDB cdb = new CommonDB();

	// 여러개 가져오기
	public ArrayList<Reply> getRepliesByArticleIdx(int articleIdx) {

		String sql = String.format("SELECT * FROM articleReply WHERE articleIdx = %d", articleIdx);

		return cdb.selectList(sql, new ReplyRowMapper());
	}

	// 한개 가져오기
	public Reply getReplyByIdx(int idx) {

		String sql = String.format("SELECT * FROM articleReply WHERE idx = %d", idx);

		return cdb.getOne(sql, new ReplyRowMapper());

	}

	// 댓글 등록하기
	public void insertReply(int articleIdx, int memberIdx, String body, String nickname) {

		String sql = String.format(
				"INSERT INTO articleReply SET regDate=NOW(), articleIdx = '%d', memberIdx='%d', `body` = '%s', nickname = '%s'",
				articleIdx, memberIdx, body, nickname);

		cdb.updateQuery(sql);
	}

	// 댓글 수정하기
	public void updateReply(int idx, String body) {

		String sql = String.format("UPDATE articleReply SET `body`='%s' WHERE idx='%d'", body, idx);

		cdb.updateQuery(sql);
	}

	// 댓글 삭제하기
	public void deleteReply(int idx) {

		String sql = String.format("DELETE FROM articleReply WHERE idx='%d'", idx);

		cdb.updateQuery(sql);
	}
}
