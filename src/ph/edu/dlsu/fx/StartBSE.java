package ph.edu.dlsu.fx;

import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.opencv.core.Mat;
import ph.edu.dlsu.fx.utils.ScreenSize;
import ph.edu.dlsu.fx.utils.Utils;
import ph.edu.dlsu.fx.vision.ObjectDetector;

/**
 * Created by cobalt on 3/6/16.
 */
public class StartBSE extends BaseCameraScene {

    // cascade classifier
    private ObjectDetector faceDetector = new ObjectDetector();


    // Create content for the Main Menu scene
    public Parent createStartContent() {

        // Get the screen size
        ScreenSize screen = new ScreenSize();
        displayWidth = screen.getDisplayWidth();
        displayHeight = screen.getDisplayHeight();

        // Frame size
        frameWidth = 0.8 * displayWidth;
        frameHeight = displayHeight;

        // Create Main Menu pane
        Pane rootNode = new Pane();
        rootNode.setPrefSize(displayWidth, displayHeight);

        // Initialize background image and load to Imageview
        ImageView imgBackground = Utils.loadImage2View("res/BSEdepth2.png", displayWidth, displayHeight);
        if (imgBackground != null) {
            rootNode.getChildren().add(imgBackground);
        }

        currentFrame = Utils.loadImage2View("res/video.jpg", frameWidth, frameHeight);
        currentFrame.setTranslateX((displayWidth - frameWidth) / 2.0);
        currentFrame.setTranslateY(0);
        rootNode.getChildren().add(currentFrame);
        startCamera();

        // Create Menu title and content
        createHMenu();

        // Add menu w/ title in the Pane
        rootNode.getChildren().add(menuBox);

        return rootNode;
    }

    public void createHMenu() {
        final CustomMenuItem home = new CustomMenuItem("HOME", menuWidth, menuHeight);
        final CustomMenuItem training = new CustomMenuItem("TRAINING", menuWidth, menuHeight);
        final CustomMenuItem browse = new CustomMenuItem("FACTS", menuWidth, menuHeight);
        final CustomMenuItem help = new CustomMenuItem("HELP", menuWidth, menuHeight);
        final CustomMenuItem about = new CustomMenuItem("ABOUT", menuWidth, menuHeight);
        final CustomMenuItem exit = new CustomMenuItem("EXIT", menuWidth, menuHeight);

        // handle menu events
        home.setOnMouseClicked(e -> {
            stopCamera();
            App.onHome();
        });

        training.setOnMouseClicked(e -> {
                    stopCamera();
                    App.onTraining();
                }
        );

        exit.setOnMouseClicked(e -> {
            Boolean confirmQuit = App.onExit();
            if (confirmQuit) {
                stopCamera();
            }
        });

        menuBox = new MenuHBox(
                home,
                training,
                browse,
                help,
                about,
                exit);

        menuBox.setTranslateX((displayWidth - 6 * menuWidth) / 2.0);
        menuBox.setTranslateY(0);
    }

    @Override
    public void onCameraFrame(Mat frame) {
        faceDetector.detectAndDisplay(frame);
    }
}