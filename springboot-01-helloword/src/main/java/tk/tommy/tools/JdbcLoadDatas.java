package tk.tommy.tools;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcLoadDatas {

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "oracle.jdbc.OracleDriver";
	static final String DB_URL = "jdbc:oracle:thin:@192.168.0.23:1521:zvo11g01";

	// Database credentials
	static final String USER = "mypaycenter";
	static final String PASS = "myPay4Zv";

	public static List<CustDbInfo> getCustsInfo() {
		List<CustDbInfo> custDbInfos = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName(JDBC_DRIVER);

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT * FROM PY_CUST";
			ResultSet rs = stmt.executeQuery(sql);

			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name

				CustDbInfo c = new CustDbInfo();
				c.setId(rs.getString("ID"));
				c.setDbHost(rs.getString("DB_HOST"));
				c.setDbName(rs.getString("DB_NAME"));
				c.setDbPwd(rs.getString("DB_PWD"));
				c.setDbUser(rs.getString("DB_USER"));
				c.setName(rs.getString("NAME"));
				c.setStatus(rs.getString("STATUS"));
				// Display values
				custDbInfos.add(c);
			}
			// STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try

		return custDbInfos;
	}// end main
}