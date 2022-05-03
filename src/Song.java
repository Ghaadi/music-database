import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.*;
import java.sql.*;

public class Song implements IOption {

	final private JFrame frame;
	final private Connection connection;
	
	public Song(JFrame frame, Connection c) {
		this.frame = frame;
		this.frame.getContentPane().removeAll();
		this.frame.repaint();
		
		JButton proceedButton = new JButton("Proceed");
		proceedButton.setBounds(535, 392, 89, 23);
		frame.getContentPane().add(proceedButton);

		JButton backButton = new JButton("Back");
		backButton.setBounds(10, 392, 89, 23);
		frame.getContentPane().add(backButton);
		backAction(backButton);
		
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

	public void retrieve() {
		JLabel lblNewLabel = new JLabel("Retrieve Songs:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 10, 200, 20);
		frame.getContentPane().add(lblNewLabel);

		JTextField textField = new JTextField();
		textField.setBounds(120, 9, 150, 22);
		frame.getContentPane().add(textField);
	}

	public void insert() {
		JLabel insertLabel = new JLabel("Insert Song:");
		insertLabel.setBounds(10, 10, 200, 20);

		JLabel titleLabel = new JLabel("Title");
		titleLabel.setBounds(10, 50, 200, 14);
		JTextField titleField = new JTextField();
		titleField.setBounds(10, 70, 200, 25);

		JLabel releasableIdLabel = new JLabel("Releasable ID");
		releasableIdLabel.setBounds(10, 100, 200, 14);
		JTextField releasableIdField = new JTextField();
		releasableIdField.setBounds(10, 120, 200, 25);

		JLabel musicIdLabel = new JLabel("Music ID");
		musicIdLabel.setBounds(10, 150, 200, 14);
		JTextField musicIdField = new JTextField();
		musicIdField.setBounds(10, 170, 200, 25);

		JLabel albumIdLabel = new JLabel("Album ID");
		albumIdLabel.setBounds(10, 200, 200, 14);
		JTextField albumIdField = new JTextField();
		albumIdField.setBounds(10, 220, 200, 25);

		JLabel[] labels = { insertLabel, titleLabel, releasableIdLabel, musicIdLabel, albumIdLabel };
		JTextField[] textFields = { titleField, releasableIdField, musicIdField, albumIdField };

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
		return "Songs";
	}

}
