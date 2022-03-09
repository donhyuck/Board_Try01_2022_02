package com.don.board.model.article;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T> {

	// 구체적인 기능은 Rowmapper를 표방한 클래스에서 처리한다.
	public T getRow(ResultSet rs) throws SQLException;

}
