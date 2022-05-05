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

	private void proceedInsert(JButton proceedButton) {
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
							option.insert();
						}
					});
				}
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

	public void retrieve() {
		JLabel lblNewLabel = new JLabel("Retrieve Artists:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 10, 200, 20);
		frame.getContentPane().add(lblNewLabel);

		JTextField textField = new JTextField();
		textField.setBounds(120, 9, 150, 22);
		frame.getContentPane().add(textField);

		JButton proceedButton = new JButton("Proceed");
		proceedButton.setBounds(535, 392, 89, 23);
		frame.getContentPane().add(proceedButton);

//		proceedRetrieve(proceedButton, textField.getText());
		proceedButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().length() > 0) {
					try {
						Statement statement = connection.createStatement();
						String sql = "Select * from SINGER s where s.name = \"" + textField.getText() + "\"";
						ResultSet resultSet = statement.executeQuery(sql);
						while (resultSet.next()) {
							String id = resultSet.getString(1);
							String name = resultSet.getString(2);
							String nationality = resultSet.getString(3);
							Date date_of_birth = resultSet.getDate(4);
							String aID = resultSet.getString(5);
							JLabel t = new JLabel(id + " " + name + " " + nationality);
							t.setFont(new Font("Tahoma", Font.PLAIN, 14));
							t.setBounds(50, 50, 200, 200);
							frame.getContentPane().add(t);
							System.out.println(t.getText());
							frame.repaint();
						}
						resultSet.close();
						statement.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});


	}

	public void insert() {
		JButton proceedButton = new JButton("Proceed");
		proceedButton.setBounds(535, 392, 89, 23);
		frame.getContentPane().add(proceedButton);
		proceedInsert(proceedButton);

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

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete() {
		JLabel lblNewLabel = new JLabel("Delete Song:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 10, 200, 20);
		frame.getContentPane().add(lblNewLabel);

		JTextField textField = new JTextField();
		textField.setBounds(120, 9, 150, 22);
		frame.getContentPane().add(textField);
	}

	@Override
	public String toString() {
		return "Artists";
	}

}
