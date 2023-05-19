public class CourseScore {
    private Course course;
    private int obtainedMarks;
    private char grade;
    private int attainedCredits;
    private float Percentage;

    public CourseScore(Course course, int obtainedMarks, char grade, int attainedCredits, float Percentage) {
        this.course = course;
        this.obtainedMarks = obtainedMarks;
        this.grade = grade;
        this.attainedCredits = attainedCredits;
        this.Percentage = Percentage;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public float getPercentage() {
        return Percentage;
    }

    public void setPercentage(float percentage) {
        Percentage = percentage;
    }

    public int getObtainedMarks() {
        return obtainedMarks;
    }

    public void setObtainedMarks(int obtainedMarks) {
        this.obtainedMarks = obtainedMarks;
    }

    public char getGrade() {
        return grade;
    }

    public void setGrade(char grade) {
        this.grade = grade;
    }

    public int getAttainedCredits() {
        return attainedCredits;
    }

    public void setAttainedCredits(int attainedCredits) {
        this.attainedCredits = attainedCredits;
    }
}