import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.*;

public class Singer implements IOption {

	final private JFrame frame;

	public Singer(JFrame frame) {
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
		JLabel lblNewLabel = new JLabel("Retrieve Singers:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 10, 200, 20);
		frame.getContentPane().add(lblNewLabel);

		JTextField textField = new JTextField();
		textField.setBounds(120, 9, 150, 22);
		frame.getContentPane().add(textField);
	}

	@Override
	public void insert() {
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

		JLabel activeYearsLabel = new JLabel("Active Years");
		activeYearsLabel.setBounds(10, 200, 200, 14);
		JTextField activeYearsField = new JTextField();
		activeYearsField.setBounds(10, 220, 200, 25);

		JLabel dateJoinedLabel = new JLabel("Date Joined (if member of any band)");
		dateJoinedLabel.setBounds(10, 250, 200, 14);
		JTextField dateJoinedField = new JTextField();
		dateJoinedField.setBounds(10, 270, 200, 25);

		JLabel websiteLabel = new JLabel("Website");
		websiteLabel.setBounds(10, 300, 200, 14);
		JTextField websiteField = new JTextField();
		websiteField.setBounds(10, 320, 200, 25);

		JLabel[] labels = { insertLabel, nameLabel, nationalityLabel, dateOfBirthLabel, activeYearsLabel,
				dateJoinedLabel, websiteLabel };
		JTextField[] textFields = { nameField, nationalityField, dateOfBirthField, activeYearsField, dateJoinedField,
				websiteField };

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
