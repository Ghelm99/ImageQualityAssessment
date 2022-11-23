package model;

import java.awt.*;

public class ImageData {

	private final Image image;
	private final int impairmentLevel;
	private final int mosEvaluation;

	private final String imageFileName;

	public ImageData(Image image, int impairmentLevel, int mosEvaluation, String imageFileName) {
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

	public String getImageFileName() {
		return imageFileName;
	}

	@Override
	public String toString() {
		return "ImageData{" +
				"image=" + image +
				", impairmentLevel=" + impairmentLevel +
				", mosEvaluation=" + mosEvaluation +
				'}';
	}
}
