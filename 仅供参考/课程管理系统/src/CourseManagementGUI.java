import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


public class CourseManagementGUI extends JFrame {
    private CourseManager courseManager;
    private JTable courseTable;
    private DefaultTableModel tableModel;

    public CourseManagementGUI(CourseManager courseManager) {
        this.courseManager = courseManager;
        setTitle("课程管理系统");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initializeUI();
    }

    private void initializeUI() {
        String[] columnNames = {"课程名", "课程编号", "课程类别", "开课年级", "任课老师"};
        tableModel = new DefaultTableModel(columnNames, 0);
        courseTable = new JTable(tableModel);
        loadCoursesToTable();

        JScrollPane scrollPane = new JScrollPane(courseTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        JButton addButton = new JButton("添加课程");
        JButton updateButton = new JButton("修改课程");
        JButton deleteButton = new JButton("删除课程");
        JButton searchButton = new JButton("查询课程");

        controlPanel.add(addButton);
        controlPanel.add(updateButton);
        controlPanel.add(deleteButton);
        controlPanel.add(searchButton);
        add(controlPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> showCourseDialog(null));
        updateButton.addActionListener(e -> {
            int selectedRow = courseTable.getSelectedRow();
            if (selectedRow != -1) {
                Course course = getCourseFromTable(selectedRow);
                showCourseDialog(course);
            } else {
                JOptionPane.showMessageDialog(this, "请选择要修改的课程");
            }
        });
        deleteButton.addActionListener(e -> {
            int selectedRow = courseTable.getSelectedRow();
            if (selectedRow != -1) {
                String courseId = (String) tableModel.getValueAt(selectedRow, 1);
                courseManager.removeCourse(courseId);
                tableModel.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "请选择要删除的课程");
            }
        });
        searchButton.addActionListener(e -> {
            String searchId = JOptionPane.showInputDialog("输入课程编号查询:");
            if (searchId != null) {
                Course course = courseManager.getCourses().stream()
                        .filter(c -> c.getCourseId().equals(searchId))
                        .findFirst().orElse(null);
                if (course != null) {
                    JOptionPane.showMessageDialog(this, course.toString());
                } else {
                    JOptionPane.showMessageDialog(this, "未找到课程");
                }
            }
        });
    }

    private void showCourseDialog(Course course) {
        JDialog dialog = new JDialog(this, "课程信息", true);
        dialog.setSize(400, 300);
        dialog.setLayout(new GridLayout(6, 2));

        JTextField courseNameField = new JTextField(course != null ? course.getCourseName() : "");
        JTextField courseIdField = new JTextField(course != null ? course.getCourseId() : "");
        JComboBox<String> categoryBox = new JComboBox<>(new String[]{"选修", "必修"});
        categoryBox.setSelectedItem(course != null ? course.getCourseCategory() : "选修");
        JComboBox<String> gradeBox = new JComboBox<>(new String[]{"大一", "大二", "大三", "大四"});
        gradeBox.setSelectedItem(course != null ? course.getCourseGrade() : "大一");
        JTextField teacherNameField = new JTextField(course != null ? course.getTeacherName() : "");

        dialog.add(new JLabel("课程名:"));
        dialog.add(courseNameField);
        dialog.add(new JLabel("课程编号:"));
        dialog.add(courseIdField);
        dialog.add(new JLabel("课程类别:"));
        dialog.add(categoryBox);
        dialog.add(new JLabel("开课年级:"));
        dialog.add(gradeBox);
        dialog.add(new JLabel("任课老师:"));
        dialog.add(teacherNameField);

        JButton saveButton = new JButton("保存");
        saveButton.addActionListener(e -> {
            String courseName = courseNameField.getText();
            String courseId = courseIdField.getText();
            String category = (String) categoryBox.getSelectedItem();
            String grade = (String) gradeBox.getSelectedItem();
            String teacherName = teacherNameField.getText();

            if (course == null) { // 添加新课程
                courseManager.addCourse(new Course(courseName, courseId, category, grade, teacherName));
            } else { // 更新已有课程
                courseManager.updateCourse(new Course(courseName, courseId, category, grade, teacherName));
            }
            loadCoursesToTable();
            dialog.dispose();
        });

        dialog.add(saveButton);
        // 设置弹窗居中
        dialog.setLocationRelativeTo(this);

        dialog.setVisible(true);
    }

    private Course getCourseFromTable(int row) {
        String courseName = (String) tableModel.getValueAt(row, 0);
        String courseId = (String) tableModel.getValueAt(row, 1);
        String courseCategory = (String) tableModel.getValueAt(row, 2);
        String courseGrade = (String) tableModel.getValueAt(row, 3);
        String teacherName = (String) tableModel.getValueAt(row, 4);
        return new Course(courseName, courseId, courseCategory, courseGrade, teacherName);
    }

    private void loadCoursesToTable() {
        tableModel.setRowCount(0);
        for (Course course : courseManager.getCourses()) {
            tableModel.addRow(new Object[]{
                    course.getCourseName(),
                    course.getCourseId(),
                    course.getCourseCategory(),
                    course.getCourseGrade(),
                    course.getTeacherName()
            });
        }
    }
}
