package com.don.board.model.article;

import java.sql.ResultSet;
import java.sql.SQLException;

// RowMapper를 표방함을 나타내며, getRow기능을 특정한다.
// ArticleRowMapper는 RowMapper의 일원 중 하나로써, getRow기능을 수행할 수 있다.
public class ArticleRowMapper implements RowMapper<Article> {

	public Article getRow(ResultSet rs) throws SQLException {

		int idx = rs.getInt("idx");
		String regDate = rs.getString("regDate");
		String title = rs.getString("title");
		String body = rs.getString("body");
		String nickname = rs.getString("nickname");

		Article article = new Article(idx, regDate, title, body, nickname);

		return article;
	}

}
