import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Singer implements IOption {

	final private JFrame frame;
	final private Connection connection;
	final String artistID;

	public Singer(JFrame frame, Connection c) {
		this.frame = frame;
		this.frame.getContentPane().removeAll();
		this.frame.repaint();
		this.connection = c;
		this.artistID = "";
	}

	public Singer(JFrame frame, Connection c, String artistID) {
		this.frame = frame;
		this.frame.getContentPane().removeAll();
		this.frame.repaint();
		this.connection = c;
		this.artistID = artistID;
	}

	private void backAction(JButton backButton) {
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.revalidate();
				frame.repaint();

				String sqlMaxArtist = "SELECT max(ID) from ARTIST";

				try {
					Statement statement = connection.createStatement();
					ResultSet resultSet = statement.executeQuery(sqlMaxArtist);
					resultSet.next();
					String artistID = resultSet.getString(1);

					String sql = "DELETE FROM Artist WHERE ID = " + artistID;
					statement.execute(sql);
					frame.getContentPane().removeAll();
					frame.revalidate();
					frame.repaint();
					new GUI().initialize(frame);
				} catch (SQLException ex) {
					ex.printStackTrace();
				}

				
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

		JButton backButton = new JButton("Back");
		backButton.setBounds(10, 392, 89, 23);
		frame.getContentPane().add(backButton);
		backAction(backButton);

		JLabel insertLabel = new JLabel("Insert Singer:");
		insertLabel.setBounds(10, 10, 200, 20);

		JLabel nationalityLabel = new JLabel("Nationality");
		nationalityLabel.setBounds(10, 50, 200, 14);
		JTextField nationalityField = new JTextField();
		nationalityField.setBounds(10, 70, 200, 25);

		JLabel dateOfBirthLabel = new JLabel("Date of Birth");
		dateOfBirthLabel.setBounds(10, 100, 200, 14);
		JTextField dateOfBirthField = new JTextField();
		dateOfBirthField.setBounds(10, 120, 200, 25);

		JLabel[] labels = { insertLabel, nationalityLabel, dateOfBirthLabel };
		JTextField[] textFields = { nationalityField, dateOfBirthField };

		Arrays.asList(labels).forEach((JLabel label) -> {
			frame.add(label);
		});

		Arrays.asList(textFields).forEach((JTextField textField) -> {
			frame.add(textField);
		});

		proceedButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (nationalityField.getText().length() == 0 || dateOfBirthField.getText().length() == 0) {
					JLabel lblNewLabel_1 = new JLabel("Please fill the fields");
					lblNewLabel_1.setForeground(Color.RED);
					lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
					lblNewLabel_1.setBounds(486, 375, 138, 14);
					frame.getContentPane().add(lblNewLabel_1);
					frame.repaint();
				} else {
					String sqlMaxSinger = "SELECT max(singerID) from SINGER";
					String sqlMaxArtist = "SELECT max(ID) from ARTIST";
					String sql = "Insert INTO Singer Values(?, ?, ?, ?, ?)";
					try {
						Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery(sqlMaxArtist);
						resultSet.next();
						int artistID = Integer.valueOf(resultSet.getString(1));
						resultSet = statement.executeQuery(sqlMaxSinger);
						resultSet.next();
						int singerID = Integer.valueOf(resultSet.getString(1)) + 1;

						String sqlName = "Select name from ARTIST where ID = " + String.valueOf(artistID);
						resultSet = statement.executeQuery(sqlName);
						resultSet.next();
						String singerName = resultSet.getString(1);

						Date dateOfBirth = Date.valueOf(dateOfBirthField.getText());

						resultSet.close();
						statement.close();
						PreparedStatement prepStmt = connection.prepareStatement(sql);
						prepStmt.setString(1, String.valueOf(singerID));
						prepStmt.setString(2, singerName);
						prepStmt.setString(3, nationalityField.getText());
						prepStmt.setDate(4, dateOfBirth);
						prepStmt.setString(5, String.valueOf(artistID));

						prepStmt.executeUpdate();
						prepStmt.close();

						frame.getContentPane().removeAll();
						frame.revalidate();
						frame.repaint();
						new GUI().initialize(frame);
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				}
			}
		});

	}

	@Override
	public void update() {
		JButton proceedButton = new JButton("Proceed");
		proceedButton.setBounds(535, 392, 89, 23);
		frame.getContentPane().add(proceedButton);

		JButton backButton = new JButton("Back");
		backButton.setBounds(10, 392, 89, 23);
		frame.getContentPane().add(backButton);
		backAction(backButton);

		JLabel insertLabel = new JLabel("Update Singer:");
		insertLabel.setBounds(10, 10, 200, 20);

		JLabel nationalityLabel = new JLabel("Nationality");
		nationalityLabel.setBounds(10, 50, 200, 14);
		JTextField nationalityField = new JTextField();
		nationalityField.setBounds(10, 70, 200, 25);

		JLabel dateOfBirthLabel = new JLabel("Date of Birth");
		dateOfBirthLabel.setBounds(10, 100, 200, 14);
		JTextField dateOfBirthField = new JTextField();
		dateOfBirthField.setBounds(10, 120, 200, 25);

		JLabel[] labels = { insertLabel, nationalityLabel, dateOfBirthLabel };
		JTextField[] textFields = { nationalityField, dateOfBirthField };

		Arrays.asList(labels).forEach((JLabel label) -> {
			frame.getContentPane().add(label);
		});

		Arrays.asList(textFields).forEach((JTextField textField) -> {
			frame.getContentPane().add(textField);
		});

		proceedButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sql = "UPDATE Singer SET";
					boolean hasEntered = false;

					if (nationalityField.getText().length() > 0) {
						sql += " nationality = \"" + nationalityField.getText() + "\"";
						hasEntered = true;
					}

					if (dateOfBirthField.getText().length() > 0) {
						if (!hasEntered) {
							sql += " date_of_birth = \"" + dateOfBirthField.getText() + "\"";
							hasEntered = true;
						} else {
							sql += ", date_of_birth = \"" + dateOfBirthField.getText() + "\"";
						}
					}

					sql += " WHERE aID = " + artistID;

					PreparedStatement prepStmt = connection.prepareStatement(sql);
					prepStmt.execute();
					
					frame.getContentPane().removeAll();
					frame.revalidate();
					frame.repaint();
					new GUI().initialize(frame);
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});

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

	}

	public String toString() {
		return "Singers";
	}
}
