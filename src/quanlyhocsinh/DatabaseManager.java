package quanlyhocsinh;

import java.sql.*;

public class DatabaseManager {
	static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	static final String DB_URL = "jdbc:sqlserver://localhost:1433;integratedSecurity=true";

	static Connection con = null;

	public static void setDSHS(DanhSachHocSinh dshs) {
		PreparedStatement st;
		try {
			st = con.prepareStatement("DELETE FROM HOCSINH");
			st.execute();

			for (HocSinh hs : dshs.getDanhSach())
				addHocSinh(hs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static DanhSachHocSinh getDSHS() {
		DanhSachHocSinh dshs = new DanhSachHocSinh();
		try {
			PreparedStatement st = con.prepareStatement("SELECT * FROM HOCSINH");
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				HocSinh hs = new HocSinh();

				hs.maHocSinh = rs.getString("MAHS");
				hs.tenHocSinh = rs.getNString("TENHS");
				hs.diem = rs.getFloat("DIEM");
				hs.hinhAnh = rs.getNString("HINHANH");
				hs.diaChi = rs.getNString("DIACHI");
				hs.ghiChu = rs.getNString("GHICHU");

				dshs.addHocSinh(hs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dshs;
	}

	public static void addHocSinh(HocSinh hs) {
		PreparedStatement st;
		try {
			String addStatement = "INSERT INTO HOCSINH " + "(MAHS, TENHS, DIEM, HINHANH, DIACHI, GHICHU) " + "VALUES (";
			addStatement += "'" + hs.maHocSinh + "', ";
			addStatement += "N'" + hs.tenHocSinh + "', ";
			addStatement += hs.diem + ", ";
			addStatement += "N'" + hs.hinhAnh + "', ";
			addStatement += "N'" + hs.diaChi + "', ";
			addStatement += "N'" + hs.ghiChu + "')";

			st = con.prepareStatement(addStatement);
			st.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void updateHocSinh(String mhs, HocSinh hs, int column) {
		PreparedStatement st;
		try {
			String updateStatement = "UPDATE HOCSINH SET ";
			switch (column) {
			case 0:
				updateStatement += "MAHS = '" + hs.maHocSinh + "'";
				break;
			case 1:
				updateStatement += "TENHS = N'" + hs.tenHocSinh + "'";
				break;
			case 2:
				updateStatement += "DIEM = " + hs.diem;
				break;
			case 3:
				updateStatement += "HINHANH = N'" + hs.hinhAnh + "'";
				break;
			case 4:
				updateStatement += "DIACHI = N'" + hs.diaChi + "'";
				break;
			case 5:
				updateStatement += "GHICHU = N'" + hs.ghiChu + "'";
				break;
			}
			updateStatement += " WHERE MAHS = '" + mhs + "'";

			st = con.prepareStatement(updateStatement);
			st.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void removeHocSinh(String mhs) {
		PreparedStatement st;
		try {
			st = con.prepareStatement("DELETE FROM HOCSINH WHERE MAHS = '" + mhs + "'");
			st.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	static boolean checkDBExists(String dbName) {

		try {
			ResultSet resultSet = con.getMetaData().getCatalogs();

			while (resultSet.next()) {
				String databaseName = resultSet.getString(1);
				if (databaseName.equals(dbName))
					return true;
			}
			resultSet.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	static boolean checkTableExists(String tableName) {

		try {
			ResultSet resultSet = con.getMetaData().getTables(null, null, tableName, null);
			return resultSet.next();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public static void init() {
		try {
			Class.forName(JDBC_DRIVER);
			con = DriverManager.getConnection(DB_URL);

			PreparedStatement st;
			if (!checkDBExists("QLHS")) {
				st = con.prepareStatement("CREATE DATABASE QLHS");
				st.execute();
			}
			st = con.prepareStatement("USE QLHS");
			st.execute();

			if (!checkTableExists("HOCSINH")) {
				st = con.prepareStatement("CREATE TABLE HOCSINH(\r\n" + "	MAHS VARCHAR(10),\r\n"
						+ "	TENHS NVARCHAR(30),\r\n" + "	DIEM FLOAT,\r\n" + "	HINHANH NVARCHAR(50),\r\n"
						+ "	DIACHI NVARCHAR(50),\r\n" + "	GHICHU NVARCHAR(50)\r\n" + ")");
				st.execute();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	protected void finalize() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
