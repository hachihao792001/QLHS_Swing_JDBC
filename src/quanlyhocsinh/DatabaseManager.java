package quanlyhocsinh;

import java.sql.*;

public class DatabaseManager {
	public DatabaseManager() {

		Connection con = null;

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(
					"jdbc:sqlserver://localhost:1433;databaseName=QLTGDeTai;user=hao;password=password1");

			PreparedStatement st = con.prepareStatement("select * from GIAOVIEN");

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int maGV = rs.getInt("MAGV");
				String hoTen = rs.getNString("HOTEN");
				int luong = rs.getInt("LUONG");
				String phai = rs.getString("PHAI");
				String ngSinh = rs.getString("NGSINH");
				String diaChi = rs.getNString("DIACHI");
				String GVQLCM = rs.getString("GVQLCM");
				String maBM = rs.getString("MABM");

				System.out.println(maGV + " | " + hoTen + " | " + luong + " | " + phai + " | " + ngSinh + " | " + diaChi
						+ " | " + GVQLCM + " | " + maBM);

			}

			con.close();
		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
		}

	}

}
