package quanlyhocsinh;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class AddScreen extends JDialog {
	AddScreen() {
		JPanel addContent = new JPanel();
		addContent.setLayout(new BoxLayout(addContent, BoxLayout.Y_AXIS));

		JPanel mhsLine = createInfoLine("Mã học sinh");
		JPanel tenLine = createInfoLine("Tên học sinh");
		JPanel diemLine = createInfoLine("Điểm");
		JPanel hinhAnhLine = createInfoLine("Hình ảnh");
		JPanel diaChiLine = createInfoLine("Địa chỉ");
		JPanel ghiChuLine = createInfoLine("Ghi chú");

		JButton addButton = new JButton("Thêm");
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				HocSinh newHocSinh = new HocSinh();
				newHocSinh.maHocSinh = ((JTextField) mhsLine.getComponent(1)).getText();
				if (!newHocSinh.maHocSinh.isEmpty()) {
					if (Main.danhSachHocSinh.findHocSinh(newHocSinh.maHocSinh) != null) {
						JOptionPane.showMessageDialog(addButton, "Mã học sinh đã tồn tại", "Không thể thêm",
								JOptionPane.WARNING_MESSAGE, null);
					} else {
						newHocSinh.tenHocSinh = ((JTextField) tenLine.getComponent(1)).getText();
						try {

							newHocSinh.diem = Float.parseFloat(((JTextField) diemLine.getComponent(1)).getText());
							newHocSinh.hinhAnh = ((JTextField) hinhAnhLine.getComponent(1)).getText();
							newHocSinh.diaChi = ((JTextField) diaChiLine.getComponent(1)).getText();
							newHocSinh.ghiChu = ((JTextField) ghiChuLine.getComponent(1)).getText();

							Main.danhSachHocSinh.addHocSinh(newHocSinh);
							MainScreen.UpdateDSHSTable();
							DatabaseManager.addHocSinh(newHocSinh);

							JOptionPane.showMessageDialog(addContent, "Thêm học sinh thành công!", "Thông báo",
									JOptionPane.INFORMATION_MESSAGE);
							dispose();

						} catch (NumberFormatException n) {
							JOptionPane.showMessageDialog(addContent, "Điểm phải là 1 số thập phân", "Không thể thêm",
									JOptionPane.WARNING_MESSAGE, null);
						}

					}
				}
			}
		});

		addContent.add(mhsLine);
		addContent.add(tenLine);
		addContent.add(diemLine);
		addContent.add(hinhAnhLine);
		addContent.add(diaChiLine);
		addContent.add(ghiChuLine);
		addContent.add(addButton);

		this.setTitle("Thêm học sinh");
		this.setContentPane(addContent);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setModalityType(DEFAULT_MODALITY_TYPE);
		this.pack();
		this.setVisible(true);
	}

	JPanel createInfoLine(String info) {
		JPanel infoLine = new JPanel(new FlowLayout());
		JLabel infoLabel = new JLabel(info);
		infoLabel.setPreferredSize(new Dimension(100, 20));
		JTextField infoTF = new JTextField();
		infoTF.setPreferredSize(new Dimension(300, 20));

		infoLine.add(infoLabel);
		infoLine.add(infoTF);
		return infoLine;
	}

}
