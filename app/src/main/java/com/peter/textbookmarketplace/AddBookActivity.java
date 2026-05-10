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

                String title = etTitle.getText().toString().trim();
                String seller = etSeller.getText().toString().trim();
                String copiesStr = etCopies.getText().toString().trim();
                String priceStr = etPrice.getText().toString().trim();
                String banking = etBanking.getText().toString().trim();

                if (title.isEmpty() || seller.isEmpty()
                        || copiesStr.isEmpty()
                        || priceStr.isEmpty()
                        || banking.isEmpty()) {

                    Toast.makeText(this,
                            "Please fill all fields",
                            Toast.LENGTH_SHORT).show();

                    return;
                }

                int copies = Integer.parseInt(copiesStr);
                double price = Double.parseDouble(priceStr);

                Textbook book = new Textbook(
                        title,
                        seller,
                        copies,
                        price,
                        banking
                );

                repository.addBook(book);

                Toast.makeText(this,
                        "Book added successfully!",
                        Toast.LENGTH_SHORT).show();

                etTitle.setText("");
                etSeller.setText("");
                etCopies.setText("");
                etPrice.setText("");
                etBanking.setText("");

            } catch (DuplicateBookException e) {

                Toast.makeText(this,
                        e.getMessage(),
                        Toast.LENGTH_SHORT).show();

            } catch (Exception e) {

                Toast.makeText(this,
                        "Invalid input!",
                        Toast.LENGTH_SHORT).show();
            }
        });    }
}