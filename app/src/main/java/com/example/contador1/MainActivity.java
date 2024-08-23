package com.example.contador1;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnStart;
    private TextView tvCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Exercício");
        }

        btnStart = findViewById(R.id.btnStart);
        tvCounter = findViewById(R.id.tvCounter);

        btnStart.setOnClickListener(v -> startCountdown());
    }

    private void startCountdown() {
        btnStart.setEnabled(false);

        CountdownTask countdownTask = new CountdownTask();
        countdownTask.execute(10);
    }

    private class CountdownTask extends AsyncTask<Integer, Integer, Void> {

        @Override
        protected Void doInBackground(Integer... params) {
            int count = params[0];
            while (count >= 0) {
                try {
                    Thread.sleep(1000); // Espera 1 segundo
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(count);
                count--;
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            tvCounter.setText(String.valueOf(values[0]));
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            btnStart.setEnabled(true);
        }
    }
}
