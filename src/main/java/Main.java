import controller.IqaFrameController;
import model.ImageRepo;
import view.IqaFrame;

/* The cleanest main possible */
public class Main {

	private static final String imagePath = "./images";
	private static final String repoFilePath = "./repo.csv";

	public static void main(String[] args) {

		IqaFrameController controller = new IqaFrameController(
				new IqaFrame(),
				new ImageRepo(imagePath, repoFilePath)
		);

	}

}
