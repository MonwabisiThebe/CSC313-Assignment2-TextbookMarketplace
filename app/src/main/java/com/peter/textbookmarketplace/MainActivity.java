package com.peter.textbookmarketplace;

import android.content.Intent;
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
                String copiesStr = etCopies.getText().toString();
                String priceStr = etPrice.getText().toString();
                String banking = etBanking.getText().toString();

                if (title.isEmpty() || seller.isEmpty() || copiesStr.isEmpty()
                        || priceStr.isEmpty() || banking.isEmpty()) {

                    Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_SHORT).show();
                    return;
                }

                int copies = Integer.parseInt(copiesStr);
                double price = Double.parseDouble(priceStr);

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
                Toast.makeText(this, "Invalid input format!", Toast.LENGTH_SHORT).show();
            }
        });

        // VIEW BOOKS
        btnViewBooks.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ViewBooksActivity.class));
        });

        // SEARCH
        btnSearch.setOnClickListener(v -> {

            String title = etTitle.getText().toString();
            String seller = etSeller.getText().toString();

            List<Textbook> results;

            if (!title.isEmpty()) {
                results = repository.searchByTitle(title);
            } else if (!seller.isEmpty()) {
                results = repository.searchBySeller(seller);
            } else {
                Toast.makeText(this, "Enter title or seller to search", Toast.LENGTH_SHORT).show();
                return;
            }

            if (results.isEmpty()) {
                Toast.makeText(this, "No matching books found", Toast.LENGTH_SHORT).show();
                return;
            }

            StringBuilder builder = new StringBuilder();
            builder.append("SEARCH RESULTS\n\n");

            for (Textbook b : results) {
                builder.append(" ").append(b.getTitle()).append("\n");
                builder.append(" ").append(b.getSellerName()).append("\n");
                builder.append(" R").append(b.getPrice()).append("\n\n");
            }
        });
    }
}