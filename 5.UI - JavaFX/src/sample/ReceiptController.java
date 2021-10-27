package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class ReceiptController implements Initializable {                                  //https://www.youtube.com/watch?v=SGZUQvuqL5Q

    @FXML
    private Label FirstName, Surname, Age, City, NIC, VaccineType, Booth, date, time;

    /**
     * back button
     * @param actionEvent
     * @throws Exception
     */
    public void Back(ActionEvent actionEvent) throws Exception{
        Stage PrevStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("FormFXML.fxml"));
        root.getStylesheets().add(getClass().getResource("/css/Form.css").toString());
        PrevStage.getIcons().add(new Image(getClass().getResourceAsStream("/img/virus.png")));
        PrevStage.setScene(new Scene(root, 800,450));
        PrevStage.setTitle("Covid-19 Vaccination Center");
        PrevStage.show();

        Stage previousStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }

    /**
     * close button
     * @param actionEvent
     * @throws Exception
     */
    public void close(ActionEvent actionEvent) throws Exception
    {
        Stage previousStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }

    /**
     * sets text by passed value
     * @param first
     * @param sur
     * @param city
     * @param nic
     * @param age
     * @param radio
     * @param booth
     */
    public void SetText(String first, String sur, String city, String nic, int age, String radio, int booth)
    {
        FirstName.setText(first);
        Surname.setText(sur);
        City.setText(city);
        NIC.setText(nic);
        Age.setText(String.valueOf(age));
        VaccineType.setText(radio);
        Booth.setText(String.valueOf(booth));
        dateValueSet();
        TimeValueSet();
    }

    /**
     * set date
     */
    public void dateValueSet()
    {
        Date day = new Date();
        SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
        date.setText(currentDate.format(day));
        SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm");
        time.setText(currentTime.format(day));
    }

    /**
     * set time
     */
    public void TimeValueSet()
    {
        Date day = new Date();
        SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm");
        time.setText(currentTime.format(day));
    }

    /**
     * initialize the class to be called
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
