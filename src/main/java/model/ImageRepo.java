package model;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ImageRepo {

	private final HashSet<ImageData> imageSet;

	/**
	 * Class constructor, used for instantiate a full instance of the ImageRepo.
	 *
	 * @param imageFolderPath refers to the absolute path (starting from the project directory) of directory containing
	 *                        images.
	 */
	public ImageRepo(String imageFolderPath) {
		imageSet = new HashSet<>();

		try {
			fillImageRepo(imageFolderPath);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Simple imageSet getter method.
	 */
	public HashSet<ImageData> getImageSet() {
		return imageSet;
	}

	/**
	 * Private method used to fill the image HashSet.
	 *
	 * @param imageFolderPath refers to the absolute path (starting from the project directory) of directory containing
	 *                        *                        images.
	 */
	private void fillImageRepo(String imageFolderPath) throws IOException {
		File imageDirectory = new File(imageFolderPath);
		File[] imageFiles = imageDirectory.listFiles();

		assert imageFiles != null;
		Iterator<File> iterator = Arrays.stream(imageFiles).iterator();

		while (iterator.hasNext()) {
			imageSet.add(fromImageFileToImageData(iterator.next()));
		}
	}

	/**
	 * Private method used to convert an ImageFile to an ImageData object.
	 *
	 * @param imageFile specifies the imageFile to be converted into ImageData.
	 */
	private ImageData fromImageFileToImageData(File imageFile) throws IOException {

		List<String> tokens = Collections.list(new StringTokenizer(imageFile.getName(), "."))
				.stream().map(token -> (String) token)
				.collect(Collectors.toList());

		String impairmentLevel = String.valueOf(tokens.get(0).charAt(tokens.get(0).length() - 1));
		return new ImageData(ImageIO.read(imageFile), Integer.parseInt(impairmentLevel), 0, imageFile.getName());
	}

}
