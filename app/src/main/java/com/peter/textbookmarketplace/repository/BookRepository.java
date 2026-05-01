package com.peter.textbookmarketplace.repository;

import com.peter.textbookmarketplace.model.Textbook;
import com.peter.textbookmarketplace.exception.DuplicateBookException;

import java.util.ArrayList;
import java.util.List;

public class BookRepository {

    private static BookRepository instance;
    private List<Textbook> books;

    private BookRepository() {
        books = new ArrayList<>();
    }

    public static BookRepository getInstance() {
        if (instance == null) {
            instance = new BookRepository();
        }
        return instance;
    }

    public void addBook(Textbook book) throws DuplicateBookException {
        for (Textbook b : books) {
            if (b.getTitle().equalsIgnoreCase(book.getTitle())) {
                throw new DuplicateBookException("This textbook already exists");
            }
        }
        books.add(book);
    }

    public List<Textbook> getAllBooks() {
        return books;
    }

    public List<Textbook> searchByTitle(String title) {
        List<Textbook> results = new ArrayList<>();
        for (Textbook b : books) {
            if (b.getTitle().toLowerCase().contains(title.toLowerCase())) {
                results.add(b);
            }
        }
        return results;
    }

    public List<Textbook> searchBySeller(String seller) {
        List<Textbook> results = new ArrayList<>();
        for (Textbook b : books) {
            if (b.getSellerName().toLowerCase().contains(seller.toLowerCase())) {
                results.add(b);
            }
        }
        return results;
    }
}