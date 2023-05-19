public class Course {
    private String courseId;
    private String courseName;
    private int credits;
    private int totalMarks;
    private int passingCriteria;

    public Course(String courseId, String courseName, int credits, int totalMarks, int passingCriteria) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credits = credits;
        this.totalMarks = totalMarks;
        this.passingCriteria = passingCriteria;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }

    public int getPassingCriteria() {
        return passingCriteria;
    }

    public void setPassingCriteria(int passingCriteria) {
        this.passingCriteria = passingCriteria;
    }
}
