package pharmacyApplication;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import pharmacyApplicationFactories.FactoryDAL;
import pharmacyApplicationFactories.FactoryPrescription;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class PrescriptionUI {

	public JFrame frame;
	private JTextField recDailyDoseText;
	private JTextField numberPrescriptions;
	private JTextField numberContainers;
	private JCheckBox exceedDailyDose;
	private JLabel label;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JButton addButton;
	private JButton removeButton;
	private JButton clearButton;
	private JButton exitButton;
	private JSpinner preDailyDose;
	private JSpinner duration;
	private JScrollPane scrollPane;
	private JTextArea description;
	private List<String> pharmaList;
	private InterfaceDAL dal;
	private int maxValue;
	private int containerSize;
	private boolean availableOverTheCounter;
	private JTable prescriptionTable;
	private JScrollPane scrollPane_1;
	private InterfacePrescription prescription;
	private JCheckBox addComment;
	private String comment;
	private JComboBox<String> pharmaceuticalCombo;
	private JPopupMenu popupMenu;
	private JMenuItem editComment;
	private JMenuItem decrementDosage;
	private JTextArea textArea;
	private JPanel pnl;

	/**
	 * Create the application.
	 * 
	 * @throws SQLException
	 */
	public PrescriptionUI(String connectionURL, String username, String password) throws SQLException {
		dal = FactoryDAL.create();
		dal.connect(connectionURL, username, password);
		pharmaList = dal.getPharmaName();
		prescription = FactoryPrescription.create();
		initializeUIElements();
		initializeUIFunctionality();
	}

	private void initializeUIFunctionality() {
		pharmaceuticalCombo.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				pharmaceuticalComboSelected(e);
			}
		});

		preDailyDose.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				checkDailyDose();
			}
		});

		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addItem();
			}
		});

		exceedDailyDose.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				canExceedDailyDose(e);
			}
		});

		removeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				removeItem();
			}
		});

		prescriptionTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				setRemoveButton();
			}
		});

		clearButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clearPrescription();
			}
		});

		exitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});

		editComment.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				editComment();
			}
		});

		decrementDosage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				decrementDosage();
			}
		});

		popupMenu.addPopupMenuListener(new PopupMenuListener() {

			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						highlightSelectedRow();
					}
				});
			}

			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {
				// TODO Auto-generated method stub

			}
		});

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initializeUIElements() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1045, 295);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 20, 93, 28, 86, 86, 86, 86, 0, 97 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 23, 20, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 20.0, 1.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 10.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		lblNewLabel = new JLabel("Pharmaceutical Name");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		panel.add(lblNewLabel, gbc_lblNewLabel);

		lblNewLabel_1 = new JLabel("Rec. Daily Dose");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 1;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);

		lblNewLabel_2 = new JLabel("Prescribed Daily Dose");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 3;
		gbc_lblNewLabel_2.gridy = 1;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);

		lblNewLabel_3 = new JLabel("Description & special details");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 4;
		gbc_lblNewLabel_3.gridy = 1;
		panel.add(lblNewLabel_3, gbc_lblNewLabel_3);

		lblNewLabel_4 = new JLabel("Duration");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 5;
		gbc_lblNewLabel_4.gridy = 1;
		panel.add(lblNewLabel_4, gbc_lblNewLabel_4);

		label = new JLabel("");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 3;
		gbc_label.gridy = 2;
		panel.add(label, gbc_label);

		pharmaceuticalCombo = new JComboBox<String>();
		GridBagConstraints gbc_pharmaceuticalCombo = new GridBagConstraints();
		gbc_pharmaceuticalCombo.fill = GridBagConstraints.HORIZONTAL;
		gbc_pharmaceuticalCombo.anchor = GridBagConstraints.NORTH;
		gbc_pharmaceuticalCombo.insets = new Insets(0, 0, 5, 5);
		gbc_pharmaceuticalCombo.gridx = 1;
		gbc_pharmaceuticalCombo.gridy = 3;
		for (String pharmaName : pharmaList) {
			pharmaceuticalCombo.addItem(pharmaName);
		}
		panel.add(pharmaceuticalCombo, gbc_pharmaceuticalCombo);

		recDailyDoseText = new JTextField();
		recDailyDoseText.setEditable(false);
		GridBagConstraints gbc_recDailyDoseText = new GridBagConstraints();
		gbc_recDailyDoseText.fill = GridBagConstraints.HORIZONTAL;
		gbc_recDailyDoseText.anchor = GridBagConstraints.NORTH;
		gbc_recDailyDoseText.insets = new Insets(0, 0, 5, 5);
		gbc_recDailyDoseText.gridx = 2;
		gbc_recDailyDoseText.gridy = 3;
		panel.add(recDailyDoseText, gbc_recDailyDoseText);
		recDailyDoseText.setColumns(10);

		preDailyDose = new JSpinner();
		preDailyDose.setModel(new SpinnerNumberModel(1, 1, null, 1));
		GridBagConstraints gbc_preDailyDose = new GridBagConstraints();
		gbc_preDailyDose.anchor = GridBagConstraints.NORTH;
		gbc_preDailyDose.fill = GridBagConstraints.HORIZONTAL;
		gbc_preDailyDose.insets = new Insets(0, 0, 5, 5);
		gbc_preDailyDose.gridx = 3;
		gbc_preDailyDose.gridy = 3;
		panel.add(preDailyDose, gbc_preDailyDose);

		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 4;
		gbc_scrollPane.gridy = 3;
		panel.add(scrollPane, gbc_scrollPane);

		description = new JTextArea();
		description.setEditable(false);
		description.setWrapStyleWord(true);
		description.setLineWrap(true);
		scrollPane.setViewportView(description);

		duration = new JSpinner();
		duration.setModel(new SpinnerNumberModel(1, 1, null, 1));
		GridBagConstraints gbc_duration = new GridBagConstraints();
		gbc_duration.anchor = GridBagConstraints.NORTH;
		gbc_duration.fill = GridBagConstraints.HORIZONTAL;
		gbc_duration.insets = new Insets(0, 0, 5, 5);
		gbc_duration.gridx = 5;
		gbc_duration.gridy = 3;
		panel.add(duration, gbc_duration);

		addComment = new JCheckBox("Add Comment");
		GridBagConstraints gbc_addComment = new GridBagConstraints();
		gbc_addComment.insets = new Insets(0, 0, 5, 5);
		gbc_addComment.anchor = GridBagConstraints.NORTHWEST;
		gbc_addComment.gridx = 6;
		gbc_addComment.gridy = 3;
		panel.add(addComment, gbc_addComment);

		addButton = new JButton("Add");
		GridBagConstraints gbc_addButton = new GridBagConstraints();
		gbc_addButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_addButton.insets = new Insets(0, 0, 5, 0);
		gbc_addButton.gridx = 7;
		gbc_addButton.gridy = 3;
		panel.add(addButton, gbc_addButton);

		exceedDailyDose = new JCheckBox("OK to exceed Daily Dose");
		GridBagConstraints gbc_exceedDailyDose = new GridBagConstraints();
		gbc_exceedDailyDose.insets = new Insets(0, 0, 5, 5);
		gbc_exceedDailyDose.gridx = 3;
		gbc_exceedDailyDose.gridy = 4;
		panel.add(exceedDailyDose, gbc_exceedDailyDose);

		removeButton = new JButton("Remove");
		removeButton.setEnabled(false);
		GridBagConstraints gbc_removeButton = new GridBagConstraints();
		gbc_removeButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_removeButton.anchor = GridBagConstraints.EAST;
		gbc_removeButton.insets = new Insets(0, 0, 5, 0);
		gbc_removeButton.gridx = 7;
		gbc_removeButton.gridy = 4;
		panel.add(removeButton, gbc_removeButton);

		scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridheight = 3;
		gbc_scrollPane_1.gridwidth = 6;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 1;
		gbc_scrollPane_1.gridy = 5;

		prescriptionTable = new JTable();
		prescriptionTable.setPreferredScrollableViewportSize(new Dimension(scrollPane_1.WIDTH, scrollPane_1.HEIGHT));
		prescriptionTable.setShowVerticalLines(false);
		prescriptionTable.setShowHorizontalLines(false);
		prescriptionTable.setFillsViewportHeight(true);

		prescriptionTable.setPreferredScrollableViewportSize(new Dimension(450, 1000));
		prescriptionTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		panel.add(scrollPane_1, gbc_scrollPane_1);
		scrollPane_1.setMinimumSize(scrollPane_1.getPreferredSize());
		scrollPane_1.setViewportView(prescriptionTable);

		clearButton = new JButton("Clear");
		clearButton.setEnabled(false);
		GridBagConstraints gbc_clearButton = new GridBagConstraints();
		gbc_clearButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_clearButton.insets = new Insets(0, 0, 5, 0);
		gbc_clearButton.gridx = 7;
		gbc_clearButton.gridy = 5;
		panel.add(clearButton, gbc_clearButton);

		lblNewLabel_5 = new JLabel("Total Number of Prescription Items");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 1;
		gbc_lblNewLabel_5.gridy = 8;
		panel.add(lblNewLabel_5, gbc_lblNewLabel_5);

		numberPrescriptions = new JTextField();
		numberPrescriptions.setText("0");
		numberPrescriptions.setEditable(false);
		GridBagConstraints gbc_numberPrescriptions = new GridBagConstraints();
		gbc_numberPrescriptions.insets = new Insets(0, 0, 5, 5);
		gbc_numberPrescriptions.fill = GridBagConstraints.HORIZONTAL;
		gbc_numberPrescriptions.gridx = 2;
		gbc_numberPrescriptions.gridy = 8;
		panel.add(numberPrescriptions, gbc_numberPrescriptions);
		numberPrescriptions.setColumns(10);

		lblNewLabel_6 = new JLabel("Total Number of Containers");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_6.gridx = 5;
		gbc_lblNewLabel_6.gridy = 8;
		panel.add(lblNewLabel_6, gbc_lblNewLabel_6);

		numberContainers = new JTextField();
		numberContainers.setText("0");
		numberContainers.setEditable(false);
		GridBagConstraints gbc_numberContainers = new GridBagConstraints();
		gbc_numberContainers.insets = new Insets(0, 0, 5, 5);
		gbc_numberContainers.fill = GridBagConstraints.HORIZONTAL;
		gbc_numberContainers.gridx = 6;
		gbc_numberContainers.gridy = 8;
		panel.add(numberContainers, gbc_numberContainers);
		numberContainers.setColumns(10);

		exitButton = new JButton("Exit");
		GridBagConstraints gbc_exitButton = new GridBagConstraints();
		gbc_exitButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_exitButton.insets = new Insets(0, 0, 5, 0);
		gbc_exitButton.gridx = 7;
		gbc_exitButton.gridy = 8;
		panel.add(exitButton, gbc_exitButton);

		textArea = new JTextArea(5, 20);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);

		pnl = new JPanel(new BorderLayout());
		pnl.add(new JLabel("Please edit the comment:"), BorderLayout.NORTH);
		pnl.add(textArea, BorderLayout.CENTER);

		popupMenu = new JPopupMenu();
		editComment = new JMenuItem("Edit Comment");
		decrementDosage = new JMenuItem("Decrement Dosage");

		popupMenu.add(editComment);
		popupMenu.add(decrementDosage);
		prescriptionTable.setComponentPopupMenu(popupMenu);

		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));

		dal.setCurrentPharmaName(pharmaList.get(0));
		updateValues();
	}

	private void pharmaceuticalComboSelected(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			String pharmaName = pharmaceuticalCombo.getSelectedItem().toString();
			dal.setCurrentPharmaName(pharmaName);
			updateValues();
		}
	}

	private void checkDailyDose() {
		if ((Integer) preDailyDose.getValue() > Integer.parseInt(recDailyDoseText.getText())) {
			if (!exceedDailyDose.isSelected()) {
				addButton.setEnabled(false);
			}
			addComment.setEnabled(false);
			addComment.setSelected(true);
		} else {
			addButton.setEnabled(true);
			addComment.setEnabled(true);
			addComment.setSelected(false);
		}
	}

	private void canExceedDailyDose(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			addButton.setEnabled(true);
		} else {
			if (Integer.parseInt(preDailyDose.getValue().toString()) > Integer.parseInt(recDailyDoseText.getText())) {
				if (!exceedDailyDose.isSelected()) {
					addButton.setEnabled(false);
				}
			}
		}
	}

	private void addItem() {
		String descriptionComment = description.getText();
		if (addComment.isSelected()) {
			comment = JOptionPane.showInputDialog("Write a Comment");
			if (comment != null) {
				comment = formatComment(comment);
				descriptionComment += "; " + comment;
			} else {
				return;
			}
		}

		prescription.addPrescriptionItem(pharmaceuticalCombo.getSelectedItem().toString(),
				(Integer) preDailyDose.getValue(), (Integer) duration.getValue(), containerSize,
				availableOverTheCounter, descriptionComment);
		List<PrescriptionItem> prescriptionItems = prescription.getPrescriptionItems();
		prescriptionTable.setModel(new PrescriptionTableModel(prescriptionItems));
		resizeColumnWidth(prescriptionTable);
		updatePrescriptionCounter(prescription.getNumberOfPharmaceuticals());
		updateNumberContainers(prescription.getNumberOfContainers());
	}

	private void removeItem() {
		int row = prescriptionTable.getSelectedRow();
		String pharmaceuticalName = prescriptionTable.getModel().getValueAt(row, 0).toString();
		prescription.removePrescriptionItem(pharmaceuticalName);
		List<PrescriptionItem> prescriptionItems = prescription.getPrescriptionItems();
		prescriptionTable.setModel(new PrescriptionTableModel(prescriptionItems));
		resizeColumnWidth(prescriptionTable);
		updatePrescriptionCounter(prescription.getNumberOfPharmaceuticals());
		updateNumberContainers(prescription.getNumberOfContainers());
	}

	private void setRemoveButton() {
		if (prescriptionTable.getSelectedRow() != -1) {
			removeButton.setEnabled(true);
		} else {
			removeButton.setEnabled(false);
		}
	}

	private void clearPrescription() {
		prescription.clearPrescription();
		prescriptionTable.setModel(new PrescriptionTableModel(new ArrayList<PrescriptionItem>()));
		resizeColumnWidth(prescriptionTable);
		updatePrescriptionCounter(0);
		updateNumberContainers(0);
	}

	private void editComment() {
		PrescriptionTableModel model = (PrescriptionTableModel) prescriptionTable.getModel();
		int row = prescriptionTable.getSelectedRow();
		textArea.setText(prescriptionTable.getModel().getValueAt(row, 5).toString());
		int okCxl = JOptionPane.showConfirmDialog(null, pnl, "Enter Data", JOptionPane.OK_CANCEL_OPTION);
		if (okCxl == JOptionPane.OK_OPTION) {
			String comment = formatComment(textArea.getText());
			model.setCommentAt(comment, row);
		}
	}

	private void decrementDosage() {
		PrescriptionTableModel model = (PrescriptionTableModel) prescriptionTable.getModel();
		int row = prescriptionTable.getSelectedRow();
		int currentValue = (Integer) prescriptionTable.getValueAt(row, 2);
		if (currentValue != 1) {
			model.setDailyDoseAt(currentValue - 1, row);
		}
	}

	private void highlightSelectedRow() {
		int rowAtPoint = prescriptionTable
				.rowAtPoint(SwingUtilities.convertPoint(popupMenu, new Point(0, 0), prescriptionTable));
		if (rowAtPoint > -1) {
			prescriptionTable.setRowSelectionInterval(rowAtPoint, rowAtPoint);
		}

		int row = prescriptionTable.getSelectedRow();
		decrementDosage.setEnabled((Integer) prescriptionTable.getValueAt(row, 2) != 1);
	}

	private void updateValues() {
		List<Medicine> listOfMedicines = dal.getPharmaInfo();
		for (Medicine medicine : listOfMedicines) {
			containerSize = medicine.getSize();
			maxValue = medicine.getRecDailyDose();
			String containerType = medicine.getContainerType();
			String finalText = medicine.getDescription();
			int availableOverTheCounter = medicine.getAvailableOverTheCounter();
			int storeInFridge = medicine.getStoreInFridge();
			if (availableOverTheCounter == 1) {
				finalText += "; Available over the counter and maybe cheaper";
				this.availableOverTheCounter = true;
			} else {
				this.availableOverTheCounter = false;
			}

			finalText += storeInFridge == 1 ? "; MUST BE STORED IN FRIDGE" : "";
			finalText += "; Comes in a " + (containerType == "Box" ? ("box of " + containerSize + " tablets")
					: (containerSize + "ml " + containerType));

			recDailyDoseText.setText(String.valueOf(maxValue));
			description.setText(finalText);
		}
		preDailyDose.setModel(new SpinnerNumberModel(1, 1, maxValue * 2, 1));
		duration.setModel(new SpinnerNumberModel(1, 1, null, 1));
	}

	private void resizeColumnWidth(JTable table) {
		final TableColumnModel columnModel = table.getColumnModel();
		int[] widths = new int[6];
		for (int column = 0; column < table.getColumnCount(); column++) {
			int width = 50; // Minimum width
			for (int row = 0; row < table.getRowCount(); row++) {
				TableCellRenderer renderer = table.getCellRenderer(row, column);
				Component comp = table.prepareRenderer(renderer, row, column);

				width = Math.max(comp.getPreferredSize().width + 1, width);
			}
			TableColumn tableColumn = columnModel.getColumn(column);
			TableCellRenderer headerRenderer = tableColumn.getHeaderRenderer();
			if (headerRenderer == null) {
				headerRenderer = table.getTableHeader().getDefaultRenderer();
			}
			Object headerValue = tableColumn.getHeaderValue();
			Component headerComp = headerRenderer.getTableCellRendererComponent(table, headerValue, false, false, 0,
					column);
			width = Math.max(width, headerComp.getPreferredSize().width) + 10;
			columnModel.getColumn(column).setPreferredWidth(width);
			widths[column] = width;
		}
		if (IntStream.of(widths).sum() < scrollPane_1.getWidth()) {
			int sum = IntStream.of(Arrays.copyOfRange(widths, 0, 5)).sum();
			columnModel.getColumn(5).setPreferredWidth(scrollPane_1.getWidth() - sum);
		}
	}

	private void updatePrescriptionCounter(int prescriptionCounter) {
		numberPrescriptions.setText(String.valueOf(prescriptionCounter));
		clearButton.setEnabled(prescription.getNumberOfPharmaceuticals() != 0);
	}

	private void updateNumberContainers(int numberOfContainers) {
		numberContainers.setText(String.valueOf(numberOfContainers));
	}

	private String formatComment(String comment) {
		String regex = "(.*)(;$)";
		Pattern r = Pattern.compile(regex);
		Matcher m = r.matcher(comment);
		if (!m.find()) {
			comment += ";";
		}
		return comment += "\n";
	}
}
