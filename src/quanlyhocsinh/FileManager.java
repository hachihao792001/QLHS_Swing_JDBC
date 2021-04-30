package quanlyhocsinh;

import java.io.*;

public class FileManager {

	public static boolean fileExist(String fileName) {
		return (new File(fileName)).exists();
	}

	public static DanhSachHocSinh importFromCSV(String fileName) throws IOException {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));
		} catch (FileNotFoundException e) {
			return null;
		}

		DanhSachHocSinh dshs = new DanhSachHocSinh();
		String line = reader.readLine();
		try {
			while (line != null && !line.isEmpty()) {
				dshs.addHocSinh(new HocSinh(line));
				line = reader.readLine();
			}
		} catch (Exception num) {
			reader.close();
			return null;
		}
		reader.close();
		return dshs;
	}

	public static void exportToCSV(String fileName, DanhSachHocSinh dshs) throws IOException {
		BufferedWriter writer = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(new File(fileName)), "UTF-8"));
		writer.write(dshs.toString());
		writer.close();
	}
}
