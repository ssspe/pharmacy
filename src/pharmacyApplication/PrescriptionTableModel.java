package pharmacyApplication;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class PrescriptionTableModel extends AbstractTableModel {
	private List<PrescriptionItem> prescriptionItems = new ArrayList<PrescriptionItem>();
	String[] columnNames = { "Product Name", "Duration", "Prescribed Daily Dose", "Number of Containers", "OTC",
			"Comments" };

	public PrescriptionTableModel(List<PrescriptionItem> prescriptionItems) {
		this.prescriptionItems = prescriptionItems;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	}

	@Override
	public int getRowCount() {
		return prescriptionItems.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	public void setDailyDoseAt(int value, int row) {
		PrescriptionItem temp = prescriptionItems.get(row);
		temp.setPrescribedDailyDose(value);

		prescriptionItems.set(row, temp);
		fireTableRowsUpdated(row, row);
	}

	public void setCommentAt(String value, int row) {
		PrescriptionItem temp = prescriptionItems.get(row);
		temp.setComment(value);

		prescriptionItems.set(row, temp);
		fireTableRowsUpdated(row, row);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		PrescriptionItem prescriptionItem = prescriptionItems.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return prescriptionItem.getPharmaceuticalName();
		case 1:
			return prescriptionItem.getDuration();
		case 2:
			return prescriptionItem.getPrescribedDailyDose();
		case 3:
			return prescriptionItem.getNumberOfContainers();
		case 4:
			return prescriptionItem.isAvailableOverTheCounter();
		case 5:
			return prescriptionItem.getComments();
		}
		return null;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return String.class;
		case 1:
			return Integer.class;
		case 2:
			return Integer.class;
		case 3:
			return Integer.class;
		case 4:
			return Boolean.class;
		case 5:
			return String.class;
		}
		return null;
	}
}
