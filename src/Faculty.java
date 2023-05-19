import java.util.ArrayList;

public class Faculty extends User {
    private ArrayList<Student> students;
    private Course course;

    public Faculty(String fullName, String username, String password, Course course) {
        super(fullName, username, password);
        this.students = new ArrayList<Student>();
        this.course = course;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }
}
