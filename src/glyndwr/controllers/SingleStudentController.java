package glyndwr.controllers;

import glyndwr.dao.StudentDao;
import glyndwr.models.Module;
import glyndwr.models.Student;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by sajith on 12/22/18
 */
public class SingleStudentController implements Initializable {
    @FXML
    Label name,average,status,rank;

    @FXML
    GridPane gridPane;

    @FXML
    Button btnHome;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Student student = LeaderboardController.selectedStudent;
        name.setText(student.getName());
        average.setText(String.valueOf(student.getAverage()));
        status.setText(student.getStatus());
        rank.setText(String.valueOf(student.getRank()));

        for (int i = 0; i < student.getModules().size(); i++) {

            Label label = new Label(student.getModules().get(i).getName());
            label.setStyle("-fx-font-weight: bold");
            gridPane.add(label, 0, i+3);

            gridPane.add(new Label(String.valueOf(student.getModules().get(i).getMarks())), 1, i+3);
        }
    }

    public void viewHome() throws IOException {
        Stage stage = (Stage) btnHome.getScene().getWindow();
        Pane root = FXMLLoader.load(getClass().getResource("/glyndwr/views/mainMenu.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
}
