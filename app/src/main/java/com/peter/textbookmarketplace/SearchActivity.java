package com.peter.textbookmarketplace;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.peter.textbookmarketplace.model.Textbook;
import com.peter.textbookmarketplace.repository.BookRepository;

import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private BookRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        repository = BookRepository.getInstance();

        EditText etSearch = findViewById(R.id.etSearch);
        Button btnSearch = findViewById(R.id.btnDoSearch);
        TextView tvResults = findViewById(R.id.tvResults);

        btnSearch.setOnClickListener(v -> {
            String query = etSearch.getText().toString();

            List<Textbook> results = repository.searchByTitle(query);

            if (results.isEmpty()) {
                tvResults.setText("No results found");
                return;
            }

            StringBuilder builder = new StringBuilder();

            for (Textbook b : results) {
                builder.append(" ").append(b.getTitle()).append("\n");
                builder.append(" ").append(b.getSellerName()).append("\n");
                builder.append("ZAR ").append(b.getPrice()).append("\n\n");
            }

            tvResults.setText(builder.toString());
        });
    }
}