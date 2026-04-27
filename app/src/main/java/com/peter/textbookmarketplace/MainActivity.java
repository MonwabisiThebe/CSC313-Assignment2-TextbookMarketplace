package com.peter.textbookmarketplace;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.peter.textbookmarketplace.repository.BookRepository;
import com.peter.textbookmarketplace.model.Textbook;
import com.peter.textbookmarketplace.exception.DuplicateBookException;

public class MainActivity extends AppCompatActivity {

    private BookRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize repository (OOP core)
        repository = new BookRepository();

        // connect UI buttons
        Button btnAddBook = findViewById(R.id.btnAddBook);
        Button btnViewBooks = findViewById(R.id.btnViewBooks);
        Button btnSearch = findViewById(R.id.btnSearch);

        btnAddBook.setOnClickListener(v -> {

            try {
                Textbook book = new Textbook(
                        "Java Programming",
                        "Peter",
                        2,
                        150.0,
                        "1234567890"
                );

                repository.addBook(book);

                Toast.makeText(this, "Book added successfully!", Toast.LENGTH_SHORT).show();

            } catch (DuplicateBookException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}