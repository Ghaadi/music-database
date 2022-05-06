import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import javax.swing.*;

public class Artist implements IOption {

	final private JFrame frame;
	final private Connection connection;
	final ButtonGroup btnGroup = new ButtonGroup();

	public Artist(JFrame frame, Connection c) {
		this.frame = frame;
		this.frame.getContentPane().removeAll();
		this.frame.repaint();
		this.connection = c;
	}

	private String getSelectedButton(ButtonGroup btnGroup) {

		for (Enumeration<AbstractButton> buttons = btnGroup.getElements(); buttons.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();

			if (button.isSelected()) {
				return button.getText();
			}
		}

		return null;
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

	public void retrieve() {
		JLabel lblNewLabel = new JLabel("Retrieve Artists:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 10, 200, 20);
		frame.getContentPane().add(lblNewLabel);

		JTextField textField = new JTextField();
		textField.setBounds(120, 9, 150, 22);
		frame.getContentPane().add(textField);

		ButtonGroup btnGroup = new ButtonGroup();

		JRadioButton radioBtn1 = new JRadioButton("Singers");
		radioBtn1.setBounds(10, 50, 109, 23);
		frame.getContentPane().add(radioBtn1);

		JRadioButton radioBtn2 = new JRadioButton("Bands");
		radioBtn2.setBounds(10, 70, 109, 23);
		frame.getContentPane().add(radioBtn2);

		btnGroup.add(radioBtn1);
		btnGroup.add(radioBtn2);

		JButton proceedButton = new JButton("Proceed");
		proceedButton.setBounds(535, 392, 89, 23);
		frame.getContentPane().add(proceedButton);

		proceedButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (btnGroup.isSelected(null)) {
					JLabel lblNewLabel_1 = new JLabel("Please choose an option");
					lblNewLabel_1.setForeground(Color.RED);
					lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
					lblNewLabel_1.setBounds(486, 375, 138, 14);
					frame.getContentPane().add(lblNewLabel_1);
					frame.repaint();
				} else {
					ArrayList<IOption> options = new ArrayList<>(
							Arrays.asList(new Singer(frame, connection), new Band(frame, connection)));
					String selection = getSelectedButton(btnGroup);
					options.forEach((IOption option) -> {
						if (selection.equals(option.toString())) {
							option.retrieve();
						}
					});
				}
			}
		});

	}

	public void insert() {
		JButton proceedButton = new JButton("Proceed");
		proceedButton.setBounds(535, 392, 89, 23);
		frame.getContentPane().add(proceedButton);

		JButton backButton = new JButton("Back");
		backButton.setBounds(10, 392, 89, 23);
		frame.getContentPane().add(backButton);
		backAction(backButton);

		JLabel insertLabel = new JLabel("Insert Artist:");
		insertLabel.setBounds(10, 10, 200, 20);

		JLabel nameLabel = new JLabel("Name");
		nameLabel.setBounds(10, 50, 200, 14);
		JTextField nameField = new JTextField();
		nameField.setBounds(10, 70, 200, 25);

		JLabel websiteLabel = new JLabel("Website");
		websiteLabel.setBounds(10, 100, 200, 14);
		JTextField websiteField = new JTextField();
		websiteField.setBounds(10, 120, 200, 25);

		JLabel activeYearsLabel = new JLabel("Active years");
		activeYearsLabel.setBounds(10, 150, 200, 14);
		JTextField activeYearsField = new JTextField();
		activeYearsField.setBounds(10, 170, 200, 25);

		JRadioButton radioBtn1 = new JRadioButton("Singers");
		radioBtn1.setBounds(400, 60, 109, 23);

		JRadioButton radioBtn2 = new JRadioButton("Bands");
		radioBtn2.setBounds(400, 80, 109, 23);

		JLabel[] labels = { insertLabel, nameLabel, websiteLabel, activeYearsLabel };
		JTextField[] textFields = { nameField, websiteField, activeYearsField };
		JRadioButton[] buttons = { radioBtn1, radioBtn2 };

		Arrays.asList(labels).forEach((JLabel label) -> {
			frame.getContentPane().add(label);
		});

		Arrays.asList(textFields).forEach((JTextField textField) -> {
			frame.getContentPane().add(textField);
		});

		Arrays.asList(buttons).forEach((JRadioButton button) -> {
			frame.getContentPane().add(button);
			btnGroup.add(button);
		});

		proceedButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (btnGroup.isSelected(null)) {
					JLabel lblNewLabel_1 = new JLabel("Please choose an option");
					lblNewLabel_1.setForeground(Color.RED);
					lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
					lblNewLabel_1.setBounds(486, 375, 138, 14);
					frame.getContentPane().add(lblNewLabel_1);
					frame.repaint();
				} else if (nameField.getText().length() == 0 || activeYearsField.getText().length() == 0
						|| websiteField.getText().length() == 0) {
					JLabel lblNewLabel_1 = new JLabel("Please fill the fields");
					lblNewLabel_1.setForeground(Color.RED);
					lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
					lblNewLabel_1.setBounds(486, 375, 138, 14);
					frame.getContentPane().add(lblNewLabel_1);
					frame.repaint();
				} else {
					String sqlMax = "SELECT max(ID) from ARTIST";
					String sql = "Insert INTO ARTIST Values(?, ?, ?, ?)";
					try {
						Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery(sqlMax);
						resultSet.next();
						int id = Integer.valueOf(resultSet.getString(1)) + 1;
						resultSet.close();
						statement.close();
						PreparedStatement prepStmt = connection.prepareStatement(sql);
						prepStmt.setString(1, String.valueOf(id));
						prepStmt.setString(2, nameField.getText());
						prepStmt.setString(3, activeYearsField.getText());
						prepStmt.setString(4, websiteField.getText());

						prepStmt.executeUpdate();
						prepStmt.close();
					} catch (SQLException ex) {
						ex.printStackTrace();
					}

					ArrayList<IOption> options = new ArrayList<>(
							Arrays.asList(new Singer(frame, connection), new Band(frame, connection)));
					String selection = getSelectedButton(btnGroup);
					options.forEach((IOption option) -> {
						if (selection.equals(option.toString())) {
							option.insert();
						}
					});
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

		JLabel insertLabel = new JLabel("Update Artist:");
		insertLabel.setBounds(10, 10, 200, 20);

		JTextField idField = new JTextField();
		idField.setBounds(120, 9, 150, 22);
		frame.getContentPane().add(idField);

		JLabel nameLabel = new JLabel("Name");
		nameLabel.setBounds(10, 50, 200, 14);
		JTextField nameField = new JTextField();
		nameField.setBounds(10, 70, 200, 25);

		JLabel websiteLabel = new JLabel("Website");
		websiteLabel.setBounds(10, 100, 200, 14);
		JTextField websiteField = new JTextField();
		websiteField.setBounds(10, 120, 200, 25);

		JLabel activeYearsLabel = new JLabel("Active years");
		activeYearsLabel.setBounds(10, 150, 200, 14);
		JTextField activeYearsField = new JTextField();
		activeYearsField.setBounds(10, 170, 200, 25);

		JRadioButton radioBtn1 = new JRadioButton("Singers");
		radioBtn1.setBounds(400, 60, 109, 23);

		JRadioButton radioBtn2 = new JRadioButton("Bands");
		radioBtn2.setBounds(400, 80, 109, 23);

		JLabel[] labels = { insertLabel, nameLabel, websiteLabel, activeYearsLabel };
		JTextField[] textFields = { nameField, websiteField, activeYearsField };
		JRadioButton[] buttons = { radioBtn1, radioBtn2 };

		Arrays.asList(labels).forEach((JLabel label) -> {
			frame.getContentPane().add(label);
		});

		Arrays.asList(textFields).forEach((JTextField textField) -> {
			frame.getContentPane().add(textField);
		});

		Arrays.asList(buttons).forEach((JRadioButton button) -> {
			frame.getContentPane().add(button);
			btnGroup.add(button);
		});

		proceedButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnGroup.isSelected(null)) {
					JLabel lblNewLabel_1 = new JLabel("Please choose an option");
					lblNewLabel_1.setForeground(Color.RED);
					lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
					lblNewLabel_1.setBounds(486, 375, 138, 14);
					frame.getContentPane().add(lblNewLabel_1);
					frame.repaint();
				} else if (nameField.getText().length() == 0 && activeYearsField.getText().length() == 0
						&& websiteField.getText().length() == 0) {
					JLabel lblNewLabel_1 = new JLabel("Please fill at least one of the fields");
					lblNewLabel_1.setForeground(Color.RED);
					lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
					lblNewLabel_1.setBounds(486, 375, 200, 14);
					frame.getContentPane().add(lblNewLabel_1);
					frame.repaint();
				} else {
					try {
						String id = idField.getText();

						String sql = "UPDATE Artist SET";
						boolean hasEntered = false;

						if (nameField.getText().length() > 0) {
							sql += " name = \"" + nameField.getText() + "\"";
							hasEntered = true;
						}

						if (activeYearsField.getText().length() > 0) {
							if (!hasEntered) {
								sql += " active_years = \"" + activeYearsField.getText() + "\"";
								hasEntered = true;
							} else {
								sql += ", active_years = \"" + activeYearsField.getText() + "\"";
							}
						}

						if (websiteField.getText().length() > 0) {
							if (!hasEntered) {
								sql += " website = \"" + websiteField.getText() + "\"";
								hasEntered = true;
							} else {
								sql += ", website = \"" + websiteField.getText() + "\"";
							}
						}

						sql += " WHERE ID = " + id;

						PreparedStatement prepStmt = connection.prepareStatement(sql);
						prepStmt.execute();
					} catch (SQLException ex) {
						ex.printStackTrace();
					}

					ArrayList<IOption> options = new ArrayList<>(
							Arrays.asList(new Singer(frame, connection, idField.getText()), new Band(frame, connection)));
					String selection = getSelectedButton(btnGroup);
					options.forEach((IOption option) -> {
						if (selection.equals(option.toString())) {
							option.update();
						}
					});
				}
			}
		});
	}

	@Override
	public void delete() {
		JButton proceedButton = new JButton("Proceed");
		proceedButton.setBounds(535, 392, 89, 23);
		frame.getContentPane().add(proceedButton);

		JLabel lblNewLabel = new JLabel("Delete Artist:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 10, 200, 20);
		frame.getContentPane().add(lblNewLabel);

		JTextField textField = new JTextField();
		textField.setBounds(120, 9, 150, 22);
		frame.getContentPane().add(textField);

		proceedButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().length() == 0) {
					JLabel lblNewLabel_1 = new JLabel("Please fill the text field");
					lblNewLabel_1.setForeground(Color.RED);
					lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
					lblNewLabel_1.setBounds(486, 375, 138, 14);
					frame.getContentPane().add(lblNewLabel_1);
					frame.repaint();
				} else {
					String sql = "DELETE FROM Artist WHERE ID = " + textField.getText();
					try {
						Statement statement = connection.createStatement();
						statement.execute(sql);

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
	public String toString() {
		return "Artists";
	}

}
