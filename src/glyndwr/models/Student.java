package glyndwr.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sajith on 12/22/18
 */
public class Student {

    private String name;
    private int studentId;
    private List<Module> modules = new ArrayList<>();
    private String status;
    private float average;
    private int rank=0;

    public Student(int id) {
        this.studentId = id;
    }

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getAverage() {
        return average;
    }

    public void setAverage(float average) {
        this.average = average;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
