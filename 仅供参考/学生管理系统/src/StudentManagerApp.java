import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentManagerApp {
    private StudentManager studentManager;

    public StudentManagerApp() {
        studentManager = new StudentManager();
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("学生管理系统");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel managePanel = new JPanel();
        managePanel.setLayout(new GridLayout(14, 1));

        JTextField nameField = new JTextField();
        JTextField idField = new JTextField();
        JTextField classField = new JTextField();
        JTextField mathField = new JTextField();
        JTextField englishField = new JTextField();
        JTextField computerField = new JTextField();
        JTextField peField = new JTextField();

        managePanel.add(new JLabel("姓名:"));
        managePanel.add(nameField);
        managePanel.add(new JLabel("学号:"));
        managePanel.add(idField);
        managePanel.add(new JLabel("班级:"));
        managePanel.add(classField);
        managePanel.add(new JLabel("数学:"));
        managePanel.add(mathField);
        managePanel.add(new JLabel("英语:"));
        managePanel.add(englishField);
        managePanel.add(new JLabel("计算机:"));
        managePanel.add(computerField);
        managePanel.add(new JLabel("体育:"));
        managePanel.add(peField);

        JButton addButton = new JButton("添加");
        JButton updateButton = new JButton("更新");
        JButton deleteButton = new JButton("删除");

        managePanel.add(addButton);
        managePanel.add(updateButton);
        managePanel.add(deleteButton);

        tabbedPane.add("管理学生", managePanel);

        JPanel queryPanel = new JPanel();
        queryPanel.setLayout(new BorderLayout());

        JTextField queryField = new JTextField();
        queryPanel.add(queryField, BorderLayout.NORTH);

        JTextArea queryResult = new JTextArea();
        queryPanel.add(new JScrollPane(queryResult), BorderLayout.CENTER);

        JButton queryButton = new JButton("查询");
        queryPanel.add(queryButton, BorderLayout.SOUTH);

        tabbedPane.add("查询学生", queryPanel);

        frame.add(tabbedPane);
        frame.setVisible(true);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String id = idField.getText();
                String className = classField.getText();
                double math = Double.parseDouble(mathField.getText());
                double english = Double.parseDouble(englishField.getText());
                double computer = Double.parseDouble(computerField.getText());
                double pe = Double.parseDouble(peField.getText());
                Student student = new Student(name, id, className, math, english, computer, pe);
                studentManager.addStudent(student);
                JOptionPane.showMessageDialog(frame, "学生已添加！");
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String id = idField.getText();
                String className = classField.getText();
                double math = Double.parseDouble(mathField.getText());
                double english = Double.parseDouble(englishField.getText());
                double computer = Double.parseDouble(computerField.getText());
                double pe = Double.parseDouble(peField.getText());
                Student student = new Student(name, id, className, math, english, computer, pe);
                studentManager.updateStudent(student);
                JOptionPane.showMessageDialog(frame, "学生信息已更新！");
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                studentManager.removeStudent(id);
                JOptionPane.showMessageDialog(frame, "学生已删除！");
            }
        });

        queryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String course = queryField.getText();
                StringBuilder result = new StringBuilder();
                for (Student student : studentManager.getFailingStudents(course)) {
                    result.append(student).append("\n");
                }
                queryResult.setText(result.toString());
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StudentManagerApp();
            }
        });
    }
}
