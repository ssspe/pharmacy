package com.zircon.pharmacyApplication;

import java.awt.EventQueue;

public class Main {

	/**
	 * Main entry point to launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrescriptionUI window = new PrescriptionUI("jdbc:mysql://localhost:3306/doc-u-med", "spencer",
							"password");
					window.frame.setVisible(true);
					window.frame.setResizable(false);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
