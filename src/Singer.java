import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Singer implements IOption {

	final private JFrame frame;
	final private Connection connection;

	public Singer(JFrame frame, Connection c) {
		this.frame = frame;
		this.frame.getContentPane().removeAll();
		this.frame.repaint();
		this.connection = c;
	}

	private void proceedAction(JButton proceedButton) {
		proceedButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
	}

	private void backAction(JButton backButton) {
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.revalidate();
				frame.repaint();
				new GUI().initialize(frame);
			}
		});
	}

	@Override
	public void retrieve() {
		JLabel lblNewLabel = new JLabel("Retrieve Singers:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 10, 200, 20);
		frame.getContentPane().add(lblNewLabel);

		JTextField textField = new JTextField();
		textField.setBounds(120, 9, 150, 22);
		frame.getContentPane().add(textField);

		JButton proceedButton = new JButton("Proceed");
		proceedButton.setBounds(535, 392, 89, 23);
		frame.getContentPane().add(proceedButton);

		proceedButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().length() > 0) {

					try {
						Statement statement = connection.createStatement();
						String sql = "Select a.ID, s.name,s.nationality,s.date_of_birth, a.active_years, a.website From ARTIST a JOIN SINGER s ON (a.name=s.name AND s.name = \""
								+ textField.getText() + "\");";

						ResultSet resultSet = statement.executeQuery(sql);
						String[] columns = { "ID", "Name", "Nationality", "Date of Birth", "Active Years", "Website" };

						DefaultTableModel model = new DefaultTableModel();
						JTable table = new JTable(model);
						table.setBounds(10, 40, 600, 300);
						JScrollPane sp = new JScrollPane(table);
						frame.add(sp);
						frame.add(table);

						for (String s : columns) {
							model.addColumn(s);
						}

						model.addRow(columns);

						ArrayList<Object[]> values = new ArrayList<>();

						while (resultSet.next()) {
							String id = resultSet.getString(1);
							String name = resultSet.getString(2);
							String nationality = resultSet.getString(3);
							Date dateOfBirth = resultSet.getDate(4);
							String activeYears = resultSet.getString(5);
							String website = resultSet.getString(6);
							values.add(new Object[] { id, name, nationality, dateOfBirth.toString(), activeYears,
									website });
						}

						for (Object[] arr : values) {
							model.addRow(arr);
						}

						frame.repaint();

						resultSet.close();
						statement.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
	}

	@Override
	public void insert() {
		JButton proceedButton = new JButton("Proceed");
		proceedButton.setBounds(535, 392, 89, 23);
		frame.getContentPane().add(proceedButton);
		proceedAction(proceedButton);

		JButton backButton = new JButton("Back");
		backButton.setBounds(10, 392, 89, 23);
		frame.getContentPane().add(backButton);
		backAction(backButton);

		JLabel insertLabel = new JLabel("Insert Singer:");
		insertLabel.setBounds(10, 10, 200, 20);

		JLabel nameLabel = new JLabel("Name");
		nameLabel.setBounds(10, 50, 200, 14);
		JTextField nameField = new JTextField();
		nameField.setBounds(10, 70, 200, 25);

		JLabel nationalityLabel = new JLabel("Nationality");
		nationalityLabel.setBounds(10, 100, 200, 14);
		JTextField nationalityField = new JTextField();
		nationalityField.setBounds(10, 120, 200, 25);

		JLabel dateOfBirthLabel = new JLabel("Date of Birth");
		dateOfBirthLabel.setBounds(10, 150, 200, 14);
		JTextField dateOfBirthField = new JTextField();
		dateOfBirthField.setBounds(10, 170, 200, 25);

		JLabel artistIdLabel = new JLabel("Artist ID");
		artistIdLabel.setBounds(10, 200, 200, 14);
		JTextField artistIdField = new JTextField();
		artistIdField.setBounds(10, 220, 200, 25);

		JLabel[] labels = { insertLabel, nameLabel, nationalityLabel, dateOfBirthLabel, artistIdLabel };
		JTextField[] textFields = { nameField, nationalityField, dateOfBirthField, artistIdField };

		Arrays.asList(labels).forEach((JLabel label) -> {
			frame.add(label);
		});

		Arrays.asList(textFields).forEach((JTextField textField) -> {
			frame.add(textField);
		});

//		String sql = "Insert INTO Singer Values(" + nameField.getText() + ", " + nationalityField.getText() + ", "
//				+ dateOfBirthField.getText() + ", " + artistIdField.getText() + ")";
//			try {
//				PreparedStatement prepStmt = connection.prepareStatement(sql);
//				prepStmt.setString(1, nameField.getText());
//				prepStmt.setString(2, nationalityField.getText());
//				prepStmt.setString(3, dateOfBirthField.getText());
//				prepStmt.setString(4, artistIdField.getText());
//
//				prepStmt.executeUpdate();
//				prepStmt.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete() {
		JLabel lblNewLabel = new JLabel("Delete Singer:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 10, 200, 20);
		frame.getContentPane().add(lblNewLabel);

		JTextField textField = new JTextField();
		textField.setBounds(120, 9, 150, 22);
		frame.getContentPane().add(textField);

//		try{
//			Statement statement = connection.createStatement();
//			String sql = "Delete * from SINGER s where s.name = " + textField.getText();
//			ResultSet resultSet = statement.executeQuery(sql);
//			resultSet.close();
//			statement.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
	}

	public String toString() {
		return "Singers";
	}
}
