public class Main {
    public static void main(String[] args) {
        CourseManager manager = new CourseManager();
        CourseManagementGUI gui = new CourseManagementGUI(manager);
        gui.setVisible(true);
    }
}
