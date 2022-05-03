import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.Arrays;

import javax.swing.*;

public class Playlist implements IOption {

	final private JFrame frame;
	final private Connection connection;

	Playlist(JFrame frame, Connection c) {
		this.frame = frame;
		this.frame.getContentPane().removeAll();;
		this.frame.repaint();
		this.connection = c;
		
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
		JLabel lblNewLabel = new JLabel("Retrieve Playlists:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 10, 200, 20);
		frame.getContentPane().add(lblNewLabel);

		JTextField textField = new JTextField();
		textField.setBounds(120, 9, 150, 22);
		frame.getContentPane().add(textField);
	}

	@Override
	public void insert() {
		JLabel insertLabel = new JLabel("Insert Playlist:");
		insertLabel.setBounds(10, 10, 200, 20);

		JLabel titleLabel = new JLabel("Title");
		titleLabel.setBounds(10, 50, 200, 14);
		JTextField titleField = new JTextField();
		titleField.setBounds(10, 70, 200, 25);

		JLabel userLabel = new JLabel("User ID");
		userLabel.setBounds(10, 100, 200, 14);
		JTextField userField = new JTextField();
		userField.setBounds(10, 120, 200, 25);

		JLabel numOfSongsLabel = new JLabel("Number of Songs");
		numOfSongsLabel.setBounds(10, 150, 200, 14);
		JTextField numOfSongsField = new JTextField();
		numOfSongsField.setBounds(10, 170, 200, 25);
		
		JLabel musicIdLabel = new JLabel("Music ID");
		musicIdLabel.setBounds(10, 150, 200, 14);
		JTextField musicIdField = new JTextField();
		musicIdField.setBounds(10, 170, 200, 25);

		JLabel[] labels = { insertLabel, titleLabel, userLabel, numOfSongsLabel, musicIdLabel };
		JTextField[] textFields = { titleField, userField, numOfSongsField, musicIdField };

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
		JLabel lblNewLabel = new JLabel("Delete Playlist:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 10, 200, 20);
		frame.getContentPane().add(lblNewLabel);

		JTextField textField = new JTextField();
		textField.setBounds(120, 9, 150, 22);
		frame.getContentPane().add(textField);
	}

	public String toString() {
		return "Playlists";
	}
}
