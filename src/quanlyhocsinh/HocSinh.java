package quanlyhocsinh;

public class HocSinh {
	public String maHocSinh;
	public String tenHocSinh;
	public float diem;
	public String hinhAnh;
	public String diaChi, ghiChu;

	public HocSinh() {
	}

	public HocSinh(String maHS, String ten, float diem, String duongDanHinh, String diaChi, String ghiChu) {
		maHocSinh = maHS;
		tenHocSinh = ten;
		this.diem = diem;
		this.hinhAnh = duongDanHinh;
		this.diaChi = diaChi;
		this.ghiChu = ghiChu;
	}

	public HocSinh(String line) {
		String[] hsData = line.split(",");
		for (int i = 0; i < hsData.length; i++)
			hsData[i] = hsData[i].trim();

		this.maHocSinh = hsData[0];
		this.tenHocSinh = hsData[1];
		this.diem = Float.parseFloat(hsData[2]);
		this.hinhAnh = hsData[3];
		this.diaChi = hsData[4];
		this.ghiChu = hsData[5];
	}

	public String toString() {
		return this.maHocSinh + ", " + this.tenHocSinh + ", " + this.diem + ", " + this.hinhAnh + ", " + this.diaChi
				+ ", " + this.ghiChu;
	}
}
