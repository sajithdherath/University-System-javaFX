package glyndwr.models;

/**
 * Created by sajith on 12/22/18
 */
public class Module {

    private int id;
    private String name;
    private int marks;

    public Module() {
    }

    public Module(int id, String name, int marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public Module(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
