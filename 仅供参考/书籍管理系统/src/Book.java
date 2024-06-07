import java.io.Serializable;

class Book implements Serializable {
    private String title;
    private String isbn;
    private String publisher;
    private String author;
    private boolean isBorrowed;
    private String borrower;

    public Book(String title, String isbn, String publisher, String author, boolean isBorrowed, String borrower) {
        this.title = title;
        this.isbn = isbn;
        this.publisher = publisher;
        this.author = author;
        this.isBorrowed = isBorrowed;
        this.borrower = borrower;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    public String getBorrower() {
        return borrower;
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }

    @Override
    public String toString() {
        return title + " - " + isbn + " - " + publisher + " - " + author + (isBorrowed ? " (已借出: " + borrower + ")" : " (未借出)");
    }
}