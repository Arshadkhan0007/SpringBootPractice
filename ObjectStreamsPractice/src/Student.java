import java.io.Serializable;

public class Student implements Serializable {

    private Integer studentId;
    private String studentName;
    private double studentMarks;

    //Default Constructor
    public Student() {
    }

    //Parameter Constructor
    public Student(Integer studentId, String studentName, double studentMarks) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentMarks = studentMarks;
    }

    //Getters and Setters
    public Integer getStudentId() {
        return studentId;
    }
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public double getStudentMarks() {
        return studentMarks;
    }
    public void setStudentMarks(double studentMarks) {
        this.studentMarks = studentMarks;
    }

    //Overriding toString
    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", studentMarks=" + studentMarks +
                '}';
    }
}
