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
	private JLabel pharmaNameLabel;
	private JLabel recDailyDoseLabel;
	private JLabel preDailyDoseLabel;
	private JLabel descriptionLabel;
	private JLabel durationLabel;
	private JLabel numberOfPrescriptionsLabel;
	private JLabel numberOfContainersLabel;
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
	 * Connects to the DAL and retrieves the list of medicine names.
	 * 
	 * @throws SQLException
	 */
	public PrescriptionUI(String connectionURL, String username, String password) throws SQLException {
		dal = FactoryDAL.create();
		dal.connect(connectionURL, username, password);
		pharmaList = dal.getPharmaName();
		prescription = FactoryPrescription.create();
		initializeUIElements();
		resizeColumnWidth(prescriptionTable);
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

		pharmaNameLabel = new JLabel("Pharmaceutical Name");
		GridBagConstraints gbc_pharmaNameLabel = new GridBagConstraints();
		gbc_pharmaNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_pharmaNameLabel.gridx = 1;
		gbc_pharmaNameLabel.gridy = 1;
		panel.add(pharmaNameLabel, gbc_pharmaNameLabel);

		recDailyDoseLabel = new JLabel("Rec. Daily Dose");
		GridBagConstraints gbc_recDailyDoseLabel = new GridBagConstraints();
		gbc_recDailyDoseLabel.insets = new Insets(0, 0, 5, 5);
		gbc_recDailyDoseLabel.gridx = 2;
		gbc_recDailyDoseLabel.gridy = 1;
		panel.add(recDailyDoseLabel, gbc_recDailyDoseLabel);

		preDailyDoseLabel = new JLabel("Prescribed Daily Dose");
		GridBagConstraints gbc_preDailyDoseLabel = new GridBagConstraints();
		gbc_preDailyDoseLabel.insets = new Insets(0, 0, 5, 5);
		gbc_preDailyDoseLabel.gridx = 3;
		gbc_preDailyDoseLabel.gridy = 1;
		panel.add(preDailyDoseLabel, gbc_preDailyDoseLabel);

		descriptionLabel = new JLabel("Description & special details");
		GridBagConstraints gbc_descriptionLabel = new GridBagConstraints();
		gbc_descriptionLabel.insets = new Insets(0, 0, 5, 5);
		gbc_descriptionLabel.gridx = 4;
		gbc_descriptionLabel.gridy = 1;
		panel.add(descriptionLabel, gbc_descriptionLabel);

		durationLabel = new JLabel("Duration");
		GridBagConstraints gbc_durationLabel = new GridBagConstraints();
		gbc_durationLabel.insets = new Insets(0, 0, 5, 5);
		gbc_durationLabel.gridx = 5;
		gbc_durationLabel.gridy = 1;
		panel.add(durationLabel, gbc_durationLabel);

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
		pharmaceuticalCombo.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				pharmaceuticalComboSelected(e);
			}
		});
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
		preDailyDose.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				checkDailyDose();
			}
		});
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
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addItem();
			}
		});
		panel.add(addButton, gbc_addButton);

		exceedDailyDose = new JCheckBox("OK to exceed Daily Dose");
		GridBagConstraints gbc_exceedDailyDose = new GridBagConstraints();
		gbc_exceedDailyDose.insets = new Insets(0, 0, 5, 5);
		gbc_exceedDailyDose.gridx = 3;
		gbc_exceedDailyDose.gridy = 4;
		exceedDailyDose.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				canExceedDailyDose(e);
			}
		});
		panel.add(exceedDailyDose, gbc_exceedDailyDose);

		removeButton = new JButton("Remove");
		removeButton.setEnabled(false);
		GridBagConstraints gbc_removeButton = new GridBagConstraints();
		gbc_removeButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_removeButton.anchor = GridBagConstraints.EAST;
		gbc_removeButton.insets = new Insets(0, 0, 5, 0);
		gbc_removeButton.gridx = 7;
		gbc_removeButton.gridy = 4;
		removeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				removeItem();
			}
		});
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
		prescriptionTable.setShowVerticalLines(false);
		prescriptionTable.setShowHorizontalLines(false);
		prescriptionTable.setFillsViewportHeight(true);

		prescriptionTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		prescriptionTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				setRemoveButton();
			}
		});
		
		panel.add(scrollPane_1, gbc_scrollPane_1);
		scrollPane_1.setViewportView(prescriptionTable);

		clearButton = new JButton("Clear");
		clearButton.setEnabled(false);
		GridBagConstraints gbc_clearButton = new GridBagConstraints();
		gbc_clearButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_clearButton.insets = new Insets(0, 0, 5, 0);
		gbc_clearButton.gridx = 7;
		gbc_clearButton.gridy = 5;
		clearButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clearPrescription();
			}
		});
		panel.add(clearButton, gbc_clearButton);

		numberOfPrescriptionsLabel = new JLabel("Total Number of Prescription Items");
		GridBagConstraints gbc_numberOfPrescriptionsLabel = new GridBagConstraints();
		gbc_numberOfPrescriptionsLabel.insets = new Insets(0, 0, 5, 5);
		gbc_numberOfPrescriptionsLabel.gridx = 1;
		gbc_numberOfPrescriptionsLabel.gridy = 8;
		panel.add(numberOfPrescriptionsLabel, gbc_numberOfPrescriptionsLabel);

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

		numberOfContainersLabel = new JLabel("Total Number of Containers");
		GridBagConstraints gbc_numberOfContainersLabel = new GridBagConstraints();
		gbc_numberOfContainersLabel.insets = new Insets(0, 0, 5, 5);
		gbc_numberOfContainersLabel.anchor = GridBagConstraints.EAST;
		gbc_numberOfContainersLabel.gridx = 5;
		gbc_numberOfContainersLabel.gridy = 8;
		panel.add(numberOfContainersLabel, gbc_numberOfContainersLabel);

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
		exitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});

		textArea = new JTextArea(5, 20);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);

		pnl = new JPanel(new BorderLayout());
		pnl.add(new JLabel("Please edit the comment:"), BorderLayout.NORTH);
		pnl.add(textArea, BorderLayout.CENTER);

		editComment = new JMenuItem("Edit Comment");
		editComment.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				editComment();
			}
		});
		decrementDosage = new JMenuItem("Decrement Dosage");
		decrementDosage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				decrementDosage();
			}
		});

		popupMenu = new JPopupMenu();
		popupMenu.add(editComment);
		popupMenu.add(decrementDosage);
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
		prescriptionTable.setComponentPopupMenu(popupMenu);

		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		updateValues(pharmaList.get(0));
	}

	/**
	 * Will update the UI elements when a new medicine is selected from the drop
	 * down box.
	 * 
	 * @param e Item event checking if the comboBox has been selected.
	 */
	private void pharmaceuticalComboSelected(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			String pharmaName = pharmaceuticalCombo.getSelectedItem().toString();
			updateValues(pharmaName);
		}
	}

	/**
	 * Checks if the prescribed daily does is more than recommended. If so it
	 * toggles the add button and forces the add comment checkbox.
	 */
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
			exceedDailyDose.setSelected(false);
		}
	}

	/**
	 * Callback function to check if the user has allowed the prescription item to
	 * exceed the daily dose and if so will toggle the add button.
	 * 
	 * @param e Item event checking if the checkbox is selected.
	 */
	private void canExceedDailyDose(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			addButton.setEnabled(true);
		} else {
			if ((Integer) preDailyDose.getValue() > Integer.parseInt(recDailyDoseText.getText())) {
				if (!exceedDailyDose.isSelected()) {
					addButton.setEnabled(false);
				}
			}
		}
	}

	/**
	 * Adds an item to the prescription using the user set items on the UI. Will
	 * then update the table, the prescription counter and the number of containers
	 * accordingly.
	 */
	private void addItem() {
		String descriptionComment = description.getText();

		// Checking if the user wants to add a comment.
		if (addComment.isSelected()) {
			comment = JOptionPane.showInputDialog("Write a Comment");
			if (comment != null) {
				descriptionComment += "; " + formatComment(comment);
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

	/**
	 * Removes the current selected prescription item from the prescription and
	 * updates the table, the prescription counter and the number of containers
	 * accordingly.
	 */
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

	/**
	 * Enables or disables the remove button based on whether or not a row is
	 * selected.
	 */
	private void setRemoveButton() {
		removeButton.setEnabled(prescriptionTable.getSelectedRow() != -1);
	}

	/**
	 * Will clear the prescription and remove all rows from the table. Will also
	 * update the prescription counter and number containers to 0.
	 */
	private void clearPrescription() {
		prescription.clearPrescription();
		prescriptionTable.setModel(new PrescriptionTableModel(new ArrayList<PrescriptionItem>()));
		resizeColumnWidth(prescriptionTable);
		updatePrescriptionCounter(0);
		updateNumberContainers(0);
	}

	/**
	 * Allows a user to edit the comment of a specific prescription item in the
	 * table.
	 */
	private void editComment() {
		PrescriptionTableModel model = (PrescriptionTableModel) prescriptionTable.getModel();
		int row = prescriptionTable.getSelectedRow();
		textArea.setText(prescriptionTable.getModel().getValueAt(row, 5).toString());
		int selectedOption = JOptionPane.showConfirmDialog(null, pnl, "Enter Data", JOptionPane.OK_CANCEL_OPTION);
		if (selectedOption == JOptionPane.OK_OPTION) {
			String comment = formatComment(textArea.getText());
			model.setCommentAt(comment, row);
		}
	}

	/**
	 * Decrements the dosage of the currently selected row. Even though we disable
	 * the button if the value is already one, it is worth checking again.
	 */
	private void decrementDosage() {
		PrescriptionTableModel model = (PrescriptionTableModel) prescriptionTable.getModel();
		int row = prescriptionTable.getSelectedRow();
		int currentValue = (Integer) prescriptionTable.getValueAt(row, 2);
		if (currentValue != 1) {
			model.setDailyDoseAt(currentValue - 1, row);
		}
	}

	/**
	 * Highlights the row that has just been interacted with. Also enables/disables
	 * the decrement dosage in the context menu based on whether or not the current
	 * dosage is more than 1.
	 */
	private void highlightSelectedRow() {
		int rowAtPoint = prescriptionTable
				.rowAtPoint(SwingUtilities.convertPoint(popupMenu, new Point(0, 0), prescriptionTable));
		if (rowAtPoint > -1) {
			prescriptionTable.setRowSelectionInterval(rowAtPoint, rowAtPoint);
		}

		int row = prescriptionTable.getSelectedRow();
		decrementDosage.setEnabled((Integer) prescriptionTable.getValueAt(row, 2) != 1);
	}

	/**
	 * Updates the UI values with the selected pharmaceuticals values from the
	 * database.
	 */
	private void updateValues(String pharmaName) {
		Medicine medicine = dal.getPharmaInfo(pharmaName);
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

		// If stored in a fridge update the final text
		finalText += storeInFridge == 1 ? "; MUST BE STORED IN FRIDGE" : "";

		// Change the text based on teh container type
		finalText += "; Comes in a " + (containerType.equals("Box") ? ("box of " + containerSize + " tablets")
				: (containerSize + "ml " + containerType));

		recDailyDoseText.setText(String.valueOf(maxValue));
		description.setText(finalText);

		// Reset the models of the spinners so they can't go below 0.
		preDailyDose.setModel(new SpinnerNumberModel(1, 1, maxValue * 2, 1));
		duration.setModel(new SpinnerNumberModel(1, 1, null, 1));
		addComment.setSelected(false);
		addComment.setEnabled(true);
		exceedDailyDose.setSelected(false);
	}

	/**
	 * Resizes the columns of a table based on the content or header, which ever is
	 * bigger.
	 * 
	 * @param table The table to resize.
	 */
	private void resizeColumnWidth(JTable table) {
		final TableColumnModel columnModel = table.getColumnModel();
		int[] widths = new int[table.getColumnCount()];
		for (int column = 0; column < table.getColumnCount(); column++) {
			int width = 50; // Minimum width of column
			for (int row = 0; row < table.getRowCount(); row++) {
				TableCellRenderer renderer = table.getCellRenderer(row, column);
				Component comp = table.prepareRenderer(renderer, row, column);

				// Choosing the width of the row or min width
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
			// Choosing the width of the column or the last set width
			width = Math.max(width, headerComp.getPreferredSize().width) + 10;
			columnModel.getColumn(column).setPreferredWidth(width);
			widths[column] = width; // Holding array of all width lengths
		}
		if (IntStream.of(widths).sum() < scrollPane_1.getWidth()) {
			// If the length of all columns does not fill the scroll pane then extend the
			// last column to fit the scroll pane.
			int sum = IntStream.of(Arrays.copyOfRange(widths, 0, 5)).sum();
			columnModel.getColumn(5).setPreferredWidth(scrollPane_1.getWidth() - sum);
		}
	}

	/**
	 * Updates the prescription counter text. Also checks whether the clear button
	 * should be enabled.
	 * 
	 * @param prescriptionCounter prescription counter text.
	 */
	private void updatePrescriptionCounter(int prescriptionCounter) {
		numberPrescriptions.setText(String.valueOf(prescriptionCounter));
		clearButton.setEnabled(prescription.getNumberOfPharmaceuticals() != 0);
	}

	/**
	 * @param numberOfContainers Update the number of containers text.
	 */
	private void updateNumberContainers(int numberOfContainers) {
		numberContainers.setText(String.valueOf(numberOfContainers));
	}

	/**
	 * Checks that a comment ends in a colon, and adds one if it does not. Also adds
	 * a line break.
	 * 
	 * @param comment The comment to format.
	 * @return (String) Formatted comment
	 */
	private String formatComment(String comment) {
		String regex = "(.*)(;$)"; // Regex that checks if the end of a line ends in ';'
		Pattern r = Pattern.compile(regex);
		Matcher m = r.matcher(comment);
		if (!m.find()) {
			comment += ";";
		}
		return comment += "\n";
	}
}
