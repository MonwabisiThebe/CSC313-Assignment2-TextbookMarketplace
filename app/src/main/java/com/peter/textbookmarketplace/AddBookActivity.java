package com.peter.textbookmarketplace;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.peter.textbookmarketplace.exception.DuplicateBookException;
import com.peter.textbookmarketplace.model.Textbook;
import com.peter.textbookmarketplace.repository.BookRepository;

public class AddBookActivity extends AppCompatActivity {

    private BookRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        repository = BookRepository.getInstance();

        EditText etTitle = findViewById(R.id.etTitle);
        EditText etSeller = findViewById(R.id.etSeller);
        EditText etCopies = findViewById(R.id.etCopies);
        EditText etPrice = findViewById(R.id.etPrice);
        EditText etBanking = findViewById(R.id.etBanking);

        Button btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(v -> {
            try {
                String title = etTitle.getText().toString();
                String seller = etSeller.getText().toString();
                int copies = Integer.parseInt(etCopies.getText().toString());
                double price = Double.parseDouble(etPrice.getText().toString());
                String banking = etBanking.getText().toString();

                Textbook book = new Textbook(title, seller, copies, price, banking);

                repository.addBook(book);

                Toast.makeText(this, "Book added!", Toast.LENGTH_SHORT).show();

            } catch (DuplicateBookException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, "Invalid input!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}