import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookManagementSystem extends JFrame {
    private List<Book> bookList = new ArrayList<>();
    private JTextField titleField, isbnField, publisherField, authorField, borrowerField;
    private JCheckBox isBorrowedCheckBox;
    private DefaultListModel<String> listModel;
    private JList<String> bookJList;
    private int selectedBookIndex = -1;

    public BookManagementSystem() {
        setTitle("书籍管理系统");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        loadBooks();

        // 创建GUI
        JPanel panel = new JPanel(new GridLayout(7, 2));
        panel.add(new JLabel("书名:"));
        titleField = new JTextField();
        panel.add(titleField);

        panel.add(new JLabel("ISBN:"));
        isbnField = new JTextField();
        panel.add(isbnField);

        panel.add(new JLabel("出版社:"));
        publisherField = new JTextField();
        panel.add(publisherField);

        panel.add(new JLabel("作者:"));
        authorField = new JTextField();
        panel.add(authorField);

        panel.add(new JLabel("借书人姓名:"));
        borrowerField = new JTextField();
        panel.add(borrowerField);

        panel.add(new JLabel("是否已借出:"));
        isBorrowedCheckBox = new JCheckBox();
        panel.add(isBorrowedCheckBox);

        JButton addButton = new JButton("添加/修改书籍");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addOrUpdateBook();
            }
        });
        panel.add(addButton);

        JButton deleteButton = new JButton("删除书籍");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteBook();
            }
        });
        panel.add(deleteButton);

        listModel = new DefaultListModel<>();
        bookJList = new JList<>(listModel);
        bookJList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                selectedBookIndex = bookJList.getSelectedIndex();
                if (selectedBookIndex != -1) {
                    loadBookDetails(selectedBookIndex);
                }
            }
        });
        refreshBookList();

        add(panel, BorderLayout.CENTER);
        add(new JScrollPane(bookJList), BorderLayout.EAST);

        JButton saveButton = new JButton("保存并退出");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveBooks();
                System.exit(0);
            }
        });
        add(saveButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void addOrUpdateBook() {
        String title = titleField.getText();
        String isbn = isbnField.getText();
        String publisher = publisherField.getText();
        String author = authorField.getText();
        String borrower = borrowerField.getText();
        boolean isBorrowed = isBorrowedCheckBox.isSelected();

        if (selectedBookIndex != -1) {
            Book book = bookList.get(selectedBookIndex);
            book.setTitle(title);
            book.setIsbn(isbn);
            book.setPublisher(publisher);
            book.setAuthor(author);
            book.setBorrowed(isBorrowed);
            book.setBorrower(borrower);
            refreshBookList();
        } else {
            Book newBook = new Book(title, isbn, publisher, author, isBorrowed, borrower);
            bookList.add(newBook);
            refreshBookList();
        }
    }

    private void deleteBook() {
        int selectedIndex = bookJList.getSelectedIndex();
        if (selectedIndex != -1) {
            Book selectedBook = bookList.get(selectedIndex);
            if (!selectedBook.isBorrowed()) {
                bookList.remove(selectedIndex);
                refreshBookList();
            } else {
                JOptionPane.showMessageDialog(this, "无法删除已借出的书籍");
            }
        }
    }

    private void refreshBookList() {
        listModel.clear();
        for (Book book : bookList) {
            listModel.addElement(book.toString());
        }
    }

    private void loadBookDetails(int index) {
        Book book = bookList.get(index);
        titleField.setText(book.getTitle());
        isbnField.setText(book.getIsbn());
        publisherField.setText(book.getPublisher());
        authorField.setText(book.getAuthor());
        borrowerField.setText(book.getBorrower());
        isBorrowedCheckBox.setSelected(book.isBorrowed());
    }

    private void loadBooks() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("books.dat"))) {
            bookList = (List<Book>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // 忽略错误
        }
    }

    private void saveBooks() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("books.dat"))) {
            oos.writeObject(bookList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new BookManagementSystem();
    }
}
