import java.io.Serializable;

public class Course implements Serializable {
    private String courseName;
    private String courseId;
    private String courseCategory;
    private String courseGrade;
    private String teacherName;

    public Course(String courseName, String courseId, String courseCategory, String courseGrade, String teacherName) {
        this.courseName = courseName;
        this.courseId = courseId;
        this.courseCategory = courseCategory;
        this.courseGrade = courseGrade;
        this.teacherName = teacherName;
    }

    // Getters
    public String getCourseName() {
        return courseName;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseCategory() {
        return courseCategory;
    }

    public String getCourseGrade() {
        return courseGrade;
    }

    public String getTeacherName() {
        return teacherName;
    }

    // Setters
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public void setCourseCategory(String courseCategory) {
        this.courseCategory = courseCategory;
    }

    public void setCourseGrade(String courseGrade) {
        this.courseGrade = courseGrade;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    // toString
    @Override
    public String toString() {
        return "Course{" +
                "courseName='" + courseName + '\'' +
                ", courseId='" + courseId + '\'' +
                ", courseCategory='" + courseCategory + '\'' +
                ", courseGrade='" + courseGrade + '\'' +
                ", teacherName='" + teacherName + '\'' +
                '}';
    }
}
