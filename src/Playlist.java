import java.awt.Font;
import java.util.Arrays;

import javax.swing.*;

public class Playlist implements IOption {

	final private JFrame frame;

	Playlist(JFrame frame) {
		this.frame = frame;
	}

	@Override
	public void retrieve() {
		frame.getContentPane().removeAll();
		frame.repaint();

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
		frame.getContentPane().removeAll();
		frame.repaint();

		JLabel insertLabel = new JLabel("Insert Playlist:");
		insertLabel.setBounds(10, 10, 200, 20);

		JLabel titleLabel = new JLabel("Title");
		titleLabel.setBounds(10, 50, 200, 14);
		JTextField titleField = new JTextField();
		titleField.setBounds(10, 70, 200, 25);

		JLabel userLabel = new JLabel("User");
		userLabel.setBounds(10, 100, 200, 14);
		JTextField userField = new JTextField();
		userField.setBounds(10, 120, 200, 25);

		JLabel numOfSongsLabel = new JLabel("Number of Songs");
		numOfSongsLabel.setBounds(10, 150, 200, 14);
		JTextField numOfSongsField = new JTextField();
		numOfSongsField.setBounds(10, 170, 200, 25);

		JLabel[] labels = { insertLabel, titleLabel, userLabel, numOfSongsLabel };
		JTextField[] textFields = { titleField, userField, numOfSongsField };

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
		frame.getContentPane().removeAll();
		frame.repaint();

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
