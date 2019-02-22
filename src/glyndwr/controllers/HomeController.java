package glyndwr.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {
    @FXML
    Button btnStudent;

    @FXML
    GridPane grid;

    public void viewStudentForm() throws IOException {
        Stage stage = (Stage) btnStudent.getScene().getWindow();
        Pane root = FXMLLoader.load(getClass().getResource("/glyndwr/views/studentForm.fxml"));
        stage.setTitle("University System");
        Scene scene = new Scene(root);
        stage.setScene(scene);

    }
    public void viewLeaderboard() throws IOException {
        Stage stage = (Stage) btnStudent.getScene().getWindow();
        Pane root = FXMLLoader.load(getClass().getResource("/glyndwr/views/leaderboard.fxml"));
        stage.setTitle("University System");
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

}
