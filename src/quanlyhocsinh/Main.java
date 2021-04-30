package quanlyhocsinh;

import java.io.IOException;
import javax.swing.*;

public class Main {
	static void createAndShowUI() throws IOException {
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		new MainScreen();
	}

	static DanhSachHocSinh danhSachHocSinh = new DanhSachHocSinh();

	public static void main(String[] args) throws IOException {
		DatabaseManager.init();
		danhSachHocSinh = DatabaseManager.getDSHS();

		createAndShowUI();

	}
}