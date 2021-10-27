package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FormController {
    private static String fName, sName, city, nic, RadioButValue;
    private static int age;
    private int boothNo;

    String[] array = {".", ".", ".", ".", ".", ".",};

    @FXML
    public TextField FormFirstName, FormSurname, FormAge, FormCity, FormNIC;

    @FXML
    public RadioButton FormVac1, FormVac2, FormVac3;

    boolean valid = false;

    /**
     * generate receipt function
     * @param actionEvent
     * @throws Exception
     */
    @FXML
    public void GenerateReceipt(ActionEvent actionEvent) throws Exception{
        PassData();

        if (valid){
            Stage newFormStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Receipt.fxml"));
            Parent root = loader.load();
            root.getStylesheets().add(getClass().getResource("/css/Receipt.css").toString());
            newFormStage.getIcons().add(new Image(getClass().getResourceAsStream("/img/virus.png")));
            newFormStage.setScene(new Scene(root, 630,450));
            newFormStage.setTitle("Covid-19 Vaccination Center");
            newFormStage.show();

            Stage previousStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            previousStage.close();

            ReceiptController receipt = loader.getController();
            receipt.SetText(fName, sName, city, nic, age, RadioButValue, boothNo);
            valid = false;
        }
    }

    /**
     * get data/validate/pass data
     */
    public void PassData()
    {
        if (FormFirstName.getText().length() == 0)
        {
            alert("Field Empty","Please enter your first name");
        }else if (FormSurname.getText().length() == 0){
            alert("Field Empty","Please enter your surname");
        }
        else if (FormCity.getText().length() == 0){
            alert("Field Empty","Please enter your city");
        }
        else if (FormNIC.getText().length() == 0){
            alert("Field Empty","Please enter your NIC/Passport number");
        }
        else if (FormAge.getText().length() == 0){
            alert("Field Empty","Please enter your age");
        }
        else if (Integer.parseInt(FormAge.getText()) < 18 && Integer.parseInt(FormAge.getText()) > 100){
            alert("Age not within range","Please enter a valid age to vaccinate");
        }
        else {
            fName = FormFirstName.getText();
            sName = FormSurname.getText();
            city = FormCity.getText();
            nic = FormNIC.getText();
            age = Integer.parseInt(FormAge.getText());

            ToggleGroup tg = new ToggleGroup();
            FormVac1.setToggleGroup(tg);
            FormVac2.setToggleGroup(tg);
            FormVac3.setToggleGroup(tg);

            RadioButton rb = (RadioButton)tg.getSelectedToggle();


            if (rb != null)
            {
                RadioButValue = rb.getText();
                if (RadioButValue.equals("AstraZeneca") && array[0].equals("."))
                {
                    boothNo = 0;
                    array[0] = "occupied";
                    valid = true;
                }
                else if (RadioButValue.equals("AstraZeneca") && array[1].equals("."))
                {
                    boothNo = 1;
                    array[1] = "occupied";
                    valid = true;
                }
                else if (RadioButValue.equals("Sinopharm") && array[2].equals("."))
                {
                    boothNo = 2;
                    array[2] = "occupied";
                    valid = true;
                }
                else if (RadioButValue.equals("Sinopharm") && array[3].equals("."))
                {
                    boothNo = 3;
                    array[3] = "occupied";
                    valid = true;
                }
                else if (RadioButValue.equals("Pfizer") && array[4].equals("."))
                {
                    boothNo = 4;
                    array[4] = "occupied";
                    valid = true;
                }
                else if (RadioButValue.equals("Pfizer") && array[5].equals("."))
                {
                    boothNo = 5;
                    array[5] = "occupied";
                    valid = true;
                }
                else {
                    alert("Booth full", "Please try again in 5 minutes");
                }
            }
            else {
                alert("Select Vaccine", "Please select a vaccine type");
            }
        }
    }

    /**
     * shows a alert
     * @param title
     * @param text
     */
    public void alert(String title, String text)
    {
        // create a alert
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        Stage stage = (Stage) a.getDialogPane().getScene().getWindow();
        stage.setAlwaysOnTop(true);
        // set alert type
        a.setAlertType(Alert.AlertType.INFORMATION);
        a.setTitle(title);
        a.setHeaderText(text);
        a.setContentText("");

        // show the dialog
        a.show();
    }

    /**
     * clear the text
     */
    public void clear()
    {
        FormFirstName.clear();
        FormSurname.clear();
        FormAge.clear();
        FormNIC.clear();
        FormCity.clear();
    }
}
