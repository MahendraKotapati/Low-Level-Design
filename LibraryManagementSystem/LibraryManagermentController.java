import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;



/*

Problem Statement
Design and implement a Library Management System that allows members to borrow and return books, manages book inventory, tracks loans, and supports catalog search.

Requirements
Book Management: The system manages a catalog of books, each with multiple copies.
Member Management: The system manages library members who can borrow and return books.
Loan Management: The system tracks which member has borrowed which book copy and when.
Borrowing and Returning: Members can borrow available book copies and return them.
Catalog Search: Members can search for books by title, author, or ISBN.
Extensibility: Easy to add new features such as reservations, fines, or notifications.







*/



enum BOOK_STATUS {
    AVAILABLE,
    OUT_OF_STOCK
}

class Book {
    String bookId;
    String title;
    String author;
    String ISBN;
    int year;
    BOOK_STATUS status;

    Book(String title, String author, String ISBN, int year, BOOK_STATUS status) {
        this.bookId = UUID.randomUUID().toString();
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.year = year;
        this.status = status;
    }

}

class Member {
    String memberId;
    String name;
    String email;
    int noOfBooksBorrowed;
    ArrayList<BorrowBookTransaction> borrowHistory;
    

    Member(String memberId, String name, String email) {
        this.memberId = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.noOfBooksBorrowed = 0;
    }

    public void addToBorrowHistory(String bookId, String memberId, int loanDurationDays) {
        BorrowBookTransaction bt = new BorrowBookTransaction(bookId, memberId, loanDurationDays);
        borrowHistory.add(bt);
    } 

    public void updateBooksBorrowed(int noOfBooksBorrowed) {
        this.noOfBooksBorrowed = noOfBooksBorrowed;
    }
}

class BorrowBookTransaction {
    String bookId;
    String memberId;
    int loanDurationDays;
    boolean isReturned;

    BorrowBookTransaction(String bookId, String memberId, int loanDurationDays) {
        this.bookId = bookId;
        this.memberId = memberId;
        this.loanDurationDays = loanDurationDays;
        this.isReturned = false;
    }

    public void updateIsReturned(boolean isReturned) {
        this.isReturned = isReturned;
    }
}

class LibraryCatalog {
    private ConcurrentHashMap<String, Book> books;

    LibraryCatalog() {
        books = new ConcurrentHashMap<String, Book>();
    }

    public void borrowBook(String bookId) {
        Book book = this.getBook(bookId);
        book.status = BOOK_STATUS.OUT_OF_STOCK;
        books.put(bookId, book);
    } 

    public void returnBook(String bookId) {
        Book book = this.getBook(bookId);
        book.status = BOOK_STATUS.AVAILABLE;
        books.put(bookId, book);
    } 


    public void addBook(String bookId, Book book) {
        books.put(bookId, book);
    } 
    
    public Book getBook(String bookId) {
        return books.get(bookId);
    } 

    public void updateBook(String bookId, Book book) {
        books.put(bookId, book);
    }  
    
    public void removeBook(String bookId, Book book) {
        books.put(bookId, book);
    } 
}

class LibraryManagementController {
    private final int MAX_BOOKS_PER_MEMBER = 6;
    private final int MAX_LOAN_DAYS = 30;
    


    public void borrowBook(String bookId, String memberId, int loanDurationDays) {

    }

    public void returnBook(String bookId, String memberId) {

    }

}