package controller;

import model.ImageRepo;

public class Main {

	private static final String imagePath = "./images";

	public static void main(String[] args) {

		ImageRepo repo = new ImageRepo(imagePath);

	}

}
