package com.peter.textbookmarketplace.service;

import com.peter.textbookmarketplace.exception.DuplicateBookException;
import com.peter.textbookmarketplace.model.Textbook;

import java.util.List;

public interface BookService {

    void addBook(Textbook book) throws DuplicateBookException;

    List<Textbook> getAllBooks();

    List<Textbook> searchByTitle(String title);

    List<Textbook> searchBySeller(String sellerName);
}