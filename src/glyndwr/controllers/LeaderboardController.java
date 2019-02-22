package glyndwr.controllers;

import com.mysql.cj.xdevapi.Table;
import glyndwr.dao.StudentDao;
import glyndwr.models.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by sajith on 12/22/18
 */
public class LeaderboardController implements Initializable {

    @FXML
    TableView tableView;
    @FXML
    Button viewStudent;

    public static Student selectedStudent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        StudentDao studentDao = new StudentDao();
        final ObservableList<Student> data = FXCollections.observableArrayList(studentDao.getAllStudentsScores());
        TableColumn firstCol = new TableColumn("Rank");
        firstCol.setMinWidth(200);
        firstCol.setCellValueFactory(new PropertyValueFactory<Student, String>("rank"));
        TableColumn secondCol = new TableColumn("Student Name");
        secondCol.setMinWidth(226);
        secondCol.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        TableColumn thirdCol = new TableColumn("Average");
        thirdCol.setMinWidth(200);
        thirdCol.setCellValueFactory(new PropertyValueFactory<Student, Float>("average"));
        TableColumn fourthCol = new TableColumn("Pass/Fail");
        fourthCol.setMinWidth(200);
        fourthCol.setCellValueFactory(new PropertyValueFactory<Student, String>("status"));
        tableView.setItems(data);
        tableView.getColumns().addAll(firstCol, secondCol, thirdCol, fourthCol);


    }

    public void viewSingleUser() throws IOException {

        selectedStudent= (Student) tableView.getSelectionModel().getSelectedItem();
        if(selectedStudent!=null) {
            System.out.println(selectedStudent.getName());
            Stage stage = (Stage) viewStudent.getScene().getWindow();
            Pane root = FXMLLoader.load(getClass().getResource("/glyndwr/views/singleStudent.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
        }else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Selection Invalid");
            errorAlert.setContentText("Please select a student");
            errorAlert.showAndWait();
        }
    }
}
