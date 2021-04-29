package quanlyhocsinh;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

@SuppressWarnings("serial")
public class MainScreen extends JFrame implements ActionListener {

	static String[] columnNames = { "Mã học sinh", "Tên học sinh", "Điểm", "Hình ảnh", "Địa chỉ", "Ghi chú" };
	static JTable dshsTable;

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

		dshsTable = new JTable(Main.danhSachHocSinh.toObjectMatrix(), columnNames);
		dshsTable.setFillsViewportHeight(true);
		dshsTable.setRowHeight(30);
		dshsTable.getDefaultEditor(Object.class).addCellEditorListener(new CellEditorListener() {
			@Override
			public void editingStopped(ChangeEvent e) {
				int selectedRow = dshsTable.getSelectedRow();
				int selectedColumn = dshsTable.getSelectedColumn();
				String oldMHS = Main.danhSachHocSinh.danhSach.get(selectedRow).maHocSinh;
				HocSinh editedHocSinh = Main.danhSachHocSinh.findHocSinh(oldMHS);

				Object editedData = dshsTable.getValueAt(selectedRow, selectedColumn);
				switch (selectedColumn) {
				case 0:
					if (!editedData.toString().equals(oldMHS)) {
						if (Main.danhSachHocSinh.findHocSinh(editedData.toString()) == null)
							editedHocSinh.maHocSinh = editedData.toString();
						else {
							JOptionPane.showMessageDialog(mainContent, "Mã học sinh đã tồn tại", "Không thể cập nhật",
									JOptionPane.WARNING_MESSAGE, null);
							UpdateDSHSTable();
						}
					}
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

				DatabaseManager.updateHocSinh(oldMHS, editedHocSinh, selectedColumn);
			}

			@Override
			public void editingCanceled(ChangeEvent e) {
			}
		});

		c = GridBagPos(0, 0, 1, 2);
		c.insets = new Insets(20, 15, 0, 0);
		mainContent.add(new JScrollPane(dshsTable), c);

		// ****************** BUTTONS ******************
		JPanel buttonsPanel = new JPanel(new FlowLayout());

		JButton detailsButton;
		detailsButton = new JButton("Thông tin chi tiết");
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
		JPanel sortDSHSPanel = new JPanel(new GridBagLayout());
		JLabel sortDSHSLabel = new JLabel("Sắp xếp danh sách học sinh");

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

		JButton sortButton = new JButton("Sắp xếp");
		sortButton.setActionCommand("sort");
		sortButton.addActionListener(this);

		c = GridBagPos(0, 0, 3, 1);
		c.insets = new Insets(0, 0, 10, 0);
		sortDSHSPanel.add(sortDSHSLabel, c);

		sortDSHSPanel.add(xepTheoLabel, GridBagPos(0, 1, 1, 1));
		c = GridBagPos(1, 1, 1, 1);
		c.insets = new Insets(0, 5, 0, 0);
		c.anchor = GridBagConstraints.WEST;
		sortDSHSPanel.add(mhsRB, c);
		sortDSHSPanel.add(diemRB, GridBagPos(2, 1, 1, 1));

		sortDSHSPanel.add(thuTuLabel, GridBagPos(0, 2, 1, 1));
		c = GridBagPos(1, 2, 1, 1);
		c.insets = new Insets(0, 5, 0, 0);
		c.anchor = GridBagConstraints.WEST;
		sortDSHSPanel.add(tangDanRB, c);
		sortDSHSPanel.add(giamDanRB, GridBagPos(2, 2, 1, 1));

		c = GridBagPos(0, 3, 3, 1);
		c.insets = new Insets(10, 0, 0, 0);
		sortDSHSPanel.add(sortButton, c);
		sortDSHSPanel.setBorder(BorderFactory.createLineBorder(Color.black));

		sidePanel.add(sortDSHSPanel);

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

		this.setTitle("Quản lý học sinh");
		this.setContentPane(mainContent);
		this.setResizable(false);
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
					DatabaseManager.removeHocSinh(selectedHocSinh.maHocSinh);
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
				String[] options = { "Thêm", "Ghi đè" };
				int choice = JOptionPane.showOptionDialog(this,
						"Bạn muốn thêm các học sinh vào danh sách có sẵn hoặc ghi đè thành danh sách mới?",
						"Thêm hay ghi đè", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
						options[0]);

				String fileName = jfc.getSelectedFile().getAbsolutePath();
				try {
					if (choice == 0)
						Main.danhSachHocSinh.merge(FileManager.importFromCSV(fileName));
					else
						Main.danhSachHocSinh = FileManager.importFromCSV(fileName);

					UpdateDSHSTable();
					dshsTable.setRowSelectionInterval(0, 0);
					DatabaseManager.setDSHS(Main.danhSachHocSinh);
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
		TableModel model = new DefaultTableModel(Main.danhSachHocSinh.toObjectMatrix(), columnNames);
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
