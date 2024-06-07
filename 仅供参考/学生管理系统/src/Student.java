import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    private String id;
    private String className;
    private double mathScore;
    private double englishScore;
    private double computerScore;
    private double peScore;

    public Student(String name, String id, String className, double mathScore, double englishScore, double computerScore, double peScore) {
        this.name = name;
        this.id = id;
        this.className = className;
        this.mathScore = mathScore;
        this.englishScore = englishScore;
        this.computerScore = computerScore;
        this.peScore = peScore;
    }

    // Getters and setters for each field
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public double getMathScore() {
        return mathScore;
    }

    public void setMathScore(double mathScore) {
        this.mathScore = mathScore;
    }

    public double getEnglishScore() {
        return englishScore;
    }

    public void setEnglishScore(double englishScore) {
        this.englishScore = englishScore;
    }

    public double getComputerScore() {
        return computerScore;
    }

    public void setComputerScore(double computerScore) {
        this.computerScore = computerScore;
    }

    public double getPeScore() {
        return peScore;
    }

    public void setPeScore(double peScore) {
        this.peScore = peScore;
    }

    // toString method for displaying student information
    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", className='" + className + '\'' +
                ", mathScore=" + mathScore +
                ", englishScore=" + englishScore +
                ", computerScore=" + computerScore +
                ", peScore=" + peScore +
                '}';
    }
}
