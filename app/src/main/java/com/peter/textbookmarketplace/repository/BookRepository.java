package com.peter.textbookmarketplace.repository;

import com.peter.textbookmarketplace.model.Textbook;
import com.peter.textbookmarketplace.exception.DuplicateBookException;

import java.util.ArrayList;
import java.util.List;

public class BookRepository {

    // 🔥 STATIC = shared across all activities
    private static List<Textbook> books = new ArrayList<>();

    public void addBook(Textbook book) throws DuplicateBookException {

        for (Textbook b : books) {
            if (b.getTitle().equalsIgnoreCase(book.getTitle())
                    && b.getSellerName().equalsIgnoreCase(book.getSellerName())) {
                throw new DuplicateBookException("This textbook already exists...");
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