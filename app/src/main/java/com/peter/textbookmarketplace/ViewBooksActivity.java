package com.peter.textbookmarketplace;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.peter.textbookmarketplace.model.Textbook;
import com.peter.textbookmarketplace.repository.BookRepository;

import java.util.List;

public class ViewBooksActivity extends AppCompatActivity {

    private BookRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_books);

        TextView tvBooks = findViewById(R.id.tvBooks);

        repository = new BookRepository();

        List<Textbook> books = repository.getAllBooks();

        if (books == null || books.isEmpty()) {
            tvBooks.setText("No books available");
            return;
        }

        StringBuilder builder = new StringBuilder();

        for (Textbook b : books) {
            builder.append(" Title: ").append(b.getTitle()).append("\n");
            builder.append(" Seller: ").append(b.getSellerName()).append("\n");
            builder.append(" Copies: ").append(b.getCopies()).append("\n");
            builder.append(" Price: ZAR").append(b.getPrice()).append("\n");
            builder.append(" Banking: ").append(b.getBankingInfo()).append("\n");
            builder.append("----------------------\n\n");
        }

        tvBooks.setText(builder.toString());
    }
}