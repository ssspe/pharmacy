package pharmacyApplication;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class PrescriptionUI {

	private JFrame frame;
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
	private JTable table;
	private List<String> list;
	private DAL d;
	private int maxValue;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrescriptionUI window = new PrescriptionUI();
					window.frame.setVisible(true);
					window.frame.setResizable(false);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws SQLException
	 */
	public PrescriptionUI() throws SQLException {
		d = new DAL();
		list = d.getPharmaName();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1045, 295);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 20, 93, 28, 86, 86, 86, 86, 0, 97 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 23, 20, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 20.0, 1.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
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

		JComboBox pharmaceuticalCombo = new JComboBox();
		GridBagConstraints gbc_pharmaceuticalCombo = new GridBagConstraints();
		gbc_pharmaceuticalCombo.fill = GridBagConstraints.HORIZONTAL;
		gbc_pharmaceuticalCombo.anchor = GridBagConstraints.NORTH;
		gbc_pharmaceuticalCombo.insets = new Insets(0, 0, 5, 5);
		gbc_pharmaceuticalCombo.gridx = 1;
		gbc_pharmaceuticalCombo.gridy = 3;
		for (String pharmaName : list) {
			pharmaceuticalCombo.addItem(pharmaName);
		}
		pharmaceuticalCombo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					String pharmaName = pharmaceuticalCombo.getSelectedItem().toString();
					d.setCurrentPharmaName(pharmaName);
					updateValues(pharmaName);
				}
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
		preDailyDose.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
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
		description.setWrapStyleWord(true);
		description.setLineWrap(true);
		scrollPane.setViewportView(description);

		duration = new JSpinner();
		duration.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		GridBagConstraints gbc_duration = new GridBagConstraints();
		gbc_duration.anchor = GridBagConstraints.NORTH;
		gbc_duration.fill = GridBagConstraints.HORIZONTAL;
		gbc_duration.insets = new Insets(0, 0, 5, 5);
		gbc_duration.gridx = 5;
		gbc_duration.gridy = 3;
		panel.add(duration, gbc_duration);

		JCheckBox addComment = new JCheckBox("Add Comment");
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
				System.out.println("f");
				
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
		        if(e.getStateChange() == ItemEvent.SELECTED) {
		        	preDailyDose.setModel(new SpinnerNumberModel(Integer.parseInt(preDailyDose.getValue().toString()), new Integer(0), null, new Integer(1)));
		        } else {
		        	if(Integer.parseInt(preDailyDose.getValue().toString()) > Integer.parseInt(recDailyDoseText.getText())) {
		        		preDailyDose.setModel(new SpinnerNumberModel(Integer.parseInt(recDailyDoseText.getText()), 0, maxValue, 1));
		        	}
		        	else {
		        		preDailyDose.setModel(new SpinnerNumberModel(Integer.parseInt(preDailyDose.getValue().toString()), 0, maxValue, 1));
		        	}	
		        };
		    }
		});
		panel.add(exceedDailyDose, gbc_exceedDailyDose);
		
		removeButton = new JButton("Remove");
		GridBagConstraints gbc_removeButton = new GridBagConstraints();
		gbc_removeButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_removeButton.anchor = GridBagConstraints.EAST;
		gbc_removeButton.insets = new Insets(0, 0, 5, 0);
		gbc_removeButton.gridx = 7;
		gbc_removeButton.gridy = 4;
		panel.add(removeButton, gbc_removeButton);

		table = new JTable();
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.gridwidth = 6;
		gbc_table.gridheight = 2;
		gbc_table.insets = new Insets(0, 0, 5, 5);
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 1;
		gbc_table.gridy = 5;
		panel.add(table, gbc_table);

		clearButton = new JButton("Clear");
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
		gbc_lblNewLabel_5.gridy = 7;
		panel.add(lblNewLabel_5, gbc_lblNewLabel_5);

		numberPrescriptions = new JTextField();
		numberPrescriptions.setEditable(false);
		GridBagConstraints gbc_numberPrescriptions = new GridBagConstraints();
		gbc_numberPrescriptions.insets = new Insets(0, 0, 5, 5);
		gbc_numberPrescriptions.fill = GridBagConstraints.HORIZONTAL;
		gbc_numberPrescriptions.gridx = 2;
		gbc_numberPrescriptions.gridy = 7;
		panel.add(numberPrescriptions, gbc_numberPrescriptions);
		numberPrescriptions.setColumns(10);

		lblNewLabel_6 = new JLabel("Total Number of Containers");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_6.gridx = 5;
		gbc_lblNewLabel_6.gridy = 7;
		panel.add(lblNewLabel_6, gbc_lblNewLabel_6);

		exitButton = new JButton("Exit");
		GridBagConstraints gbc_exitButton = new GridBagConstraints();
		gbc_exitButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_exitButton.insets = new Insets(0, 0, 5, 0);
		gbc_exitButton.gridx = 7;
		gbc_exitButton.gridy = 7;
		exitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});

		numberContainers = new JTextField();
		numberContainers.setEditable(false);
		GridBagConstraints gbc_numberContainers = new GridBagConstraints();
		gbc_numberContainers.insets = new Insets(0, 0, 5, 5);
		gbc_numberContainers.fill = GridBagConstraints.HORIZONTAL;
		gbc_numberContainers.gridx = 6;
		gbc_numberContainers.gridy = 7;
		panel.add(numberContainers, gbc_numberContainers);
		numberContainers.setColumns(10);
		panel.add(exitButton, gbc_exitButton);

		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		d.setCurrentPharmaName(list.get(0));
		updateValues(list.get(0));
	}

	private void updateValues(String pharmaName) {
		ResultSet resultSet = d.getPharmaInfo();
		try {
			while (resultSet.next()) {
				int size = resultSet.getInt("ContainerSize");
				maxValue = resultSet.getInt("RecommendedDailyDose");
				String containerType = resultSet.getString("ContainerType");
				String finalText = resultSet.getString("Description");
				
				if (resultSet.getInt("AvailableOverTheCounter") == 1) {
					finalText += "; Available over the counter and maybe cheaper";
				}
				
				if (resultSet.getInt("StoreInFridge") == 1) {
					finalText += "; MUST BE STORED IN FRIDGE";
				}
				
				switch (containerType) {
					case "Box":
						finalText += "; Comes in a box of " + size + " tablets";
						break;
					default:
						finalText += "; Comes in a " + size + "ml " + containerType;
						break;
				}
				recDailyDoseText.setText(String.valueOf(maxValue));
				description.setText(finalText);
			}
			
		if (exceedDailyDose.isSelected()) {
			preDailyDose.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		}
		else {
			preDailyDose.setModel(new SpinnerNumberModel(0, 0, maxValue, 1));
		}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
