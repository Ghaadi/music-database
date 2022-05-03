import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;

import javax.swing.*;

public class Releasable implements IOption {

	final private JFrame frame;
	final ButtonGroup btnGroup = new ButtonGroup();
	final private Connection connection;

	public Releasable(JFrame frame, Connection c) {
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

	private void proceedAction(JButton proceedButton) {
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
							Arrays.asList(new Song(frame, connection), new Album(frame), new Playlist(frame, connection)));
					String selection = getSelectedButton(btnGroup);
					if (getSelectedButton(btnGroup).equals("Songs")) {
						options.forEach((IOption option) -> {
							if (selection.equals(option.toString())) {
								option.insert();
							}
						});
					} else if (getSelectedButton(btnGroup).equals("Albums")) {
						options.forEach((IOption option) -> {
							if (selection.equals(option.toString())) {
								option.insert();
							}
						});
					} else if (getSelectedButton(btnGroup).equals("Playlists")) {
						options.forEach((IOption option) -> {
							if (selection.equals(option.toString())) {
								option.insert();
							}
						});
					}
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
		JLabel lblNewLabel = new JLabel("Retrieve Songs:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 10, 200, 20);
		frame.getContentPane().add(lblNewLabel);

		JTextField textField = new JTextField();
		textField.setBounds(120, 9, 150, 22);
		frame.getContentPane().add(textField);
	}

	public void insert() {
		JButton proceedButton = new JButton("Proceed");
		proceedButton.setBounds(535, 392, 89, 23);
		frame.getContentPane().add(proceedButton);
		proceedAction(proceedButton);

		JButton backButton = new JButton("Back");
		backButton.setBounds(10, 392, 89, 23);
		frame.getContentPane().add(backButton);
		backAction(backButton);
		
		JLabel insertLabel = new JLabel("Insert Releasable:");
		insertLabel.setBounds(10, 10, 200, 20);

		JLabel artistIdLabel = new JLabel("Artist ID");
		artistIdLabel.setBounds(10, 50, 200, 14);
		JTextField artistIdField = new JTextField();
		artistIdField.setBounds(10, 70, 200, 25);

		JLabel genreLabel = new JLabel("Genre");
		genreLabel.setBounds(10, 100, 200, 14);
		JTextField genreField = new JTextField();
		genreField.setBounds(10, 120, 200, 25);

		JLabel releaseDateLabel = new JLabel("Release Date");
		releaseDateLabel.setBounds(10, 150, 200, 14);
		JTextField releaseDateField = new JTextField();
		releaseDateField.setBounds(10, 170, 200, 25);

		JLabel durationLabel = new JLabel("Duration");
		durationLabel.setBounds(10, 200, 200, 14);
		JTextField durationField = new JTextField();
		durationField.setBounds(10, 220, 200, 25);

		JLabel recordLabelNameLabel = new JLabel("Record Label Name");
		recordLabelNameLabel.setBounds(10, 250, 200, 14);
		JTextField recordLabelNameField = new JTextField();
		recordLabelNameField.setBounds(10, 270, 200, 25);

		JRadioButton radioBtn1 = new JRadioButton("Songs");
		radioBtn1.setBounds(400, 60, 109, 23);

		JRadioButton radioBtn2 = new JRadioButton("Albums");
		radioBtn2.setBounds(400, 100, 109, 23);

		JRadioButton radioBtn3 = new JRadioButton("Playlists");
		radioBtn3.setBounds(400, 80, 109, 23);

		JLabel[] labels = { insertLabel, artistIdLabel, genreLabel, releaseDateLabel, durationLabel,
				recordLabelNameLabel };
		JTextField[] textFields = { artistIdField, genreField, releaseDateField, durationField, recordLabelNameField };
		JRadioButton[] buttons = { radioBtn1, radioBtn2, radioBtn3 };

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
		return "Releasables";
	}

}
