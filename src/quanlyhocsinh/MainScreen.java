package quanlyhocsinh;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class MainScreen extends JFrame implements ActionListener {

	static String[] columnNames = { "Mã học sinh", "Tên học sinh", "Điểm", "Hình ảnh", "Địa chỉ", "Ghi chú" };
	static JTable dshsTable;
	JScrollPane dshsScrollPane;

	JRadioButton tangDanRB;
	JRadioButton giamDanRB;
	JRadioButton mhsRB;
	JRadioButton diemRB;

	public MainScreen() throws IOException {
		JPanel mainContent = new JPanel(new GridBagLayout());
		GridBagConstraints c;

		/*
		 * Object[][] data = { { 19120219, "Hà Chí Hào", 8.55, "hao.jpg", "địa chỉ 1",
		 * "ghi chú 1" }, { 123, "Nguyễn Văn A", 7.6, "a.png", "địa chỉ 2", "ghi chú 2"
		 * }, { 453, "Trần Thị B", 8.9, "b.jpg", "địa chỉ 3", "ghi chú 3" }, { 159,
		 * "Nguyễn Anh C", 9.9, "c.png", "địa chỉ 4", "ghi chú 4" }, { 222, "Lê Mỹ D",
		 * 9.6, "d.png", "địa chỉ 5", "ghi chú 5" } };
		 */

		Object[][] data = new Object[0][6];
		dshsTable = new JTable(data, columnNames);
		dshsTable.setFillsViewportHeight(true);
		dshsTable.setRowHeight(30);
		dshsTable.getDefaultEditor(Object.class).addCellEditorListener(new CellEditorListener() {
			@Override
			public void editingStopped(ChangeEvent e) {
				int selectedRow = dshsTable.getSelectedRow();
				int selectedColumn = dshsTable.getSelectedColumn();
				HocSinh editedHocSinh = Main.danhSachHocSinh
						.findHocSinh(dshsTable.getValueAt(selectedRow, 0).toString());

				Object editedData = dshsTable.getValueAt(selectedRow, selectedColumn);
				switch (selectedColumn) {
				case 0:
					editedHocSinh.maHocSinh = editedData.toString();
					break;
				case 1:
					editedHocSinh.tenHocSinh = editedData.toString();
					break;
				case 2:
					editedHocSinh.diem = Float.parseFloat(editedData.toString());
					break;
				case 3:
					editedHocSinh.hinhAnh = editedData.toString();
					break;
				case 4:
					editedHocSinh.diaChi = editedData.toString();
					break;
				case 5:
					editedHocSinh.ghiChu = editedData.toString();
					break;
				}
			}

			@Override
			public void editingCanceled(ChangeEvent e) {
			}
		});

		dshsScrollPane = new JScrollPane(dshsTable);
		c = GridBagPos(0, 0, 1, 2);
		c.insets = new Insets(20, 15, 0, 0);
		mainContent.add(dshsScrollPane, c);

		// ****************** BUTTONS ******************
		JPanel buttonsPanel = new JPanel(new FlowLayout());

		JButton detailsButton;
		detailsButton = new JButton("Chi tiết học sinh");
		detailsButton.setActionCommand("details");
		detailsButton.addActionListener(this);
		buttonsPanel.add(detailsButton);

		JButton deleteButton;
		deleteButton = new JButton("Xoá học sinh");
		deleteButton.setActionCommand("delete");
		deleteButton.addActionListener(this);
		buttonsPanel.add(deleteButton);

		JButton addButton;
		addButton = new JButton("Thêm học sinh");
		addButton.setActionCommand("add");
		addButton.addActionListener(this);
		buttonsPanel.add(addButton);

		c = GridBagPos(0, 2, 1, 1);
		c.anchor = GridBagConstraints.CENTER;
		mainContent.add(buttonsPanel, c);

		// -----------SIDE PANEL -------------------------------------------------------
		GridLayout sidePanelGrid = new GridLayout();
		sidePanelGrid.setColumns(1);
		sidePanelGrid.setRows(2);
		sidePanelGrid.setVgap(10);
		JPanel sidePanel = new JPanel(sidePanelGrid);

		// **************** SHOWLIST PANEL *****************
		JPanel showDSHSPanel = new JPanel(new GridBagLayout());
		JLabel xemDSHSLabel = new JLabel("Sắp xếp danh sách học sinh");

		JLabel thuTuLabel = new JLabel("Thứ tự");
		tangDanRB = new JRadioButton("Tăng dần");
		giamDanRB = new JRadioButton("Giảm dần");
		ButtonGroup thuTuGroup = new ButtonGroup();
		thuTuGroup.add(tangDanRB);
		thuTuGroup.add(giamDanRB);
		thuTuGroup.setSelected(tangDanRB.getModel(), true);

		JLabel xepTheoLabel = new JLabel("Xếp theo");
		mhsRB = new JRadioButton("Mã học sinh");
		diemRB = new JRadioButton("Điểm");
		ButtonGroup xepTheoGroup = new ButtonGroup();
		xepTheoGroup.add(mhsRB);
		xepTheoGroup.add(diemRB);
		xepTheoGroup.setSelected(mhsRB.getModel(), true);

		JButton xemButton = new JButton("Sắp xếp");
		xemButton.setActionCommand("sort");
		xemButton.addActionListener(this);

		c = GridBagPos(0, 0, 3, 1);
		c.insets = new Insets(0, 0, 10, 0);
		showDSHSPanel.add(xemDSHSLabel, c);
		showDSHSPanel.add(thuTuLabel, GridBagPos(0, 1, 1, 1));
		c = GridBagPos(1, 1, 1, 1);
		c.insets = new Insets(0, 5, 0, 0);
		c.anchor = GridBagConstraints.WEST;
		showDSHSPanel.add(tangDanRB, c);
		showDSHSPanel.add(giamDanRB, GridBagPos(2, 1, 1, 1));

		showDSHSPanel.add(xepTheoLabel, GridBagPos(0, 2, 1, 1));
		c = GridBagPos(1, 2, 1, 1);
		c.insets = new Insets(0, 5, 0, 0);
		c.anchor = GridBagConstraints.WEST;
		showDSHSPanel.add(mhsRB, c);
		showDSHSPanel.add(diemRB, GridBagPos(2, 2, 1, 1));
		c = GridBagPos(0, 3, 3, 1);
		c.insets = new Insets(10, 0, 0, 0);
		showDSHSPanel.add(xemButton, c);
		showDSHSPanel.setBorder(BorderFactory.createLineBorder(Color.black));

		sidePanel.add(showDSHSPanel);

		// ******************** CSV Panel ********************
		JPanel csvPanel = new JPanel();
		csvPanel.setLayout(new BoxLayout(csvPanel, BoxLayout.Y_AXIS));

		JLabel csvLabel = new JLabel("Import và export danh sách học sinh từ file csv");
		csvLabel.setAlignmentX(CENTER_ALIGNMENT);

		JButton importButton = new JButton("Import");
		importButton.setAlignmentX(CENTER_ALIGNMENT);
		importButton.setActionCommand("import");
		importButton.addActionListener(this);

		JButton exportButton = new JButton("Export");
		exportButton.setAlignmentX(CENTER_ALIGNMENT);
		exportButton.setActionCommand("export");
		exportButton.addActionListener(this);

		csvPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		csvPanel.add(csvLabel);
		csvPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		csvPanel.add(importButton);
		csvPanel.add(Box.createRigidArea(new Dimension(0, 3)));
		csvPanel.add(exportButton);
		csvPanel.setBorder(BorderFactory.createLineBorder(Color.black));

		sidePanel.add(csvPanel);

		c = GridBagPos(1, 0, 1, 1);
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(20, 15, 0, 15);
		mainContent.add(sidePanel, c);

		this.setTitle("Thông tin");
		this.setContentPane(mainContent);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "details": {
			if (dshsTable.getSelectedRow() != -1) {
				HocSinh selectedHocSinh = Main.danhSachHocSinh
						.findHocSinh(dshsTable.getValueAt(dshsTable.getSelectedRow(), 0).toString());

				new DetailsScreen(selectedHocSinh);
			}
			break;
		}
		case "delete": {
			if (dshsTable.getSelectedRow() != -1) {
				HocSinh selectedHocSinh = Main.danhSachHocSinh
						.findHocSinh(dshsTable.getValueAt(dshsTable.getSelectedRow(), 0).toString());

				int confirmDelete = JOptionPane.showConfirmDialog(this,
						"Bạn có chắc muốn xoá học sinh " + selectedHocSinh.tenHocSinh + "?");
				if (confirmDelete == JOptionPane.YES_OPTION) {
					Main.danhSachHocSinh.removeHocSinh(selectedHocSinh.maHocSinh);
					UpdateDSHSTable();
				}
			}
			break;
		}
		case "add": {
			new AddScreen();
			break;
		}
		case "sort": {
			if (mhsRB.isSelected())
				Main.danhSachHocSinh.sapXepHocSinhTheoMHS(tangDanRB.isSelected());
			else
				Main.danhSachHocSinh.sapXepHocSinhTheoDiem(tangDanRB.isSelected());

			UpdateDSHSTable();
			break;
		}
		case "import": {
			JFileChooser jfc = new JFileChooser();
			jfc.setDialogTitle("Import danh sách học sinh từ file csv");
			jfc.showDialog(null, "Import");
			jfc.setVisible(true);

			if (jfc.getSelectedFile() != null) {
				String fileName = jfc.getSelectedFile().getAbsolutePath();
				try {
					Main.danhSachHocSinh = FileManager.importFromCSV(fileName);
					UpdateDSHSTable();
					dshsTable.setRowSelectionInterval(0, 0);
				} catch (IOException e1) {

				}
			}
			break;
		}
		case "export": {
			JFileChooser jfc = new JFileChooser();
			jfc.setDialogTitle("Export danh sách học sinh ra 1 file csv");
			jfc.showSaveDialog(this);
			jfc.setVisible(true);

			if (jfc.getSelectedFile() != null) {
				String fileName = jfc.getSelectedFile().getAbsolutePath();
				try {
					FileManager.exportToCSV(fileName, Main.danhSachHocSinh);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			break;
		}
		}

	}

	public static void UpdateDSHSTable() {
		Object[][] data = new Object[Main.danhSachHocSinh.danhSach.size()][6];
		for (int i = 0; i < data.length; i++) {
			data[i][0] = Main.danhSachHocSinh.danhSach.get(i).maHocSinh;
			data[i][1] = Main.danhSachHocSinh.danhSach.get(i).tenHocSinh;
			data[i][2] = Main.danhSachHocSinh.danhSach.get(i).diem;
			data[i][3] = Main.danhSachHocSinh.danhSach.get(i).hinhAnh;
			data[i][4] = Main.danhSachHocSinh.danhSach.get(i).diaChi;
			data[i][5] = Main.danhSachHocSinh.danhSach.get(i).ghiChu;
		}

		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		dshsTable.setModel(model);
	}

	public static GridBagConstraints GridBagPos(int x, int y, int width, int height) {
		GridBagConstraints result = new GridBagConstraints();
		result.gridx = x;
		result.gridy = y;
		result.gridwidth = width;
		result.gridheight = height;
		return result;
	}
}
