package com.peter.textbookmarketplace;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.peter.textbookmarketplace.repository.BookRepository;
import com.peter.textbookmarketplace.model.Textbook;
import com.peter.textbookmarketplace.exception.DuplicateBookException;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BookRepository repository;

    private EditText etTitle, etSeller, etCopies, etPrice, etBanking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize repository
        repository = new BookRepository();

        // connect EditTexts
        etTitle = findViewById(R.id.etTitle);
        etSeller = findViewById(R.id.etSeller);
        etCopies = findViewById(R.id.etCopies);
        etPrice = findViewById(R.id.etPrice);
        etBanking = findViewById(R.id.etBanking);

        // connect buttons
        Button btnAddBook = findViewById(R.id.btnAddBook);
        Button btnViewBooks = findViewById(R.id.btnViewBooks);
        Button btnSearch = findViewById(R.id.btnSearch);

        // ADD BOOK
        btnAddBook.setOnClickListener(v -> {

            try {
                String title = etTitle.getText().toString();
                String seller = etSeller.getText().toString();
                int copies = Integer.parseInt(etCopies.getText().toString());
                double price = Double.parseDouble(etPrice.getText().toString());
                String banking = etBanking.getText().toString();

                Textbook book = new Textbook(title, seller, copies, price, banking);

                repository.addBook(book);

                Toast.makeText(this, "Book added successfully!", Toast.LENGTH_SHORT).show();

                // clear fields
                etTitle.setText("");
                etSeller.setText("");
                etCopies.setText("");
                etPrice.setText("");
                etBanking.setText("");

            } catch (DuplicateBookException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
                Toast.makeText(this, "Please fill all fields correctly!", Toast.LENGTH_SHORT).show();
            }
        });

        // VIEW BOOKS
        btnViewBooks.setOnClickListener(v -> {

            List<Textbook> books = repository.getAllBooks();

            if (books.isEmpty()) {
                Toast.makeText(this, "No books available", Toast.LENGTH_SHORT).show();
                return;
            }

            StringBuilder builder = new StringBuilder();

            for (Textbook b : books) {
                builder.append("Title: ").append(b.getTitle()).append("\n");
                builder.append("Seller: ").append(b.getSellerName()).append("\n");
                builder.append("Price: ").append(b.getPrice()).append("\n\n");
            }

            Toast.makeText(this, builder.toString(), Toast.LENGTH_LONG).show();
        });

        // SEARCH (we’ll complete this next)
        btnSearch.setOnClickListener(v -> {
            Toast.makeText(this, "Search feature coming next", Toast.LENGTH_SHORT).show();
        });
    }
}