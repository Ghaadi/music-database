import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.*;
import java.sql.*;

public class RecordLabel implements IOption {

	final private JFrame frame;
	final private Connection connection;
	
	public RecordLabel(JFrame frame, Connection c) {
		this.frame = frame;
		this.frame.getContentPane().removeAll();
		this.frame.repaint();
		this.connection = c;
//		JButton proceedButton = new JButton("Proceed");
//		proceedButton.setBounds(535, 392, 89, 23);
//		frame.getContentPane().add(proceedButton);

		JButton backButton = new JButton("Back");
		backButton.setBounds(10, 392, 89, 23);
		frame.getContentPane().add(backButton);
		backAction(backButton);
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
		JLabel lblNewLabel = new JLabel("Retrieve Record Label:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 10, 200, 20);
		frame.getContentPane().add(lblNewLabel);

		JTextField textField = new JTextField();
		textField.setBounds(170, 9, 150, 22);
		frame.getContentPane().add(textField);
	}

	public void insert() {
		JLabel insertLabel = new JLabel("Insert Record Label:");
		insertLabel.setBounds(10, 10, 200, 20);

		JLabel nameLabel = new JLabel("Name");
		nameLabel.setBounds(10, 50, 200, 14);
		JTextField nameField = new JTextField();
		nameField.setBounds(10, 70, 200, 25);

		JLabel founderLabel = new JLabel("Founder");
		founderLabel.setBounds(10, 100, 200, 14);
		JTextField founderField = new JTextField();
		founderField.setBounds(10, 120, 200, 25);

		JLabel distributorLabel = new JLabel("Distributor");
		distributorLabel.setBounds(10, 150, 200, 14);
		JTextField distributorField = new JTextField();
		distributorField.setBounds(10, 170, 200, 25);

		JLabel[] labels = { insertLabel, nameLabel, founderLabel, distributorLabel };
		JTextField[] textFields = { nameField, founderField, distributorField };

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
		JLabel lblNewLabel = new JLabel("Delete Record Label:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 10, 200, 20);
		frame.getContentPane().add(lblNewLabel);

		JTextField textField = new JTextField();
		textField.setBounds(120, 9, 150, 22);
		frame.getContentPane().add(textField);
	}

	@Override
	public String toString() {
		return "Record Labels";
	}

}
