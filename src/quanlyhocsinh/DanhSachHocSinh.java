package quanlyhocsinh;

import java.util.*;

public class DanhSachHocSinh {
	List<HocSinh> danhSach;

	public DanhSachHocSinh() {
		danhSach = new ArrayList<HocSinh>();
	}

	void merge(DanhSachHocSinh otherDSHS) {
		for (HocSinh hs : otherDSHS.danhSach) {
			if (findHocSinh(hs.maHocSinh) == null)
				danhSach.add(hs);
		}
	}

	Object[][] toObjectMatrix() {
		Object[][] data = new Object[danhSach.size()][6];
		for (int i = 0; i < data.length; i++) {
			data[i][0] = danhSach.get(i).maHocSinh;
			data[i][1] = danhSach.get(i).tenHocSinh;
			data[i][2] = danhSach.get(i).diem;
			data[i][3] = danhSach.get(i).hinhAnh;
			data[i][4] = danhSach.get(i).diaChi;
			data[i][5] = danhSach.get(i).ghiChu;
		}

		return data;
	}

	HocSinh findHocSinh(String mhs) {
		for (int i = 0; i < danhSach.size(); i++)
			if (danhSach.get(i).maHocSinh.compareTo(mhs) == 0)
				return danhSach.get(i);
		return null;
	}

	void updateHocSinh(HocSinh newHS) {
		int i;
		for (i = 0; i < danhSach.size(); i++)
			if (danhSach.get(i).maHocSinh.compareTo(newHS.maHocSinh) == 0)
				break;

		danhSach.set(i, newHS);
	}

	void removeHocSinh(String mhs) {
		danhSach.removeIf(x -> x.maHocSinh.compareTo(mhs) == 0);
	}

	void sapXepHocSinhTheoMHS(boolean tangDan) {
		danhSach.sort(new Comparator<HocSinh>() {
			public int compare(HocSinh c1, HocSinh c2) {
				return tangDan ? c1.maHocSinh.compareTo(c2.maHocSinh) : c2.maHocSinh.compareTo(c1.maHocSinh);
			}
		});
	}

	void sapXepHocSinhTheoDiem(boolean tangDan) {
		danhSach.sort(new Comparator<HocSinh>() {
			public int compare(HocSinh c1, HocSinh c2) {
				if (c1.diem < c2.diem)
					return tangDan ? -1 : 1;
				if (c1.diem > c2.diem)
					return tangDan ? 1 : -1;
				return 0;
			}
		});
	}

	public String toString() {
		String result = "";
		for (HocSinh hs : danhSach)
			result += hs.toString() + "\n";
		return result;
	}
}
