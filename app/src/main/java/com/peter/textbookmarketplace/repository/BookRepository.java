package com.peter.textbookmarketplace.repository;

import com.peter.textbookmarketplace.exception.DuplicateBookException;
import com.peter.textbookmarketplace.model.Textbook;
import com.peter.textbookmarketplace.service.BookService;

import java.util.ArrayList;
import java.util.List;

public class BookRepository implements BookService {

    private List<Textbook> books = new ArrayList<>();

    @Override
    public void addBook(Textbook book) throws DuplicateBookException {

        for (Textbook b : books) {
            if (b.getTitle().equalsIgnoreCase(book.getTitle())
                    && b.getSellerName().equalsIgnoreCase(book.getSellerName())) {
                throw new DuplicateBookException("This textbook already exists for this seller!");
            }
        }

        books.add(book);
    }

    @Override
    public List<Textbook> getAllBooks() {
        return books;
    }

    @Override
    public List<Textbook> searchByTitle(String title) {

        List<Textbook> result = new ArrayList<>();

        for (Textbook b : books) {
            if (b.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(b);
            }
        }

        return result;
    }

    @Override
    public List<Textbook> searchBySeller(String sellerName) {

        List<Textbook> result = new ArrayList<>();

        for (Textbook b : books) {
            if (b.getSellerName().toLowerCase().contains(sellerName.toLowerCase())) {
                result.add(b);
            }
        }

        return result;
    }
}