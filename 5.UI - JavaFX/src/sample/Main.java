package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    /**
     * first stage
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        root.getStylesheets().add(getClass().getResource("/css/MainStyle.css").toString());
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/img/virus.png")));
        primaryStage.setTitle("Covid-19 Vaccination Center");
        primaryStage.setScene(new Scene(root, 600, 450));
        primaryStage.show();
    }


    /**
     * main
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
