import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    private List<Student> students;

    public StudentManager() {
        this.students = new ArrayList<>();
        loadStudents();
    }

    public void addStudent(Student student) {
        students.add(student);
        saveStudents();
    }

    public void removeStudent(String id) {
        students.removeIf(student -> student.getId().equals(id));
        saveStudents();
    }

    public Student getStudent(String id) {
        return students.stream().filter(student -> student.getId().equals(id)).findFirst().orElse(null);
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    public void updateStudent(Student updatedStudent) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(updatedStudent.getId())) {
                students.set(i, updatedStudent);
                break;
            }
        }
        saveStudents();
    }

    public List<Student> getFailingStudents(String course) {
        List<Student> failingStudents = new ArrayList<>();
        for (Student student : students) {
            double score = 0;
            switch (course.toLowerCase()) {
                case "math":
                    score = student.getMathScore();
                    break;
                case "english":
                    score = student.getEnglishScore();
                    break;
                case "computer":
                    score = student.getComputerScore();
                    break;
                case "pe":
                    score = student.getPeScore();
                    break;
            }
            if (score < 60) {
                failingStudents.add(student);
            }
        }
        return failingStudents;
    }

    private void saveStudents() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("students.dat"))) {
            oos.writeObject(students);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadStudents() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("students.dat"))) {
            students = (List<Student>) ois.readObject();
        } catch (FileNotFoundException e) {
            // File not found, initialize with an empty list
            students = new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}