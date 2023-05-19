import java.util.ArrayList;

public class Student extends User {
    private ArrayList<Course> courses;
    private ScoreCard scoreCard;

    public Student(String fullName, String username, String password) {
        super(fullName, username, password);
        this.courses = new ArrayList<Course>();
        this.scoreCard = new ScoreCard(this);
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    public void removeCourse(Course course) {
        courses.remove(course);
    }

    public ScoreCard getScoreCard() {
        return scoreCard;
    }

    public void setScoreCard(ScoreCard scoreCard) {
        this.scoreCard = scoreCard;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }
}
