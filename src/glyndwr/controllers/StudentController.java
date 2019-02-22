package glyndwr.controllers;

import glyndwr.dao.ModuleDao;
import glyndwr.dao.StudentDao;
import glyndwr.models.Module;
import glyndwr.models.Student;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by sajith on 12/22/18
 */
public class StudentController implements Initializable {
    @FXML
    GridPane grid;

    @FXML
    Button btnAdd;

    @FXML
    TextField name, scoreTxt0, scoreTxt1, scoreTxt2, scoreTxt3, scoreTxt4, scoreTxt5;

    private List<TextField> textFields = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Module> modules = modules();

        for (int i = 0; i < modules.size(); i++) {
            grid.add(new Label(modules.get(i).getName()), 0, i);
            TextField textField = new TextField();
            textField.setId("scoreTxt" + i);
            textFields.add(textField);
            grid.add(textField, 1, i);
        }
    }

    private List<Module> modules() {
        ModuleDao moduleDao = new ModuleDao();
        return moduleDao.getModules();
    }

    public void addStudent() throws IOException {
        boolean validated = true;
        List<Module> modules = modules();
        for (int i = 0; i < modules.size(); i++) {
            int score = Integer.parseInt(textFields.get(i).getText());
            if (score >= 0 && score <= 100) {
                modules.get(i).setMarks(score);
            } else {
                validated = false;
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Input not valid");
                errorAlert.setContentText("Mark should be within 0 and 100");
                errorAlert.showAndWait();
            }
        }
        if (validated) {
            Student student = new Student();
            student.setName(name.getText());
            student.setModules(modules);
            student.setAverage(calculateAvg(student.getModules()));
            student.setStatus(status(student.getAverage()));
            StudentDao studentDao = new StudentDao();
            studentDao.saveStudent(student);
            Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
            errorAlert.setHeaderText("Student Added");
            if(student.getStatus().equals("PASS")){
                errorAlert.setContentText(student.getName() + " has successfully passed the year!\nAverage Mark: "+student.getAverage());
            }
            else {
                errorAlert.setContentText(student.getName() + " has Failed the year!\nAverage mark: "+student.getAverage());
            }
            errorAlert.showAndWait();
            viewHome();
        }
    }

    private float calculateAvg(List<Module> modules) {
        float avg = 0;
        int total = 0;
        for (Module module : modules) {
            total += module.getMarks();
        }
        avg = total / 6f;
        return avg;
    }

    private String status(float average) {
        if (average >= 40) {
            return "PASS";
        } else {
            return "FAIL";
        }
    }

    public void viewHome() throws IOException {
        Stage stage = (Stage) btnAdd.getScene().getWindow();
        Pane root = FXMLLoader.load(getClass().getResource("/glyndwr/views/mainMenu.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
}
