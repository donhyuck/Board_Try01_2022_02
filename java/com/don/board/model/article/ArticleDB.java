package com.don.board.model.article;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ArticleDB {

	CommonDB cdb = new CommonDB();

	// 여러개 가져오기
	public ArrayList<Article> getArticles() {

		String sql = "SELECT * FROM article";

		return cdb.selectList(sql, new ArticleRowMapper());
	}

	// 한개 가져오기
	public Article getArticleByIdx(int idx) {

		String sql = String.format("SELECT * FROM article WHERE idx = %d", idx);

		return cdb.getOne(sql, new ArticleRowMapper());
	}

	// 게시글 등록하기
	public void insertArticle(String title, String body, String nickname) {

		String sql = String.format(
				"INSERT INTO article SET regDate=NOW(), `title` = '%s', `body` = '%s', nickname = '%s'", title, body,
				nickname);

		cdb.updateQuery(sql);
	}

	// 게시글 수정하기
	public void updateArticle(int idx, String title, String body) {

		String sql = String.format("UPDATE article SET `title` = '%s', `body` = '%s' WHERE idx=%d", title, body, idx);

		cdb.updateQuery(sql);
	}

	// 게시글 삭제하기
	public void deleteArticle(int idx) {

		String sql = String.format("DELETE FROM article WHERE idx=%d", idx);

		cdb.updateQuery(sql);
	}
}
