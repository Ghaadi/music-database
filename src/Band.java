import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.sql.*;

import javax.swing.*;

public class Band implements IOption {

	final private JFrame frame;
	final private Connection connection;
	final String artistID;

	public Band(JFrame frame, Connection c) {
		this.frame = frame;
		this.frame.getContentPane().removeAll();
		this.frame.repaint();
		this.connection = c;
		this.artistID = "";
	}
	
	public Band(JFrame frame, Connection c, String artistID) {
		this.frame = frame;
		this.frame.getContentPane().removeAll();
		this.frame.repaint();
		this.connection = c;
		this.artistID = artistID;
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
		JLabel lblNewLabel = new JLabel("Retrieve Bands:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 10, 200, 20);
		frame.getContentPane().add(lblNewLabel);

		JTextField textField = new JTextField();
		textField.setBounds(120, 9, 150, 22);
		frame.getContentPane().add(textField);
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
		
		JLabel insertLabel = new JLabel("Insert Band:");
		insertLabel.setBounds(10, 10, 200, 20);

		JLabel titleLabel = new JLabel("Name");
		titleLabel.setBounds(10, 50, 200, 14);
		JTextField titleField = new JTextField();
		titleField.setBounds(10, 70, 200, 25);

		JLabel numMembersLabel = new JLabel("Number of Members");
		numMembersLabel.setBounds(10, 100, 200, 14);
		JTextField numMembersField = new JTextField();
		numMembersField.setBounds(10, 120, 200, 25);

		JLabel membersLabel = new JLabel("Members");
		membersLabel.setBounds(10, 150, 200, 14);
		JTextField membersField = new JTextField();
		membersField.setBounds(10, 170, 200, 25);
		
		JLabel numOfSongsLabel = new JLabel("Origin");
		numOfSongsLabel.setBounds(10, 200, 200, 14);
		JTextField numOfSongsField = new JTextField();
		numOfSongsField.setBounds(10, 220, 200, 25);
		
		JLabel artistIdLabel = new JLabel("Artist ID");
		artistIdLabel.setBounds(10, 250, 200, 14);
		JTextField artistIdField = new JTextField();
		artistIdField.setBounds(10, 270, 200, 25);

		JLabel[] labels = { insertLabel, titleLabel, numMembersLabel, membersLabel, numOfSongsLabel, artistIdLabel };
		JTextField[] textFields = { titleField, numMembersField, membersField, numOfSongsField, artistIdField };

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
		JLabel lblNewLabel = new JLabel("Delete Band:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 10, 200, 20);
		frame.getContentPane().add(lblNewLabel);

		JTextField textField = new JTextField();
		textField.setBounds(120, 9, 150, 22);
		frame.getContentPane().add(textField);
	}

	public String toString() {
		return "Bands";
	}

}
