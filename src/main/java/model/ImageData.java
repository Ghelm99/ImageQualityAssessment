package model;

import java.awt.*;

public class ImageData {

	private final Image image;

	private final int impairmentLevel;
	private final String imageFileName;
	private int mosEvaluation;

	ImageData(Image image, int impairmentLevel, int mosEvaluation, String imageFileName) {
		this.image = image;
		this.impairmentLevel = impairmentLevel;
		this.mosEvaluation = mosEvaluation;
		this.imageFileName = imageFileName;
	}

	public Image getImage() {
		return image;
	}

	public int getImpairmentLevel() {
		return impairmentLevel;
	}

	public int getMosEvaluation() {
		return mosEvaluation;
	}

	public void setMosEvaluation(int mosEvaluation) {
		this.mosEvaluation = mosEvaluation;
	}

	public String getImageFileName() {
		return imageFileName;
	}

}
