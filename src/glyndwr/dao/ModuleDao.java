package glyndwr.dao;

import glyndwr.ConnectionPool;
import glyndwr.models.Module;
import glyndwr.models.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sajith on 12/22/18
 */
public class ModuleDao {

    private Connection connection = ConnectionPool.getInstance().getConnection();

    PreparedStatement preparedStatement;

    public void saveModules(Student student) {
        String sql = "INSERT INTO student_module values(?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            for (Module module : student.getModules()) {
                preparedStatement.setInt(1, student.getStudentId());
                preparedStatement.setInt(2, module.getId());
                preparedStatement.setInt(3, module.getMarks());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Module> getModules() {
        List<Module> modules = new ArrayList<>();
        String sql = "SELECT * FROM module";
        try {
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                modules.add(new Module(resultSet.getInt(1), resultSet.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modules;
    }

    public List<Module> getStudentScores(Student student) {
        List<Module> modules = new ArrayList<>();
        String sql = "SELECT * FROM student_module where student_id=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, student.getStudentId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Module module = getModule(resultSet.getInt("module_id"));
                module.setMarks(resultSet.getInt("mark"));
                modules.add(module);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modules;
    }

    public Module getModule(int id) {
        Module module = new Module();
        String sql = "SELECT * FROM module where id=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            module.setName(resultSet.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return module;
    }
}
