package controller;

import com.opencsv.CSVWriter;
import model.ImageData;
import model.ImageRepo;
import view.IqaFrame;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Optional;

public class IqaFrameController {

	private final IqaFrame view;
	private final ImageRepo model;
	private final ArrayList<JRadioButton> radioButtonLinkedList;
	private ArrayList<ImageData> evaluatedImageData;
	private Iterator<ImageData> imageDataIterator;
	private ImageData currentImage;
	private int currentImageNumber;
	private String evaluatorName;

	public IqaFrameController(IqaFrame view, ImageRepo model) {

		this.view = view;
		this.model = model;

		initView();
		initController();

		radioButtonLinkedList = view.getRadioButtonLinkedList();
		currentImageNumber = 0;

	}

	private void initView() {

		view.setTitle("Image Quality Assessment Software");
		view.setExtendedState(Frame.MAXIMIZED_BOTH);
		view.setUndecorated(false);
		view.setContentPane(view.getMainPanel());
		view.setVisible(true);
		view.setDefaultCloseOperation(IqaFrame.EXIT_ON_CLOSE);

		goToStartTheTestState();

	}

	private void initController() {

		view.getStartButton().addActionListener(e -> startButtonClicked());
		view.getRestartButton().addActionListener(e -> restartButtonClicked());
		view.getRadioButton1().addActionListener(e -> radioButtonClicked(view.getRadioButton1()));
		view.getRadioButton2().addActionListener(e -> radioButtonClicked(view.getRadioButton2()));
		view.getRadioButton3().addActionListener(e -> radioButtonClicked(view.getRadioButton3()));
		view.getRadioButton4().addActionListener(e -> radioButtonClicked(view.getRadioButton4()));
		view.getRadioButton5().addActionListener(e -> radioButtonClicked(view.getRadioButton5()));
		view.getNextImageButton().addActionListener(e -> nextImageClicked());

	}

	private void startButtonClicked() {

		String name = view.getNameTextField().getText().trim();

		if (name.equals("")) {

			view.getDisplayLabel().setText("Please insert your name!");

		} else {

			evaluatorName = name;

			goToEvaluationState();

			Collections.shuffle(model.getToBeEvaluatedList());
			evaluatedImageData = new ArrayList<>();
			imageDataIterator = model.getToBeEvaluatedList().iterator();
			nextImage();

		}

	}

	private void restartButtonClicked() {

		evaluatedImageData.clear();
		Collections.shuffle(model.getToBeEvaluatedList());
		currentImageNumber = 0;
		imageDataIterator = model.getToBeEvaluatedList().iterator();

		goToStartTheTestState();
	}

	private void nextImageClicked() {

		if (selectedRadioButton().isPresent()) {

			currentImage.setMosEvaluation(Integer.parseInt(selectedRadioButton().get().getText().trim()));
			evaluatedImageData.add(currentImage);

			view.getRadioButtonGroup().clearSelection();
			nextImage();

		}

	}

	private void radioButtonClicked(JRadioButton radioButton) {

		radioButton.setSelected(true);

	}

	private void nextImage() {

		if (imageDataIterator.hasNext()) {

			currentImageNumber++;
			currentImage = imageDataIterator.next();
			view.getDisplayLabel().setIcon(new ImageIcon(currentImage.getImage()));
			view.getImageNumberLabel().setText("Image " + currentImageNumber);

		} else {

			writeAllToCsv();
			goToDataSavedState();

		}
	}

	private void writeAllToCsv() {

		CSVWriter writer;

		try {

			writer = new CSVWriter(new FileWriter(model.getRepoFile().getPath(), true));

		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		for (ImageData toBeWritten : evaluatedImageData) {
			String[] toBeWrittenSting = {toBeWritten.getImageFileName(),
					String.valueOf(toBeWritten.getImpairmentLevel()),
					String.valueOf(toBeWritten.getMosEvaluation()),
					evaluatorName.trim()
			};
			writer.writeNext(toBeWrittenSting);
		}

		try {
			writer.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	private void setRadioButtonsEnabled(boolean value) {

		view.getRadioButton1().setEnabled(value);
		view.getRadioButton2().setEnabled(value);
		view.getRadioButton3().setEnabled(value);
		view.getRadioButton4().setEnabled(value);
		view.getRadioButton5().setEnabled(value);

	}

	private Optional<JRadioButton> selectedRadioButton() {

		return radioButtonLinkedList.stream().parallel().filter(AbstractButton::isSelected).findAny();

	}

	private void goToStartTheTestState() {

		view.getDisplayLabel().setIcon(null);
		view.getDisplayLabel().setText("Start the test!");
		view.getStartButton().setEnabled(true);
		view.getRestartButton().setEnabled(false);
		view.getNameTextField().setText("");
		view.getNameTextField().setEnabled(true);
		view.getImageNumberLabel().setVisible(false);
		setRadioButtonsEnabled(false);
		view.getNextImageButton().setEnabled(false);

	}

	private void goToEvaluationState() {

		view.getDisplayLabel().setText("");
		view.getStartButton().setEnabled(false);
		view.getRestartButton().setEnabled(true);
		view.getNameTextField().setEnabled(false);
		view.getImageNumberLabel().setVisible(true);
		setRadioButtonsEnabled(true);
		view.getNextImageButton().setEnabled(true);

	}

	private void goToDataSavedState() {

		view.getDisplayLabel().setIcon(null);
		view.getDisplayLabel().setText("Data saved!");
		view.getStartButton().setEnabled(false);
		view.getRestartButton().setEnabled(true);
		view.getNameTextField().setEnabled(false);
		view.getImageNumberLabel().setVisible(false);
		setRadioButtonsEnabled(false);
		view.getNextImageButton().setEnabled(false);

	}

}
