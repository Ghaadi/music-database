import java.awt.EventQueue;
import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class GUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		frame = new JFrame();
		frame.setBounds(150, 150, 650, 465);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		initialize(frame);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	void initialize(JFrame frame) {

		JLabel lblNewLabel = new JLabel("Choose an action:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 10, 119, 14);
		frame.getContentPane().add(lblNewLabel);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "", "Retrieve", "Insert", "Update", "Delete" }));
		comboBox.setBounds(139, 9, 91, 22);
		ButtonGroup btnGroup = new ButtonGroup();

		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!comboBox.getSelectedItem().equals(null)) {
					JRadioButton radioBtn1 = new JRadioButton("Songs");
					radioBtn1.setBounds(10, 50, 109, 23);
					frame.getContentPane().add(radioBtn1);

					JRadioButton radioBtn2 = new JRadioButton("Albums");
					radioBtn2.setBounds(10, 70, 109, 23);
					frame.getContentPane().add(radioBtn2);

					JRadioButton radioBtn3 = new JRadioButton("Playlists");
					radioBtn3.setBounds(10, 90, 109, 23);
					frame.getContentPane().add(radioBtn3);

					JRadioButton radioBtn4 = new JRadioButton("Singers");
					radioBtn4.setBounds(10, 110, 109, 23);
					frame.getContentPane().add(radioBtn4);

					JRadioButton radioBtn5 = new JRadioButton("Bands");
					radioBtn5.setBounds(10, 130, 109, 23);
					frame.getContentPane().add(radioBtn5);

					JRadioButton radioBtn6 = new JRadioButton("Users");
					radioBtn6.setBounds(10, 150, 109, 23);
					frame.getContentPane().add(radioBtn6);

					btnGroup.add(radioBtn1);
					btnGroup.add(radioBtn2);
					btnGroup.add(radioBtn3);
					btnGroup.add(radioBtn4);
					btnGroup.add(radioBtn5);
					btnGroup.add(radioBtn6);
					frame.repaint();
				}
			}
		});

		frame.getContentPane().add(comboBox);

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
					ArrayList<IOption> options = new ArrayList<>(Arrays.asList(new Song(frame), new Album(frame),
							new Playlist(frame), new Singer(frame), new Band(frame), new User(frame)));
					String selection = getSelectedButton(btnGroup);
					if (comboBox.getSelectedItem().equals("Retrieve")) {
						options.forEach((IOption option) -> {
							if (selection.equals(option.toString())) {
								option.retrieve();
							}
						});
					} else if (comboBox.getSelectedItem().equals("Insert")) {
						options.forEach((IOption option) -> {
							if (selection.equals(option.toString())) {
								option.insert();
							}
						});
					} else if (comboBox.getSelectedItem().equals("Update")) {
						options.forEach((IOption option) -> {
							if (selection.equals(option.toString())) {
								option.update();
							}
						});
					} else if (comboBox.getSelectedItem().equals("Delete")) {
						options.forEach((IOption option) -> {
							if (selection.equals(option.toString())) {
								option.delete();
							}
						});
					}
				}
			}
		});

	}

	public String getSelectedButton(ButtonGroup btnGroup) {

		for (Enumeration<AbstractButton> buttons = btnGroup.getElements(); buttons.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();

			if (button.isSelected()) {
				return button.getText();
			}
		}

		return null;

	}

}
