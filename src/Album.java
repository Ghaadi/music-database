import java.awt.Font;
import java.util.Arrays;

import javax.swing.*;

public class Album implements IOption {

	final private JFrame frame;

	public Album(JFrame frame) {
		this.frame = frame;
	}

	@Override
	public void retrieve() {
		frame.getContentPane().removeAll();
		frame.repaint();

		JLabel lblNewLabel = new JLabel("Retrieve Albums:");
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

		JLabel insertLabel = new JLabel("Insert Album:");
		insertLabel.setBounds(10, 10, 200, 20);

		JLabel titleLabel = new JLabel("Title");
		titleLabel.setBounds(10, 50, 200, 14);
		JTextField titleField = new JTextField();
		titleField.setBounds(10, 70, 200, 25);

		JLabel artistLabel = new JLabel("Artist");
		artistLabel.setBounds(10, 100, 200, 14);
		JTextField artistField = new JTextField();
		artistField.setBounds(10, 120, 200, 25);

		JLabel numOfSongsLabel = new JLabel("Number of Songs");
		numOfSongsLabel.setBounds(10, 150, 200, 14);
		JTextField numOfSongsField = new JTextField();
		numOfSongsField.setBounds(10, 170, 200, 25);

		JLabel ratingsLabel = new JLabel("Ratings");
		ratingsLabel.setBounds(10, 200, 200, 14);
		JTextField ratingsField = new JTextField();
		ratingsField.setBounds(10, 220, 200, 25);

		JLabel genreLabel = new JLabel("Genre");
		genreLabel.setBounds(10, 250, 200, 14);
		JTextField genreField = new JTextField();
		genreField.setBounds(10, 270, 200, 25);

		JLabel releaseDateLabel = new JLabel("Release Date");
		releaseDateLabel.setBounds(10, 300, 200, 14);
		JTextField releaseDateField = new JTextField();
		releaseDateField.setBounds(10, 320, 200, 25);

		JLabel durationLabel = new JLabel("Duration");
		durationLabel.setBounds(10, 350, 200, 14);
		JTextField durationField = new JTextField();
		durationField.setBounds(10, 370, 200, 25);

		JLabel recordLabelNameLabel = new JLabel("Record Label Name");
		recordLabelNameLabel.setBounds(10, 400, 200, 14);
		JTextField recordLabelNameField = new JTextField();
		recordLabelNameField.setBounds(10, 420, 200, 25);

		JLabel[] labels = { insertLabel, titleLabel, artistLabel, numOfSongsLabel, ratingsLabel, genreLabel,
				releaseDateLabel, durationLabel, recordLabelNameLabel };
		JTextField[] textFields = { titleField, artistField, numOfSongsField, ratingsField, genreField,
				releaseDateField, durationField, recordLabelNameField };

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

		JLabel lblNewLabel = new JLabel("Delete Album:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 10, 200, 20);
		frame.getContentPane().add(lblNewLabel);

		JTextField textField = new JTextField();
		textField.setBounds(120, 9, 150, 22);
		frame.getContentPane().add(textField);
	}

	public String toString() {
		return "Albums";
	}

}
