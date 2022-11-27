package view;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

/* The cleanest view possible */
public class IqaFrame extends JFrame {
	private final ButtonGroup radioButtonGroup;
	private JPanel mainPanel;
	private JPanel bottomPanel;
	private JPanel timePanel;
	private JButton startButton;
	private JButton restartButton;
	private JLabel nameLabel;
	private JTextField nameTextField;
	private JLabel imageNumberLabel;
	private JLabel timeLabel;
	private JPanel mosPanel;
	private JLabel mosLabel;
	private JRadioButton radioButton1;
	private JRadioButton radioButton2;
	private JRadioButton radioButton3;
	private JRadioButton radioButton4;
	private JRadioButton radioButton5;
	private JButton nextImageButton;
	private JPanel imagePanel;
	private JLabel displayLabel;

	public IqaFrame() {

		radioButtonGroup = new ButtonGroup();
		radioButtonGroup.add(radioButton1);
		radioButtonGroup.add(radioButton2);
		radioButtonGroup.add(radioButton3);
		radioButtonGroup.add(radioButton4);
		radioButtonGroup.add(radioButton5);

	}

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public JButton getStartButton() {
		return startButton;
	}

	public JButton getRestartButton() {
		return restartButton;
	}

	public JTextField getNameTextField() {
		return nameTextField;
	}

	public JLabel getImageNumberLabel() {
		return imageNumberLabel;
	}

	public JRadioButton getRadioButton1() {
		return radioButton1;
	}

	public JRadioButton getRadioButton2() {
		return radioButton2;
	}

	public JRadioButton getRadioButton3() {
		return radioButton3;
	}

	public JRadioButton getRadioButton4() {
		return radioButton4;
	}

	public JRadioButton getRadioButton5() {
		return radioButton5;
	}

	public ButtonGroup getRadioButtonGroup() {
		return radioButtonGroup;
	}

	public JButton getNextImageButton() {
		return nextImageButton;
	}

	public JLabel getDisplayLabel() {
		return displayLabel;
	}

	public ArrayList<JRadioButton> getRadioButtonLinkedList() {

		return new ArrayList<>(Arrays.asList(radioButton1, radioButton2, radioButton3, radioButton4,
				radioButton5));

	}

}
