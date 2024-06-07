import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CourseManager {
    private List<Course> courses;
    private static final String FILE_NAME = "courses.ser";

    public CourseManager() {
        courses = loadCourses();
    }

    public void addCourse(Course course) {
        courses.add(course);
        saveCourses();
    }

    public void removeCourse(String courseId) {
        courses.removeIf(course -> course.getCourseId().equals(courseId));
        saveCourses();
    }

    public void updateCourse(Course updatedCourse) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getCourseId().equals(updatedCourse.getCourseId())) {
                courses.set(i, updatedCourse);
                break;
            }
        }
        saveCourses();
    }

    public List<Course> getCourses() {
        return courses;
    }

    private void saveCourses() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(courses);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Course> loadCourses() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<Course>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
}
