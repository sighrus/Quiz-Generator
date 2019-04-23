import java.io.*;

public class score implements Serializable, Comparable<score> {
    private double grade;
    private String name = "score";
    private int id;


    public score(double grade, String name, int id){
        this.grade = grade;
        this.name = name;
        this.id = id;
    }





    public double getGrade() {
        return grade;
    }
    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setGrade(double grade) {
        this.grade = grade;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "score{" +
                "grade=" + grade +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }


    public int compareTo(score other) {
        if (this.getGrade() > other.getGrade()) {
            return -1;
        } else if (this.getGrade() < other.getGrade()) {
            return 1;
        }
        return 0;
    }
}
