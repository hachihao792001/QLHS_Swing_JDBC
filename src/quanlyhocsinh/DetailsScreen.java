package quanlyhocsinh;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;

@SuppressWarnings("serial")
public class DetailsScreen extends JFrame {
	DetailsScreen(HocSinh selectedHocSinh) {
		JPanel detailsContent = new JPanel(new GridBagLayout());

		Image scaledHSImage = null;
		try {
			BufferedImage hsImage = ImageIO.read(new File(selectedHocSinh.hinhAnh));
			scaledHSImage = hsImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		} catch (Exception io) {
		}

		JLabel hsImageLabel;
		if (scaledHSImage != null)
			hsImageLabel = new JLabel(new ImageIcon(scaledHSImage));
		else
			hsImageLabel = new JLabel("Không có hình", SwingConstants.CENTER);
		hsImageLabel.setPreferredSize(new Dimension(100, 100));

		hsImageLabel.setBorder(BorderFactory.createLineBorder(Color.black));

		GridBagConstraints c = MainScreen.GridBagPos(0, 0, 1, 1);
		c.insets = new Insets(10, 10, 0, 10);
		detailsContent.add(hsImageLabel, c);

		JPanel panel1 = new JPanel();
		GridLayout gridLayout = new GridLayout();
		gridLayout.setColumns(1);
		gridLayout.setRows(3);
		gridLayout.setVgap(5);
		panel1.setLayout(gridLayout);
		JLabel mhsLabel = new JLabel("Mã học sinh: " + selectedHocSinh.maHocSinh);
		panel1.add(mhsLabel);
		JLabel tenLabel = new JLabel("Họ tên: " + selectedHocSinh.tenHocSinh);
		panel1.add(tenLabel);
		JLabel diemLabel = new JLabel("Điểm: " + selectedHocSinh.diem);
		panel1.add(diemLabel);
		c = MainScreen.GridBagPos(1, 0, 1, 1);
		c.insets = new Insets(10, 10, 0, 10);
		detailsContent.add(panel1, c);

		JLabel diaChiLabel = new JLabel("Địa chỉ: " + selectedHocSinh.diaChi);
		c = MainScreen.GridBagPos(0, 2, 2, 1);
		c.insets = new Insets(15, 10, 5, 10);
		c.anchor = GridBagConstraints.WEST;
		detailsContent.add(diaChiLabel, c);
		JLabel ghiChuLabel = new JLabel("Ghi chú: " + selectedHocSinh.ghiChu);
		c = MainScreen.GridBagPos(0, 3, 2, 1);
		c.insets = new Insets(0, 10, 10, 10);
		c.anchor = GridBagConstraints.WEST;
		detailsContent.add(ghiChuLabel, c);

		this.setTitle("Thông tin học sinh");
		this.setContentPane(detailsContent);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}
}
