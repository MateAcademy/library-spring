package ua.library.klunniy.dto;

public class BookDTO {
    Integer bookId;
    String bookName;
    String owner;

    public BookDTO(Integer bookId, String bookName, String author, int year) {
        this.bookName = bookName;
        this.bookId = bookId;
        this.owner = bookName + ", " + author + ", " + year;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}
