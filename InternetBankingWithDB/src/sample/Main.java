package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

//  Bernard MT Condensed
// login-button
// ..\Style\style.css

// 4404103876727001

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        primaryStage.setTitle("ABCD BANK");
        primaryStage.getIcons().add(new Image("/sample/3rd.jpg"));
        Scene scene = new Scene(root, 800, 800); //"/image/login.png"
        scene.getStylesheets().add("/Style/style.css");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> Platform.exit());
    }


    public static void main(String[] args) {
        launch(args);
    }
}
