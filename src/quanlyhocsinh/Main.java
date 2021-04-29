package quanlyhocsinh;

import java.io.IOException;
import javax.swing.*;

public class Main {
	static void createAndShowUI() throws IOException {
		JFrame.setDefaultLookAndFeelDecorated(true);
		new MainScreen();
	}

	static DanhSachHocSinh danhSachHocSinh = new DanhSachHocSinh();

	public static void main(String[] args) throws IOException {
		DatabaseManager.init();
		danhSachHocSinh = DatabaseManager.getDSHS();

		createAndShowUI();

	}
}