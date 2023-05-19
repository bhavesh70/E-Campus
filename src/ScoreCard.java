import java.util.ArrayList;

public class ScoreCard {
    private Student student;
    private ArrayList<CourseScore> courseScores;
    private char overallGrade;
    private double percentile;

    private float overallpercentage;

    public ScoreCard(Student student) {
        this.student = student;
        this.courseScores = new ArrayList<>();
    }

    public void addCourseScore(CourseScore courseScore) {
        courseScores.add(courseScore);
    }

    public void removeCourseScore(CourseScore courseScore) {
        courseScores.remove(courseScore);
    }

    public float getOverallpercentage() {
        return overallpercentage;
    }

    public void setOverallpercentage(float overallpercentage) {
        this.overallpercentage = overallpercentage;
    }

    public ArrayList<CourseScore> getCourseScores() {
        return courseScores;
    }

    public void setCourseScores(ArrayList<CourseScore> courseScores) {
        this.courseScores = courseScores;
    }

    public char getOverallGrade() {
        return overallGrade;
    }

    public void setOverallGrade(char overallGrade) {
        this.overallGrade = overallGrade;
    }

    public double getPercentile() {
        return percentile;
    }

    public void setPercentile(double percentile) {
        this.percentile = percentile;
    }
}
