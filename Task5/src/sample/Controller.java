package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Controller {
    /**
     * Start button
     * @param actionEvent
     * @throws Exception
     */
    public void Start(ActionEvent actionEvent) throws Exception{
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("FormFXML.fxml"));
        root.getStylesheets().add(getClass().getResource("/css/Form.css").toString());
        newStage.getIcons().add(new Image(getClass().getResourceAsStream("/img/virus.png")));
        newStage.setScene(new Scene(root, 800,450));
        newStage.setTitle("Covid-19 Vaccination Center");
        newStage.show();

        Stage previousStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }
}
