package edu.java.contact06;

// JDBC���� ���� ��� �� SQL�� ����
public interface OracleQuery {
	// ��� ����
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	public static final String USER = "scott";
	public static final String PASSWORD = "tiger";
	
	public static final String TABLE_NAME = "EX_CONTACT";
	public static final String COL_CID = "CID";
	public static final String COL_NAME = "NAME";
	public static final String COL_PHONE = "PHONE";
	public static final String COL_EMAIL = "EMAIL";
	
	// SELECT - ORDER BY CID
	public static final String SQL_ORDER_BY_CID =
			"SELECT * FROM " + TABLE_NAME + " ORDER BY CID";
	
	// SELECT - CID
	public static final String SQL_SELECT_BY_CID =
			"SELECT * FROM " + TABLE_NAME + " WHERE " + COL_CID + " = ?";
	
	// INSERT
	public static final String SQL_INSERT = 
			"INSERT INTO " + TABLE_NAME + " VALUES (CONTACT_PK.NEXTVAL, ?, ?, ?)";
	
	// UPDATE
	public static final String SQL_UPDATE =
			"UPDATE " + TABLE_NAME +
			" SET " + COL_NAME + " = ?, " 
					+ COL_PHONE + " = ?, "
					+ COL_EMAIL + " = ? " +
			" WHERE " + COL_CID + " = ?";
	
	// DELETE
	public static final String SQL_DELETE = 
			"DELETE FROM " + TABLE_NAME + " WHERE " + COL_CID + " = ?";
}
