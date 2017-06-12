package bd;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDAO<T> {
	private final String table;
	protected final Connection connection;

	public BaseDAO(String table) {
		this.connection = new ConnectionBD().getConnection();
		this.table = table;
	}
	
	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected final String createSimpleSqlInsert(String[] columns) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO ").append(table).append(" (");
		int length = columns.length;
		for (int i = 0; i < length - 1; i++) {
			sql.append(columns[i]).append(", ");
		}
		sql.append(columns[length - 1]).append(" ) ");
		sql.append("VALUES").append(" (");
		for (int i = 0; i < length - 1; i++) {
			sql.append("?, ");
		}
		sql.append("?);");
		return sql.toString();
	}

	protected final String createSimpleSqlRead(String columnId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM ").append(table).append(" WHERE ").append(columnId).append(" = ?;");
		return sql.toString();
	}

	protected final String createSimpleSqlUpdate(String[] columns, String columnPK) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE ").append(table).append(" SET ");
		int length = columns.length;
		for (int i = 0; i < length - 1; i++) {
			sql.append(columns[i]).append(" = ?, ");
		}
		sql.append(columns[length - 1]).append(" = ? ");
		sql.append("WHERE ").append(columnPK).append(" = ?;");
		return sql.toString();
	}
	
	protected final String createSimpleSqlDelete(String columnPK) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM ").append(table).append(" WHERE ").append(columnPK).append(" = ?;");
		return sql.toString();
	}

	protected final String createSimpleSqlGetAll() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM ").append(table).append(";");
		return sql.toString();
	}

	protected final String appendPercentBeforeAndAfter(String word) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("%").append(word).append("%");
		return stringBuilder.toString();
	}
}