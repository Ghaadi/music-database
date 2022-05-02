import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.*;

public class User implements IOption {

	final private JFrame frame;

	public User(JFrame frame) {
		this.frame = frame;
		
		JButton backButton = new JButton("Back");
		backButton.setBounds(10, 392, 89, 23);
		frame.getContentPane().add(backButton);
		backAction(backButton);
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
		JLabel lblNewLabel = new JLabel("Retrieve Users:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 10, 200, 20);
		frame.getContentPane().add(lblNewLabel);

		JTextField textField = new JTextField();
		textField.setBounds(120, 9, 150, 22);
		frame.getContentPane().add(textField);
	}

	@Override
	public void insert() {
		JLabel insertLabel = new JLabel("Insert User:");
		insertLabel.setBounds(10, 10, 200, 20);

		JLabel nameLabel = new JLabel("Name");
		nameLabel.setBounds(10, 50, 200, 14);
		JTextField nameField = new JTextField();
		nameField.setBounds(10, 70, 200, 25);

		JLabel addressLabel = new JLabel("Address");
		addressLabel.setBounds(10, 100, 200, 14);
		JTextField addressField = new JTextField();
		addressField.setBounds(10, 120, 200, 25);

		JLabel phoneNumLabel = new JLabel("Phone Number");
		phoneNumLabel.setBounds(10, 150, 200, 14);
		JTextField phoneNumField = new JTextField();
		phoneNumField.setBounds(10, 170, 200, 25);

		JLabel dateOfBirthLabel = new JLabel("Date of Birth");
		dateOfBirthLabel.setBounds(10, 200, 200, 14);
		JTextField dateOfBirthField = new JTextField();
		dateOfBirthField.setBounds(10, 220, 200, 25);

		JLabel[] labels = { insertLabel, nameLabel, addressLabel, phoneNumLabel, dateOfBirthLabel };
		JTextField[] textFields = { nameField, addressField, phoneNumField, dateOfBirthField };

		Arrays.asList(labels).forEach((JLabel label) -> {
			frame.add(label);
		});

		Arrays.asList(textFields).forEach((JTextField textField) -> {
			frame.add(textField);
		});
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete() {
		JLabel lblNewLabel = new JLabel("Delete User:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 10, 200, 20);
		frame.getContentPane().add(lblNewLabel);

		JTextField textField = new JTextField();
		textField.setBounds(120, 9, 150, 22);
		frame.getContentPane().add(textField);
	}
	
	public String toString() {
		return "Users";
	}

}
