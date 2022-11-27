package model;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ImageRepo {

	private final ArrayList<ImageData> toBeEvaluatedList;

	private final File repoFile;

	/**
	 * Class constructor, used for instantiate a full instance of the ImageRepo.
	 *
	 * @param imageFolderPath refers to the absolute path (starting from the project directory) of directory containing
	 *                        images.
	 * @param repoFilePath    refers to the absolute path (starting from the project directory) of the CSV file used to
	 *                        save the assessment results.
	 */
	public ImageRepo(String imageFolderPath, String repoFilePath) {
		toBeEvaluatedList = new ArrayList<>();

		try {
			fillImageRepo(imageFolderPath);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		repoFile = new File(repoFilePath);

	}

	/**
	 * Private method used to fill the image HashSet.
	 *
	 * @param imageFolderPath refers to the absolute path (starting from the project directory) of directory containing
	 *                                           images.
	 */
	private void fillImageRepo(String imageFolderPath) throws IOException {

		File imageDirectory = new File(imageFolderPath);
		File[] imageFiles = imageDirectory.listFiles();

		Iterator<File> iterator = null;
		if (imageFiles != null) {
			iterator = Arrays.stream(imageFiles).iterator();

			while (iterator.hasNext()) {
				toBeEvaluatedList.add(fromImageFileToImageData(iterator.next()));
			}
		} else {

			throw new IOException("The imageFolder doesn't contain images!");

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

	/**
	 * Simple repoFile getter method.
	 */
	public File getRepoFile() {
		return repoFile;
	}

	/**
	 * Simple imageSet getter method.
	 */
	public ArrayList<ImageData> getToBeEvaluatedList() {
		return toBeEvaluatedList;
	}
}
