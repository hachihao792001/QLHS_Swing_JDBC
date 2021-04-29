package quanlyhocsinh;

import java.io.*;
import java.util.ArrayList;

public class FileManager {

	public static boolean fileExist(String fileName) {
		return (new File(fileName)).exists();
	}

	public static DanhSachHocSinh importFromCSV(String fileName) throws IOException {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(new File(fileName)));
		} catch (FileNotFoundException e) {
			return null;
		}

		DanhSachHocSinh dshs = new DanhSachHocSinh();
		dshs.danhSach = new ArrayList<HocSinh>();
		String line = reader.readLine();
		try {
			while (line != null && !line.isEmpty()) {
				dshs.danhSach.add(new HocSinh(line));
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
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(fileName)));
		writer.write(dshs.toString());
		writer.close();
	}
}
