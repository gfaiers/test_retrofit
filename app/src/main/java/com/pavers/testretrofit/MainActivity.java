package com.pavers.testretrofit;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {
    private MainViewModel mainViewModel;
    private TextView tvResponse;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        mainViewModel = new ViewModelProvider(
                this,
                new ViewModelProvider.AndroidViewModelFactory(getApplication())
        ).get(MainViewModel.class);
        Button start = findViewById(R.id.start);
        start.setOnClickListener(v -> mainViewModel.start());
        Button stop = findViewById(R.id.stop);
        stop.setOnClickListener(v -> mainViewModel.stop());
        tvResponse = findViewById(R.id.tvResponse);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainViewModel.getResponse().observe(this, response -> tvResponse.setText(response));
    }
}
