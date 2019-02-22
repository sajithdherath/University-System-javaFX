package glyndwr.dao;

import glyndwr.ConnectionPool;
import glyndwr.models.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sajith on 12/22/18
 */
public class StudentDao {

    private Connection connection = ConnectionPool.getInstance().getConnection();

    PreparedStatement preparedStatement;

    public List<Student> getAllStudentsScores() {

        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM student ORDER BY average DESC";
        try {
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Student student = new Student(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setAverage(resultSet.getFloat("average"));
                student.setStatus(resultSet.getString("status"));
                student.setRank(students.size() + 1);
                ModuleDao moduleDao = new ModuleDao();
                student.setModules(moduleDao.getStudentScores(student));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public Student getStudentById(int studentId) {
        String sql = "SELECT * FROM student WHERE id=?";
        Student student = new Student(studentId);
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, studentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            student.setName(resultSet.getString("name"));
            student.setAverage(resultSet.getFloat("average"));
            student.setStatus(resultSet.getString("status"));
            ModuleDao moduleDao = new ModuleDao();
            student.setModules(moduleDao.getStudentScores(student));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    public Student saveStudent(Student student) {
        String sql = "INSERT INTO student (name,average,status) VALUES(?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setFloat(2, student.getAverage());
            preparedStatement.setString(3, student.getStatus());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            student.setStudentId(resultSet.getInt(1));
            ModuleDao moduleDao = new ModuleDao();
            moduleDao.saveModules(student);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }


}
